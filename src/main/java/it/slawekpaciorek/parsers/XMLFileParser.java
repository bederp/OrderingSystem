package it.slawekpaciorek.parsers;

import it.slawekpaciorek.model.Product;
import it.slawekpaciorek.model.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLFileParser extends DefaultHandler implements FileParser {

    private Logger logger = LoggerFactory.getLogger(XMLFileParser.class);
    private File file = new File(getClass().getClassLoader().getResource("order_import.xml").getFile());

    private UserOrder userOrder = null;
    private Product product = null;
    private StringBuilder xmlData = null;
    private List<UserOrder> userOrders = null;

    private boolean bClientId = false;
    private boolean bRequestId = false;
    private boolean bName = false;
    private boolean bQuantity = false;
    private boolean bPrice = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("request")){
            userOrder = new UserOrder();
            product = new Product();

            if(userOrders == null)
                userOrders = new ArrayList<>();

        }
        else if(qName.equalsIgnoreCase("clientid")){
            bClientId = true;

        }
        else if(qName.equalsIgnoreCase("requestid")){
            bRequestId = true;

        }
        else if(qName.equalsIgnoreCase("name")){
            bName = true;

        }
        else if(qName.equalsIgnoreCase("quantity")){
            bQuantity = true;

        }
        else if(qName.equalsIgnoreCase("price")){
            bPrice = true;
        }

        xmlData = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (bClientId) {
            userOrder.setUserId(Integer.parseInt(xmlData.toString()));
            bClientId = false;

        } else if (bRequestId) {
            userOrder.setRequestId(Long.parseLong(xmlData.toString()));
            bRequestId = false;

        } else if (bName) {
            product.setName(xmlData.toString());
            bName = false;

        } else if (bQuantity) {
            product.setQuantity(Integer.parseInt(xmlData.toString()));
            bQuantity = false;

        }
        else if (bPrice) {
            product.setPrice(Double.parseDouble(xmlData.toString()));
            bPrice = false;
        }


        if (qName.equalsIgnoreCase("request")) {
            // add Employee object to list
            if(userOrders.contains(userOrder))
                userOrders.stream().filter(x->x.equals(userOrder)).findAny().get().addProductTOList(product);
            else{
                userOrder.addProductTOList(product);
                userOrders.add(userOrder);
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        xmlData.append(new String(ch, start, length));
    }

    @Override
    public List<UserOrder> parsDataFromFile() throws SAXException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        try{
            logger.info("Starting parsing form file : " + file.getName());
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(file, this);

        } catch (ParserConfigurationException | IOException e) {
            logger.error("There were some errors during parsing");
            e.printStackTrace();
        }

        logger.info("End parsing from file");
        return userOrders;
    }

    public static void main(String[] args) throws SAXException {

        List<UserOrder>orders = new XMLFileParser().parsDataFromFile();

        System.out.println(orders);
    }
}

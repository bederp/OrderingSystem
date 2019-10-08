package it.slawekpaciorek.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XMLFileParser extends DefaultHandler implements FileParser {

    private Logger logger = LoggerFactory.getLogger(XMLFileParser.class);
    private File file;
    private StringBuilder xmlData;
    private StringBuilder result;

    private boolean bClientId = false;
    private boolean bRequestId = false;
    private boolean bName = false;
    private boolean bQuantity = false;
    private boolean bPrice = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        xmlData = new StringBuilder();
        if (qName.equalsIgnoreCase("request")) {
            if (result == null) {
                result = new StringBuilder();
            }
        } else if (qName.equalsIgnoreCase("clientid")) {
            bClientId = true;

        } else if (qName.equalsIgnoreCase("requestid")) {
            bRequestId = true;

        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;

        } else if (qName.equalsIgnoreCase("quantity")) {
            bQuantity = true;

        } else if (qName.equalsIgnoreCase("price")) {
            bPrice = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (bClientId) {
            result.append(xmlData.toString()).append(",");
            bClientId = false;

        } else if (bRequestId) {
            result.append(xmlData.toString()).append(",");
            bRequestId = false;

        } else if (bName) {
            result.append(xmlData.toString()).append(",");
            bName = false;

        } else if (bQuantity) {
            result.append(xmlData.toString()).append(",");
            bQuantity = false;
        } else if (bPrice) {
            bPrice = false;
        } else {
            result.append(xmlData.toString().replaceAll("\n", "").trim()).append(",");
            result.append("\n");
        }


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        xmlData.append(new String(ch, start, length));
    }

    @Override
    public String parsDataFromFile() throws SAXException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        try {
            logger.info("Starting parsing form file : " + file.getName());
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(file, this);

        } catch (ParserConfigurationException | IOException e) {
            logger.error("There were some errors during parsing");
            e.printStackTrace();
        }

        logger.info("End parsing from file");

        return this.result.toString();
    }

    @Override
    public void parseToFile(List<UserOrder> orders, String path, String fileName) throws IOException {

        logger.info("Parsing data to file");

        File file = new File(path + fileName + ".xml");

        if (file.exists()) {

            ObjectMapper mapper = new XmlMapper();
            FileWriter writer = new FileWriter(file);
            writer.append("<Requests>");
            try {
                for (UserOrder order : orders) {
                    String xml = mapper.writeValueAsString(order);
                    writer.write(xml);
                }
                System.out.println(file.getAbsolutePath());
            } catch (IOException e) {
                logger.warn("Something went wrong, check Stack Trace");
                e.printStackTrace();
            }

            writer.append("</Requests>");

            writer.flush();
            writer.close();
        }
        logger.info("Finished parsing data to file, check directory for report");
    }

    public void setFile(File file) {
        logger.info("Setting the file for parser");
        this.file = file;
    }
}

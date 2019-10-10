package it.slawekpaciorek.parsers;

import it.slawekpaciorek.model.UserOrder;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

public interface FileParser {

    public String parsDataFromFile() throws SAXException;

    public void parseToFile(List<UserOrder>  orders, String path, String fileName) throws IOException;

}

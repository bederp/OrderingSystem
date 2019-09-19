package it.slawekpaciorek.parsers;

import it.slawekpaciorek.model.UserOrder;
import org.xml.sax.SAXException;

import java.util.List;

public interface FileParser {

    public List<UserOrder> parsDataFromFile() throws SAXException;

}

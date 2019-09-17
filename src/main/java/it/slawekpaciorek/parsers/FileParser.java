package it.slawekpaciorek.parsers;

import it.slawekpaciorek.model.UserOrder;

import java.util.List;

public interface FileParser {

    public List<UserOrder> parsDataFromFile();

}

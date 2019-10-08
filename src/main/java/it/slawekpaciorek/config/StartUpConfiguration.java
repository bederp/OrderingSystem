package it.slawekpaciorek.config;

import it.slawekpaciorek.parsers.CSVFileParser;
import it.slawekpaciorek.parsers.XMLFileParser;
import it.slawekpaciorek.repo.InMemoryDB;
import it.slawekpaciorek.services.InMemoryDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.File;

public class StartUpConfiguration {

    private XMLFileParser xmlParser = new XMLFileParser();
    private CSVFileParser csvParser = new CSVFileParser();
    private Logger logger = LoggerFactory.getLogger("Configuration");
    private InMemoryDBService service = new InMemoryDBService();

    public void configuration(String[] arguments){

        logger.info("Starting the app ....");

        for(String argument : arguments){

            if(argument.contains(".xml")){

                File file = new File(argument);
                logger.info("Importing data from file :" + file.getName());

                xmlParser.setFile(file);

                try {

                    service.getOrdersFromString(xmlParser.parsDataFromFile())
                            .forEach(InMemoryDB::addOrder);

                } catch (SAXException e) {
                    logger.warn("Something goes wrong with parser, pleas contact with service");
                    e.printStackTrace();
                }

                logger.info("Successful importing data");

            }

            else if (argument.contains(".csv")){

                File file = new File(argument);
                logger.info("Importing data from file : " + file.getName());

                csvParser.setFile(file);

                service.getOrdersFromString(csvParser.parsDataFromFile()).forEach(InMemoryDB::addOrder);
                logger.info("Successful importing data");
            }

            else
                logger.warn("You didn't pass any data to import or your file has incorrect extension, pleas import data manually");

        }

    }

}

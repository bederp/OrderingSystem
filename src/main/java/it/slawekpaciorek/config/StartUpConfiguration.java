package it.slawekpaciorek.config;

import it.slawekpaciorek.parsers.CSVFileParser;
import it.slawekpaciorek.parsers.XMLFileParser;
import it.slawekpaciorek.repo.InMemoryDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.File;

public class StartUpConfiguration {

    private XMLFileParser xmlParser = new XMLFileParser();
    private CSVFileParser csvParser = new CSVFileParser();
    private Logger logger = LoggerFactory.getLogger("Configuration");

    public void configuration(String[] arguments){

        logger.info("Startowanie aplikacji......");

        for(String argument : arguments){

            if(argument.contains(".xml")){

                File file = new File(argument);
                logger.info("Importowanie danych z pliku" + file.getName());

                xmlParser.setFile(file);

                try {

                    xmlParser.parsDataFromFile().forEach(InMemoryDB::addOrder);

                } catch (SAXException e) {
                    logger.warn("Coś poszło nie tak, przerwano operację");
                    e.printStackTrace();
                }

                logger.info("Zakończono import, sukces");

            }

            else if (argument.contains(".csv")){

                File file = new File(argument);
                logger.info("Import danych z pliku " + file.getName());

                csvParser.setFile(file);

                csvParser.parsDataFromFile().forEach(InMemoryDB::addOrder);
                logger.info("Importowanie zakończono skucesem");
            }

            else
                logger.warn("Nie przekazałeś żadnych danych do zimportowania, program się nie uruchomi");

        }

    }

}

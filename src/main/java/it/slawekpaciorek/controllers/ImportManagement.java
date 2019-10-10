package it.slawekpaciorek.controllers;

import it.slawekpaciorek.config.ConsoleView;
import it.slawekpaciorek.parsers.CSVFileParser;
import it.slawekpaciorek.parsers.XMLFileParser;
import it.slawekpaciorek.repo.InMemoryDB;
import it.slawekpaciorek.services.InMemoryDBService;
import org.xml.sax.SAXException;

import java.io.File;
import java.util.Scanner;

public class ImportManagement implements AppManager{
    @Override
    public void executeCommand() {

        ConsoleView.printImportInfo();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj pełną ścieżkę dostępu wraz z nazwą pliku : ");
        String consoleInput = scanner.nextLine();

        File file = new File(consoleInput);

        if(file.exists()){
            InMemoryDBService service = new InMemoryDBService();

            if(consoleInput.contains(".xml")){
                XMLFileParser parser = new XMLFileParser();
                parser.setFile(file);
                try {
                    service.getOrdersFromString(parser.parsDataFromFile())
                            .forEach(InMemoryDB::addOrder);
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }else if(consoleInput.contains(".csv")){
                CSVFileParser parser = new CSVFileParser();
                parser.setFile(file);
                service.getOrdersFromString(parser.parsDataFromFile())
                        .forEach(InMemoryDB::addOrder);
            }
            else{
                System.out.println("Podałeś błędny plik");
            }

        }

    }
}

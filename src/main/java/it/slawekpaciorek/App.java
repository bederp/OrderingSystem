package it.slawekpaciorek;

import it.slawekpaciorek.controllers.CommandSettings;
import it.slawekpaciorek.repo.InMemoryDB;
import it.slawekpaciorek.config.ConsoleView;
import it.slawekpaciorek.config.StartUpConfiguration;

import java.util.Scanner;


public class App {

    public static void main( String[] args ) {

        new StartUpConfiguration().configuration(args);

        if(InMemoryDB.getOrders().size() > 0){

            CommandSettings commander = new CommandSettings();
            commander.settingsInit();

            boolean stop = false;
            Scanner commands = new Scanner(System.in);

            while (!stop){

                commander.runWithParameter("menu");

                ConsoleView.printQueryForQuestion();
                String consoleInput = commands.next();

                stop = consoleInput.equals("exit");

                commander.runWithParameter(consoleInput);


            }

            commands.close();

        }

    }
}

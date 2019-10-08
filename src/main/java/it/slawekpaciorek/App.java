package it.slawekpaciorek;

import it.slawekpaciorek.config.ConsoleView;
import it.slawekpaciorek.config.InvalidComandExcpetion;
import it.slawekpaciorek.config.StartUpConfiguration;
import it.slawekpaciorek.config.CommandSettings;

import java.util.Scanner;


public class App {

    public static void main(String[] args) {

        new StartUpConfiguration().configuration(args);

        CommandSettings commander = new CommandSettings();
        commander.settingsInit();

        boolean stop = false;
        Scanner commands = new Scanner(System.in);

        while (!stop) {


            commander.runWithParameter("menu");
            ConsoleView.printQueryForQuestion();
            String consoleInput = commands.next();

            stop = consoleInput.equals("exit");
            commander.runWithParameter(consoleInput);

        }

        commands.close();

    }

}

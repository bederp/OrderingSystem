package it.slawekpaciorek;

import it.slawekpaciorek.controllers.CommandSettings;
import it.slawekpaciorek.views.ConsoleView;

import java.util.Scanner;


public class App {


    public static void main( String[] args )
    {

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

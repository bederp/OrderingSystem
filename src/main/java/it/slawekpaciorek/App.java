package it.slawekpaciorek;

import it.slawekpaciorek.controllers.AppManagement;

import java.util.Scanner;


public class App {



    public static void main( String[] args )
    {

        AppManagement manager = new AppManagement();
        manager.managementInit();

        boolean stop = false;
        Scanner commands = new Scanner(System.in);

        while (!stop){

            manager.runWithParameter("menu");

            System.out.print("Wpisz polecenie : ");
            String consoleInput = commands.next();

            stop = consoleInput.equals("exit");

            manager.runWithParameter(consoleInput);


        }

        commands.close();

    }
}

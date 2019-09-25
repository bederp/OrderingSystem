package it.slawekpaciorek.views;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class ConsoleView {

    private static boolean initialized = false;


    public void printMenu(){

        System.out.println("Order managment application" +
                "\n------------------------------------------------" +
                "\n\n\tMENU :" +
                "\n\t1. Sprawdź zamówienia" +
                "\n\t2. Przejdz do statystyk" +
                "\n\t3. Stwórz nowe zamówienie" +
                "\n\n------------------------------------------------");


    }

    public void printStatsModule(){
        System.out.println("Stats module" +
                "\n------------------------------------------------" +
                "\n\n\tMENU :" +
                "\n\t1. Liczba wszystkich zamówień : " +
                "\n\t2. Liczba zamówień dla wskazanego użytkownika : " +
                "\n\t3. Wartość wszystkich zamówień : " +
                "\n\t4. Wartość zamówień dla wskazanego użytkownika ; " +
                "\n\t5. Średnia wszystkich zamówień" +
                "\n\t6. Średnia zamówień dla wskazanego użytkownika" +
                "\n\n------------------------------------------------");
    }

    public void printOrdersModule(){
        System.out.println("Order module" +
                "\n------------------------------------------------" +
                "\n\n\tMENU :" +
                "\n\t1. Wyświetl wszystkie zamówienia : " +
                "\n\t2. Wyświetl zamówienia dla wskazanego użytkownia :  " +
                "\n\n------------------------------------------------");
    }


    public ConsoleView(){

        if(!initialized)
            printMenu();

        initialized = true;
    }

}

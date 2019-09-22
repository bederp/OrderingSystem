package it.slawekpaciorek.views;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class ConsoleView {

    public void printMenu(){

        System.out.println("Order managment application" +
                "\n------------------------------------------------" +
                "\n\n\tMENU :" +
                "\n\t1. Sprawdź zamówienia" +
                "\n\t2. Przejdz do statystyk" +
                "\n\t3. Stwórz nowe zamówienie" +
                "\n\n------------------------------------------------");


    }

    public ConsoleView(){
        printMenu();
    }

}

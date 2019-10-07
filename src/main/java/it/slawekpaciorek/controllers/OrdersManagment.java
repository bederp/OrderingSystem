package it.slawekpaciorek.controllers;

import it.slawekpaciorek.config.ConsoleView;
import it.slawekpaciorek.model.UserOrder;
import it.slawekpaciorek.parsers.CSVFileParser;
import it.slawekpaciorek.services.InMemoryDBService;

import java.util.List;
import java.util.Scanner;

public class OrdersManagment implements AppManager {

    private InMemoryDBService service = new InMemoryDBService();
    private Scanner scanner = new Scanner(System.in);
    private CSVFileParser fileParser = new CSVFileParser();

    @Override
    public void executeCommand() {


        ConsoleView.printOrdersModule();
        ConsoleView.printQueryForQuestion();

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("menu") && !input.equalsIgnoreCase("back")) {

            String validator = input.replaceAll("\\s", "").toLowerCase();

            if (validator.equals("seeall")) {
                List<UserOrder> orders = service.findAllOrders();
                service.displayOrders(orders);
                queryForRaports(orders);
            } else if (validator.contains("seefor")) {

                long idNumber = Long.parseLong(validator.substring(6, validator.length()));
                List<UserOrder> orders = service.findOrderForUser(idNumber);
                service.displayOrders(orders);
                queryForRaports(orders);

            } else {
                ConsoleView.printErrorInfo();
            }


            ConsoleView.printOrdersModule();
            ConsoleView.printBackInfo();
            ConsoleView.printQueryForQuestion();
            input = scanner.nextLine();
        }
    }

    private void queryForRaports(List<UserOrder> orders) {

        System.out.println("Czy chcesz wyeksportować raport do pliku CSV ? (yes/no)");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("yes")) {

            System.out.println("Type in file name : ");
            String fileName = scanner.nextLine();

            System.out.println("Type in file path : ");
            String filePath = scanner.nextLine();

            fileParser.parseToFile(orders, filePath, fileName);

        } else if (input.equalsIgnoreCase("no")) {
            System.out.println("Przejdz do następnego okna");
        } else
            System.out.println("Wpisałeś błędną komendę");

    }
}

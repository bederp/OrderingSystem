package it.slawekpaciorek.controllers;

import it.slawekpaciorek.parsers.CSVFileParser;
import it.slawekpaciorek.services.InMemoryDBService;
import it.slawekpaciorek.config.ConsoleView;


import java.util.Arrays;
import java.util.Scanner;

public class StatsManagment implements AppManager{

    private InMemoryDBService service = new InMemoryDBService();
    private Scanner scanner = new Scanner(System.in);
    private CSVFileParser csvFileParser = new CSVFileParser();
    
    @Override
    public void executeCommand() {

        ConsoleView.printStatsModule();
        ConsoleView.printQueryForQuestion();

        String input = scanner.nextLine();
        while(!input.equalsIgnoreCase("menu") && !input.equalsIgnoreCase("back")){

            String validator = input.replaceAll("\\s", "").toLowerCase();
            String report = null;
            String type = null;

            if(validator.equals("countall")){
                report = service.printAmountOfAllOrders();
                type = "Count all orders";
            }
            else if(validator.equals("avgall")){
                report = service.getAverageValueForAllOrders();
                type = "Avg for all orders";
            }
            else if(validator.equals("sumall")){
                report = service.valueOfAllOrders();
                type = "Sum of all orders";
            }
            else if(validator.contains("countfor")){
                report = service.printAmountOFOrdersForSpecificUser(getLongFromString(validator));
                type = "Count orders for user";
            }
            else if(validator.contains("avgfor")){
                report = service.getAverageValueForOrdersForSpecificUser(getLongFromString(validator));
                type = "Avg value for orders for user";
            }
            else if(validator.contains("sumfor")){
                report = service.valueOfAllOrdersForSpecificuser(getLongFromString(validator));
                type = "Sum orders for user";
            }
            else{
                ConsoleView.printErrorInfo();
            }

            if(report != null){

                ConsoleView.printReport(report);
                queryForExportToCSVFile(report, type);

            }

            ConsoleView.printStatsModule();
            ConsoleView.printBackInfo();
            ConsoleView.printQueryForQuestion();

            input = scanner.next();

        }

    }

    private long getLongFromString(String string){

        int idOfFirstNumber = 0;
        try {
            idOfFirstNumber = string.indexOf(
                    Arrays.stream(string.split("")).filter(this::checkIfLong).findFirst().orElseThrow(Exception::new));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Brak liczby w poleceniu");
        }
        try {
            return Long.parseLong(
                    string.substring(idOfFirstNumber, string.length()));
        }catch (NumberFormatException exception){
            exception.printStackTrace();
            return -1L;
        }

    }

    private boolean checkIfLong(String x) {

        try {
            Long.parseLong(x);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    private void queryForExportToCSVFile(String message, String reportType){

        System.out.println("Czy chcesz wyeksportować raport do pliku CSV ? (yes/no)");
        String input = scanner.next();

        if(input.equalsIgnoreCase("yes")){
            
            System.out.println("Type in file name : ");
            String fileName = scanner.next();

            System.out.println("Type in file path : ");
            String filePath = scanner.next();
            
            csvFileParser.parseToFile(message, fileName, filePath, reportType);
            
        }
        else if(input.equalsIgnoreCase("no")){
            System.out.println("Przejdz do następnego okna");
        }
        else
            System.out.println("Wpisałeś błędną komendę");

    }

}

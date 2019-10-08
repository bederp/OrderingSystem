package it.slawekpaciorek.config;

public class ConsoleView {

    public static void printMenu() {

        System.out.println("\nOrder managment application" +
                "\n------------------------------------------------" +
                "\n\n\tMENU :" +
                "\n\t1. Sprawdź zamówienia - ORDERS" +
                "\n\t2. Przejdz do statystyk - STATS" +
                "\n\t3. Import zamówienie - IMPORT" +
                "\n\n\t\tWPISZ 'EXIT' ABY ZAMKNĄĆ APLIKACJĘ" +
                "\n\n------------------------------------------------");


    }

    public static void printStatsModule() {
        System.out.println("\n *****Stats module*****" +
                "\n------------------------------------------------" +
                "\n\n\tMENU :" +
                "\n\t1. Liczba wszystkich zamówień - COUNT ALL" +
                "\n\t2. Liczba zamówień dla wskazanego użytkownika - COUNT FOR [id__number] " +
                "\n\t3. Wartość wszystkich zamówień - SUM ALL" +
                "\n\t4. Wartość zamówień dla wskazanego użytkownika  - SUM FOR [id_number] " +
                "\n\t5. Średnia wszystkich zamówień - AVG ALL" +
                "\n\t6. Średnia zamówień dla wskazanego użytkownika - AVG FOR [id_number]" +
                "\n\n------------------------------------------------");
    }

    public static void printOrdersModule() {
        System.out.println("\n" + "*****Order module*****" +
                "\n------------------------------------------------" +
                "\n\n\tMENU :" +
                "\n\t1. Wyświetl wszystkie zamówienia - SEE ALL " +
                "\n\t2. Wyświetl zamówienia dla wskazanego użytkownia - SEE FOR [id_number]  " +
                "\n\n------------------------------------------------");
    }

    public static void exitView() {
        System.out.println("\n" + "Zamykanie aplikacji ......");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Żegnaj !");
    }

    public static void printQueryForQuestion() {
        System.out.print("Wpisz polecenie : ");
    }

    public static void printErrorInfo() {
        System.out.println("\n" + "Wpisłeś błędną instrukcję, spróbuj jeszcze raz lub wyjdz z programu");
    }

    public static void printBackInfo() {
        System.out.println("\nTYPE IN 'MENU' OR 'BACK' TO MOVE BACK TO MAIN MENU");
    }

    public static void printReport(String report) {
        System.out.println("\n" + report);
    }

    public static void noOrdersInfo() {
        System.out.println("Nie ma żadnych zamówień, zaimportuj dane.");
    }

    public static void printImportInfo() {
        System.out.println("\n" + "*****Importing module*****" +
                "\n------------------------------------------------" +
                "\n\tPrzeszedłeś do modułu importu zamówień z zewnętrznego pliku , dozwolone rozszerzenia : 'xml' i 'csv'" +
                "\n\n------------------------------------------------");
    }
}

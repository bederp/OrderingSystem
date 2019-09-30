package it.slawekpaciorek.views;

public class ConsoleView {

    public static void printMenu(){

        System.out.println("\nOrder managment application" +
                "\n------------------------------------------------" +
                "\n\n\tMENU :" +
                "\n\t1. Sprawdź zamówienia - STATS" +
                "\n\t2. Przejdz do statystyk - ORDER" +
                "\n\t3. Stwórz nowe zamówienie - NEW ORDER" +
                "\n\n------------------------------------------------");


    }

    public static void printStatsModule(){
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

    public static void printOrdersModule(){
        System.out.println("\n" + "*****Order module*****" +
                "\n------------------------------------------------" +
                "\n\n\tMENU :" +
                "\n\t1. Wyświetl wszystkie zamówienia - SEE ALL " +
                "\n\t2. Wyświetl zamówienia dla wskazanego użytkownia - SEE FOR [id_number]  " +
                "\n\n------------------------------------------------");
    }

    public static void exitView() {
        System.out.println("\n" + "Exiting App ......");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Goog By!");
    }

    public static void printQueryForQuestion(){
        System.out.print("Wpisz polecenie : ");
    }

    public static void printErrorInfo() {
        System.out.println("\n" + "Wpisłeś błędną instrukcję, spróbuj jeszcze raz lub wyjdz z programu");
    }

    public static void printBackInfo() {
        System.out.println("\nTYPE IN 'MENU' OR 'BACK' TO MOVE BACK TO MAIN MENU");
    }

    public static void printReport(String report){
        System.out.println("\n" + report);
    }
}

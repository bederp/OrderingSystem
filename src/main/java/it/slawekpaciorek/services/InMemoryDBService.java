package it.slawekpaciorek.services;

import it.slawekpaciorek.model.Product;
import it.slawekpaciorek.model.UserOrder;
import it.slawekpaciorek.repo.InMemoryDB;

import java.util.List;
import java.util.stream.Collectors;

public class InMemoryDBService {

    public List<UserOrder> findOrderForUser(long userId){
        return InMemoryDB.getOrders().stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
    }

    public List<UserOrder> findAllOrders(){
        return InMemoryDB.getOrders();
    }

    public int getAmountOfOrders(List<UserOrder> orders){
        return orders.size();
    }

    public double getValueForOrders(List<UserOrder> orders){
        return orders.stream()
                .mapToDouble(x -> x.getProducts()
                        .stream()
                        .mapToDouble(i -> i.getPrice()*i.getQuantity())
                        .sum())
                .sum();
    }

    public double getAverageForOrders(List<UserOrder> orders){
        if(getAmountOfOrders(orders) < 1){
            return 0;
        }
        return getValueForOrders(orders) / getAmountOfOrders(orders);
    }

    public boolean checkForUser(long id){
        return InMemoryDB.getUsers().stream().noneMatch(x -> x.getIdNumber() == id);
    }

    public void displayOrders(List<UserOrder> orders){
        System.out.println("Printing orders : ");
        for(UserOrder order : orders){
            System.out.println("\nOrder nr : " + order.getRequestId() + " for User with ID : " + order.getUserId());
            System.out.printf("%18s|", "Request Id");
            System.out.printf("%18s|", "Client ID");
            System.out.printf("%18s|", "Product name");
            System.out.printf("%18s|", "Product quantity");
            System.out.printf("%18s|", "Product price");
            System.out.printf("%18s|", "Product summary");
            System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------");
            for(Product product : order.getProducts()){
                System.out.printf("%18s|", order.getRequestId());
                System.out.printf("%18s|", order.getUserId());
                System.out.printf("%18s|", product.getName());
                System.out.printf("%18s|", product.getQuantity());
                System.out.printf("%18s|", product.getPrice());
                System.out.printf("%18s|", product.getQuantity()*product.getPrice());
                System.out.println();
            }
            System.out.println("\n\tOrder summary : " + order.getProducts().stream().mapToDouble(x-> x.getPrice()*x.getQuantity()).sum());
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public String printAmountOfAllOrders() {
        return ("************************************************************" +
                            "\n\tAmount of orders in all database : " + getAmountOfOrders(findAllOrders()) +
                            "\n************************************************************"
        );
    }

    public String printAmountOFOrdersForSpecificUser(long id_Number){

        if (checkForUser(id_Number))
            return("There is no user with ID numbaer = " + id_Number);

        return ("************************************************************" +
                            "\n\tAmount of all orders for User with id number = " + id_Number + " : " + getAmountOfOrders(findOrderForUser(id_Number)) +
                            "\n************************************************************");
    }

    public String valueOfAllOrders(){
        return ("************************************************************"+
                            "\n\tTotal value for all orders : " + getValueForOrders(findAllOrders()) +
                            "\n************************************************************");
    }

    public String valueOfAllOrdersForSpecificuser(long idNumber){

        if (checkForUser(idNumber))
            return("There is no user with ID numbaer = " + idNumber);

        return("************************************************************" +
                "\n\tTotal value for all orders for USER with id = " + idNumber + " : " + getValueForOrders(findOrderForUser(idNumber)) +
                "\n************************************************************");
    }

    public String getAverageValueForAllOrders(){
        return  ("************************************************************" +
                "\n\tAverage value for all orders : " + getAverageForOrders(findAllOrders()) +
                "\n************************************************************");
    }

    public String getAverageValueForOrdersForSpecificUser(long idNumber){

        if (checkForUser(idNumber))
            return ("There is no user with ID numbaer = " + idNumber);

        return ("************************************************************" +
                "\n\tAverage value for all orders for USER with id=" + idNumber + " : " + getAverageForOrders(findOrderForUser(idNumber))+
                "\n************************************************************");
    }

}

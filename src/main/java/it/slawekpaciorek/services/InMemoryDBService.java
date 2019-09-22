package it.slawekpaciorek.services;

import it.slawekpaciorek.model.Product;
import it.slawekpaciorek.model.UserOrder;
import it.slawekpaciorek.repo.InMemoryDB;

import java.util.List;
import java.util.stream.Collectors;

public class InMemoryDBService {

    private List<UserOrder> findOrderForUser(long userId){
        return InMemoryDB.getOrders().stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
    }

    private List<UserOrder> findAllOrders(){
        return InMemoryDB.getOrders();
    }

    private int getAmountOfOrders(List<UserOrder> orders){
        return orders.size();
    }

    private double getValueForOrders(List<UserOrder> orders){
        return orders.stream()
                .mapToDouble(x -> x.getProductList()
                        .stream()
                        .mapToDouble(i -> i.getPrice()*i.getQuantity())
                        .sum())
                .sum();
    }

    private double getAverageForOrders(List<UserOrder> orders){
        if(getAmountOfOrders(orders) < 1){
            return 0;
        }
        return getValueForOrders(orders) / getAmountOfOrders(orders);
    }

    private boolean checkForUser(long id){
        return InMemoryDB.getUsers().stream().anyMatch(x->x.getIdNumber() == id);
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
            for(Product product : order.getProductList()){
                System.out.printf("%18s|", order.getRequestId());
                System.out.printf("%18s|", order.getUserId());
                System.out.printf("%18s|", product.getName());
                System.out.printf("%18s|", product.getQuantity());
                System.out.printf("%18s|", product.getPrice());
                System.out.printf("%18s|", product.getQuantity()*product.getPrice());
                System.out.println();
            }
            System.out.println("\n\tOrder summary : " + order.getProductList().stream().mapToDouble(x-> x.getPrice()*x.getQuantity()).sum());
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void printAmountOfAllOrders() {
        System.out.println("Amount of orders in all database : " + getAmountOfOrders(findAllOrders()));
    }

    public void printAmountOFOrdersForSpecificUser(long id_Number){

        if (!checkForUser(id_Number))
            System.out.println("There is no user with ID numbaer = " + id_Number);

        System.out.println("Amount of all orders for User with id number = " + id_Number + " : " + getAmountOfOrders(findOrderForUser(id_Number)) );
    }

    public void valueOfAllOrders(){
        System.out.println("Total value for all orders : " + getValueForOrders(findAllOrders()));
    }

    public void valueOfAllOrdersForSpecificuser(long idNumber){

        if (!checkForUser(idNumber))
            System.out.println("There is no user with ID numbaer = " + idNumber);

        System.out.println("Total value for all orders for USER with id = " + idNumber + " : " + getValueForOrders(findOrderForUser(idNumber)));
    }

    public void getAverageValueForAllOrders(){
        System.out.println("Average value for all orders : " + getAverageForOrders(findAllOrders()));
    }

    public void getAverageValueForOrdersForSpecificUser(long idNumber){

        if (!checkForUser(idNumber))
            System.out.println("There is no user with ID numbaer = " + idNumber);

        System.out.println("Average value for all orders for USER with id=" + idNumber + " : " + getAverageForOrders(findOrderForUser(idNumber)));
    }




}

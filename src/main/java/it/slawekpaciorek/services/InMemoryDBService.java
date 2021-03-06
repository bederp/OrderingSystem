package it.slawekpaciorek.services;

import it.slawekpaciorek.model.Product;
import it.slawekpaciorek.model.UserOrder;
import it.slawekpaciorek.repo.InMemoryDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryDBService {

    private Logger logger = LoggerFactory.getLogger("InMemoryDBService");

    public List<UserOrder> findOrderForUser(long userId){
        logger.debug("Checking database for orders for USER with ID = " + userId + ".");
        return InMemoryDB.getOrders().stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
    }

    public List<UserOrder> findAllOrders(){
        logger.debug("Checking database for orders.");
        return InMemoryDB.getOrders();
    }

    public int getAmountOfOrders(List<UserOrder> orders){
        logger.debug("Checking database for amount of orders.");
        return orders.size();
    }

    public double getValueForOrders(List<UserOrder> orders){
        logger.debug("Calculating sum of orders.");
        return orders.stream()
                .mapToDouble(x -> x.getProducts()
                        .stream()
                        .mapToDouble(i -> i.getPrice()*i.getQuantity())
                        .sum())
                .sum();
    }

    public double getAverageForOrders(List<UserOrder> orders){
        logger.debug("Calculating avg value for orders");
        if(getAmountOfOrders(orders) < 1){
            return 0;
        }
        return getValueForOrders(orders) / getAmountOfOrders(orders);
    }

    public boolean checkForUser(long id){
        logger.debug("Checking database for user with ID = " + id + ".");
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

    public List<UserOrder> getOrdersFromString(String data) {

        logger.info("Importing data from parser");

        List<UserOrder> orders = new ArrayList<>();

        String[] dataFromFile = data.split("\n");

        for (String element : dataFromFile) {

            if(element.matches("\\d+,\\d+,[A-Za-złćążęśźó]+,\\d+,\\d+.?\\d+,?")) {
                UserOrder order = new UserOrder();
                Product product = new Product();
                List<Product> products = order.getProducts();

                String[] array = element.split(",");
                order.setRequestId(Long.parseLong(array[1]));
                order.setUserId(Integer.parseInt(array[0]));
                product.setName(array[2]);
                product.setQuantity(Integer.parseInt(array[3]));
                product.setPrice(Double.valueOf(array[4]));
                order.addProductTOList(product);

                if (orders.contains(order)) {
                    orders.stream().filter(x -> x.equals(order)).findAny().get().addProductTOList(product);
                } else {
                    orders.add(order);
                }
            }
            else {
                logger.warn("Invalid data, line skiped");
            }

        }

        logger.info("Finished importing to database");
        return orders;
    }


}

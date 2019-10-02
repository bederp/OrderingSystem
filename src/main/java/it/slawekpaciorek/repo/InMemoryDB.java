package it.slawekpaciorek.repo;

import it.slawekpaciorek.model.User;
import it.slawekpaciorek.model.UserOrder;
import it.slawekpaciorek.parsers.CSVFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMemoryDB {

    protected static Logger logger = LoggerFactory.getLogger(InMemoryDB.class);

    private static Set<User> users;
    private static List<UserOrder> orders;

    public static Set<User> getUsers() {
        if(users == null) {
            logger.warn("Users list is not initialized, initializing list");
            users = new HashSet<User>();
        }
        logger.debug("Accessing to users list");
        return users;
    }

    public static void addUser(User user){
        if(users == null) {
            logger.warn("Users list is not initialized, initializing list");
            users = new HashSet<User>();
        }
        logger.debug("Adding user : " + user.toString() + " to database.");
        users.add(user);
    }

    public static List<UserOrder> getOrders() {
        if(orders == null) {
            logger.warn("Orders list is not initialized, initializing list");
            orders = new ArrayList<>();
        }

        logger.debug("Accessing to orders list");
        return orders;
    }

    public static void addOrder(UserOrder order){
        if(orders == null) {
            logger.warn("Orders list is not initialized, initializing list");
            orders = new ArrayList<UserOrder>();
        }

        logger.debug("Adding order : " + order.toString() + " to database.");
        orders.add(order);
        addUser(new User(order.getUserId()));
    }



}

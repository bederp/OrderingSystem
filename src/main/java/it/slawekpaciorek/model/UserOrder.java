package it.slawekpaciorek.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserOrder {

    Logger logger = LoggerFactory.getLogger(UserOrder.class);

    int userId;
    long requestId;
    String name;
    List<Product> productList;

    public UserOrder(){
        logger.info("Creating USER ORDER by user with id number : " + userId);
    }

    public UserOrder(int userId, String name, List<Product> productList) {
        this.userId = userId;
        this.name = name;
        this.productList = productList;

        logger.info("Creating USER ORDER by user with id number : " + userId);
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

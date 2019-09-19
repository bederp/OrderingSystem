package it.slawekpaciorek.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserOrder {

    private Logger logger = LoggerFactory.getLogger(UserOrder.class);

    int userId;

    long requestId;

    List<Product> productList;

    public UserOrder(){
        logger.info("Creating empty USER ORDER");
    }

    public UserOrder(int userId, String name, List<Product> productList) {
        this.userId = userId;
        this.productList = productList;

        logger.info("Creating USER ORDER by user with id number : " + userId);
    }

    @Override
    public String toString() {
        return "\nUserOrder{" +
                "userId=" + userId +
                ", requestId=" + requestId +
                ", productList=" + productList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOrder)) return false;
        UserOrder userOrder = (UserOrder) o;
        return userId == userOrder.userId &&
                requestId == userOrder.requestId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, requestId);
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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProductTOList(Product product){
        if(productList == null)
            setProductList(new ArrayList<>());
        logger.info("Adding product : " + product.toString() + "to list");
        productList.add(product);
    }
}

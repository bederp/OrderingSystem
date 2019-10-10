package it.slawekpaciorek.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JacksonXmlRootElement(localName = "request")
public class UserOrder {

    @JsonIgnore
    private Logger logger = LoggerFactory.getLogger(UserOrder.class);

    @JacksonXmlProperty(localName = "user_id")
    int userId;

    @JacksonXmlProperty(localName = "request_id")
    long requestId;

    @JacksonXmlElementWrapper(localName = "products")
    @JacksonXmlProperty(localName = "product")
    List<Product> products;

    public UserOrder(){
        logger.debug("Creating empty USER ORDER");
    }

    public UserOrder(int userId, List<Product> productList) {
        this.userId = userId;
        this.products = productList;

        logger.debug("Creating USER ORDER by user with id number : " + userId);
    }

    public UserOrder(int userId,long requestId, List<Product> productList) {
        this.requestId = requestId;
        this.userId = userId;
        this.products = productList;

        logger.debug("Creating USER ORDER by user with id number : " + userId);
    }

    @Override
    public String toString() {
        return "\nUserOrder{" +
                "userId=" + userId +
                ", requestId=" + requestId +
                ", productList=" + products +
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProductTOList(Product product){
        if(products == null)
            setProducts(new ArrayList<>());
        logger.debug("Adding product : " + product.toString() + "to list");
        products.add(product);
    }
}

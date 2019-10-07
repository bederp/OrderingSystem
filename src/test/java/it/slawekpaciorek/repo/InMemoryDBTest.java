package it.slawekpaciorek.repo;

import it.slawekpaciorek.model.Product;
import it.slawekpaciorek.model.UserOrder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InMemoryDBTest {

    @Test
    public void shouldCheckIfDBWorks(){
//        given
        List<UserOrder> expected = new ArrayList<>();
//        when
        List<UserOrder> result = InMemoryDB.getOrders();
//        then
        assertEquals(expected, result);
    }

    @Test
    public void shouldAddOrderToDB(){
//        given
        UserOrder expected = new UserOrder(1,1,new ArrayList<Product>());
//        when
        InMemoryDB.addOrder(expected);
        UserOrder result = InMemoryDB.getOrders().get(0);
//        then
        assertEquals(expected, result);

    }

    @Test
    public void shouldResetDataBase() {
//        given
        for (int i = 0; i < 10; i++) {
            UserOrder temp = new UserOrder();
            InMemoryDB.addOrder(temp);
        }
//        when
        InMemoryDB.cleanUp();
//        then
        assertEquals(InMemoryDB.getOrders(), Collections.emptyList());
    }

}
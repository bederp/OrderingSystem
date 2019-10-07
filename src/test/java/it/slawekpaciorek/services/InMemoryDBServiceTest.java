package it.slawekpaciorek.services;

import it.slawekpaciorek.model.Product;
import it.slawekpaciorek.model.UserOrder;
import it.slawekpaciorek.repo.InMemoryDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class InMemoryDBServiceTest {

    InMemoryDBService SUT = new InMemoryDBService();
    List<UserOrder> orders;

    @Before
    public void setOrders(){
        orders = new ArrayList<>();
        Product product1 = new Product("Bułka", 1, 10.0);
        Product product2 = new Product("Chleb", 2, 30.0);
        Product product3 = new Product("Cola", 3, 34.60);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        UserOrder order1 = new UserOrder(1,1, products);
        UserOrder order2 = new UserOrder(2,1, products);
        UserOrder order3 = new UserOrder(3,1, products);
        UserOrder order4 = new UserOrder(1,2, products);
        UserOrder order5 = new UserOrder(2,2, products);
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
    }

    @After
    public void cleanDB(){
        InMemoryDB.cleanUp();
    }

    @Test
    public void shouldCheckForOrders(){

//        Given
        List<UserOrder> expected = new ArrayList<>();
//        When
        List<UserOrder> result = SUT.findAllOrders();
//        Then
        assertEquals(expected, result);

    }

    @Test
    public void shoudCheckOrdersForUser(){
//        given
        long id = 1;
        List<UserOrder> expected = orders.stream().filter(x->x.getUserId()==id).collect(Collectors.toList());
//        when
        orders.forEach(InMemoryDB::addOrder);
        List<UserOrder> result = SUT.findOrderForUser(id);
//        then
        assertEquals(expected, result);
    }

    @Test
    public void shouldCheckOrdersForClientWithIdEquals1(){

//        given
        orders.forEach(InMemoryDB::addOrder);
        List<UserOrder> ordersForId1 = orders.stream().filter(x->x.getUserId() == 1).collect(Collectors.toList());
//        when
        List result = SUT.findOrderForUser(1);
//        then
        assertEquals(ordersForId1, result);

    }

    @Test
    public void shouldCheckSumOfAllOrdersForEmptyList(){

//        given
        double expected = 0.0;

//        when

        double result = SUT.getValueForOrders(InMemoryDB.getOrders());

//        then

        assertEquals(result, expected, 0.0);


    }

    @Test
    public void shouldChecSumOfAllOrdersForDBWithOrders(){
//        given
        double expected = orders.stream().mapToDouble(x-> x.getProducts().stream().mapToDouble(y->y.getPrice() *y.getQuantity()).sum()).sum();
//        when
        orders.forEach(InMemoryDB::addOrder);
        double result = SUT.getValueForOrders(InMemoryDB.getOrders());

//        then

        assertEquals(expected, result, Math.abs(expected-result));

    }

    @Test
    public void shouldCheckSumOfAllOrdersForSpecificUser(){
//        given
        int id = 1;
        double expected = orders.stream()
                .filter( order -> order.getUserId() == id)
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(order -> order.getProducts().stream().mapToDouble(product -> product.getQuantity()*product.getPrice()).sum())
                .sum();
//        when
        double result = SUT.getValueForOrders(SUT.findOrderForUser(id));

//        then
        assertEquals(result, expected, Math.abs(result - expected));
    }

    @Test
    public void shouldCheckAmountOfOrdersForEmptyDB(){
//        given
        int expected = 0;

//        when
        int result = SUT.getAmountOfOrders(InMemoryDB.getOrders());

//        then
        assertEquals(expected, result);

    }

    @Test
    public void shouldCheckAmountOfOrderdsWithNonEmptyDB(){

        //given
        int expected = 5;
//        when
        orders.forEach(InMemoryDB::addOrder);
        int result = SUT.getAmountOfOrders(SUT.findAllOrders());
//        then
        assertEquals(expected, result);

    }

    @Test
    public void shouldCheckAmountOfOrdersForUser(){
//        given
        long id = 1L;
        int expected = 2;
//        when
        orders.forEach(InMemoryDB::addOrder);
        int result = SUT.getAmountOfOrders(SUT.findOrderForUser(id));
//        then
        assertEquals(expected, result);
    }

    @Test
    public void shouldCheckAVGValueForAllOrders(){
//        given
        int amountOfAllOrders = orders.size();
        double sumValueOfAllOrders = orders.stream()
                .mapToDouble(order -> order.getProducts()
                        .stream()
                        .mapToDouble(product -> product.getPrice() * product.getQuantity())
                        .sum())
                .sum();
        double expected = sumValueOfAllOrders / amountOfAllOrders;
//        when
        orders.forEach(InMemoryDB::addOrder);
        double result = SUT.getAverageForOrders(SUT.findAllOrders());
//        then
        assertEquals(expected, result, Math.abs(expected-result));
    }

    @Test
    public void shouldChecAVGValueForOrdersForUser(){
//        given
        long id = 1L;
        double sum = orders.stream()
                .filter(order -> order.getUserId() == id)
                .mapToDouble(
                order->order.getProducts().stream()
                .mapToDouble(product -> product.getQuantity() * product.getPrice()).sum())
                .sum();
        int amount = 2;
        double expected = sum/amount;
//        when
        orders.forEach(InMemoryDB::addOrder);
        double result = SUT.getAverageForOrders(SUT.findOrderForUser(id));
//        then
        assertEquals(result, expected, Math.abs(result - expected));
    }

    @Test
    public void shouldRetriveDataFromString(){
//        given
        String data = "\n1,1,Bułka,1,10\n1,1,Chleb,2,30\n1,1,Cola,3,34.6\n";
        UserOrder expeted  = orders.get(0);
//        when
        UserOrder result = SUT.getOrdersFromString(data)
                .get(0);
//        then
        assertEquals(expeted, result);
    }





}
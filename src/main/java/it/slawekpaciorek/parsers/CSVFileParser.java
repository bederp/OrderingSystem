package it.slawekpaciorek.parsers;

import it.slawekpaciorek.model.Product;
import it.slawekpaciorek.model.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileParser implements FileParser {

    Logger logger = LoggerFactory.getLogger(CSVFileParser.class);

    @Override
    public List<UserOrder> parsDataFromFile() {

        List<UserOrder> orders = new ArrayList<>();
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(getClass().getClassLoader().getResource("order_import.csv").getFile()));
            scanner.next();
        } catch (FileNotFoundException e) {
            logger.error("File not found");
            e.printStackTrace();
            return new ArrayList<>();
        }

        while (scanner.hasNextLine()){

            UserOrder userOrder = new UserOrder();
            Product product = new Product();

            String[] values = scanner.next().split(",");

            product.setName(values[2]);
            product.setQuantity(Integer.parseInt(values[3]));
            product.setPrice(Double.valueOf(values[4]));

            userOrder.setUserId(Integer.parseInt(values[0]));
            userOrder.setRequestId(Long.parseLong(values[1]));

            if(orders.contains(userOrder))
                orders.stream().filter(x->x.equals(userOrder)).findAny().get().addProductTOList(product);
            else {
                userOrder.addProductTOList(product);
                orders.add(userOrder);
            }
        }


        return orders;
    }

    public static void main(String[] args) {

        List orders = new CSVFileParser().parsDataFromFile();

        System.out.println(orders);
    }

}

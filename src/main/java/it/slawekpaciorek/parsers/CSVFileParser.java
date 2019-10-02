package it.slawekpaciorek.parsers;

import it.slawekpaciorek.model.Product;
import it.slawekpaciorek.model.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileParser implements FileParser {

    Logger logger = LoggerFactory.getLogger(CSVFileParser.class);
    private File file;

    @Override
    public List<UserOrder> parsDataFromFile() {

        List<UserOrder> orders = new ArrayList<>();
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
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

    @Override
    public void parseToFile(List<UserOrder> collection,String path, String fileName) {

        String title = "New report : " + LocalDate.now().toString();
        String columns = "Request_id,Client_id,Product_name,Quantity,Price";

        try {

            File file = new File(path + fileName + ".csv");

            if(file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file.getAbsolutePath());

                fileWriter.append(title).append("\n").append(columns).append("\n");

                for (UserOrder element : collection) {
                    for (Product product : element.getProducts()) {
                        String line = element.getRequestId() + "," + element.getUserId() + "," + product.getName() + "," + product.getQuantity() + "," + product.getPrice() + "\n";
                        fileWriter.append(line);
                    }
                }

                fileWriter.flush();
                fileWriter.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void parseToFile(String msg, String fileName, String filePath, String reportType){

        String title = reportType + " : " + LocalDate.now().toString();

        try{
            File file = new File(filePath + fileName + ".csv");

            if(file.createNewFile()){
                FileWriter writer = new FileWriter(file.getAbsolutePath());
                writer.append(title).append("\n").append(msg);

                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFile(File file) {
        this.file = file;
    }
}

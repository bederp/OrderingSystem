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
import java.util.List;
import java.util.Scanner;

public class CSVFileParser implements FileParser {

    Logger logger = LoggerFactory.getLogger(CSVFileParser.class);
    private File file;

    @Override
    public String parsDataFromFile() {

        StringBuilder builder = new StringBuilder();
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
            scanner.next();
        } catch (FileNotFoundException e) {
            logger.error("File not found");
            e.printStackTrace();
            return "";
        }



        while (scanner.hasNextLine()) {
            String row = scanner.next();
            if (row.matches("\\d+,\\d+,[A-Za-złąćĆżŻóęźŹśŚ]*,\\d+,\\d+.?\\d*"))
                builder.append(row).append("\n");
        }

        return builder.toString();

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

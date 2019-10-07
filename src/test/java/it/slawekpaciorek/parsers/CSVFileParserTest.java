package it.slawekpaciorek.parsers;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CSVFileParserTest {

    CSVFileParser parser = new CSVFileParser();
    File file = new File("temp.csv");
    FileWriter writer;

    @Test
    public void shouldCheckParsedData() {

//        given
        String inputData = "Client,Request,Name,Quantity,Price\n1,1,Bułka,3,5";
        String expected = "1,1,Bułka,3,5\n";
        try {
            writer = new FileWriter(file);
            writer.append(inputData);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parser.setFile(file);
//        when
        String result = parser.parsDataFromFile();

//        then
        assertEquals(result, expected);

    }

    @Test
    public void shouldCheckValidRows() throws IOException {
//        given

        String inputData = "Client,Request,Name,Quantity,Price\n1,1,Bułka,3,5\n1,1,Chleb,4,10.0\nColumn1,Column2,Column3,Column4,Column5";
        String expected = "1,1,Bułka,3,5\n1,1,Chleb,4,10.0\n";
//        when

        writer = new FileWriter(file);
        writer.write(inputData);
        writer.flush();
        writer.close();
        parser.setFile(file);

        String result = parser.parsDataFromFile();
//        then

        assertEquals(expected, result);

    }
}
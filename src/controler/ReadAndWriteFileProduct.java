package controler;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFileProduct {
    File file = new File("Data/Product.csv");

    public void writeFile(List<Product> productList) {
        try {
            FileWriter fileWriter = new FileWriter("Data/Product.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line = "";
            for (Product product : productList) {
                line += product.getData() + "\n";
            }
            bufferedWriter.write(line);
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Product> ReaFile() {
        List<Product> productList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Product product = new Product(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), data[4]);
                productList.add(product);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }
}

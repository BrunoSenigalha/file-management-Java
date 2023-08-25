package entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ProductList {

    List<Product> productList;

    public ProductList() {
        this.productList = new ArrayList<>();
    }

    public void readAndCreateFile(String path) {

        String pathStr = path;

        File sourcefile = new File(pathStr);

        try (BufferedReader br = new BufferedReader(new FileReader(pathStr))) {

            String pathReader = br.readLine();
            while (pathReader != null) {

                String[] fields = pathReader.split(",");
                String name = fields[0];
                Double price = Double.parseDouble(fields[1]);
                Integer quantity = Integer.parseInt(fields[2]);

                productList.add(new Product(name, price, quantity));

                pathReader = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        String sourceFolderStr = sourcefile.getParent();
        boolean success = new File(sourceFolderStr + "/out").mkdir();
        System.out.println(success);

        String targetPath = sourceFolderStr + "/out/summary.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath))) {

            for (Product product : productList) {
                bw.write(product.getName() + "," + String.format("%.2f", product.totalPrice()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

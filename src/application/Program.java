package application;

import entities.Product;

import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Product> productList = new ArrayList<>();

        System.out.println("Enter file path: ");
//        String pathStr = sc.nextLine();
        String pathStr = "/home/brunosenigalha/Área de Trabalho/items-archives/items.csv";

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

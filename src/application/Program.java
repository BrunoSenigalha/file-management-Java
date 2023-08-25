package application;

import entities.ProductList;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductList productList = new ProductList();

        System.out.println("Enter file path: ");
        String pathStr = sc.nextLine();

        productList.readAndCreateFile(pathStr);
    }
}

package shop.model.persist;

import shop.exceptions.PersistException;
import shop.model.Client;
import shop.model.Product;
import shop.model.Purchase;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

//Clase donde irán los métodos de gestión de la tienda
public class ShopDAO {
    final String delimiter = ",";

    /**
     * Method that reads the csv file using the FileReader and Buffered Reader and stores the data in an array list
     *
     * @param path filepath with the compras.csv that we send from the main class
     * @return the list of purchases
     */
    public ArrayList<Purchase> readPurchaseCsv(String path) throws PersistException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>(); //ArrayList with the Purchase objects
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) { //We create a Buffered FileReader with the path provided to read the file
            String linePurchase = null; //Skip first line
            int contPurchaseId = 1;
            bf.readLine();

            while ((linePurchase = bf.readLine()) != null) { //Loop that keeps looping until the reader doesn't find more lines.
                String[] parts = linePurchase.split(delimiter); //We split the read line by the provided delimiter

                Client c = new Client(parts[0]);
                Product p = new Product(parts[1]);
                Purchase purchase = new Purchase(contPurchaseId, c, p, Integer.parseInt(parts[2])); //We create a Purchase object with the elements of the read line

                purchases.add(purchase); //add the purchase object to the list
                contPurchaseId++;
            }

        } catch (FileNotFoundException ex) {
            throw new PersistException("File compras.csv not found on " + path);
        } catch (IOException ex) {
            throw new PersistException("Error while trying to read input compras.csv file");
        }
        return purchases;
    }

    /**
     * Method that reads the csv file using the FileReader and Buffered Reader and stores the data in an array list
     *
     * @param path filepath with the productos.csv that we send from the main class
     * @return the list of products
     */
    public ArrayList<Product> readProductCsv(String path) throws PersistException {
        ArrayList<Product> products = new ArrayList<Product>(); //ArrayList with the Product objects
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) { //We create a Buffered FileReader with the path provided to read the file
            String lineProduct = null; //Skip first line
            int contProductId = 1;
            bf.readLine();

            while ((lineProduct = bf.readLine()) != null) { //Loop that keeps looping until the reader doesn't find more lines.
                String[] parts = lineProduct.split(delimiter); //We split the read line by the provided delimiter

                Product product = new Product(contProductId, parts[0], Double.parseDouble(parts[1]));//We create a Product object with the elements of the read line

                products.add(product); //add the product object to the list
                contProductId++;
            }
        } catch (FileNotFoundException ex) {
            throw new PersistException("File productos.csv not found on " + path);
        } catch (IOException ex) {
            throw new PersistException("Error while trying to read input productos.csv file");
        }
        return products;
    }

    /**
     * Method that returns the correct format of the line
     *
     * @param name      name of the product
     * @param price     price of the product
     * @param delimiter delimiter that splits the elements of the line
     * @return
     */
    private static String earningToCsvLine(String name, Double price, String delimiter) {
        return name + delimiter + " " + price;
    }

    /**
     * method that writes the csv file with the output of the earnings for each product and the total
     *
     * @param filename        String with the filepath name
     * @param productEarnings Hashmap with the total product earnings
     * @param total           total sum of the earnings for each product
     */
    public void writeEarningsCsv(String filename, HashMap<String, Double> productEarnings, Double total) throws PersistException {
        try (BufferedWriter bw = new BufferedWriter((new FileWriter((filename))))) {
            bw.write("producto,precio \n");
            for (String i : productEarnings.keySet()) {
                String name = i;
                Double price = productEarnings.get(i);
                bw.write(earningToCsvLine(name, price, delimiter) + "\n");
            }
            bw.write("Total: " + total);
        } catch (IOException ex) {
            throw new PersistException("Error while trying to write output ventas.csv file");
        }
    }

    /**method that writes a new product to the product.csv
     *
     * @param filename
     * @param products
     * @param name
     * @param price
     */
    public void addProduct(String filename, ArrayList<Product> products, String name, Double price) {
        try (BufferedWriter bw = new BufferedWriter((new FileWriter((filename))))) {
            bw.write("producto,precio \n");
            for (Product p : products) {
                bw.write(earningToCsvLine(p.getName(), p.getPrice(), delimiter) + "\n");
            }
            bw.write(name + delimiter + " " + price);

        } catch (IOException ex) {
            System.out.println("Error writing csv");
        }

    }
}
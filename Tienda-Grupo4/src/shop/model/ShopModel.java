package shop.model;

import shop.exceptions.PersistException;
import shop.model.persist.ShopDAO;
import java.util.ArrayList;
import java.util.HashMap;

public class ShopModel {
    private final ShopDAO shopDAO;
    private final String filepathPurchase = "data/compras.csv";
    private final String filePathProduct = "data/productos.csv";
    private final String outPutFile = "data/ventas.csv";

    public ShopModel(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    public ArrayList<Purchase> readPurchaseCsv() throws PersistException {
        ArrayList<Purchase> purchases = shopDAO.readPurchaseCsv(filepathPurchase);
        return purchases;
    }

    public ArrayList<Product> readProductCsv() throws PersistException {
        ArrayList<Product> products = shopDAO.readProductCsv(filePathProduct);
        return products;
    }

    public void writeEarningsCsv(HashMap<String, Double> productEarnings, Double total) throws PersistException {
        shopDAO.writeEarningsCsv(outPutFile, productEarnings, total);
    }

    public void addProduct(ArrayList products, String name, Double price) {
        shopDAO.addProduct(filePathProduct, products, name, price);
    }

    /**
     * Method that returns a hashmap with the name of the products and a double with the total earnings for each.
     * @param products arraylist with the products
     * @param purchases arraylist with the purchases
     * @return productsEarnings hashmap with the name of each product and the total earnings for each
     */
    public HashMap<String, Double> productsEarnings(ArrayList<Product> products, ArrayList<Purchase>purchases) {
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < purchases.size(); j++) {
                if (products.get(i).getName().equals(purchases.get(j).getProduct().getName())) {
                    purchases.get(j).getProduct().setPrice(products.get(i).getPrice());
                    purchases.get(j).getProduct().setIdProduct(products.get(i).getIdProduct());
                }
            }
        }
        HashMap<String, Double> productsEarnings = new HashMap<>();

        for (int i = 0; i < products.size(); i++) {
            double suma = 0;
            String productName = products.get(i).getName();
            for (int j = 0; j < purchases.size(); j++) {
                if (productName.equals(purchases.get(j).getProduct().getName())){
                    suma += purchases.get(j).getProduct().getPrice() * purchases.get(j).getQuantity();
                }
            }
            productsEarnings.put(products.get(i).getName(),suma);
        }
        return productsEarnings;
    }

    /**
     * Sums the total earnings and returns that value
     * @param productsEarnings
     * @return totalEarnings double to return
     */
    public double totalEarnings(HashMap<String, Double> productsEarnings) {
        double totalEarnings = 0;
        for (double value: productsEarnings.values()) {
            totalEarnings += value;
        }
        return totalEarnings;
    }
}

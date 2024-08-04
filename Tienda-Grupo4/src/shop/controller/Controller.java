package shop.controller;

import shop.exceptions.PersistException;
import shop.model.ShopModel;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final ShopModel model;

    public Controller(ShopModel model) {
        this.model = model;
    }

    public void start() {
        HashMap<String, Double> productEarnings = null;
        char opt = 'a';
        Double total = 0.0;
        showMessage("Leyendo ficheros...");
        try{
            productEarnings = model.productsEarnings(model.readProductCsv(), model.readPurchaseCsv());
            showMessage("Ficheros leídos correctamente.");
            
        }catch (PersistException ex) {
            showMessage(ex.toString());
        }
        do {
            showMenu();
            opt = promptString("Escribe una opción: ").charAt(0);

            switch (opt){
                case '1' :
                try{
                    String proname = promptString("Escribe el nombre del producto: ");
                    Double price = promptDouble("Escribe el precio del producto: ");
                    model.addProduct(model.readProductCsv(), proname, price);
                    showMessage("Producto añadido correctamente");  
                }catch (PersistException ex) {
                    showMessage(ex.toString());
                }
                    break;
                case '2' :
                    if (productEarnings != null){
                        showMessage("Mostrando ventas para cada producto: ");
                        showHashMapList(productEarnings);
                        total = model.totalEarnings(productEarnings);
                        showMessage("Ventas totales: ");
                        showNumber(total);
                    }else{
                        showMessage("La lista está vacía.");
                    }
                    break;
                case '3':
                    try {
                        showMessage("Escribiendo el fichero...");
                        model.writeEarningsCsv(productEarnings, total);
                        showMessage("Fichero ventas.csv escrito correctamente en la carpeta data.");
                    } catch (PersistException ex) {
                        showMessage(ex.toString());
                    }
                    break;

                case '0':
                    showMessage("Saliendo de la app...");
                    break;

                default:
                    showMessage("Error: Escribe una opción correcta");
                    break;

            }
        }while (opt!='0');
    }

    /***************** VIEW METHODS **********************/

    /**
     * Displays a message.
     * @param message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prompts a message and reads an input from user
     *
     * @param message the message to prompt
     * @return input from user
     */
    public String promptString(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public Double promptDouble(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        boolean comp = false;
        double number = 0;

        while(!comp){
            try {
                number= sc.nextDouble();
                comp = true;
            }
            catch (InputMismatchException ex) {
                showMessage("Error: Incorrect format of data. Please input a number with a coma:");
                comp = false;
                sc.next();
            }
        }
        return number;
    }

    /**
     * Displays a list of objects.
     * @param <T> object element toshown
     * @param data collection of elements
     */
    private <T> void showList(List<T> data) {
        System.out.println(data);
    }
    /**
     * Displays a hashMap of objects.
     * object element toshown
     * @param data collection of elements
     */
    private void showHashMapList(HashMap<String, Double> data) {
        showMessage("Earnings for each product: ");
        System.out.println(data);
    }

    /**
     * Displays a message.
     * @param number to display
     */
    public void showNumber(Double number) {
        System.out.println(number);
    }

    public void showMenu() {
        System.out.println("Elija una opción : ");
        System.out.println("\t1. Insertar nuevo producto en el fichero productos.csv");
        System.out.println("\t2. Mostrar ventas");
        System.out.println("\t3. Crear fichero.csv con las ventas");
        System.out.println("\t0. Salir");
        //System.out.println("\t4. Nueva compra ");
    }

}

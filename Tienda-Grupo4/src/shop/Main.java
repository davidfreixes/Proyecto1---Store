package shop;

import shop.controller.Controller;
import shop.model.ShopModel;
import shop.model.persist.ShopDAO;

public class Main {

    public static void main(String[] args) {
        ShopDAO shopDAO = new ShopDAO();
        ShopModel model = new ShopModel(shopDAO);
        Controller controller = new Controller(model);
        controller.start();
    }
}
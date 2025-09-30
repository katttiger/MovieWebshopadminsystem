package se.iths.cecilia.webshopadmin.controller;

import se.iths.cecilia.webshopadmin.DAO.ProductDAOInterface;
import se.iths.cecilia.webshopadmin.DAO.ProductDAOJOptionPane;
import se.iths.cecilia.webshopadmin.view.MenuhandlerJOptionPane;
import se.iths.cecilia.webshopadmin.view.UIInterface;

public class ControllerJOptionPane implements ControllerInterface {
    private UIInterface menuhandler;
    private ProductDAOInterface productDAO;

    public ControllerJOptionPane() {
        this.menuhandler = new MenuhandlerJOptionPane();
        this.productDAO = new ProductDAOJOptionPane();
    }

    @Override
    public int Start() {
        menuhandler.printMenu();
        int userRequest = menuhandler.userMenuChoice();
        actionWithJOptionPane(userRequest);
        return userRequest;
    }

    public void actionWithJOptionPane(int userRequest) {
        switch (userRequest) {
            case 1 -> {
                productDAO.addProduct();
            }
            case 2 -> {
                productDAO.listAllProducts();

            }
            case 3 -> {
                productDAO.searchForProduct();

            }
            case 4 -> System.out.println("Closing application");
        }
    }
}

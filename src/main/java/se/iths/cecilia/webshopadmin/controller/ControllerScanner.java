package se.iths.cecilia.webshopadmin.controller;

import se.iths.cecilia.webshopadmin.DAO.ProductDAOInterface;
import se.iths.cecilia.webshopadmin.DAO.ProductDAOScanner;
import se.iths.cecilia.webshopadmin.view.menuhandler.MenuHandlerInterface;
import se.iths.cecilia.webshopadmin.view.menuhandler.MenuhandlerScanner;

public class ControllerScanner implements ControllerInterface {
    private MenuHandlerInterface menuhandler;
    private ProductDAOInterface productDAO;

    public ControllerScanner() {
        this.menuhandler = new MenuhandlerScanner();
        this.productDAO = new ProductDAOScanner();
    }

    @Override
    public int Start() {
        menuhandler.printMenu();
        int userRequest = menuhandler.userMenuChoice();
        actionWithScanner(userRequest);
        return userRequest;
    }

    public void actionWithScanner(int userRequest) {
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

    public void returnToMenuPrompt() {
        System.out.println("Press enter to return to menu.");
    }
}

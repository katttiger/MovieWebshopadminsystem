package se.iths.cecilia.webshopadmin.controller;

import se.iths.cecilia.webshopadmin.DAO.ProductDAO;
import se.iths.cecilia.webshopadmin.view.MenuhandlerJOptionPane;

import java.util.Scanner;

public class Controller implements ControllerInterface {
    private MenuhandlerJOptionPane menuhandler;
    private ProductDAO productDAO;

    public Controller() {
        this.menuhandler = new MenuhandlerJOptionPane();
        this.productDAO = new ProductDAO();
    }

    @Override
    public int Start() {
        menuhandler.printMenu();
        int userRequest = menuhandler.userMenuChoice();
        action(userRequest);
        return userRequest;
    }

    public void action(int userRequest) {
        switch (userRequest) {
            case 1 -> {
                productDAO.addProduct();
                returnToMenuPrompt();
            }
            case 2 -> {
                productDAO.listAllProducts();
                returnToMenuPrompt();
            }
            case 3 -> {
                productDAO.searchForProduct();
                returnToMenuPrompt();
            }
            case 4 -> {
                System.out.println("Closing application");
            }
        }
    }

    public void returnToMenuPrompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press enter to return to menu.");
        sc.nextLine();
    }
}

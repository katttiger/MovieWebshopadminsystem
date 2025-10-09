package se.iths.cecilia.webshopadmin.view.menuhandler;

import se.iths.cecilia.webshopadmin.controller.Errorcheck;

import java.util.ArrayList;
import java.util.List;

public class MenuhandlerScanner implements MenuHandlerInterface {
    private final List<String> menuOptions;

    public MenuhandlerScanner() {
        menuOptions = new ArrayList<>() {
            {
                add("Add product");
                add("List all products");
                add("Search for product");
                add("Close application");
            }
        };
    }

    public void printMenu() {
        for (int i = 0; i < menuOptions.toArray().length; i++) {
            System.out.println("[" + (i + 1) + "] " + menuOptions.get(i));
        }
    }

    @Override
    public int userMenuChoice() {
        int choice;
        do {
            System.out.println("Enter choice: ");
            choice = Errorcheck.checkIntegerInput();
            if (choice < 1 || choice > 4) {
                System.out.println("You may only enter a choice between 1 and 4.");
            }
        } while (choice > 4 || choice < 1);

        switch (choice) {
            case 1 -> choice = 1;
            case 2 -> choice = 2;
            case 3 -> choice = 3;
            case 4 -> choice = 4;
        }
        return choice;
    }
}

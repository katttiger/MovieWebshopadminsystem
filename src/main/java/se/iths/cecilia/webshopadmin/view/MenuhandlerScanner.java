package se.iths.cecilia.webshopadmin.view;

import se.iths.cecilia.webshopadmin.controller.Errorcheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuhandlerScanner implements UIInterface {
    private List<String> menuOptions;
    Scanner sc = new Scanner(System.in);
    private Errorcheck errorcheck = new Errorcheck();

    public MenuhandlerScanner() {
        menuOptions = new ArrayList<>();
        menuOptions = setMenuOptions();
    }

    @Override
    public void printMenu() {
        for (int i = 0; i < menuOptions.toArray().length; i++) {
            System.out.println("[" + (i + 1) + "] " + menuOptions.get(i));
        }
    }

    @Override
    public int userMenuChoice() {
        int choice = 0;
        do {
            System.out.println("Enter choice: ");
            choice = errorcheck.checkIntegerInput();
            if (choice < 1 || choice > 4) {
                System.out.println("You may only enter a choice between 1 and 4.");
            }
        } while (choice > 4 || choice < 1);

        switch (choice) {
            case 1 -> {
                choice = 1;
            }
            case 2 -> {
                choice = 2;
            }
            case 3 -> {
                choice = 3;
            }
            case 4 -> {
                choice = 4;
            }
        }
        return choice;
    }

    private List<String> setMenuOptions() {
        String optionOne = "Add product";
        String optionTwo = "List all products";
        String optionThree = "Search for product";
        String optionFour = "Close application";

        menuOptions.add(optionOne);
        menuOptions.add(optionTwo);
        menuOptions.add(optionThree);
        menuOptions.add(optionFour);

        return menuOptions;

    }
}

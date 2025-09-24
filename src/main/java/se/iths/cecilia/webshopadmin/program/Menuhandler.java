package se.iths.cecilia.webshopadmin.program;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menuhandler {
    private List<String> menuOptions;

    public ProgramLogic programLogic;

    public Menuhandler() {
        menuOptions = new ArrayList<>();
        menuOptions = setMenuOptions();
        this.programLogic = new ProgramLogic();
    }

    public void printMenu() {
        for (int i = 0; i < menuOptions.toArray().length; i++) {
            System.out.println("[" + (i + 1) + "] " + menuOptions.get(i));
        }
    }

    public void userMenuChoice() {
        int choice = 0;
        do {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("Enter choice: ");
                choice = sc.hasNextInt() ? sc.nextInt() : -1;
                if (choice == -1) {
                    throw new InputMismatchException();
                }

                switch (choice) {
                    case 1 -> programLogic.addProduct();
                    case 2 -> programLogic.listAllProducts();
                    case 3 -> programLogic.searchForProduct();
                    case 4 -> programLogic.closeApplication();
                    default -> System.out.println("Invalid choice. You may only enter a number between 1 and 4.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. You may only enter a whole digit between 1 and 4.");
            }
        } while (choice < 1 || choice > 4);
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

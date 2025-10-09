package se.iths.cecilia.webshopadmin.view.menuhandler;

import javax.swing.*;

public class MenuhandlerJOptionPane implements MenuHandlerInterface {
    @Override
    public void printMenu() {
    }

    @Override
    public int userMenuChoice() {
        int choice = 0;
        String answer;
        do {
            answer = JOptionPane.showInputDialog(
                    "1. Add product" +
                            "\n 2. List all products" +
                            "\n 3. Search for product" +
                            "\n 4. Close application");
            try {
                switch (Integer.parseInt(answer)) {
                    case 1 -> choice = 1;
                    case 2 -> choice = 2;
                    case 3 -> choice = 3;
                    case 4 -> choice = 4;
                    default -> {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please enter a valid number.");
                        choice = 0;
                    }
                }
            } catch (NumberFormatException | NullPointerException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a valid number.");
            }
        } while (choice < 1 || choice > 4);
        return choice;
    }
}

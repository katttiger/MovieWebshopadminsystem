package se.iths.cecilia.webshopadmin.view.menuhandler;

import javax.swing.*;

public class MenuhandlerJOptionPane implements MenuHandlerInterface {
    @Override
    public void printMenu() {
    }

    @Override
    public int userMenuChoice() {
        int choice = 0;
        do {
            String[] options = new String[]{
                    "Add product",
                    "List all products",
                    "Search for products",
                    "Close application"};

            int answer = JOptionPane.showOptionDialog(
                    null,
                    "Enter choice",
                    "Main menu",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[3]);

            switch (answer) {
                case 0 -> choice = 1;
                case 1 -> choice = 2;
                case 2 -> choice = 3;
                case 3 -> choice = 4;
            }

        } while (choice < 1);
        return choice;
    }
}

package se.iths.cecilia.webshopadmin.controller;

import se.iths.cecilia.webshopadmin.view.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Errorcheck {
    static Scanner sc = new Scanner(System.in);

    public static int checkIntegerInput() {
        int userInput = -1;
        try {
            userInput = sc.hasNextInt() ? sc.nextInt() : -1;
            sc.nextLine();
            if (userInput == -1) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            UI.info("Invalid option.");
        }
        return userInput;
    }

}

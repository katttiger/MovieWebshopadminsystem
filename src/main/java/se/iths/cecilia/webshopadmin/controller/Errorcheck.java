package se.iths.cecilia.webshopadmin.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Errorcheck {
    Scanner sc = new Scanner(System.in);

    public String checkIsStringEmpty() {
        String value = sc.nextLine();
        if (value.isEmpty() || value.isBlank()) {
            System.out.println("Text cannot be empty. Please try again.");
        }
        return value;
    }

    public int isNumberPositive(int pendingNumber) {
        if (pendingNumber < 1) {
            System.out.println("Number may not be negative or 0");
            return 0;
        }
        return pendingNumber;
    }

    public int checkIntegerInput() {
        int userInput = -1;
        try {
            userInput = sc.hasNextInt() ? sc.nextInt() : -1;
            sc.nextLine();
            if (userInput == -1) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid option.");
        }
        return userInput;
    }

}

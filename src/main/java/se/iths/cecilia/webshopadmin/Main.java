package se.iths.cecilia.webshopadmin;

import se.iths.cecilia.webshopadmin.controller.ControllerInterface;
import se.iths.cecilia.webshopadmin.controller.ControllerJOptionPane;
import se.iths.cecilia.webshopadmin.controller.ControllerScanner;

import java.util.Scanner;

public class Main {

    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        //TODO: FIX MENY

        System.out.println("""
                Chose your interface:
                [1] Scanner
                [2] JOPtionPane
                [3] Close application""");

        int request;
        do {
            System.out.println("Enter your choice");
            request = sc.hasNextInt() ? sc.nextInt() : 0;
            sc.nextLine();
            switch (request) {
                case 1 -> {
                    ControllerInterface controller = new ControllerScanner();
                    request = controller.Start();
                }
                case 2 -> {
                    ControllerInterface controller = new ControllerJOptionPane();
                    request = controller.Start();
                }
                case 3 -> {
                    request = 4;
                }
                default -> System.out.println("Invalid choice.");
            }
        } while (request != 4);
    }
}

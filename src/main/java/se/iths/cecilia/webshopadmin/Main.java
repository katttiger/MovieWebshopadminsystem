package se.iths.cecilia.webshopadmin;

import se.iths.cecilia.webshopadmin.controller.ControllerInterface;
import se.iths.cecilia.webshopadmin.controller.ControllerJOptionPane;
import se.iths.cecilia.webshopadmin.controller.ControllerScanner;
import se.iths.cecilia.webshopadmin.view.UI;

import java.util.Scanner;

public class Main {

    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        UI ui = new UI();
        System.out.println(ui.menu());
        int request;

        do {
            System.out.println("Enter your choice: ");
            request = sc.hasNextInt() ? sc.nextInt() : 0;
            sc.nextLine();

            switch (request) {
                case 1 -> {
                    do {
                        ControllerInterface controller = new ControllerScanner();
                        request = controller.Start();
                    } while (request != 4);
                }
                case 2 -> {
                    do {
                        ControllerInterface controller = new ControllerJOptionPane();
                        request = controller.Start();
                    } while (request != 4);
                }
                case 3 -> {
                    request = 4;
                    sc.close();
                }
                default -> System.out.println("Invalid choice.");
            }
        } while (request != 4);
    }
}

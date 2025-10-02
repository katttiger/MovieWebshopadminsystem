package se.iths.cecilia.webshopadmin;

import se.iths.cecilia.webshopadmin.controller.ControllerInterface;
import se.iths.cecilia.webshopadmin.controller.ControllerJOptionPane;
import se.iths.cecilia.webshopadmin.controller.ControllerScanner;

import java.util.Scanner;

public class Main {

    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("""
                Chose your interface:
                [1] Scanner
                [2] JOPtionPane
                [3] Close application
                Enter your choice:""");

        int request;
        do {
            request = sc.nextInt();
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
            }
        } while (request != 4);
    }
}


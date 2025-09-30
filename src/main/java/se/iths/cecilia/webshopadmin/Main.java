package se.iths.cecilia.webshopadmin;

import se.iths.cecilia.webshopadmin.controller.ControllerInterface;
import se.iths.cecilia.webshopadmin.controller.ControllerJOptionPane;

public class Main {
    public static void main(String[] args) {
        ControllerInterface controller = new ControllerJOptionPane();
        
        //print menu to see allow user to choose between JOptionPane and Scanner

        int request = 0;
        do {
            request = controller.Start();
        } while (request != 4);
    }
}

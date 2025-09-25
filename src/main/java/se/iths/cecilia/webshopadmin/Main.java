package se.iths.cecilia.webshopadmin;

import se.iths.cecilia.webshopadmin.controller.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        int request = 0;
        do {
            request = controller.Start();
        } while (request != 4);
    }
}

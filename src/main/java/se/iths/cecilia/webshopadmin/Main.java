package se.iths.cecilia.webshopadmin;

import se.iths.cecilia.webshopadmin.program.Menuhandler;

public class Main {
    public static void main(String[] args) {
        Menuhandler menuhandler = new Menuhandler();
        menuhandler.printMenu();
        menuhandler.userMenuChoice();
    }
}

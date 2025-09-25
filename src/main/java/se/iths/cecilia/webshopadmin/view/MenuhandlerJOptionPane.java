package se.iths.cecilia.webshopadmin.view;

import java.util.List;

public class MenuhandlerJOptionPane implements UIInterface {

    private List<String> menuOptions;

    @Override
    public void printMenu() {
    }

    @Override
    public int userMenuChoice() {
        return 0;
    }

    private List<String> setMenuOptions() {
        String optionOne = "Add product";
        String optionTwo = "List all products";
        String optionThree = "Search for product";
        String optionFour = "Close application";

        menuOptions.add(optionOne);
        menuOptions.add(optionTwo);
        menuOptions.add(optionThree);
        menuOptions.add(optionFour);

        return menuOptions;

    }
}

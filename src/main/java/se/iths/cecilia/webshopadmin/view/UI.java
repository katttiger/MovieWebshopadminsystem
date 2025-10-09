package se.iths.cecilia.webshopadmin.view;

public class UI implements UIInterface {
    public static String prompt(String message) {
        return message;
    }

    public static void info(String message) {
        System.out.println(message);
    }

    public static String menu() {
        return """
                Chose your interface:
                [1] Scanner
                [2] JOPtionPane
                [3] Close application"""
                ;
    }
}

package se.iths.cecilia.webshopadmin.view;

public class UI implements UIInterface {
    @Override
    public String prompt(String message) {
        return message;
    }

    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public String menu() {
        return """
                Chose your interface:
                [1] Scanner
                [2] JOPtionPane
                [3] Close application
                Enter your choice:"""
                ;
    }
}

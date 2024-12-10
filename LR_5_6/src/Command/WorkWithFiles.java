package Command;

import FinancialAssets.Derivative;
import FinancialAssets.Obligation;
import Menu.Menu;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WorkWithFiles implements Command {
    private Scanner scanner;
    private Menu menu;
    private List<Derivative> derivatives;
    private List<Obligation> obligations;

    public WorkWithFiles(Scanner scanner, Menu menu, List<Derivative> derivatives, List<Obligation> obligations) {
        this.scanner = scanner;
        this.menu = menu;
        this.derivatives = derivatives;
        this.obligations = obligations;
    }

    @Override
    public void execute() {
        int choice = 0;
        while (choice != 3) {
            menu.printFileMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> chooseFile();
                case 2 -> checkLogger();
                case 3 -> System.out.println("Returning");
                default -> System.out.println("Wrong input");
            }
        }
    }
    public void chooseFile(){
        System.out.println("W.I.P");
    }

    public void checkLogger() {
        System.out.println("W.I.P");
    }
}


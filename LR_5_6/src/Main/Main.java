package Main;

import Command.*;
import FinancialAssets.Derivative;
import FinancialAssets.Obligation;
import Menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        List<Derivative> derivatives = new ArrayList<>();
        List<Obligation> obligations = new ArrayList<>();

        Invoker invoker = new Invoker();

        invoker.setCommand(1, new WorkWithDerivatives(derivatives, obligations, scanner, menu));
        invoker.setCommand(2, new WorkWithObligations(obligations, scanner, menu));
        invoker.setCommand(3, new WorkWithFiles(scanner, menu,derivatives,obligations));

        int choice = 0;
        while (choice != invoker.getCommandSize()+1) {
            menu.printMenu();
            choice = scanner.nextInt();
            if (choice == invoker.getCommandSize()+1) {
                System.out.println("End of work");
            } else {
                invoker.executeCommand(choice);
            }
        }
    }
}

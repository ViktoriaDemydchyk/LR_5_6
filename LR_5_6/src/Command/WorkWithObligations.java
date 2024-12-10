package Command;

import FinancialAssets.LiabilityInsurance;
import FinancialAssets.LifeInsurance;
import FinancialAssets.Obligation;
import FinancialAssets.PropertyInsurance;
import Menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkWithObligations implements Command {
    private List<Obligation> obligations;
    private Scanner scanner;
    private Menu menu;

    public WorkWithObligations(List<Obligation> obligations, Scanner scanner, Menu menu) {
        this.obligations = obligations;
        this.scanner = scanner;
        this.menu = menu;
    }

    @Override
    public void execute() {
        int choice = 0;
        while (choice != 5) {
            menu.printObligationMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addObligation();
                case 2 -> removeObligation();
                case 3 -> riskAnalyze();
                case 4 -> obligationCompare();
                case 5 -> System.out.println("Returning");
                default -> System.out.println("Wrong input");
            }
        }
    }

    private void addObligation() {
        int choice;
        menu.printOTMenu();
        choice = scanner.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("Wrong input");
            choice = scanner.nextInt();
        }

        Obligation obligation = createObligation(choice);
        if (obligation != null) {
            obligations.add(obligation);
            System.out.println("Obligation added successfully");
        }
        else {
            System.out.println("Failed to create obligation");
        }
    }

    private Obligation createObligation(int choice) {
        return switch (choice) {
            case 1 -> createPropertyObligation();
            case 2 -> createLifeObligation();
            case 3 -> createLiabilityObligation();
            default -> {
                System.out.println("Invalid choice");
                yield null;
            }
        };
    }

    private PropertyInsurance createPropertyObligation() {
        System.out.println("Enter details for Property Insurance:");
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Price: ");
        int price = scanner.nextInt();
        System.out.print("Compensation: ");
        int value = scanner.nextInt();

        double riskLevel = getRiskLevel();

        System.out.print("Payout: ");
        int payout = scanner.nextInt();
        System.out.print("Term: ");
        int term = scanner.nextInt();
        System.out.print("Location: ");
        String location = scanner.next();
        System.out.print("Policy Details: ");
        String policyDetails = scanner.next();

        return new PropertyInsurance(name, price, value, riskLevel, payout, term, location, policyDetails);
    }

    private LifeInsurance createLifeObligation() {
        System.out.println("Enter details for Life Insurance:");
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Price: ");
        int price = scanner.nextInt();
        System.out.print("Compensation: ");
        int value = scanner.nextInt();

        double riskLevel = getRiskLevel();

        System.out.print("Payout: ");
        int payout = scanner.nextInt();
        System.out.print("Term: ");
        int term = scanner.nextInt();
        System.out.print("Beneficiary: ");
        String beneficiary = scanner.next();

        return new LifeInsurance(name, price, value, riskLevel, payout, term, beneficiary);
    }

    private LiabilityInsurance createLiabilityObligation() {
        System.out.println("Enter details for Liability Insurance:");
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Price: ");
        int price = scanner.nextInt();
        System.out.print("Compensation: ");
        int value = scanner.nextInt();

        double riskLevel = getRiskLevel();

        System.out.print("Payout: ");
        int payout = scanner.nextInt();
        System.out.print("Term: ");
        int term = scanner.nextInt();
        System.out.print("Deductible: ");
        int deductible = scanner.nextInt();
        System.out.print("Policy Type: ");
        String policyType = scanner.next();

        return new LiabilityInsurance(name, price, value, riskLevel, payout, term, deductible, policyType);
    }

    private double getRiskLevel() {
        int riskLevel;
        do {
            System.out.print("Risk Level (1-100): ");
            riskLevel = scanner.nextInt();
            if (riskLevel < 1 || riskLevel > 100) {
                System.out.println("Please enter a valid risk level between 1 and 100");
            }
        } while (riskLevel < 1 || riskLevel > 100);

        return riskLevel / 100.0;
    }


    private void removeObligation() {
        if (obligations.isEmpty()) {
            System.out.println("No obligations to remove");
            return;
        }
        List<Obligation> chosenObligations = chooseObligations(obligations);
        for (Obligation obligation : chosenObligations) {
            obligations.remove(obligation);
        }
    }

    private void riskAnalyze() {
        if (obligations.isEmpty()){
            System.out.println("No obligations to remove");
            return;
        }
        List<Obligation> list = chooseObligations(obligations);
        for(Obligation obligation : list){
            System.out.println("Obligation: " + obligation.getName());
            System.out.println("Risk score: "+ obligation.riskCoeficient()*100+"\n");
        }
    }

    private void obligationCompare() {
        if (obligations.size() < 2) {
            System.out.println("Not enough obligations to compare. At least 2 are required.");
            return;
        }

        System.out.println("Select two obligations to compare:");
        List<Obligation> chosenObligations = chooseObligations(obligations);

        if (chosenObligations.size() != 2) {
            System.out.println("You must select exactly two obligations for comparison.");
            return;
        }

        Obligation obligation1 = chosenObligations.get(0);
        Obligation obligation2 = chosenObligations.get(1);

        System.out.println("\nComparing:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-20s %-20s %-20s\n", "Name", obligation1.getName(), obligation2.getName());
        System.out.printf("%-20s %-20s %-20s\n", "Price", obligation1.getPrice(), obligation2.getPrice());
        System.out.printf("%-20s %-20s %-20s\n", "Compensation", obligation1.getValue(), obligation2.getValue());
        System.out.printf("%-20s %-20s %-20s\n", "Risk", obligation1.getRiskLevel() * 100 + "%", obligation2.getRiskLevel() * 100 + "%");
        System.out.printf("%-20s %-20s %-20s\n", "Term", obligation1.getTerm(), obligation2.getTerm());

        if (obligation1 instanceof PropertyInsurance property1) {
            System.out.printf("%-20s %-20s\n", "Location", property1.getLocation());
            System.out.printf("%-20s %-20s\n", "Policy Details", property1.getPolicyDetails());
        } else if (obligation1 instanceof LifeInsurance life1) {
            System.out.printf("%-20s %-20s\n", "Beneficiary", life1.getBeneficiary());
        } else if (obligation1 instanceof LiabilityInsurance liability1) {
            System.out.printf("%-20s %-20s\n", "Deductible", liability1.getDeductible());
            System.out.printf("%-20s %-20s\n", "Policy Type", liability1.getPolicyType());
        }

        if (obligation2 instanceof PropertyInsurance property2) {
            System.out.printf("%-20s %-20s\n", "Location", property2.getLocation());
            System.out.printf("%-20s %-20s\n", "Policy Details", property2.getPolicyDetails());
        } else if (obligation2 instanceof LifeInsurance life2) {
            System.out.printf("%-20s %-20s\n", "Beneficiary", life2.getBeneficiary());
        } else if (obligation2 instanceof LiabilityInsurance liability2) {
            System.out.printf("%-20s %-20s\n", "Deductible", liability2.getDeductible());
            System.out.printf("%-20s %-20s\n", "Policy Type", liability2.getPolicyType());
        }

        System.out.println("---------------------------------------------------------");
    }


    private List<Obligation> chooseObligations(List<Obligation> list){
        List<Obligation> res = new ArrayList<>();
        int page = 0;
        int itemsPerPage = 10;
        while (true) {
            int start = page * itemsPerPage;
            int end = Math.min(start + itemsPerPage, list.size());

            System.out.println("Page " + (page + 1));
            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + list.get(i).getName());
            }

            System.out.println("Enter the number of the obligation to add, 'n' for next page, 'p' for previous or 'q' for exit");
            String input = scanner.next();

            if (input.equalsIgnoreCase("q")) {
                break;
            } else if (input.equalsIgnoreCase("n")) {
                if (end < list.size()) page++;
            } else if (input.equalsIgnoreCase("p")) {
                if (page > 0) page--;
            } else {
                try {
                    int index = Integer.parseInt(input) - 1;
                    if (index >= start && index < end) {
                        Obligation selectedObligation = list.get(index);
                        if (!res.contains(selectedObligation)) {
                            res.add(selectedObligation);
                            System.out.println("Obligation added.");
                        } else {
                            System.out.println("This obligation has already been added.");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
        }
        System.out.println("Derivative created successfully.");
        return res;
    }
}

package Command;

import FinancialAssets.Derivative;
import FinancialAssets.Obligation;
import Menu.Menu;
import Utility.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkWithDerivatives implements Command {
    List<Derivative> derivatives;
    List<Obligation> obligations;
    Scanner scanner;
    Menu menu;
    SearchParameters parameters;

    public WorkWithDerivatives(List<Derivative> derivatives, List<Obligation> obligations, Scanner scanner, Menu menu){
        this.derivatives = derivatives;
        this.obligations = obligations;
        this.scanner = scanner;
        this.menu = menu;
        this.parameters = new SearchParameters();
    }
    @Override
    public void execute(){
        int choice = 0;
        while (choice!=7){
            menu.printDerivativeMenu();
            choice = scanner.nextInt();
            switch (choice){
                case 1 -> createDerivative();
                case 2 -> complexAnalyze();
                case 3 -> sortObligations();
                case 4 -> changeObligations();
                case 5 -> findInRange();
                case 6 -> derivativesList();
                case 7 -> System.out.println("Returning");
                default -> System.out.println("Wrong input");
            }
        }
    }

    public void createDerivative() {
        System.out.println("Enter name of derivative: ");
        String name;
        boolean exists;

        do {
            name = scanner.next();
            exists = false;

            for (Derivative derivative : derivatives) {
                if (derivative.getName().equals(name)) {
                    System.out.println("Derivative with that name already exists. Enter a different name.");
                    exists = true;
                    break;
                }
            }
        } while (exists);

        Derivative newDerivative = new Derivative(name);
        if(!obligations.isEmpty()) {
            List<Obligation> res = chooseObligations(obligations);
            newDerivative.setObligations(res);
            obligations.removeAll(res);
            System.out.println("Derivative was created");
        }
        else{
            System.out.println("There is no obligations to add");
        }
        derivatives.add(newDerivative);
    }


    public void complexAnalyze() {
        Derivative derivative = chooseDerivative();
        if (derivative != null) {
            List<Obligation> obligations = derivative.getObligations();
            if (obligations.isEmpty()) {
                System.out.println("No obligations in the selected derivative.");
                return;
            }
            double totalRiskCoefficient = 0;
            double totalRisk = 0;
            int totalPayout = 0;
            int totalTerm = 0;
            int totalCompensation = 0;
            double minRisk = Double.MAX_VALUE;
            double maxRisk = Double.MIN_VALUE;
            int minPayout = Integer.MAX_VALUE;
            int maxPayout = Integer.MIN_VALUE;
            int minTerm = Integer.MAX_VALUE;
            int maxTerm = Integer.MIN_VALUE;

            for (Obligation obligation : obligations) {
                double riskCoefficient = obligation.riskCoeficient();
                double risk = obligation.getRiskLevel();
                int payout = obligation.getPayout();
                int term = obligation.getTerm();

                totalRisk += risk;
                totalPayout += payout;
                totalTerm += term;
                totalCompensation += obligation.getValue();
                totalRiskCoefficient += riskCoefficient;

                if (risk < minRisk) minRisk = risk;
                if (risk > maxRisk) maxRisk = risk;
                if (payout < minPayout) minPayout = payout;
                if (payout > maxPayout) maxPayout = payout;
                if (term < minTerm) minTerm = term;
                if (term > maxTerm) maxTerm = term;
            }

            int count = obligations.size();
            double avgRisk = totalRisk / count;
            double avgPayout = (double) totalPayout / count;
            double avgTerm = (double) totalTerm / count;

            System.out.println("Total price: " + derivative.calculatePrice());
            System.out.println("Risk score: " + totalRiskCoefficient);
            System.out.println("Average Risk: " + avgRisk*100);
            System.out.println("Minimum Risk: " + minRisk*100);
            System.out.println("Maximum Risk: " + maxRisk*100);
            System.out.println("Average Payout: " + avgPayout);
            System.out.println("Minimum Payout: " + minPayout);
            System.out.println("Maximum Payout: " + maxPayout);
            System.out.println("Average Term: " + avgTerm);
            System.out.println("Minimum Term: " + minTerm);
            System.out.println("Maximum Term: " + maxTerm);
            System.out.println("Total compensation: " + totalCompensation);
        } else {
            System.out.println("Something went wrong.");
        }
    }


    public void sortObligations(){
        int choice;
        Derivative selectedDerivative = chooseDerivative();
        if(selectedDerivative!=null) {
            menu.printSortDerivativeMenu();
            choice = scanner.nextInt();
            while (choice < 0 || choice > 5) {
                choice = scanner.nextInt();
                System.out.println("Wrong input");
            }
            switch (choice) {
                case 1:
                    selectedDerivative.sortByRisk();
                    break;
                case 2:
                    selectedDerivative.sortByPayout();
                    break;
                case 3:
                    selectedDerivative.sortByPrice();
                    break;
                case 4:
                    selectedDerivative.sortByTerm();
                    break;
                case 5:
                    selectedDerivative.sortByValue();
                    break;
                default:
                    System.out.println("Something went wrong");
                    break;
            }
        }
        else{
            System.out.println("Something went wrong");
        }
    }

    public void changeObligations(){
        Derivative derivative = chooseDerivative();
        if(derivative!=null) {
            menu.printCODMenu();
            int choice = 0;
            while (choice != 3) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addObligation(derivative);
                        break;
                    case 2:
                        deleteObligation(derivative);
                        break;
                    case 3:
                        System.out.println("Returning...");
                        break;
                    default:
                        System.out.println("Wrong input");
                        break;
                }
            }
        }
        else{
            System.out.println("Something went wrong");
        }
    }

    public void findInRange(){
        List<Obligation> result = new ArrayList<>();
        Derivative derivative = chooseDerivative();
        if(derivative!= null) {
            parameters.setToDefault();
            int choice = 0;
            while (choice != 6) {
                menu.printFIRMenu();
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        pickRisk();
                        break;
                    case 2:
                        pickPrice();
                        break;
                    case 3:
                        pickIncome();
                        break;
                    case 4:
                        pickCompensation();
                        break;
                    case 5:
                        pickTerm();
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Wrong input");
                }
            }
            for (Obligation obligation : derivative.getObligations()) {
                boolean matches = isMatches(obligation);
                if (matches) {
                    result.add(obligation);
                }
            }
            if(result.isEmpty()){
                System.out.println("There are no obligations that meet these criteria");
            }
            else{
                System.out.println();
                for(Obligation obligation: result){
                    System.out.println(obligation+"\n");
                }
                System.out.println();
            }
        }
        else{
            System.out.println("Something went wrong");
        }
    }

    public void derivativesList(){
        if(derivatives.isEmpty()){
            System.out.println("There is no derivative");
        }
        else{
            System.out.println();
            for(Derivative derivative : derivatives){
                System.out.println(derivative+"\n");
            }
            System.out.println();
        }
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

    private Derivative chooseDerivative(){
        int page = 0;
        int itemsPerPage = 10;
        if (derivatives.isEmpty()) {
            System.out.println("No derivatives available.");
            return null;
        }
        while (true) {
            int start = page * itemsPerPage;
            int end = Math.min(start + itemsPerPage, derivatives.size());

            System.out.println("Page " + (page + 1));
            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + derivatives.get(i).getName());
            }

            System.out.println("Enter the number of the derivative to select, 'n' for next page or 'p' for previous:");
            String input = scanner.next();
            if (input.equalsIgnoreCase("n")) {
                if (end < derivatives.size()) page++;
            } else if (input.equalsIgnoreCase("p")) {
                if (page > 0) page--;
            } else {
                try {
                    int index = Integer.parseInt(input) - 1;
                    if (index >= start && index < end) {
                        System.out.println("You have selected: " + derivatives.get(index).getName());
                        return  derivatives.get(index);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
        }
    }

    private boolean isMatches(Obligation obligation) {
        boolean matches = true;
        if (parameters.getRisk() && (obligation.getRiskLevel() < parameters.getMinRisk() || obligation.getRiskLevel() > parameters.getMaxRisk())) {
            matches = false;
        }
        if (parameters.getPrice() && (obligation.getPrice() < parameters.getMinPrice() || obligation.getPrice() > parameters.getMaxPrice())) {
            matches = false;
        }

        if (parameters.getIncome() && (obligation.getPayout() < parameters.getMinIncome() || obligation.getPayout() > parameters.getMaxIncome())) {
            matches = false;
        }

        if (parameters.getTerm() && (obligation.getTerm() < parameters.getMinTerm() || obligation.getTerm() > parameters.getMaxTerm())) {
            matches = false;
        }

        if(parameters.getCompensation() && (obligation.getValue() < parameters.getMinCompensation() || obligation.getValue() > parameters.getMaxCompensation())){
            matches = false;
        }
        return matches;
    }

    public void pickRisk(){
        if(parameters.getRisk()){
            int choice;
            System.out.println("This option is already chose. Undo? 1 - yes, 2 - no");
            choice = scanner.nextInt();
            while (choice<1 || choice>2){
                System.out.println("Wrong input");
                choice = scanner.nextInt();
            }
            if(choice == 1){
                parameters.setRisk(false);
            }
        }
        else{
            int numMin;
            int numMax;
            System.out.println("Enter min risk(0-100)");
            numMin = scanner.nextInt();
            while (numMin < 0 || numMin > 100 ){
                System.out.println("Wrong input");
                numMin = scanner.nextInt();
            }
            System.out.println("Enter max risk");
            numMax = scanner.nextInt();
            while (numMax < numMin || numMax>100){
                if(numMax<numMin){
                    System.out.println("Max risk can not be smaller that min risk");
                }
                else{
                    System.out.println("Wrong input");
                }
                numMax = scanner.nextInt();
            }
            parameters.setRisk(true);
            parameters.setMaxRisk((double) numMax/100);
            parameters.setMinRisk((double) numMax/100);
        }
    }

    public void pickPrice() {
        if (parameters.getPrice()) {
            int choice;
            System.out.println("This option is already chosen. Undo? 1 - yes, 2 - no");
            choice = scanner.nextInt();
            while (choice < 1 || choice > 2) {
                System.out.println("Wrong input");
                choice = scanner.nextInt();
            }
            if (choice == 1) {
                parameters.setPrice(false);
            }
        } else {
            int numMin, numMax;
            System.out.println("Enter min price:");
            numMin = scanner.nextInt();
            while (numMin < 0) {
                System.out.println("Wrong input");
                numMin = scanner.nextInt();
            }
            System.out.println("Enter max price:");
            numMax = scanner.nextInt();
            while (numMax < numMin) {
                System.out.println("Max price cannot be smaller than min price");
                numMax = scanner.nextInt();
            }
            parameters.setPrice(true);
            parameters.setMinPrice(numMin);
            parameters.setMaxPrice(numMax);
        }
    }

    public void pickIncome() {
        if (parameters.getIncome()) {
            int choice;
            System.out.println("This option is already chosen. Undo? 1 - yes, 2 - no");
            choice = scanner.nextInt();
            while (choice < 1 || choice > 2) {
                System.out.println("Wrong input");
                choice = scanner.nextInt();
            }
            if (choice == 1) {
                parameters.setIncome(false);
            }
        } else {
            int numMin, numMax;
            System.out.println("Enter min income:");
            numMin = scanner.nextInt();
            while (numMin < 0) {
                System.out.println("Wrong input");
                numMin = scanner.nextInt();
            }
            System.out.println("Enter max income:");
            numMax = scanner.nextInt();
            while (numMax < numMin) {
                System.out.println("Max income cannot be smaller than min income");
                numMax = scanner.nextInt();
            }
            parameters.setIncome(true);
            parameters.setMinIncome(numMin);
            parameters.setMaxIncome(numMax);
        }
    }

    public void pickTerm() {
        if (parameters.getTerm()) {
            int choice;
            System.out.println("This option is already chosen. Undo? 1 - yes, 2 - no");
            choice = scanner.nextInt();
            while (choice < 1 || choice > 2) {
                System.out.println("Wrong input");
                choice = scanner.nextInt();
            }
            if (choice == 1) {
                parameters.setTerm(false);
            }
        } else {
            int numMin, numMax;
            System.out.println("Enter min term:");
            numMin = scanner.nextInt();
            while (numMin < 0) {
                System.out.println("Wrong input");
                numMin = scanner.nextInt();
            }
            System.out.println("Enter max term:");
            numMax = scanner.nextInt();
            while (numMax < numMin) {
                System.out.println("Max term cannot be smaller than min term");
                numMax = scanner.nextInt();
            }
            parameters.setTerm(true);
            parameters.setMinTerm(numMin);
            parameters.setMaxTerm(numMax);
        }
    }

    public void pickCompensation() {
        if (parameters.getCompensation()) {
            int choice;
            System.out.println("This option is already chosen. Undo? 1 - yes, 2 - no");
            choice = scanner.nextInt();
            while (choice < 1 || choice > 2) {
                System.out.println("Wrong input");
                choice = scanner.nextInt();
            }
            if (choice == 1) {
                parameters.setCompensation(false);
            }
        } else {
            int numMin, numMax;
            System.out.println("Enter min compensation:");
            numMin = scanner.nextInt();
            while (numMin < 0) {
                System.out.println("Wrong input");
                numMin = scanner.nextInt();
            }
            System.out.println("Enter max compensation:");
            numMax = scanner.nextInt();
            while (numMax < numMin) {
                System.out.println("Max compensation cannot be smaller than min compensation");
                numMax = scanner.nextInt();
            }
            parameters.setCompensation(true);
            parameters.setMinCompensation(numMin);
            parameters.setMaxCompensation(numMax);
        }
    }

    public void addObligation(Derivative derivative){
        List<Obligation> list = chooseObligations(obligations);
        derivative.addObligations(list);
    }

    public void deleteObligation(Derivative derivative){
        if(derivative.getObligations().isEmpty()){
            System.out.println("No obligation in derivative");
        }
        else{
            List<Obligation> list = chooseObligations(derivative.getObligations());
            derivative.removeObligations(list);
            obligations.addAll(list);
        }
    }

}
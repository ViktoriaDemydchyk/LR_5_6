package FinancialAssets;

import Utility.SearchParameters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Derivative {
    private String name;
    private List<Obligation> obligations;

    public Derivative(String name){
        this.obligations = new ArrayList<>();
        this.name = name;
    }

    public void setObligations(List<Obligation> list){
        this.obligations = list;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public List<Obligation> getObligations(){
        return obligations;
    }

    public void addObligation(Obligation newObligation){
        obligations.add(newObligation);
    }

    public void addObligations(List<Obligation> list){
        for(Obligation obligation : list){
            addObligation(obligation);
        }
    }

    public void removeObligation(String name){
        obligations.removeIf(obligation -> name.equals(obligation.getName()));
    }

    public void removeObligations(List<Obligation> list){
        for(Obligation obligation : list){
            removeObligation(obligation.getName());
        }
    }

    public int calculatePrice(){
        int price = 0;
        for(Obligation obligation : obligations){
            price+=obligation.getPrice();
        }
        return price;
    }

    public void sortByRisk(){
        obligations.sort(Comparator.comparing(Obligation::getRiskLevel));
    }

    public void sortByTerm(){
        obligations.sort(Comparator.comparing(Obligation::getTerm));
    }

    public void sortByPrice(){
        obligations.sort(Comparator.comparing(Obligation::getPrice));
    }

    public void sortByValue(){
        obligations.sort(Comparator.comparing(Obligation::getValue));
    }

    public void sortByPayout(){
        obligations.sort(Comparator.comparing(Obligation::getPayout));
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("Derivative Name: " + name + "\nObligations:\n");
        for (Obligation obligation : obligations) {
            result.append(obligation.toString()).append("\n");
        }
        return result.toString();
    }
}

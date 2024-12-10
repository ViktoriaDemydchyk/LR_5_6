package FinancialAssets;

public abstract class Obligation {
    protected String name;
    protected int price;
    protected int value;
    protected double riskLevel;
    protected int payout;
    protected int term;

    public Obligation(String name, int price, int value, double riskLevel, int payout, int term) {
        this.name = name;
        this.price = price;
        this.value = value;
        this.riskLevel = riskLevel;
        this.payout = payout;
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public double getRiskLevel() {
        return riskLevel;
    }

    public int getPayout() {
        return payout;
    }

    public int getTerm() {
        return term;
    }

    public int getValue(){
        return value;
    }

    @Override
    public abstract String toString();

    public abstract double riskCoeficient();
}


package FinancialAssets;

public class PropertyInsurance extends Obligation {
    private String location;
    private String policyDetails;
    public PropertyInsurance(String name,int price,int value, double riskLevel, int payout,int term, String location, String policyDetails) {
        super(name,price,value,riskLevel,payout,term);
        this.location = location;
        this.policyDetails = policyDetails;
    }

    public String getLocation(){
        return location;
    }

    public String getPolicyDetails(){
        return policyDetails;
    }

    @Override
    public String toString() {
        return name + "\nPrice: " + price + "\nRisk level: " + riskLevel + "\nPayouts: " + payout + "\nProperty value: " + value + "\nLocation: " + location + "\nDetails: " + policyDetails;
    }


    @Override
    public double riskCoeficient(){
        return (double) payout/(value*riskLevel);
    }
}

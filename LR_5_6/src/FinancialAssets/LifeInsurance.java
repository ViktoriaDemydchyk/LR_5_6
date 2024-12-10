package FinancialAssets;

public class LifeInsurance extends Obligation {
    private String beneficiary;
    public LifeInsurance(String name,int price, int value, double riskLevel,int payout, int term, String beneficiary) {
        super(name,price,value,riskLevel,payout,term);
        this.beneficiary = beneficiary;
    }

    public String getBeneficiary(){
        return beneficiary;
    }

    @Override
    public String toString() {
        return name + "\nPrice: " + price + "\nRisk level: " + riskLevel + "\nPayouts: " + payout + "\nBeneficiary: " + beneficiary + "\nCompensation amount: " + value;
    }


    @Override
    public double riskCoeficient(){
        return payout*term*(1-riskLevel)/value;
    }
}

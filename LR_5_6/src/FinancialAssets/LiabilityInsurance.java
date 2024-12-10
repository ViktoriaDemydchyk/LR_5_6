package FinancialAssets;

public class LiabilityInsurance extends Obligation{
    private int deductible;
    private String policyType;
    public LiabilityInsurance(String name,int price, int value, double riskLevel,int payout,int term, int deductible, String policyType) {
        super(name,price,value,riskLevel,payout,term);
        this.deductible = deductible;
        this.policyType = policyType;
    }

    public int getDeductible(){
        return deductible;
    }

    public String getPolicyType(){
        return policyType;
    }

    @Override
    public String toString() {
        return name + "\nPrice: " + price + "\nRisk level: " + riskLevel + "\nPayouts: " + payout + "\nCoverage limits: " + value + "\nInsurance deductible: " + deductible + "\nPolicy type: " + policyType;
    }


    @Override
    public double riskCoeficient(){
        return (double) payout/((value-deductible)*riskLevel);
    }
}

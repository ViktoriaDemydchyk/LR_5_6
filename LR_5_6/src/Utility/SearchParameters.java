package Utility;

public class SearchParameters {
    private double minRisk;
    private double maxRisk;
    private int minPrice;
    private int maxPrice;
    private int minIncome;
    private int maxIncome;
    private int minTerm;
    private int maxTerm;
    private int minCompensation;
    private int maxCompensation;
    private boolean risk;
    private boolean price;
    private boolean income;
    private boolean term;
    private boolean compensation;

    public SearchParameters(){
        this.maxRisk = 0;
        this.minRisk = 0;
        this.minPrice = 0;
        this.maxPrice = 0;
        this.minIncome = 0;
        this.maxIncome = 0;
        this.maxTerm = 0;
        this.minTerm = 0;
        this.minCompensation = 0;
        this.maxCompensation = 0;
        this.risk = false;
        this.price = false;
        this.income = false;
        this.term = false;
        this.compensation = false;
    }

    public void setRisk(boolean risk){
        this.risk = risk;
    }

    public void setMaxRisk(double maxRisk){
        this.maxRisk = maxRisk;
    }

    public void setMinRisk(double minRisk){
        this.minRisk = minRisk;
    }

    public void setPrice(boolean price){
        this.price = price;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(int minPrice){
        this.minPrice = minPrice;
    }

    public void setIncome(boolean income){
        this.income = income;
    }

    public void setMaxIncome(int maxIncome) {
        this.maxIncome = maxIncome;
    }

    public void setMinIncome(int minIncome) {
        this.minIncome = minIncome;
    }

    public void setTerm(boolean term){
        this.term = term;
    }

    public void setMaxTerm(int maxTerm) {
        this.maxTerm = maxTerm;
    }

    public void setMinTerm(int minTerm) {
        this.minTerm = minTerm;
    }

    public void setCompensation(boolean compensation){
        this.compensation = compensation;
    }

    public void setMaxCompensation(int maxCompensation){
        this.maxCompensation = maxCompensation;
    }

    public void setMinCompensation(int minCompensation){
        this.minCompensation = minCompensation;
    }

    public boolean getRisk(){
        return risk;
    }

    public double getMaxRisk() {
        return maxRisk;
    }

    public double getMinRisk() {
        return minRisk;
    }

    public boolean getIncome(){
        return income;
    }

    public int getMaxIncome() {
        return maxIncome;
    }

    public int getMinIncome() {
        return minIncome;
    }

    public boolean getPrice(){
        return price;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public boolean getTerm(){
        return term;
    }

    public int getMaxTerm() {
        return maxTerm;
    }

    public int getMinTerm() {
        return minTerm;
    }

    public boolean getCompensation(){
        return compensation;
    }

    public int getMaxCompensation(){
        return maxCompensation;
    }

    public int getMinCompensation(){
        return minCompensation;
    }

    public void setToDefault(){
        this.maxRisk = 0;
        this.minRisk = 0;
        this.minPrice = 0;
        this.maxPrice = 0;
        this.minIncome = 0;
        this.maxIncome = 0;
        this.maxTerm = 0;
        this.minTerm = 0;
        this.minCompensation = 0;
        this.maxCompensation = 0;
        this.risk = false;
        this.price = false;
        this.income = false;
        this.term = false;
        this.compensation = false;
    }
}

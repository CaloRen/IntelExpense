package intelexpense.intelexpense;

import android.annotation.TargetApi;
import android.os.Build;

import java.text.NumberFormat;
import java.time.YearMonth;

public class GlobalVariables {

    //DatabaseHelper dbh;

    //double monthlyInc = dbh.getMonthlyIncomeAmount("joel", 2018, 11);
    double monthlyInc = 6000;
    double dailyBudget = monthlyInc / getRemainingDays();
    double totalExp = 0;
    double remBalAmt = dailyBudget;
    double remSavAmt = 0.00;
    double expClothing = 0;
    double expEntertainment = 0;
    double expFood = 0;
    double expGas = 0;
    double expGrocery = 0;
    double expInsurance = 0;
    double expTranspo = 0;
    double expUtil = 0;
    String itemDesc;
    double expAmt = 0;
    String expCategory;

    public double getMonthlyInc() {
        return monthlyInc;
    }

    public void setMonthlyInc(double monthlyInc) {
        this.monthlyInc = monthlyInc;
    }

    public double getDailyBudget() {
        return dailyBudget;
    }

    public void setDailyBudget(double dailyBudget) {
        this.dailyBudget = dailyBudget;
    }

    public double getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(double expAmt) {
        this.totalExp = totalExp + expAmt;
    }

    public double getRemBalAmt() {
        return remBalAmt;
    }

    public void setRemBalAmt(double remBalAmt) {
        this.remBalAmt = remBalAmt;
    }

    public double getRemSavAmt() {
        return remSavAmt;
    }

    public void setRemSavAmt(double remSavAmt) {
        this.remSavAmt = remSavAmt;
    }

    public double getExpClothing() {
        return expClothing;
    }

    public void setExpClothing(double expClothing) {
        this.expClothing = expClothing;
    }

    public double getExpEntertainment() {
        return expEntertainment;
    }

    public void setExpEntertainment(double expEntertainment) {
        this.expEntertainment = expEntertainment;
    }

    public double getExpFood() {
        return expFood;
    }

    public void setExpFood(double expFood) {
        this.expFood = expFood;
    }

    public double getExpGas() {
        return expGas;
    }

    public void setExpGas(double expGas) {
        this.expGas = expGas;
    }

    public double getExpGrocery() {
        return expGrocery;
    }

    public void setExpGrocery(double expGrocery) {
        this.expGrocery = expGrocery;
    }

    public double getExpInsurance() {
        return expInsurance;
    }

    public void setExpInsurance(double expInsurance) {
        this.expInsurance = expInsurance;
    }

    public double getExpTranspo() {
        return expTranspo;
    }

    public void setExpTranspo(double expTranspo) {
        this.expTranspo = expTranspo;
    }

    public double getExpUtil() {
        return expUtil;
    }

    public void setExpUtil(double expUtil) {
        this.expUtil = expUtil;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public double getExpAmt() {
        return expAmt;
    }

    public void setExpAmt(double expAmt) {
        this.expAmt = expAmt;
    }

    public String getExpCategory() {
        return expCategory;
    }

    public void setExpCategory(String expCategory) {
        this.expCategory = expCategory;
    }


    @TargetApi(Build.VERSION_CODES.O)
    public double getRemainingDays() {
        YearMonth yearMonth = YearMonth.of(2018, 11);
        int daysInMonth = yearMonth.lengthOfMonth();
        return daysInMonth;
    }
}

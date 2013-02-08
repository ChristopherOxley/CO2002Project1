/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author chrisoxley
 */

public class COCalculations {
    public static double calculateFutureValue(double amount, double period, double interestRate){
        
        // As per the calculation in the slides: Future Value = Amount_Invested * (1+Interest_Rate)N
        return amount * Math.pow((1+interestRate),period);
    }
    
    public static double calculatePresentValue(double amount, double period, double interestRate){
        
        // As per the calculation in the slides: Present Value = Future Value / (1+Interest_Rate)N
        return amount / Math.pow((1+interestRate),period);
        
    }
}

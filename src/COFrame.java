

import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chrisoxley
 */
public class COFrame extends JFrame{
    
    private double period;
    private double amount;
    private double interestRate; 
    private double result;
    
    
    public COFrame(){
        this.setBounds(100, 100, 500, 500);
        
        period = 5;
        amount = 100000;
        interestRate = 5;
        
        
        
        result = COCalculations.calculateFutureValue(amount, period, interestRate);
        System.out.println(result);
        result = COCalculations.calculatePresentValue(amount, period, interestRate);
        System.out.println(result);

    }
    
    
}

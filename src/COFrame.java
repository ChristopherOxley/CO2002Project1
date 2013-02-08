

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chrisoxley
 */
public class COFrame extends JFrame implements ActionListener{
    
    private double period;
    private double amount;
    private double interestRate; 
    private double result;
    
    
    public COFrame(){
        this.setBounds(100, 100, 500, 500);
        
        period = 5;
        amount = 100000;
        interestRate = 0.05;
        
        



        // Create the container to add the UI elements to and set the layoutmanager.
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        
        // Create the buttons to perform the respective actions when the user 
        // clicks the button.
        JButton btnFutureValue = new JButton();
        btnFutureValue.setText("Future Value");
        btnFutureValue.addActionListener(this);
        c.add(btnFutureValue);
        
        JButton btnPresentValue = new JButton();
        btnPresentValue.setText("Present Value");       
        btnPresentValue.addActionListener(this);
        c.add(btnPresentValue);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton)e.getSource();
        
        if(button.getText().equals("Future Value")){
        result = COCalculations.calculateFutureValue(amount, period, interestRate);
        System.out.println(result);
        }
    
        if(button.getText().equals("Present Value")){
        result = COCalculations.calculatePresentValue(amount, period, interestRate);
        System.out.println(result);
        }
    }
    
    
    
    
    
}



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
    
    private JTextField textAmount;
    private JTextField textInterestRate;
    private JTextField textPeriod;
    
    
    public COFrame(){
        
        // Set the initial size of the frame.
        this.setBounds(100, 100, 500, 500);

        // Create the container to add the UI elements to and set the layoutmanager.
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        
        // Add a label and text box referring to the Amount to be input by the user.
        JLabel lblAmount = new JLabel();
        lblAmount.setText("Amount");
        lblAmount.setPreferredSize(new Dimension(100, 30));
        c.add(lblAmount);
        textAmount = new JTextField();
        textAmount.setPreferredSize(new Dimension(100, 30));
        textAmount.setText("100000");
        c.add(textAmount);
        c.add(newSpacer(this));

        // Add a label and text box referring to the Interest Rate to be input by the user.
        JLabel lblInterestRate = new JLabel();
        lblInterestRate.setText("Interest Rate");
        lblInterestRate.setPreferredSize(new Dimension(100, 30));
        c.add(lblInterestRate);
        textInterestRate = new JTextField();
        textInterestRate.setPreferredSize(new Dimension(100, 30));
        textInterestRate.setText("5");
        c.add(textInterestRate);
        c.add(newSpacer(this));

        // Add a label and text box referring to the Period to be input by the user.
        JLabel lblPeriod = new JLabel();
        lblPeriod.setText("Period");
        lblPeriod.setPreferredSize(new Dimension(100, 30));
        c.add(lblPeriod);
        textPeriod = new JTextField();
        textPeriod.setPreferredSize(new Dimension(100, 30));
        textPeriod.setText("5");
        c.add(textPeriod);
        c.add(newSpacer(this));

        
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
        
                
        period = Integer.parseInt(textPeriod.getText()) ;
        amount = Integer.parseInt(textAmount.getText());
        interestRate = Integer.parseInt(textInterestRate.getText());
        interestRate = interestRate/100.00;
        
        if(button.getText().equals("Future Value")){
        result = COCalculations.calculateFutureValue(amount, period, interestRate);
        System.out.println(result);
        }
    
        if(button.getText().equals("Present Value")){
        result = COCalculations.calculatePresentValue(amount, period, interestRate);
        System.out.println(result);
        }
    }
    
    // Creates a blank JLabel to act as a spacer for the UI to help the flowlayout
    // present it's components in a more aesthetically pleasing manner.
    private static JLabel newSpacer(JFrame frame){  
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension((int)(frame.getBounds().getWidth()), 30));
        return spacer;
    }
    
    
}

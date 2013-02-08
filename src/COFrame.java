
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chrisoxley
 */
public class COFrame extends JFrame implements ActionListener {

    private int period;
    private double amount;
    private double interestRate;
    private double result;
    private JTextField textAmount;
    private JTextField textInterestRate;
    private JTextField textPeriod;

    public COFrame() {

        // Set the initial size of the frame.
        this.setBounds(100, 100, 500, 500);
        this.setTitle("CO2002 Assignment 1 (Feb 2013)");
        
        // Create the container to add the UI elements to and set the layoutmanager.
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());

        JLabel lblTitle = new JLabel();
        lblTitle.setText("Future Value and Present Value Calculator by CO106");
        c.add(lblTitle);
        c.add(newSpacer(this));
        
        // Add a label and text box referring to the Amount to be input by the user.
        JLabel lblAmount = new JLabel();
        lblAmount.setText("Amount (£)");
        lblAmount.setPreferredSize(new Dimension(150, 30));
        c.add(lblAmount);
        textAmount = new JTextField();
        textAmount.setPreferredSize(new Dimension(100, 30));
        textAmount.setText("100000"); //Default amount
        c.add(textAmount);
        c.add(newSpacer(this));

        // Add a label and text box referring to the Interest Rate to be input by the user.
        JLabel lblInterestRate = new JLabel();
        lblInterestRate.setText("Interest Rate (%)");
        lblInterestRate.setPreferredSize(new Dimension(150, 30));
        c.add(lblInterestRate);
        textInterestRate = new JTextField();
        textInterestRate.setPreferredSize(new Dimension(100, 30));
        textInterestRate.setText("5"); //Default amount
        c.add(textInterestRate);
        c.add(newSpacer(this));

        // Add a label and text box referring to the Period to be input by the user.
        JLabel lblPeriod = new JLabel();
        lblPeriod.setText("Period (Years)");
        lblPeriod.setPreferredSize(new Dimension(150, 30));
        c.add(lblPeriod);
        textPeriod = new JTextField();
        textPeriod.setPreferredSize(new Dimension(100, 30));
        textPeriod.setText("5"); //Default amount
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

        c.add(newSpacer(this));



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();

        boolean allInputsValid = true;

        // Convert the input text for "Period" to a float and display a dialog
        // box if the conversion fails.
        try {
            period = Integer.parseInt(textPeriod.getText());
        } catch (NumberFormatException ex) {
            allInputsValid = false;
            JOptionPane.showMessageDialog(this, "Period is not a valid Number. Please Enter a Valid Number i.e. Whole Years");
        }

        // Convert the input text for "Amount" to a float and display a dialog
        // box if the conversion fails.
        try {
            amount = Float.parseFloat(textAmount.getText());
        } catch (NumberFormatException ex) {
            allInputsValid = false;
            JOptionPane.showMessageDialog(this, "Amount is not a valid Number. Please Enter a Valid Number");
        }

        // Convert the input text for "Interest Rate" to a float and display a dialog
        // box if the conversion fails.
        // We also need to divide the whole number input by 100 to be able to perform the correct
        // calculation. i.e. input of 5 needs to be changed to 0.05 for the formulas to work.
        try {
            interestRate = Float.parseFloat(textInterestRate.getText());
            interestRate = interestRate / 100.00;

        } catch (NumberFormatException ex) {
            allInputsValid = false;
            JOptionPane.showMessageDialog(this, "Interest rate is not a valid Number. Please Enter a Valid Number");
        }


        // Boundary check inputs if the inputs are valid up to now.
        if (allInputsValid) {
            if (amount <= 0 || period <= 0 || interestRate <= 0) {
                allInputsValid = false;
                JOptionPane.showMessageDialog(this, "All values must be larger than 0.");
            }
            if (interestRate > 100) {
                allInputsValid = false;
                JOptionPane.showMessageDialog(this, "Interest must be less than 100");
            }
        }

        // Only if all inputs are valid should we perform calculations.
        if (allInputsValid) {
            
            // Test for the button pressed.
            if (button.getText().equals("Future Value")) {
                                
                // Calculate the result.
                result = COCalculations.calculateFutureValue(amount, period, interestRate);
                
                // Set the column headers and the data for each column. We round all decimal values to 
                // 2 decimal places when displaying.
                String[] columnNames = {"No. of Years", "Amount", "Annual Interest", "Future Value"};
                Object[][] data = {{period,
                        new DecimalFormat("#.##").format(amount),
                        new DecimalFormat("#.##").format(interestRate * 100)+"%",
                        new DecimalFormat("#.##").format(result)}};
                JTable table = new JTable(data, columnNames);
                addTable(table, this);

            }
            if (button.getText().equals("Present Value")) {

                // Calculate the result.
                result = COCalculations.calculatePresentValue(amount, period, interestRate);
                
                // Set the column headers and the data for each column. We round all decimal values to 
                // 2 decimal places when displaying.
                String[] columnNames = {"No. of Years", "Amount", "Annual Interest", "Present Value"};
                Object[][] data = {{period,
                        new DecimalFormat("#.##").format(amount),
                        new DecimalFormat("#.##").format(interestRate * 100)+"%",
                        new DecimalFormat("#.##").format(result)}};
                JTable table = new JTable(data, columnNames);
                addTable(table, this);
            }
        }
    }
    
    
    // Static method to add a table to a given View.
    private static void addTable(JTable t, COFrame frame) {

        // Go through the components on the table, if one is a Container since we
        // know the structure of the UI, we know it is the table. We can then remove
        // it from the UI to be able to add another in it's place.
        Container con = frame.getContentPane();
        for (Component c : con.getComponents()) {
            if (c.getClass() == Container.class) {
                frame.getContentPane().remove(c);
            }
        }

        //Create a new container to hold the table.
        Container tableContainer = new Container();
        tableContainer.add(t);
        tableContainer.setLayout(new BorderLayout());

        // Iterate over each column and set it's size.
        TableColumn col;
        for (int i = 0; i < t.getColumnCount(); i++) {
            col = t.getColumnModel().getColumn(i);
            col.setPreferredWidth(120);
        }

        // Add the table to the custom container and add the container to the frame.
        tableContainer.add(t.getTableHeader(), BorderLayout.PAGE_START);
        tableContainer.add(t, BorderLayout.CENTER);
        con.add(tableContainer);
        
        // Force the view to refresh.
        frame.setVisible(true);
    }

    // Creates a blank JLabel to act as a spacer for the UI to help the flowlayout
    // present it's components in a more aesthetically pleasing manner.
    private static JLabel newSpacer(JFrame frame) {
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension((int) (frame.getBounds().getWidth()), 30));
        return spacer;
    }
}

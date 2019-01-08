package saldao8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TransactionDialog extends JDialog implements ActionListener
{
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 200;

    private JLabel amountLabel = new JLabel("Amount: ");
    private JTextField amountTextField = new JTextField(10);
    private JLabel accountIdLabel = new JLabel("Account ID: ");
    private JTextField accountIdTextField = new JTextField(10);
    JPanel inputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton actionButton = new JButton("Deposit");
    JButton abortButton = new JButton("Abort");
    
    private OverviewWindow ow;
    int accountId;
    String personalIdentityNumber;
    int action;

    public TransactionDialog(int action, OverviewWindow ow, String personalIdentityNumber, int accountId)
    {
        super(ow, "Deposit", true);
        
        this.ow = ow;
        this.accountId = accountId;
        this.personalIdentityNumber = personalIdentityNumber; 
        this.action = action;
        
        if(action == ow.WITHDRAW)
        {
            setTitle("Withdraw");
            actionButton.setText("Withdraw");
        }
        
        createWindowContent();
        
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        //???.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createWindowContent()
    {
        inputPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
      
        inputPanel.setLayout(new GridLayout(2,  2));
        inputPanel.add(amountLabel);
        inputPanel.add(amountTextField);
        inputPanel.add(accountIdLabel);
        inputPanel.add(accountIdTextField);
        accountIdTextField.setEditable(false);
        accountIdTextField.setText(Integer.toString(accountId));
        this.add(inputPanel);
        
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        buttonPanel.add(actionButton);
        buttonPanel.add(abortButton);
        abortButton.addActionListener(this);
        actionButton.addActionListener(this);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == actionButton)
        {
            if(action == ow.DEPOSIT)
            {
                if(ow.deposit(personalIdentityNumber, accountId, Double.parseDouble(amountTextField.getText())))
                {
                    this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Transaction could not be performed!", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                if(ow.withdraw(personalIdentityNumber, accountId, Double.parseDouble(amountTextField.getText())))
                {
                    this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Transaction could not be performed!", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            //JOptionPane.showMessageDialog(null, "Could not deposit amount! \n (Customer or account could not be found)", "Alert", JOptionPane.ERROR_MESSAGE);
        }
        
        if(e.getSource() == abortButton)
        {
            //https://stackoverflow.com/a/6970105
            this.dispose();     
        }
    }
}


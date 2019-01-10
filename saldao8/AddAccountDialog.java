package saldao8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddAccountDialog extends JDialog implements ActionListener
{
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 250;

    private JTextField personalIdentityTextField = new JTextField(20);
    private JButton createButton = new JButton("Create");
    private JButton abortButton = new JButton("Abort");
    private JComboBox accountList;
    
    private OverviewLogicWin overviewLogicWin;
    private String personalIdentityNumber;

    public AddAccountDialog(OverviewLogicWin overviewLogicWin, String personalIdentityNumber)
    {
        super(overviewLogicWin, "New Account", true);
        
        this.overviewLogicWin = overviewLogicWin;
        this.personalIdentityNumber = personalIdentityNumber;
        
        createWindowContent();
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createWindowContent()
    {
        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));
        
        String[] options = { "Savings account", "Credit Account"};
        accountList = new JComboBox(options);
        //Create the combo box, select item at index 1.
        //Indices start at 0, so 4 specifies the pig.
        accountList.setSelectedIndex(0);
        accountList.addActionListener(this);
      
        JLabel typeLabel = new JLabel("Account type: ");
        JLabel personalIdentityLabel = new JLabel("Personal identity number: ");

        inputPanel.setLayout(new GridLayout(2,  2));
        inputPanel.add(typeLabel);
        inputPanel.add(accountList);
        inputPanel.add(personalIdentityLabel);
        inputPanel.add(personalIdentityTextField);
        personalIdentityTextField.setEditable(false);
        personalIdentityTextField.setText(personalIdentityNumber);
        this.add(inputPanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        buttonPanel.add(createButton);
        buttonPanel.add(abortButton);
        abortButton.addActionListener(this);
        createButton.addActionListener(this);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == createButton)
        {
            if(accountList.getSelectedIndex() == 0)
            {
                if(!overviewLogicWin.createSavingsAccount(personalIdentityTextField.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Customer does not exist!", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    this.dispose();
                }
            }
            
            if(accountList.getSelectedIndex() == 1)
            {
                if(!overviewLogicWin.createCreditAccount(personalIdentityTextField.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Customer does not exist!", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    this.dispose();
                }
            }
        }
        
        if(e.getSource() == abortButton)
        {
            //https://stackoverflow.com/a/6970105
            this.dispose();     
        }
    }
}


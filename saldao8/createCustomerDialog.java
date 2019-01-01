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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class createCustomerDialog extends JDialog implements ActionListener
{
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 250;

    private JLabel firstNameLabel = new JLabel("First name: ");
    private JLabel lastNameLabel = new JLabel("Last name: ");
    private JLabel personalIdentityLabel = new JLabel("Personal identity number: ");
    private JTextField firstNameTextField = new JTextField(20);
    private JTextField lastNameTextField = new JTextField(20);
    private JTextField personalIdentityTextField= new JTextField(20);
    JPanel inputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton createButton = new JButton("Create");
    JButton abortButton = new JButton("Abort");

    public createCustomerDialog(BankLogic bankLogic, JFrame parentFrame, String frameTitle, boolean modality)
    {
        super(parentFrame, frameTitle, modality);
        
        createWindowContent();
        
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pack();
        //setLocationRelativeTo(null);
        //setResizable(false);
        //setTitle("New Customer");
        
        //this.setVisible(true);
        
//        customerFirstNameTextField.setText("");
//        customerLastNameTextField.setText("");
//        customerPNRTextField.setText("");
    }

    private void createWindowContent()
    {
        inputPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        inputPanel.setLayout(new GridLayout(3,  2));
        inputPanel.add(firstNameLabel);
        inputPanel.add(firstNameTextField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameTextField);
        inputPanel.add(personalIdentityLabel);
        inputPanel.add(personalIdentityTextField);
        this.add(inputPanel);
        
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        buttonPanel.add(createButton);
        buttonPanel.add(abortButton);
        abortButton.addActionListener(this);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == createButton)
        {
            
        }
        
        if(e.getSource() == abortButton)
        {
            //https://stackoverflow.com/a/6970105
            this.dispose();     
        }
    }
    
    /*public static void main(String[] args)
    {
        JFrame frame = new createCustomerWin();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }*/

}

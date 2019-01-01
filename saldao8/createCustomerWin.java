package saldao8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class createCustomerWin extends JFrame
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

    public createCustomerWin()
    {
        createWindowContent();
        
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("New Customer");
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
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new createCustomerWin();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

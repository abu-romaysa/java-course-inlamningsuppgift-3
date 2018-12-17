package saldao8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MenuItem;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class MainWindow extends JFrame
{
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 100;
    private JButton overviewButton = new JButton("Overview");
    private JButton handleCustomerButton = new JButton("Handle Customer");
    private JButton newCustomerButton = new JButton("New Customer");
    private JButton newAccountButton = new JButton("New Account");
    private JButton closeAccountButton = new JButton("Close Account");
    private JButton depositButton = new JButton("Deposit");
    private JButton withdrawButton = new JButton("Withdraw");
    private JButton transactionsButton = new JButton("Transactions");
    private JLabel label = new JLabel("QUICK ACCESS VIEW");

    public MainWindow()
    {
        createComponents();
        this.setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);

    }

    private void createComponents()
    {
        createMenu();
        createMainWindowContent();     
    }
    
    private void createMainWindowContent()
    {
        JPanel mainPanel = new JPanel();
        JPanel quickOptionsPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        //quickOptionsPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        /*quickOptionsPanel.setLayout(new GridLayout(3, 3, 5, 5));
        titlePanel.add(label);
        quickOptionsPanel.add(button1);
        quickOptionsPanel.add(button2);
        quickOptionsPanel.add(button3);
        quickOptionsPanel.add(button4);
        quickOptionsPanel.add(button5);
        quickOptionsPanel.add(button6);
        quickOptionsPanel.setBorder(new EmptyBorder(new Insets(45, 70, 45, 70)));*/
        
        
        label.setFont(new Font("Serif", Font.BOLD, 20)); //https://stackoverflow.com/a/29148550
        titlePanel.add(label);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        
        JPanel overviewOnePanel = new JPanel();
        JPanel overviewTwoPanel = new JPanel();
        JPanel overviewThreePanel = new JPanel();
        JPanel overviewFourPanel = new JPanel();
        
        overviewOnePanel.setLayout(new FlowLayout());
        overviewTwoPanel.setLayout(new FlowLayout());
        overviewThreePanel.setLayout(new FlowLayout());
        overviewFourPanel.setLayout(new FlowLayout());        
        
        overviewButton.setPreferredSize(new Dimension(150,50));
        handleCustomerButton.setPreferredSize(new Dimension(150,50));
        newCustomerButton.setPreferredSize(new Dimension(150,50));
        newAccountButton.setPreferredSize(new Dimension(150,50));
        closeAccountButton.setPreferredSize(new Dimension(150,50));
        depositButton.setPreferredSize(new Dimension(150,50));
        withdrawButton.setPreferredSize(new Dimension(150,50));
        transactionsButton.setPreferredSize(new Dimension(150,50));
        
        overviewOnePanel.add(overviewButton);
        overviewTwoPanel.add(handleCustomerButton);
        overviewTwoPanel.add(newCustomerButton);
        overviewThreePanel.add(newAccountButton);
        overviewThreePanel.add(closeAccountButton);
        overviewFourPanel.add(depositButton);
        overviewFourPanel.add(withdrawButton);
        overviewFourPanel.add(transactionsButton);
        
        TitledBorder overviewOnePanelTitle = BorderFactory.createTitledBorder("Overview");
        TitledBorder overviewTwoPanelTitle = BorderFactory.createTitledBorder("Customer");
        TitledBorder overviewThreePanelTitle = BorderFactory.createTitledBorder("Account");
        TitledBorder overviewFourPanelTitle = BorderFactory.createTitledBorder("Actions");
        overviewOnePanel.setBorder(overviewOnePanelTitle);
        overviewTwoPanel.setBorder(overviewTwoPanelTitle);
        overviewThreePanel.setBorder(overviewThreePanelTitle);
        overviewFourPanel.setBorder(overviewFourPanelTitle);
        
        quickOptionsPanel.setLayout(new BoxLayout(quickOptionsPanel, BoxLayout.Y_AXIS));
        quickOptionsPanel.add(overviewOnePanel);
        quickOptionsPanel.add(overviewTwoPanel);
        quickOptionsPanel.add(overviewThreePanel);
        quickOptionsPanel.add(overviewFourPanel);
        //quickOptionsPanel.setBorder(new EmptyBorder(new Insets(45, 70, 45, 70)));
        
        //https://www.daniweb.com/posts/jump/1820204 // https://www.daniweb.com/programming/software-development/threads/425665/add-space-between-components-and-jframe
        //https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        quickOptionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 50, 50));
        
        mainPanel.add(titlePanel);
        mainPanel.add(quickOptionsPanel);
                
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel);
    }
    
    private void createMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("FILE");        
        JMenuItem loadMenuItem = new JMenuItem("Load");
        JMenuItem SaveMenuItem = new JMenuItem("Save");
        fileMenu.add(loadMenuItem);
        fileMenu.add(SaveMenuItem);
        
        JMenu customerMenu = new JMenu("CUSTOMER");
        JMenuItem createCustomerMenuItem = new JMenuItem("Create Customer");
        JMenuItem createAccountMenuItem = new JMenuItem("Change Customer Name");
        JMenuItem deleteAccountMenuItem = new JMenuItem("Delete Customer");
        customerMenu.add(createCustomerMenuItem);
        customerMenu.add(createAccountMenuItem);
        customerMenu.add(deleteAccountMenuItem);
        
        JMenu accountMenu = new JMenu("ACCOUNT");
        JMenuItem createSavingsAccountMenuItem = new JMenuItem("Create Savings Account");
        JMenuItem createCreditAccountMenuItem = new JMenuItem("Create Credit Account");
        JMenuItem closeAccountMenuItem = new JMenuItem("Close Account");
        accountMenu.add(createSavingsAccountMenuItem);
        accountMenu.add(createCreditAccountMenuItem);
        accountMenu.add(closeAccountMenuItem);        

        JMenu HelpMenu = new JMenu("HELP");
        
        menuBar.add(fileMenu);
        menuBar.add(customerMenu);
        menuBar.add(accountMenu);
        menuBar.add(HelpMenu);
        this.add(menuBar, BorderLayout.NORTH);        
    }

    public static void main(String[] args)
    {
        JFrame frame = new MainWindow();
        frame.setTitle("Saldao-8 Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
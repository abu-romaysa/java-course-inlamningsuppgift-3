package saldao8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;

    private JButton overviewButton = new JButton("Overview");
    private JButton handleCustomerButton = new JButton("Handle Customer");
    private JButton newCustomerButton = new JButton("New Customer");
    private JButton newAccountButton = new JButton("New Account");
    private JButton closeAccountButton = new JButton("Close Account");
    private JButton depositButton = new JButton("Deposit");
    private JButton withdrawButton = new JButton("Withdraw");
    private JButton transactionsButton = new JButton("Transactions");
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();

    private JLabel windowTitleLabel = new JLabel("QUICK ACCESS VIEW");

    private JPanel mainPanel = new JPanel();
    private JPanel quickOptionsPanel = new JPanel();
    private JPanel titlePanel = new JPanel(); 

    public MainWindow()
    {
        buttonList.add(overviewButton);
        buttonList.add(handleCustomerButton);
        buttonList.add(newCustomerButton);
        buttonList.add(newAccountButton);
        buttonList.add(closeAccountButton);
        buttonList.add(depositButton);
        buttonList.add(withdrawButton);
        buttonList.add(transactionsButton);
        
        setButtonSizes();
        addButtonListeners();
        
        createComponents();
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Saldao-8 Banking System");
    }

    private void createComponents()
    {
        createMenu();
        createMainWindowContent();     
    }
    
    private void createMainWindowContent()
    {        
        windowTitleLabel.setFont(new Font("Serif", Font.BOLD, 20)); //https://stackoverflow.com/a/29148550
        titlePanel.add(windowTitleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        
        JPanel overviewOnePanel = new JPanel();
        JPanel overviewTwoPanel = new JPanel();
        JPanel overviewThreePanel = new JPanel();
        JPanel overviewFourPanel = new JPanel();
        
        // om jag lägger i array så tar add lika många rader sedan
        overviewOnePanel.setLayout(new FlowLayout());
        overviewTwoPanel.setLayout(new FlowLayout());
        overviewThreePanel.setLayout(new FlowLayout());
        overviewFourPanel.setLayout(new FlowLayout());
        
        overviewOnePanel.add(overviewButton);
        overviewTwoPanel.add(handleCustomerButton);
        overviewTwoPanel.add(newCustomerButton);
        overviewThreePanel.add(newAccountButton);
        overviewThreePanel.add(closeAccountButton);
        overviewFourPanel.add(depositButton);
        overviewFourPanel.add(withdrawButton);
        overviewFourPanel.add(transactionsButton);

        overviewOnePanel.setBorder(BorderFactory.createTitledBorder("Overview"));
        overviewTwoPanel.setBorder(BorderFactory.createTitledBorder("Customer"));
        overviewThreePanel.setBorder(BorderFactory.createTitledBorder("Account"));
        overviewFourPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        
        quickOptionsPanel.setLayout(new BoxLayout(quickOptionsPanel, BoxLayout.Y_AXIS));
        quickOptionsPanel.add(overviewOnePanel);
        quickOptionsPanel.add(overviewTwoPanel);
        quickOptionsPanel.add(overviewThreePanel);
        quickOptionsPanel.add(overviewFourPanel);
        
        //https://www.daniweb.com/posts/jump/1820204 // https://www.daniweb.com/programming/software-development/threads/425665/add-space-between-components-and-jframe
        //https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        quickOptionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 50, 50));
        
        mainPanel.add(titlePanel);
        mainPanel.add(quickOptionsPanel);
                
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel);
    }
    
    private void setButtonSizes()
    {
        for(JButton jButton : buttonList)
        {
            jButton.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));
        }
    }
    
    private void addButtonListeners()
    {
        ActionListener buttonListener = new ButtonListener();
        for(JButton jButton : buttonList)
        {
            jButton.addActionListener(buttonListener);
        }
    }
    
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == overviewButton)
                System.out.println("Du har klickat på knappen!");
            else if(e.getSource() == handleCustomerButton)
                System.out.println("ROMAYSA");
            else if(e.getSource() == newCustomerButton)
                System.out.println("ROMAYSA");
            else if(e.getSource() == newAccountButton)
                System.out.println("ROMAYSA");
            else if(e.getSource() == closeAccountButton)
                System.out.println("ROMAYSA");
            else if(e.getSource() == depositButton)
                System.out.println("ROMAYSA");
            else if(e.getSource() == withdrawButton)
                System.out.println("ROMAYSA");
            else if(e.getSource() == transactionsButton);
            System.out.println("ROMAYSA");
        }
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
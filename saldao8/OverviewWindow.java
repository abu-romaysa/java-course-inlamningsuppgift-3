package saldao8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class OverviewWindow extends JFrame
{
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    
    private JLabel windowTitleLabel = new JLabel("SYSTEM OVERVIEW");

    private JPanel mainPanel = new JPanel();
    private JPanel customersPanel = new JPanel();
    private JPanel accountsPanel = new JPanel();
    private JPanel titlePanel = new JPanel();
    
    JList customerList = new JList();
    JList accountList = new JList();

    public OverviewWindow()
    {
        createMenu();
        
        // inspiration https://www.google.se/search?q=java+gui+for+simple+bank+system&rlz=1C1GCEU_svSE820SE821&source=lnms&tbm=isch&sa=X&ved=0ahUKEwj0pcTDqJbfAhWFCCwKHU3UC_kQ_AUIDigB&biw=1187&bih=618#imgrc=pcaBZZeDE5ShtM:
        createWindowContent();
        
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Saldao-8 Banking System");
    }
    
    private void createWindowContent()
    {
        windowTitleLabel.setFont(new Font("Serif", Font.BOLD, 20)); //https://stackoverflow.com/a/29148550
        titlePanel.add(windowTitleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        customersPanel.setLayout(new GridLayout(2, 2, 20, 5));
        createCustomerListSection();
        createAccountListSection();
        createCustomerDetailSection();
        createAccountDetailSection();
        //JLabel l2= new JLabel("select the day of the week"); 
        //customersPanel.add(l2);
        customersPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
                
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(titlePanel);
        mainPanel.add(customersPanel);
        
        this.add(mainPanel);
    }
    
    private void createCustomerListSection()
    {
      //String array to store weekdays 
        String week[]= { "Monday","Tuesday","Wednesday", 
                         "Thursday","Friday","Saturday","Sunday"}; 
        customerList.setListData(week);
        // http://www.java2s.com/Tutorial/Java/0240__Swing/SettingtheSelectionModeofaJListComponent.htm
        customerList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        
        JPanel customerListPanel = new JPanel();
        //https://stackoverflow.com/a/20359885
        customerListPanel.setLayout(new BorderLayout());
        customerListPanel.add(customerList);
        
        TitledBorder customerListTitle = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "List of Customers");
        customerListTitle.setTitlePosition(TitledBorder.ABOVE_TOP);
        customerListPanel.setBorder(customerListTitle);
        
        customersPanel.add(customerListPanel);
    }
    
    private void createAccountListSection()
    {  
        //String array to store weekdays 
        String week[]= { "Monday","Tuesday","Wednesday", 
                         "Thursday","Friday","Saturday","Sunday"}; 
        accountList.setListData(week);
        accountList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        JPanel accountListPanel = new JPanel();
        accountListPanel.setLayout(new BorderLayout());
        accountListPanel.add(accountList);

        TitledBorder accountListTitle = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "List of Accounts");
        accountListTitle.setTitlePosition(TitledBorder.ABOVE_TOP);
        accountListPanel.setBorder(accountListTitle);

        customersPanel.add(accountListPanel);
    }
    
    private void createCustomerDetailSection()
    {
        JPanel customerDetailsPanel = new JPanel();
        
        JPanel customerDetailsTextPanel = new JPanel();
        customerDetailsTextPanel.setLayout(new GridLayout(3, 2, 2, 2));
        JTextField customerFirstNameTextField = new JTextField("name", 10);
        JTextField customerLastNameTextField = new JTextField("last", 10);
        JTextField customerPNRTextField = new JTextField("pnr", 10);
        JLabel firstNameLabel = new JLabel("First Name");
        JLabel lastNameLabel = new JLabel("Last Name");
        customerDetailsTextPanel.add(firstNameLabel);
        customerDetailsTextPanel.add(lastNameLabel);
        customerDetailsTextPanel.add(customerFirstNameTextField);
        customerDetailsTextPanel.add(customerLastNameTextField);
        customerDetailsTextPanel.add(customerPNRTextField);
        
        JPanel customerDetailsButtonsPanel = new JPanel();
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        customerDetailsButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        customerDetailsButtonsPanel.add(updateButton);
        customerDetailsButtonsPanel.add(deleteButton);
        customerDetailsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        
        customerDetailsPanel.setLayout(new BorderLayout());
        customerDetailsPanel.add(customerDetailsTextPanel);
        customerDetailsPanel.add(customerDetailsButtonsPanel, BorderLayout.SOUTH);
        TitledBorder customerDetailTitle = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Customer Information");
        customerDetailTitle.setTitlePosition(TitledBorder.ABOVE_TOP);
        customerDetailsPanel.setBorder(customerDetailTitle);
        
        customersPanel.add(customerDetailsPanel);
    }
    
    private void createAccountDetailSection()
    {
        JPanel accountDetailsPanel = new JPanel();
        
        JPanel accountDetailsTextPanel = new JPanel();
        accountDetailsTextPanel.setLayout(new GridLayout(4, 2, 2, 2));
        JTextField accountTypeTextField = new JTextField("type", 10);
        JTextField accountInterestTextField = new JTextField("interest", 10);
        JTextField accountBalanceTextField = new JTextField("balance", 10);
        JTextField accountCreditTextField = new JTextField("credit", 10);
        accountCreditTextField.setVisible(false);
        JLabel typeLabel = new JLabel("Type");
        JLabel interestLabel = new JLabel("Interest");
        JLabel balanceLabel = new JLabel("Balance");
        JLabel creditLabel = new JLabel("Credit");
        creditLabel.setVisible(false);

        accountDetailsTextPanel.add(typeLabel);
        accountDetailsTextPanel.add(interestLabel);
        accountDetailsTextPanel.add(accountTypeTextField);
        accountDetailsTextPanel.add(accountInterestTextField);
        accountDetailsTextPanel.add(balanceLabel);
        accountDetailsTextPanel.add(creditLabel);
        accountDetailsTextPanel.add(accountBalanceTextField);
        accountDetailsTextPanel.add(accountCreditTextField);
        
        JPanel accountDetailsButtonsPanel = new JPanel();
        JButton addButton = new JButton("Add Account");
        JButton deleteButton = new JButton("Delete");
        JButton transactionButton = new JButton("View Transactions");
        accountDetailsButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        accountDetailsButtonsPanel.add(addButton);
        accountDetailsButtonsPanel.add(deleteButton);
        accountDetailsButtonsPanel.add(transactionButton);
        accountDetailsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        accountDetailsPanel.setLayout(new BorderLayout());
        accountDetailsPanel.add(accountDetailsTextPanel);
        accountDetailsPanel.add(accountDetailsButtonsPanel, BorderLayout.SOUTH);
        TitledBorder accountDetailTitle = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Account Information");
        accountDetailTitle.setTitlePosition(TitledBorder.ABOVE_TOP);
        accountDetailsPanel.setBorder(accountDetailTitle);
        
        customersPanel.add(accountDetailsPanel);
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
        JFrame frame = new OverviewWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

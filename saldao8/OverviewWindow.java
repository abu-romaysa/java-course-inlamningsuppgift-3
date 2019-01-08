package saldao8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import saldao8.AccountTypes.AccountType;

public class OverviewWindow extends JFrame implements AccountTypes
{
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;
    
    private JLabel windowTitleLabel = new JLabel("SYSTEM OVERVIEW");

    private JPanel mainPanel = new JPanel();
    private JPanel customersPanel = new JPanel();
    private JPanel titlePanel = new JPanel();

    private JButton updateButton = new JButton("Update");
    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete");
    private JButton clearButton = new JButton("Clear");

    private JTextField customerFirstNameTextField = new JTextField("", 10);
    private JTextField customerLastNameTextField = new JTextField("", 10);
    private JTextField customerPNRTextField = new JTextField("", 10);
    
    private JList customerList = new JList();
    private JList accountList = new JList();

    JButton addAccountButton = new JButton("Add Account");
    JButton deleteAccountButton = new JButton("Delete");
    JButton depositButton = new JButton("Deposit");
    JButton widthdrawButton = new JButton("Widthdraw");
    JButton transactionButton = new JButton("View Transactions");
    JTextField accountTypeTextField = new JTextField(10);
    JTextField accountInterestTextField = new JTextField(10);
    JTextField accountBalanceTextField = new JTextField(10);
    JTextField accountCreditTextField = new JTextField(10);
    JLabel creditLabel = new JLabel("Credit");
    
    private OverviewWindow thisWin;
    
    private BankLogic bankLogic = new BankLogic();
    
    public static final int DEPOSIT = 1;
    public static final int WITHDRAW = 2;

    public OverviewWindow()
    {
        thisWin = this;
        
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
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        customersPanel.setLayout(new GridLayout(2, 2, 20, 5));
        createCustomerListSection();
        createAccountListSection();
        createCustomerDetailSection();
        createAccountDetailSection();
        customersPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(customersPanel);
        
        this.add(mainPanel);
        
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void createCustomerListSection()
    {
        // http://www.java2s.com/Tutorial/Java/0240__Swing/SettingtheSelectionModeofaJListComponent.htm
        customerList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        customerList.setSelectedIndex(0);
        customerList.addListSelectionListener(new ListSelectionHandler());
        
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
        accountList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        accountList.addListSelectionListener(new AccountsListSelectionHandler());

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
        JLabel firstNameLabel = new JLabel("First Name");
        JLabel lastNameLabel = new JLabel("Last Name");
        customerDetailsTextPanel.add(firstNameLabel);
        customerDetailsTextPanel.add(lastNameLabel);
        customerDetailsTextPanel.add(customerFirstNameTextField);
        customerDetailsTextPanel.add(customerLastNameTextField);
        customerDetailsTextPanel.add(customerPNRTextField);
        
        if(!customerList.isSelectionEmpty())
        {
            String selectedListCustomer = (String) customerList.getSelectedValue();
            String[] selectedCustomer = selectedListCustomer.split(" "); // simple for simple case
            customerFirstNameTextField.setText(selectedCustomer[0]);
            customerLastNameTextField.setText(selectedCustomer[1]);
            customerPNRTextField.setText(selectedCustomer[2]);
        }
        
        JPanel customerDetailsButtonsPanel = new JPanel();
        customerDetailsButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        customerDetailsButtonsPanel.add(updateButton);
        customerDetailsButtonsPanel.add(addButton);
        customerDetailsButtonsPanel.add(deleteButton);
        customerDetailsButtonsPanel.add(clearButton);
        ActionListener buttonListener = new ButtonListener();
        updateButton.addActionListener(buttonListener);
        addButton.addActionListener(buttonListener);
        deleteButton.addActionListener(buttonListener);
        clearButton.addActionListener(buttonListener);
        customerDetailsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
        
        customerDetailsPanel.setLayout(new BorderLayout());
        customerDetailsPanel.add(customerDetailsTextPanel);
        customerDetailsPanel.add(customerDetailsButtonsPanel, BorderLayout.SOUTH);
        TitledBorder customerDetailTitle = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Customer Information");
        customerDetailTitle.setTitlePosition(TitledBorder.ABOVE_TOP);
        customerDetailsPanel.setBorder(customerDetailTitle);
        
        customersPanel.add(customerDetailsPanel);
    }
    
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/events/ListSelectionDemoProject/src/events/ListSelectionDemo.java
    public class ListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            int idx = customerList.getSelectedIndex();
            if(idx != -1)
            {
                String selectedListCustomer = (String) customerList.getSelectedValue();
                String[] selectedCustomer = selectedListCustomer.split(" "); // simple for simple case
                customerFirstNameTextField.setText(selectedCustomer[0]);
                customerLastNameTextField.setText(selectedCustomer[1]);
                customerPNRTextField.setText(selectedCustomer[2]);
                
                accountList.setListData(bankLogic.getAccountIds(selectedCustomer[2]).toArray());
            }
        }
    }
    
    public class AccountsListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            int idx = accountList.getSelectedIndex();
            if(idx != -1)
            {
                int selectedListAccount = (int) accountList.getSelectedValue();
                
                String accountInfo = bankLogic.getAccount(selectedListAccount);
                if(accountInfo != null)
                {
                    String[] accountSubInfo = accountInfo.split(" ");
                    String accountType = accountSubInfo[2] + " " + accountSubInfo[3];
                    
                    accountBalanceTextField.setText(accountSubInfo[1]);
                    accountTypeTextField.setText(accountType);
                    accountInterestTextField.setText(accountSubInfo[4]);
                    
                    if(accountType.equals("Credit account"))
                    {
                        accountCreditTextField.setText(accountSubInfo[5]);
                        accountCreditTextField.setVisible(true);
                        creditLabel.setVisible(true);
                    }
                    else
                    {
                        accountCreditTextField.setVisible(false);
                        creditLabel.setVisible(false);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Customer does not exists", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                clearAccountTextFields();
            }
        }
    }

    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == addButton)
            {
                if(bankLogic.createCustomer(customerFirstNameTextField.getText(), 
                                            customerLastNameTextField.getText(), 
                                            customerPNRTextField.getText()))
                {
                    customerList.setListData(bankLogic.getAllCustomers().toArray());
                    clearTextFields();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Customer already exists", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(e.getSource() == updateButton)
            {
                int idx = customerList.getSelectedIndex();
                if(idx != -1)
                {
                    String firstName = customerFirstNameTextField.getText();
                    String lastName = customerLastNameTextField.getText();
                    String personalIdentityNumber = customerPNRTextField.getText();
                    bankLogic.changeCustomerName(firstName, lastName, personalIdentityNumber);
                    System.out.println(bankLogic.getAllCustomers());
                    customerList.setListData(bankLogic.getAllCustomers().toArray());
                    clearTextFields();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You have to mark a customer in the list!");
                }
            }

            if(e.getSource() == deleteButton)
            {
                int idx = customerList.getSelectedIndex();
                if(idx != -1)
                {
                    String selectedListCustomer = (String) customerList.getSelectedValue();
                    System.out.println(selectedListCustomer);
                    String[] selectedCustomer = selectedListCustomer.split(" "); // simple for simple case
                    String personalIdentityNumber = selectedCustomer[2];
                    bankLogic.deleteCustomer(personalIdentityNumber);
                    customerList.setListData(bankLogic.getAllCustomers().toArray());
                    clearTextFields();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You have to mark a customer in the list!");
                }
            }

            if(e.getSource() == clearButton)
                clearTextFields();
        }
    }
    
    private void clearTextFields()
    {
        customerFirstNameTextField.setText("");
        customerLastNameTextField.setText("");
        customerPNRTextField.setText("");
    }
    
    private void clearAccountTextFields()
    {
        accountTypeTextField.setText("");
        accountInterestTextField.setText("");
        accountBalanceTextField.setText("");
        accountCreditTextField.setText("");
    }

    private void createAccountDetailSection()
    {
        JPanel accountDetailsPanel = new JPanel();
        
        accountDetailsPanel.setLayout(new GridLayout(1, 2, 5, 0));
        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(8, 1));
        
        JLabel typeLabel = new JLabel("Type");
        JLabel interestLabel = new JLabel("Interest");
        JLabel balanceLabel = new JLabel("Balance");
        
        accountTypeTextField.setEditable(false);
        accountInterestTextField.setEditable(false);
        accountBalanceTextField.setEditable(false);
        accountCreditTextField.setEditable(false);
        accountCreditTextField.setVisible(false);
        creditLabel.setVisible(false);

        leftPanel.add(typeLabel);
        leftPanel.add(accountTypeTextField);
        leftPanel.add(balanceLabel);
        leftPanel.add(accountBalanceTextField);
        leftPanel.add(interestLabel);
        leftPanel.add(accountInterestTextField);
        leftPanel.add(creditLabel);
        leftPanel.add(accountCreditTextField);
        
        JPanel rightPanel = new JPanel();
        JLabel empty = new JLabel();
        
        rightPanel.setLayout(new GridLayout(7, 1, 0, 5));
        rightPanel.add(addAccountButton);
        rightPanel.add(deleteAccountButton);
        rightPanel.add(depositButton);
        rightPanel.add(widthdrawButton);
        rightPanel.add(transactionButton);
        rightPanel.add(empty);
        rightPanel.add(empty);
        rightPanel.add(empty);
        
        ActionListener accountButtonListener = new AccountButtonListener();
        addAccountButton.addActionListener(accountButtonListener);
        deleteAccountButton.addActionListener(accountButtonListener);
        depositButton.addActionListener(accountButtonListener);
        widthdrawButton.addActionListener(accountButtonListener);
        transactionButton.addActionListener(accountButtonListener);

        accountDetailsPanel.add(leftPanel);
        accountDetailsPanel.add(rightPanel);
        TitledBorder accountDetailTitle = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Account Information");
        accountDetailTitle.setTitlePosition(TitledBorder.ABOVE_TOP);
        accountDetailsPanel.setBorder(accountDetailTitle);
        
        customersPanel.add(accountDetailsPanel);
    }
    
    public class AccountButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == addAccountButton)
            {
                int idx = customerList.getSelectedIndex();
                if(idx != -1)
                {
                    String selectedListCustomer = (String) customerList.getSelectedValue();
                    String[] selectedCustomer = selectedListCustomer.split(" "); // simple for simple case

                    JDialog addAccountDialog = new AddAccountDialog(thisWin, selectedCustomer[2]);
                    addAccountDialog.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You have to mark a customer in the list!");
                }
            }
            
            if(e.getSource() == deleteAccountButton)
            {
                int account_idx = accountList.getSelectedIndex();
                int customer_idx = customerList.getSelectedIndex();

                if(account_idx != -1 && customer_idx != -1)
                {          
                    int selectedListAccount = (int) accountList.getSelectedValue();
                    
                    int selectedOption = JOptionPane.showConfirmDialog(
                            null, 
                            "Are you sure you want to delete account: " + selectedListAccount, 
                            "ATTENTION", 
                            JOptionPane.YES_NO_OPTION); 
                    
                    if (selectedOption == JOptionPane.YES_OPTION) {
                        String closedAccountInfo = bankLogic.closeAccount(selectedListAccount); // todo take back pnr?
                        System.out.println("delete Account: " + closedAccountInfo);
                        if(closedAccountInfo != null)
                        {
                            String selectedListCustomer = (String) customerList.getSelectedValue();
                            String[] selectedCustomer = selectedListCustomer.split(" "); // simple for simple case
                            accountList.setListData(bankLogic.getAccountIds(selectedCustomer[2]).toArray());
                            JOptionPane.showMessageDialog(null, "Deleted Account: \n" + closedAccountInfo);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Account does not exists", "Alert", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You have to mark a customer & an account in the list!");
                }
            }
            
            if(e.getSource() == depositButton || e.getSource() == widthdrawButton)
            {
                int account_idx = accountList.getSelectedIndex();
                int customer_idx = customerList.getSelectedIndex();

                if(account_idx != -1 && customer_idx != -1)
                {          
                    int selectedListAccount = (int) accountList.getSelectedValue();
                    String selectedListCustomer = (String) customerList.getSelectedValue();
                    String[] selectedCustomer = selectedListCustomer.split(" "); // simple for simple case
                    
                    int action = DEPOSIT;
                    if(e.getSource() == widthdrawButton)
                    {
                        action = WITHDRAW;
                    }
                    
                    JDialog depositDialog = new TransactionDialog(action, thisWin, selectedCustomer[2], selectedListAccount);
                    depositDialog.setVisible(true);
                    
                    String accountInfo = bankLogic.getAccount(selectedListAccount);
                    if(accountInfo != null)
                    {
                        String[] accountSubInfo = accountInfo.split(" ");
                        String accountType = accountSubInfo[2] + " " + accountSubInfo[3];
                        
                        accountBalanceTextField.setText(accountSubInfo[1]);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You have to mark a customer & an account in the list!");
                }
            }
                    
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
    
    public boolean createSavingsAccount(String personalIdentityNumber)
    {
        if(bankLogic.createSavingsAccount(personalIdentityNumber) != -1)
        {
            accountList.setListData(bankLogic.getAccountIds(personalIdentityNumber).toArray());
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public boolean createCreditAccount(String personalIdentityNumber)
    {
        if(bankLogic.createCreditAccount(personalIdentityNumber) != -1)
        {
            accountList.setListData(bankLogic.getAccountIds(personalIdentityNumber).toArray());
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public boolean deposit(String personalIdentityNumber, int accountId, double amount)
    {
        return bankLogic.deposit(personalIdentityNumber, accountId, amount);
    }
    
    public boolean withdraw(String personalIdentityNumber, int accountId, double amount)
    {
        return bankLogic.withdraw(personalIdentityNumber, accountId, amount);
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new OverviewWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

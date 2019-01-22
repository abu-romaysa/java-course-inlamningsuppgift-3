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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class TransactionHistoryDialog extends JDialog implements ActionListener
{
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 200;

    private JPanel accountPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    
    private OverviewLogicWin overviewLogicWin;
    private int accountId;
    private String personalIdentityNumber;

    public TransactionHistoryDialog(OverviewLogicWin overviewLogicWin, String personalIdentityNumber, int accountId)
    {
        super(overviewLogicWin, "Transaction History", true);
        
        this.overviewLogicWin = overviewLogicWin;
        this.accountId = accountId;
        this.personalIdentityNumber = personalIdentityNumber;
        
        createWindowContent();
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createWindowContent()
    {
        JLabel accountIdLabel = new JLabel("Account ID: ");
        JTextField accountIdTextField = new JTextField(10);
        accountPanel.setLayout(new GridLayout(1,  2));
        accountPanel.add(accountIdLabel);
        accountPanel.add(accountIdTextField);
        accountIdTextField.setEditable(false);
        accountIdTextField.setText(Integer.toString(accountId));
        accountPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(accountPanel, BorderLayout.NORTH);
        
        JTextArea transactionTextArea = new JTextArea(5, 20);
        String transactions = overviewLogicWin.getTransactions(personalIdentityNumber, accountId);
        transactionTextArea.setText(transactions);
        transactionTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        transactionTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(transactionTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);
        
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        JButton closeButton = new JButton("Close");
        buttonPanel.add(closeButton);
        closeButton.addActionListener(this);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void actionPerformed(ActionEvent e) {
        
        String buttonText = e.getActionCommand();
        
        if(buttonText.equals("Close"))
        {
            //https://stackoverflow.com/a/6970105
            this.dispose();     
        }
    }
}


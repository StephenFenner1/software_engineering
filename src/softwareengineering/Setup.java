/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareengineering;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jah
 */
public class Setup extends javax.swing.JFrame {

    File compFile = null;
    File clientFile = null;
    int traderCount = 0;
    /**
     * Creates new form Setup
     */
    int choice;

    public Setup() {
        Object[] temp;
        while (compFile == null || clientFile == null) {

            temp = initialSetup();
            choice = (int) temp[0];
            traderCount = (int) temp[1];
            System.out.println(traderCount);

            if (choice == 1) {
                System.exit(0);
            }
        }

        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        settingsFrame = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        portfStockPanel = new javax.swing.JPanel();

        mainPane = new javax.swing.JPanel();
        tradingExchangePanel = new javax.swing.JPanel();
        clientPanel = new javax.swing.JPanel();
        clientsCBox = new javax.swing.JComboBox<>();
        clientPortfPanel = new javax.swing.JPanel();
        portfScrollPane = new javax.swing.JScrollPane();
        portfTable = new javax.swing.JTable();
        portfValuePanel = new javax.swing.JPanel();
        portfCashHLabel = new javax.swing.JLabel();
        portfInitLabel = new javax.swing.JLabel();
        portfTraderIDLabel = new javax.swing.JLabel();
        portfProfLabel = new javax.swing.JLabel();
        portfTraderMoodLabel = new javax.swing.JLabel();
        portfTraderMoodTextField = new javax.swing.JTextField();
        portfCashHTextField = new javax.swing.JTextField();
        portfInitTextField = new javax.swing.JTextField();
        portfProfitTextField = new javax.swing.JTextField();
        portfTraderIDTextField = new javax.swing.JTextField();
        portfCashedOutLabel = new javax.swing.JLabel();
        portfCashedOutTextField = new javax.swing.JTextField();
        cashOutButton = new javax.swing.JButton();
        stockPanel = new javax.swing.JPanel();
        compScrollPane = new javax.swing.JScrollPane();
        compTable = new javax.swing.JTable();
        clientButton = new javax.swing.JButton();
        tradingExButton = new javax.swing.JButton();
        companiesButton = new javax.swing.JButton();

        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        settingsFrame.setTitle("Settings");
        settingsFrame.setMinimumSize(new java.awt.Dimension(400, 600));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout settingsFrameLayout = new javax.swing.GroupLayout(settingsFrame.getContentPane());
        settingsFrame.getContentPane().setLayout(settingsFrameLayout);
        settingsFrameLayout.setHorizontalGroup(
            settingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        settingsFrameLayout.setVerticalGroup(
            settingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout portfStockPanelLayout = new javax.swing.GroupLayout(portfStockPanel);
        portfStockPanel.setLayout(portfStockPanelLayout);
        portfStockPanelLayout.setHorizontalGroup(
            portfStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );
        portfStockPanelLayout.setVerticalGroup(
            portfStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPane.setLayout(new java.awt.CardLayout());

        tradingExchangePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Trading Exchange"));

        javax.swing.GroupLayout tradingExchangePanelLayout = new javax.swing.GroupLayout(tradingExchangePanel);
        tradingExchangePanel.setLayout(tradingExchangePanelLayout);
        tradingExchangePanelLayout.setHorizontalGroup(
            tradingExchangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 906, Short.MAX_VALUE)
        );
        tradingExchangePanelLayout.setVerticalGroup(
            tradingExchangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );

        mainPane.add(tradingExchangePanel, "card2");

        clientPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Client"));

        clientsCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientsCBoxActionPerformed(evt);
            }
        });

        clientPortfPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Portfolio"));

        portfTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Company", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        portfScrollPane.setViewportView(portfTable);

        portfCashHLabel.setText("Cash Holdings: ");

        portfInitLabel.setText("Initial Value: ");

        portfTraderIDLabel.setText("Trader ID: ");

        portfProfLabel.setText("Money Made: ");

        portfTraderMoodLabel.setText("Trader Mood: ");

        portfTraderMoodTextField.setEditable(false);

        portfCashHTextField.setEditable(false);

        portfInitTextField.setEditable(false);

        portfProfitTextField.setEditable(false);

        portfTraderIDTextField.setEditable(false);

        portfCashedOutLabel.setText("Cashing Out:");

        portfCashedOutTextField.setEditable(false);

        javax.swing.GroupLayout portfValuePanelLayout = new javax.swing.GroupLayout(portfValuePanel);
        portfValuePanel.setLayout(portfValuePanelLayout);
        portfValuePanelLayout.setHorizontalGroup(
            portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(portfValuePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(portfInitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(portfCashHLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(portfCashedOutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(portfProfLabel)
                    .addComponent(portfTraderIDLabel)
                    .addComponent(portfTraderMoodLabel))
                .addGap(18, 18, 18)
                .addGroup(portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(portfCashHTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                        .addComponent(portfInitTextField)
                        .addComponent(portfProfitTextField)
                        .addComponent(portfTraderIDTextField)
                        .addComponent(portfTraderMoodTextField))
                    .addComponent(portfCashedOutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        portfValuePanelLayout.setVerticalGroup(
            portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(portfValuePanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portfCashHLabel)
                    .addComponent(portfCashHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portfInitLabel)
                    .addComponent(portfInitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portfCashedOutLabel)
                    .addComponent(portfCashedOutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portfProfLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portfProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portfTraderIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portfTraderIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(portfValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portfTraderMoodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portfTraderMoodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout clientPortfPanelLayout = new javax.swing.GroupLayout(clientPortfPanel);
        clientPortfPanel.setLayout(clientPortfPanelLayout);
        clientPortfPanelLayout.setHorizontalGroup(
            clientPortfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientPortfPanelLayout.createSequentialGroup()
                .addComponent(portfValuePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(portfScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        clientPortfPanelLayout.setVerticalGroup(
            clientPortfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(portfScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
            .addComponent(portfValuePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        cashOutButton.setText("Cash Out");
        cashOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cashOutButtonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout clientPanelLayout = new javax.swing.GroupLayout(clientPanel);
        clientPanel.setLayout(clientPanelLayout);
        clientPanelLayout.setHorizontalGroup(
            clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPanelLayout.createSequentialGroup()
                .addComponent(clientsCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cashOutButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(clientPortfPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        clientPanelLayout.setVerticalGroup(
            clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPanelLayout.createSequentialGroup()
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientsCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cashOutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientPortfPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPane.add(clientPanel, "card2");

        stockPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Company"));

        compTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Company Name", "Stock Type", "Stock Price (pence)", "Is Bankrupt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DefaultTableCellRenderer centreRenderer = new DefaultTableCellRenderer();
        centreRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        compTable.getColumn("Company Name").setCellRenderer( centreRenderer );
        compTable.getColumn("Stock Type").setCellRenderer( centreRenderer );
        compTable.getColumn("Stock Price (pence)").setCellRenderer( centreRenderer );
        compTable.getColumn("Is Bankrupt").setCellRenderer( centreRenderer );
        compScrollPane.setViewportView(compTable);

        javax.swing.GroupLayout stockPanelLayout = new javax.swing.GroupLayout(stockPanel);
        stockPanel.setLayout(stockPanelLayout);
        stockPanelLayout.setHorizontalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(compScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE)
        );
        stockPanelLayout.setVerticalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(compScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );

        mainPane.add(stockPanel, "card2");

        clientButton.setText("Client");
        clientButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientButtonMouseClicked(evt);
            }
        });
        clientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientButtonActionPerformed(evt);
            }
        });

        tradingExButton.setText("Trading Exc.");
        tradingExButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tradingExButtonMouseClicked(evt);
            }
        });

        companiesButton.setText("Companies");
        companiesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                companiesButtonMouseClicked(evt);
            }
        });
        companiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companiesButtonActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Upload New Dataset");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Settings");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        menuBar.add(jMenu1);

        jMenu2.setText("Edit");
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tradingExButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clientButton)
                        .addGap(10, 10, 10)
                        .addComponent(companiesButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientButton)
                    .addComponent(tradingExButton)
                    .addComponent(companiesButton))
                .addGap(13, 13, 13)
                .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void clientButtonMouseClicked(java.awt.event.MouseEvent evt) {                                          
        clientPanel.setVisible(true);
        tradingExchangePanel.setVisible(false);
        stockPanel.setVisible(false);
    }                                         

    private void tradingExButtonMouseClicked(java.awt.event.MouseEvent evt) {                                             
        clientPanel.setVisible(false);
        tradingExchangePanel.setVisible(true);
        stockPanel.setVisible(false);
    }                                            

    private void companiesButtonMouseClicked(java.awt.event.MouseEvent evt) {                                             
        clientPanel.setVisible(false);
        tradingExchangePanel.setVisible(false);
        stockPanel.setVisible(true);

        DefaultTableModel model = (DefaultTableModel) compTable.getModel();
        model.getDataVector().removeAllElements();
        
        companies.stream().forEach((c) -> {
            String twat = String.valueOf(c.isBankrupt());
            model.addRow(new Object[]{c.getCompanyName(), c.getStockType(),c.getStockValue(),twat});
        });


    }                                            

    private void clientButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void companiesButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        settingsFrame.setVisible(true);
        settingsFrame.setAlwaysOnTop(true);// TODO add your handling code here:
    }                                          

    private void clientsCBoxActionPerformed(java.awt.event.ActionEvent evt) {                                            

    }                                           

    private void cashOutButtonMouseReleased(java.awt.event.MouseEvent evt) {                                            
        Object[] options = {"Yes", "No"};
        int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to\ncash client out?", "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        Portfolio selectedPort = (Portfolio) clientsCBox.getSelectedItem();
        Client selectedClient = selectedPort.getClient();

        if (choice == 0) {
            selectedClient.setCashingOut(true);

        }
    }                                           
    private File chooseDSetButtonMouseClicked(java.awt.event.MouseEvent evt) {
        File tempFile = null;
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            tempFile = fileChooser.getSelectedFile();
        }
        return tempFile;
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Setup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Setup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Setup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Setup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Setup().setVisible(true);
//            }
//        });
//    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton cashOutButton;
    private javax.swing.JButton clientButton;
    private javax.swing.JPanel clientPanel;
    private javax.swing.JPanel clientPortfPanel;
    private javax.swing.JComboBox<Portfolio> clientsCBox;
    private javax.swing.JScrollPane compScrollPane;
    private javax.swing.JTable compTable;
    private javax.swing.JButton companiesButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel mainPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel portfCashHLabel;
    private javax.swing.JTextField portfCashHTextField;
    private javax.swing.JLabel portfCashedOutLabel;
    private javax.swing.JTextField portfCashedOutTextField;
    private javax.swing.JLabel portfInitLabel;
    private javax.swing.JTextField portfInitTextField;
    private javax.swing.JLabel portfProfLabel;
    private javax.swing.JTextField portfProfitTextField;
    private javax.swing.JScrollPane portfScrollPane;
    private javax.swing.JPanel portfStockPanel;
    private javax.swing.JTable portfTable;
    private javax.swing.JLabel portfTraderIDLabel;
    private javax.swing.JTextField portfTraderIDTextField;
    private javax.swing.JLabel portfTraderMoodLabel;
    private javax.swing.JTextField portfTraderMoodTextField;
    private javax.swing.JPanel portfValuePanel;
    private javax.swing.JFrame settingsFrame;
    private javax.swing.JPanel stockPanel;
    private javax.swing.JButton tradingExButton;
    private javax.swing.JPanel tradingExchangePanel;
    // End of variables declaration                   

    private Object[] initialSetup() {

        JLabel companyFileName = new JLabel();
        JLabel clientFileName = new JLabel();
        JLabel traderCountLabel = new JLabel("Enter amount of traders.");
        companyFileName.setText("File Path");
        clientFileName.setText("File Path");
        JComboBox traderCountCBox = new JComboBox();

        for (int i = 2; i < 100; i++) {
            traderCountCBox.addItem(i);
        }
        traderCountCBox.setSelectedIndex(0);
        traderCountCBox.addActionListener((ActionEvent e) -> {
            JComboBox combo = (JComboBox) e.getSource();
            traderCount = (int) combo.getSelectedItem();
        });

        JButton chooseCompDSetButton = new JButton("Choose Company File");
        JButton chooseClientDSetButton = new JButton("Choose Client File");
        chooseCompDSetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                File tempFile = chooseDSetButtonMouseClicked(evt);
                if (tempFile != null) {
                    companyFileName.setText(tempFile.getPath());

                } else {

                }
                setCompFile(tempFile);

            }
        });

        chooseClientDSetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                File tempFile = chooseDSetButtonMouseClicked(evt);
                if (tempFile != null) {
                    clientFileName.setText(tempFile.getPath());

                } else {

                }
                setClientFile(tempFile);

            }
        });

        Object[] options = {"Go", "Cancel"};
        Object[] obs = {companyFileName, chooseCompDSetButton, clientFileName, chooseClientDSetButton, traderCountLabel, traderCountCBox};
        return new Object[]{JOptionPane.showOptionDialog(null, obs, "Initial Setup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]), traderCount};

    }

    private void setCompFile(File file) {
        compFile = file;
    }

    private void setClientFile(File file) {
        clientFile = file;
    }

    public File[] getFile() {

        return new File[]{compFile, clientFile};
    }

    public int getTraderCount() {
        return traderCount;
    }

    private ArrayList<Portfolio> portfolios;
    private ArrayList<Company> companies;

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public void setPortfolios(ArrayList<Portfolio> portfolios) {
        this.portfolios = portfolios;
        //DefaultTableModel model = (DefaultTableModel) portfTable.getModel();
        portfolios.stream().forEach((c) -> {
            clientsCBox.addItem(c);
        });

        clientsCBox.addActionListener((ActionEvent e) -> {

            JComboBox combo = (JComboBox) e.getSource();
//            Portfolio pa = (Portfolio) clientsCBox.getSelectedItem();

//            System.out.println(pa.getClient());
//            Map mape = pa.getStockOwned();
//            System.out.println(mape.get("WazooIt"));
//            model.addRow(new Object[]{pa.getClient(), mape.get("WazooIt")});
            DefaultTableModel model = (DefaultTableModel) portfTable.getModel();
            model.getDataVector().removeAllElements();
            Portfolio selectedPort = (Portfolio) clientsCBox.getSelectedItem();
            Map portf = selectedPort.getStockOwned();

            companies.stream().forEach((c) -> {
                model.addRow(new Object[]{c.getCompanyName(), portf.get(c.getCompanyName())});
            });

            Client selectedClient = selectedPort.getClient();
            System.out.println("Cashing Out: " + selectedClient.isCashingOut());

            portfCashHTextField.setText(Integer.toString(selectedClient.getCashHolding()));
            portfInitTextField.setText(Integer.toString(selectedClient.getInitialValue()));
            portfProfitTextField.setText(Integer.toString(selectedClient.getCurrentValue() - selectedClient.getInitialValue()));
            Trader selectedTrader = selectedPort.getTrader();
            portfTraderIDTextField.setText(Integer.toString(selectedTrader.getID()));

            //portfTraderMoodTextField.setText(selectedTrader.mood.toString());
            portfCashedOutTextField.setText(String.valueOf(selectedClient.isCashingOut()));

        });

    }
}

//        Runnable clearText = new Runnable() {
//            @Override
//            public void run() {
//
//                traderCountJTField.setText("2");
//                JOptionPane.showMessageDialog(null,
//                        "Error: Please enter a vaild number between 2-99", "Error Massage",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//
//        };
//
//        traderCountJTField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                warn();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                warn();
//            }
//
//            public void warn() {
//                try {
//                    if (Integer.parseInt(traderCountJTField.getText()) <= 1 || Integer.parseInt(traderCountJTField.getText()) >= 100) {
//
//                        SwingUtilities.invokeLater(clearText);
//
//                    }
//                } catch (NumberFormatException e) {
//                    SwingUtilities.invokeLater(clearText);
//
//                }
//            }
//
//        });

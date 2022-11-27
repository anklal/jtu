
package trainingproject;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Reports extends javax.swing.JDialog {

   
    public Reports(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        for(int i=2000;i<2050;i++)
        {
            jComboBox2.addItem(i);
        }        
    }
     
    private void showBookings()
    {
        String Query="";
        if(jComboBox1.getSelectedIndex()==0)
        {
            try
            {
                String[] columns={"Year","No. of Bookings"};
                DefaultTableModel dtm=new DefaultTableModel(columns,0);
                jTable1.setModel(dtm);

                Statement st=TrainingProject.con.createStatement();
                ResultSet rs=st.executeQuery("select DATEPART(YEAR,BookedDate)as[Year],\n" +
                                             "COUNT(*)as [NOB]from Bookings\n" +
                                             "group by DATEPART(YEAR,BookedDate)");
        
                while(rs.next())
                {
                    Object[]row=new Object[2];
                    row[0]=rs.getString("Year");
                    row[1]=rs.getInt("NOB");
                    dtm.addRow(row);
                }
                    rs.close();
             }
             catch(Exception ex)
             {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
             }
        }
        else if(jComboBox1.getSelectedIndex()==1)
        {
            try
            {
                String[] columns={"Month","No. of Bookings"};
                DefaultTableModel dtm=new DefaultTableModel(columns,0);
                jTable1.setModel(dtm);

                Statement st=TrainingProject.con.createStatement();
                ResultSet rs=st.executeQuery("select DATENAME(MONTH,BookedDate)as[Month],\n" +
                                             "COUNT(*)as [NOB]from Bookings where DATEPART(Year,BookedDate)="+jComboBox2.getSelectedItem()+"\n" +
                                             "group by DATENAME(MONTH,BookedDate)");
        
                while(rs.next())
                {
                    Object[]row=new Object[2];
                    row[0]=rs.getString("Month");
                    row[1]=rs.getInt("NOB");
                    dtm.addRow(row);
                }
                    rs.close();
             }
             catch(Exception ex)
             {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
             }
        }
        else if(jComboBox1.getSelectedIndex()==2)
        {
            try
            {
                String[] columns={"Day","No. of Bookings"};
                DefaultTableModel dtm=new DefaultTableModel(columns,0);
                jTable1.setModel(dtm);

                Statement st=TrainingProject.con.createStatement();
                ResultSet rs=st.executeQuery("select DATEPART(Day,BookedDate)as[Day],\n" +
                                             "COUNT(*)as [NOB]from Bookings where DATEPART(Year,BookedDate)="+jComboBox2.getSelectedItem()+"\n"+"and DATEPART(Month,BookedDate)="+(jComboBox3.getSelectedIndex()+1)+"\n" +
                                             "group by DATEPART(DAY,BookedDate)");
        
                while(rs.next())
                {
                    Object[]row=new Object[2];
                    row[0]=rs.getString("Day");
                    row[1]=rs.getInt("NOB");
                    dtm.addRow(row);
                }
                    rs.close();
             }
             catch(Exception ex)
             {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
             }
        }
    }
    private void showCollections()
    {
      
        if(jComboBox1.getSelectedIndex()==0)
        {
            try
            {
                String[] columns={"Year","Total Fare"};
                DefaultTableModel dtm=new DefaultTableModel(columns,0);
                jTable1.setModel(dtm);



                Statement st=TrainingProject.con.createStatement();
                ResultSet rs=st.executeQuery("select DATEpart(Year ,BookedDate)as [Year],SUM(Fare*NoOfSeats) as [TotalFare]\n" +
                                              "from Bookings\n" +
                                              "group by DATEpart(year,BookedDate)");

            while(rs.next())
            {
                Object[]row=new Object[2];
                row[0]=rs.getString("Year");
                row[1]=rs.getFloat("TotalFare");
                dtm.addRow(row);
            }
            rs.close();
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
        else if(jComboBox1.getSelectedIndex()==1)
        {
           try
                {
                    String[] columns={"Month","Total Fare"};
                    DefaultTableModel dtm=new DefaultTableModel(columns,0);
                    jTable1.setModel(dtm);



                    Statement st=TrainingProject.con.createStatement();
                    ResultSet rs=st.executeQuery("select DATENAME(MONTH ,BookedDate)as [Month],SUM(Fare*NoOfSeats) as [TotalFare]\n "+
                                                  "from Bookings\n where datepart(Year,BookedDate)="+jComboBox2.getSelectedItem()+"\n" +
                                                  "group by DATENAME(MONTH,BookedDate)");

                while(rs.next())
                {
                    Object[]row=new Object[2];
                    row[0]=rs.getString("Month");
                    row[1]=rs.getFloat("TotalFare");
                    dtm.addRow(row);
                }
                rs.close();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                } 
        }
        else if(jComboBox1.getSelectedIndex()==2)
        {
            try
                    {
                        String[] columns={"DAY","Total Fare"};
                        DefaultTableModel dtm=new DefaultTableModel(columns,0);
                        jTable1.setModel(dtm);



                        Statement st=TrainingProject.con.createStatement();
                        ResultSet rs=st.executeQuery("select DATEpart(day ,BookedDate)as [DAY],SUM(Fare*NoOfSeats) as [TotalFare]\n "+
                                                      "from Bookings\n where Datepart(month,BookedDate)="+(jComboBox3.getSelectedIndex()+1)+"and datepart(year,BookedDate)="+jComboBox2.getSelectedItem()+"\n" +
                                                      "group by DATEpart(day,BookedDate)");

                    while(rs.next())
                    {
                        Object[]row=new Object[2];
                        row[0]=rs.getString("DAY");
                        row[1]=rs.getFloat("TotalFare");
                        dtm.addRow(row);
                    }
                    rs.close();
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Javac Tansport Undertaking- Reports");

        jLabel1.setText("Report Type");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Booking");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Collection");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Duration");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Annually", "Monthly", "Daily" }));

        jLabel3.setText("Year");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Month");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));

        jButton1.setText("Show");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(27, 27, 27)
                                .addComponent(jRadioButton2)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jRadioButton1.isSelected())
        {
            showBookings();
        }
        else
        {
            showCollections();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Reports dialog = new Reports(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

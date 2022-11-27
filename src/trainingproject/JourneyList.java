
package trainingproject;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JourneyList extends javax.swing.JDialog {

   
    public JourneyList(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        refreshData();
    }
     public void refreshData()
    {
        try
        {
            String[] columns={"Journey Id","Journey Date","FromCity","To City","Via","Vehicle Number","Driver Name","Timing"};
            DefaultTableModel dtm=new DefaultTableModel(columns,0);
            jTable1.setModel(dtm);
             
            Statement st=TrainingProject.con.createStatement();
            ResultSet rs=st.executeQuery("Select J.JourneyId,J.JourneyDate,FC.CityName as FromCityName,TC.CityName as ToCityName,R.Via,V.RegistrationCode,D.DriverName,J.Timing \n" +
                                         "from Journeys as [J],Cities as FC,Cities as TC,Routes as [R],Vehicles as [V],Drivers as [D]\n" +
                                         "where R.FromCityId=FC.CityId and R.ToCityId=Tc.CityId and J.RouteId=R.RouteId and J.VehicleId=V.VehicleId and J.DriverId=D.DriverId");
            
            while(rs.next())
            {
                Object[]row=new Object[8];
                row[0]=rs.getInt("JourneyId");
                row[1]=rs.getString("JourneyDate");
                row[2]=rs.getString("FromCityName").trim();
                row[3]=rs.getString("ToCityName").trim();
                row[4]=rs.getString("Via").trim();
                row[5]=rs.getString("RegistrationCode").trim();
                row[6]=rs.getString("DriverName").trim();
                row[7]=rs.getString("Timing");
                dtm.addRow(row);
            }
            rs.close();
            
            CommonFunctions.setColumnsWidth(jTable1);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Javac Transport Undertaking- Journey List");

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

        jButton1.setText("Edit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i=jTable1.getSelectedRow();
        
        if(i>=0)
        {
            int a=Integer.parseInt(jTable1.getValueAt(i,0).toString());
            
            Journey j=new Journey(null, true);
            j.startEditing(a);
            j.setVisible(true);
            
            refreshData();
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "No Item Selected To Edit!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row=jTable1.getSelectedRow();
        if (row>=0)
        {
            int res = JOptionPane.showConfirmDialog(rootPane, "Do you want to delete selected record?", "Travel Agency", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_OPTION)
            {
                int JID=Integer.parseInt(jTable1.getValueAt(row,0).toString());
                try
                {
                    Statement st=TrainingProject.con.createStatement();
                    st.executeUpdate("Delete from Journeys where JourneyId="+JID);

                    refreshData();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "No Item Selected to Delete!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JourneyList dialog = new JourneyList(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

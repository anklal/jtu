
package trainingproject;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class RouteList extends javax.swing.JDialog {

    
    public RouteList(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        refreshData();
    }
    public void refreshData()
    {
        try
        {
             String[] columns={"Route Id","From City","To City","Via"};
            DefaultTableModel dtm=new DefaultTableModel(columns,0);
            jTable1.setModel(dtm);
            
            
            
            Statement st=TrainingProject.con.createStatement();
            ResultSet rs=st.executeQuery("select R.RouteId,FC.CityName as FromCityName, TC.CityName as ToCityName,R.Via\n" +
                                         "from Routes as R, Cities as FC, Cities as TC\n" +
                                         "where R.FromCityId=FC.CityId and R.ToCityId=Tc.CityId");
            
            while(rs.next())
            {
                Object[]row=new Object[4];
                row[0]=rs.getInt("RouteId");
                row[1]=rs.getString("FromCityName").trim();
                row[2]=rs.getString("ToCityName").trim();
                row[3]=rs.getString("Via").trim();
                dtm.addRow(row);
            }
            rs.close();
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
        setTitle("Javac Transport Undertaking- List Routes");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i=jTable1.getSelectedRow();
        
        if(i>=0)
        {
            int a=Integer.parseInt(jTable1.getValueAt(i,0).toString());
            
            Route c=new Route(null,true);
            c.startEditing(a);
            c.setVisible(true);
            
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
                int RID=Integer.parseInt(jTable1.getValueAt(row,0).toString());
                try
                {
                    Statement st=TrainingProject.con.createStatement();
                    st.executeUpdate("Delete from Routes where RouteId="+RID);

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

    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RouteList dialog = new RouteList(new javax.swing.JFrame(), true);
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

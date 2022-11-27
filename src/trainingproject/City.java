
package trainingproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class City extends javax.swing.JDialog {

    int CityId=0;
    
    public void startEditing(int value)
    {
        CityId=value;
        try
        {
            PreparedStatement ps=TrainingProject.con.prepareStatement("select*from Cities where CityId=?");
            ps.setInt(1,CityId);
            
            ResultSet rs=ps.executeQuery();
            rs.next();
            
            String s=rs.getString("CityName").trim();
            rs.close();
            ps.close();
            jTextField1.setText(s);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }
    
    public City(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }
    
    public boolean validateData()
    {
        if(jTextField1.getText().length()==0)
        {
            JOptionPane.showMessageDialog(rootPane,"Enter City!");
            return false;
        }
        
        try
        {
            PreparedStatement ps = TrainingProject.con.prepareStatement("Select * From Cities Where CityName=? and CityID!=?");

            ps.setString(1, jTextField1.getText());
            ps.setInt(2, CityId);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                rs.close();
                ps.close();
                JOptionPane.showMessageDialog(rootPane, "City already exists!");
                return false;
            }
            else
            {
                rs.close();
                ps.close();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
        
        return true;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Javac Transport Undertaking- Add City");

        jLabel1.setText("City");

        jTextField1.setToolTipText("");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      boolean x=validateData();
      if(x==true)
      {
        try
        { 
            if(CityId==0)
            {           
                PreparedStatement ps=TrainingProject.con.prepareStatement("Insert into Cities values(?)");
                ps.setString(1,jTextField1.getText());
                ps.executeUpdate();
                
                ps.close();
                JOptionPane.showMessageDialog(rootPane, "Saved!");
                jTextField1.setText("");
            }
            else
            {
                PreparedStatement ps=TrainingProject.con.prepareStatement("update Cities set CityName=? where CityId=?");
                ps.setString(1,jTextField1.getText());
                ps.setInt(2,CityId);
                
                ps.executeUpdate();
                
                ps.close();
                
                JOptionPane.showMessageDialog(rootPane, "Changes Saved!");
                this.dispose();
            }
            
        }//try
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }//catch
      }//if    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        if(evt.getKeyChar()<65 || evt.getKeyChar()>90 && evt.getKeyChar()<97 || evt.getKeyChar()>122)
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

package trainingproject;

import java.sql.*;
import javax.swing.JOptionPane;

public class TrainingProject {

    public static Connection con;
    
    public static void main(String[] args) 
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:49157;databaseName=ProjectDB;integratedSecurity=true";
            con = DriverManager.getConnection(connectionUrl);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        Welcome w=new Welcome(null, true);
        w.setVisible(true);
    }
}

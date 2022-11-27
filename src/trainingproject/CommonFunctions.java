package trainingproject;

import javax.swing.JTable;

public class CommonFunctions {

    public static void setColumnsWidth(JTable tbl)
    {
        tbl.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        
        for(int i=0;i<tbl.getColumnCount();i++ )
        {
            tbl.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
        
    }
    
}

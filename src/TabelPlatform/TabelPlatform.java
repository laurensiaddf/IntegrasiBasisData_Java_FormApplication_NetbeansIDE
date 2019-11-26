/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TabelPlatform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class TabelPlatform extends AbstractTableModel {
  List <DataPlatform> data2 = new ArrayList<>();


@Override
public int getRowCount() {
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
return data2.size();
}

@Override
public int getColumnCount() {
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
return 3;
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
switch(columnIndex){
case 0: return data2.get(rowIndex).getRilis();
case 1: return data2.get(rowIndex).getKodeplatform();
case 2: return data2.get(rowIndex).getPlatform(); 
default: return null;
}
}

@Override
public String getColumnName(int kolom){
switch(kolom){
case 0: return"RILIS";
case 1: return"KODEPLATFORM";
case 2: return"PLATFORM";
default: return null;
}
}

public void add(DataPlatform a){
data2.add(a);
fireTableRowsInserted(getRowCount(),getColumnCount());
}

public void delete (int col,int row){
data2.remove(col);
fireTableRowsDeleted(col,row);
}



public DataPlatform get(int row){
return (DataPlatform) data2.get(row);
}

}


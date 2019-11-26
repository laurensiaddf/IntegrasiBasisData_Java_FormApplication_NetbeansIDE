/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TabelData;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author ASUS
 */
public class TabelData extends AbstractTableModel{
List <DataGame> data = new ArrayList<>();


@Override
public int getRowCount() {
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
return data.size();
}

@Override
public int getColumnCount() {
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
return 6;
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
switch(columnIndex){
case 0: return data.get(rowIndex).getIdgame();
case 1: return data.get(rowIndex).getJudulgame();
case 2: return data.get(rowIndex).getGenre();
case 3: return data.get(rowIndex).getRilis();    
case 4: return data.get(rowIndex).getDeveloper();
case 5: return data.get(rowIndex).getPlatform(); 
default: return null;
}
}

@Override
public String getColumnName(int kolom){
switch(kolom){
case 0: return"IDGAME";
case 1: return"JUDULGAME";
case 2: return"GENRE";
case 3: return"RILIS";
case 4: return"DEVELOPER";
case 5: return"PLATFORM";
default: return null;
}
}

public void add(DataGame a){
data.add(a);
fireTableRowsInserted(getRowCount(),getColumnCount());
}

public void delete (int col,int row){
data.remove(col);
fireTableRowsDeleted(col,row);
}



public DataGame get(int row){
return (DataGame) data.get(row);
}

}
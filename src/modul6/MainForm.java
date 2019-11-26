/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul6;

import TabelData.DataGame;
import TabelData.TabelData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



/**
 *
 * @author ASUS
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    
   
    private String mf_idgame;
    private String mf_judulgame;
    private String mf_genre;
    private String mf_rilis;
    private String mf_developer;
    private String mf_kodeplatform;
    private String mf_platform;
    private String mf_search;
    private String output;
    private TabelData tabeldata;
    private dbconnections c;
    private Statement script;
    
    private void Tampil(){
        try {
            int row = tabel.getRowCount();
            for(int i=0;i<row;i++){
                tabeldata.delete(0, row);
            }
            String sql="SELECT * from DB_GAME4";
            ResultSet rsShow = c.script.executeQuery(sql);
            while(rsShow.next()){
                DataGame d = new DataGame();
                d.setIdgame(rsShow.getString("IDGAME"));
                d.setJudulgame(rsShow.getString("JUDULGAME"));
                d.setGenre(rsShow.getString("GENRE"));
                d.setRilis(rsShow.getString("RILIS"));
                d.setDeveloper(rsShow.getString("DEVELOPER"));
                d.setPlatform(rsShow.getString("PLATFORM"));
                d.setKodeplatform(rsShow.getString("KODEPLATFORM"));
                tabeldata.add(d);
            }
        }catch(Exception e){
            System.err.print(e);
        }
    } 
    public String showData(DataGame dm){
        idgame.setText(dm.getIdgame());
        judulgame.setText(dm.getJudulgame());
        genre.setText(dm.getGenre());
        rilis.setText (dm.getRilis());
        developer.setText (dm.getDeveloper());
        kodeplatform.setText (dm.getKodeplatform());
        insert.setEnabled(false);
        return dm.getIdgame();
    }
    private void insertData(){
        mf_judulgame = judulgame.getText();
        mf_genre = genre.getText();
        mf_rilis = rilis.getText();
        mf_developer = developer.getText();
        mf_kodeplatform = kodeplatform.getText();
        if(judulgame!=null&&genre!=null&&rilis!=null){
            try{
                String sql = "INSERT INTO GAME (idgame, judulgame, genre, rilis, developer, kodeplatform) values (SEQ_MODUL6.nextval+1,'" +mf_judulgame+ "','"+mf_genre+"','"+mf_rilis+"','" +mf_developer+ "',"+mf_kodeplatform+")";
                c.script.executeUpdate(sql);
            }catch(SQLException e){
                System.err.print(e);
            }
            Tampil();
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            clearForm();
        }else{
            JOptionPane.showMessageDialog(null,"Setiap kolom harus diisi!");
        }
    }
    private void deleteData(){
    int app;
    mf_idgame = idgame.getText();
    if ((app=JOptionPane.showConfirmDialog(null,"Yakin ingin hapus data?","Hapus Data", JOptionPane.YES_NO_OPTION))==0){
    try {
        String sqlid = "SELECT IDGAME from GAME where idgame="+mf_idgame+"";
        ResultSet rsShow = c.script.executeQuery(sqlid);
        while (rsShow.next()){
	output = rsShow.getString(1);
}
        String sql = "DELETE from GAME where idgame ="+mf_idgame+"";
        c.script.executeUpdate(sql);
        Tampil();
        JOptionPane.showMessageDialog (null,"Berhasil dihapus");
        clearForm();
        tampil1();
    }
    catch (SQLException e){
        System.err.print(e);
            }
        }
    }
    private void updateData(String mf_idgame){
        int app;
                       
        if((app = JOptionPane.showConfirmDialog(null, "Yakin ingin update date?","Ubah Data",JOptionPane.YES_NO_OPTION))==0){
        	try{ //Query untuk update pada table database
                    mf_idgame = idgame.getText();
                    mf_judulgame = judulgame.getText();
                    mf_genre = genre.getText();
                    mf_rilis = rilis.getText();
                    mf_developer = developer.getText();
                    mf_kodeplatform = kodeplatform.getText();	
                             
                    String sqlid = "SELECT IDGAME from GAME where idgame=" +mf_idgame+ ";";
                    System.out.println(sqlid);
                    ResultSet rsShow = c.script.executeQuery(sqlid);
                    while (rsShow.next()){
                    output = rsShow.getString(1);
                    } 
                    String sql = "UPDATE GAME SET judulgame='"+mf_judulgame+"',genre='"+mf_genre+"',rilis='"+mf_rilis+"',developer='"+mf_developer+"',kodeplatform="+mf_kodeplatform+" where idgame="+mf_idgame+"" ;
                    System.out.println(sql);
                    c.script.executeUpdate(sql);
                    Tampil();
                    tampil1();
		//menampilkan message dialog bahwa data telah update
        	JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
        		clearForm();
        	}
        	catch(SQLException ex){
        	System.err.print(ex);
        }
    }}
    private void search(){
        mf_search = cari.getText();
        try {
            int row = tabel.getRowCount();
            for(int i=0;i<row;i++){
                tabeldata.delete(0, row);
            }
            String sql = "SELECT * FROM DB_GAME WHERE lower(JUDULGAME) like lower ('%"+mf_search+"%')";
            ResultSet rsShow = c.script.executeQuery(sql);
            while(rsShow.next()){
                DataGame dg = new DataGame();
                dg.setIdgame(rsShow.getString("IDGAME"));
                dg.setJudulgame(rsShow.getString("JUDULGAME"));
                dg.setGenre(rsShow.getString("GENRE"));
                dg.setRilis(rsShow.getString("RILIS"));
                dg.setDeveloper(rsShow.getString("DEVELOPER"));
                dg.setPlatform(rsShow.getString("PLATFORM"));
                dg.setKodeplatform(rsShow.getString("KODEPLATFORM"));
                tabeldata.add(dg);
            }
        }catch(Exception e){
            System.err.print(e);
        }
        
    }
    
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        c = new dbconnections();
        tabeldata = new TabelData();
        tabel.setModel(tabeldata);
        Tampil();

    }
    public void clearForm(){
        judulgame.setText(null);
        genre.setText(null);
        rilis.setText(null);
        developer.setText (null);
        kodeplatform.setText (null);
        idgame.setText(null);
        
    }
    public void tampil1(){
    	insert.setEnabled(true);
    	delete.setEnabled(true);
    	update.setEnabled(true);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        idgame = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        judulgame = new javax.swing.JTextField();
        genre = new javax.swing.JTextField();
        rilis = new javax.swing.JTextField();
        developer = new javax.swing.JTextField();
        kodeplatform = new javax.swing.JTextField();
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        tambahplatform = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Game", "Judul Game", "Genre", "Tanggal Rilis", "Pengembang", "Platform"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        jLabel1.setText("ID:");

        idgame.setText("0");

        jLabel2.setText("Judul Game");

        jLabel3.setText("Genre");

        jLabel4.setText("Tanggal Rilis");

        jLabel5.setText("Pengembang");

        jLabel6.setText("Kode Platform");

        insert.setText("Insert");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        tambahplatform.setText("Tambah Platform");
        tambahplatform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahplatformActionPerformed(evt);
            }
        });

        jLabel7.setText("CARI");

        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariKeyPressed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(judulgame)
                            .addComponent(genre)
                            .addComponent(rilis)
                            .addComponent(developer)
                            .addComponent(kodeplatform, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(insert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idgame, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tambahplatform))
                        .addGap(135, 135, 135))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(judulgame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tambahplatform))
                    .addComponent(jLabel2))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(idgame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rilis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insert))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(developer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kodeplatform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
insertData();        // TODO add your handling code here:
    }//GEN-LAST:event_insertActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
mf_idgame = idgame.getText();
updateData(mf_idgame);// TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
deleteData();        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
if(evt.getClickCount()==2){
         showData(this.tabeldata.get(tabel.getSelectedRow()));
         insert.setEnabled(false);
         delete.setEnabled(true);
         update.setEnabled(true);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_tabelMouseClicked

    private void tambahplatformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahplatformActionPerformed
        new konfigurasiplatform().setVisible(true);
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahplatformActionPerformed

    private void cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyPressed
search();        // TODO add your handling code here:
    }//GEN-LAST:event_cariKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cari;
    private javax.swing.JButton delete;
    private javax.swing.JTextField developer;
    private javax.swing.JTextField genre;
    private javax.swing.JLabel idgame;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField judulgame;
    private javax.swing.JTextField kodeplatform;
    private javax.swing.JTextField rilis;
    private javax.swing.JTable tabel;
    private javax.swing.JButton tambahplatform;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}

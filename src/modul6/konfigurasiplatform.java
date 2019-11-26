/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul6;

import TabelPlatform.DataPlatform;
import TabelPlatform.TabelPlatform;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class konfigurasiplatform extends javax.swing.JFrame {

    /**
     * Creates new form konfigurasiplatform
     */
    private String tp_rilis;
    private String tp_kodeplatform;
    private String tp_platform;
    private String output;
    private TabelPlatform tabelplatform;
    private dbconnections c;
    private Statement script;
    
    private void Tampil(){
        try {
            int row = tabel2.getRowCount();
            for(int i=0;i<row;i++){
                tabelplatform.delete(0, row);
            }
            String sql="SELECT * from PLATFORM";
            ResultSet rsShow = c.script.executeQuery(sql);
            while(rsShow.next()){
                DataPlatform p = new DataPlatform();
                p.setKodeplatform(rsShow.getString("KODEPLATFORM"));
                p.setPlatform(rsShow.getString("PLATFORM"));
                p.setRilis(rsShow.getString("RILIS"));
                tabelplatform.add(p);
            }
        }catch(Exception e){
            System.err.print(e);
        }
    } 
    public String showData(DataPlatform dp){
        kodeplatform.setText (dp.getKodeplatform());
        platform2.setText (dp.getPlatform());
        rilis2.setText (dp.getRilis());
        tambah.setEnabled(false);
        return dp.getKodeplatform();
    }
    private void insertData(){
        tp_rilis = rilis2.getText();
        tp_platform = platform2.getText();
        if(rilis2!=null&&platform2!=null){
            try{
                String sql = "INSERT INTO PLATFORM values (SEQ_MODUL6P.nextval+1,'"+tp_platform+"','" +tp_rilis+ "')";
                c.script.executeUpdate(sql);
            }catch(SQLException e){
                System.err.print(e);
            }
            Tampil();
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            clearForm();
        }
    }
    private void deleteData(){
    int app;
    tp_kodeplatform = kodeplatform.getText();
    if ((app=JOptionPane.showConfirmDialog(null,"Yakin ingin hapus data?","Hapus Data", JOptionPane.YES_NO_OPTION))==0){
    try {
        String sqlid = "SELECT KODEPLATFORM from PLATFORM where kodeplatform="+tp_kodeplatform+"";
        ResultSet rsShow = c.script.executeQuery(sqlid);
        while (rsShow.next()){
	output = rsShow.getString(1);
}
        String sql = "DELETE from PLATFORM where kodeplatform ="+tp_kodeplatform+"";
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
    private void updateData(String tp_kodeplatform){
        
                       
        if((JOptionPane.showConfirmDialog(null, "Yakin ingin update date?","Ubah Data",JOptionPane.YES_NO_OPTION))==0){
        	try{ //Query untuk update pada table database
                    tp_platform = platform2.getText();
                    tp_rilis = rilis2.getText();
                    tp_kodeplatform = kodeplatform.getText();
                             
                    String sqlid = "SELECT KODEPLATFORM from PLATFORM where kodeplatform=" +tp_kodeplatform+ "";
                    ResultSet rsShow = c.script.executeQuery(sqlid);
                    while (rsShow.next()){
                    output = rsShow.getString(1);
                    } 
                    String sql = "UPDATE PLATFORM SET platform='"+tp_platform+"',rilis='"+tp_rilis+"' where kodeplatform="+tp_kodeplatform+"" ;
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
    public konfigurasiplatform() {
        initComponents();
        c = new dbconnections();
        tabelplatform = new TabelPlatform();
        tabel2.setModel(tabelplatform);
        Tampil();
    }
    public void clearForm(){
        platform2.setText(null);
        rilis2.setText (null);
        kodeplatform.setText (null);
        
    }
    public void tampil1(){
    	tambah.setEnabled(true);
    	hapus.setEnabled(true);
    	edit.setEnabled(true);
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
        tabel2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        platform2 = new javax.swing.JTextField();
        rilis2 = new javax.swing.JTextField();
        tambah = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        kembali = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        kodeplatform = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabel2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "KODE PLATFORM", "PLATFORM", "TANGGAL RILIS"
            }
        ));
        tabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel2);

        jLabel1.setText("Platform");

        jLabel2.setText("Tanggal Rilis");

        tambah.setText("INSERT");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        edit.setText("UPDATE");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setText("DELETE");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        kembali.setText("KEMBALI");
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });

        jLabel3.setText("Kode Platform:");

        kodeplatform.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(platform2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(rilis2)))
                            .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kembali, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12)
                        .addComponent(kodeplatform, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(kodeplatform))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(platform2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambah))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rilis2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit))
                .addGap(33, 33, 33)
                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
insertData();       // TODO add your handling code here:
    }//GEN-LAST:event_tambahActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
deleteData();        // TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
tp_kodeplatform = kodeplatform.getText();
updateData(tp_kodeplatform);        // TODO add your handling code here:
    }//GEN-LAST:event_editActionPerformed

    private void tabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel2MouseClicked
if(evt.getClickCount()==2){
         showData(this.tabelplatform.get(tabel2.getSelectedRow()));
         tambah.setEnabled(false);
         hapus.setEnabled(true);
         edit.setEnabled(true);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_tabel2MouseClicked

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
new MainForm().setVisible(true);
this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_kembaliActionPerformed

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
            java.util.logging.Logger.getLogger(konfigurasiplatform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(konfigurasiplatform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(konfigurasiplatform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(konfigurasiplatform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new konfigurasiplatform().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JLabel kodeplatform;
    private javax.swing.JTextField platform2;
    private javax.swing.JTextField rilis2;
    private javax.swing.JTable tabel2;
    private javax.swing.JButton tambah;
    // End of variables declaration//GEN-END:variables

}

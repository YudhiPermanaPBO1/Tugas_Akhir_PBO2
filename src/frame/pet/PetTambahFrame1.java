package frame.pet;

import db.Koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.pet;

public class PetTambahFrame1 extends JFrame{
    int status;
    
    private final int SEDANG_TAMBAH = 101;
    private final int SEDANG_UBAH = 102;
    
    JLabel jLabel1 = new JLabel("Id");
    JLabel jLabel2 = new JLabel("Nama_Hewan");
    JLabel jLabel3 = new JLabel("Kategori");
    JLabel jLabel4 = new JLabel("Harga");
    JLabel jLabel5 = new JLabel("Stok");
    
    JTextField eId = new JTextField();
    JTextField eNama_Hewan = new JTextField();
    JTextField eKategori = new JTextField();
    JTextField eHarga = new JTextField();
    JTextField eStok = new JTextField();
    
    JButton bSimpan = new JButton("Simpan");
    JButton bBatal = new JButton("Batal");

public void setKomponen(){
    getContentPane().setLayout(null);
    getContentPane().add(jLabel1);
    getContentPane().add(jLabel2);
    getContentPane().add(jLabel3);
    getContentPane().add(jLabel4);
    getContentPane().add(jLabel5);
    getContentPane().add(eId);
    getContentPane().add(eNama_Hewan);
    getContentPane().add(eKategori);
    getContentPane().add(eHarga);
    getContentPane().add(eStok);
    getContentPane().add(bSimpan);
    getContentPane().add(bBatal);
    
    jLabel1.setBounds(70,10,50,25);
    jLabel2.setBounds(30,40,50,25);
    jLabel3.setBounds(30,70,50,25);
    jLabel4.setBounds(30,100,50,25);
    jLabel5.setBounds(30,130,50,25);
    
    eId.setBounds(130,10,50,25);
    eNama_Hewan.setBounds(130,40,270,25);
    eKategori.setBounds(130,70,200,25);
    eHarga.setBounds(130,100,270,25);
    eStok.setBounds(130,130,250,25);
    
    bSimpan.setBounds(160,160,100,25);
    bBatal.setBounds(270,160,100,25);
    
    eId.setEditable(false);
    setVisible(true);
    eNama_Hewan.requestFocus();
    setListener();
    eKategori.requestFocus();
    setListener();
    eHarga.requestFocus();
    setListener();
    eStok.requestFocus();
    setListener();
}

public PetTambahFrame1(){
    status = SEDANG_TAMBAH;
    setSize(1000,1000);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setKomponen();
}

public PetTambahFrame1(pet pet){
    status = SEDANG_UBAH;
    setSize(1000,1000);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    eId.setText(String.valueOf(pet.getId()));
    eNama_Hewan.setText(pet.getNama_hewan());
    eKategori.setText(pet.getKategori());
    eHarga.setText(pet.getHarga());
    eStok.setText(pet.getStok());
    setKomponen();
}

public void setListener(){
    bBatal.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
    
    bSimpan.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Koneksi koneksi = new Koneksi();
                Connection con = koneksi.getConnection();
                PreparedStatement ps;
                if(status==SEDANG_TAMBAH){
                    String executeQuery = "insert into tb_pet (nama_hewan) values (?)";
                    ps = con.prepareStatement(executeQuery);
                    ps.setString(1, eNama_Hewan.getText());
                    ps.setString(2, eKategori.getText());
                    ps.setString(1, eHarga.getText());
                    ps.setString(1, eStok.getText());
                }else{
                    String executeQuery = "update tb_pet set nama_hewan=? where id=?";
                    ps = con.prepareStatement(executeQuery);
                    ps.setString(1, eNama_Hewan.getText());
                    ps.setString(2, eKategori.getText());
                    ps.setString(3, eHarga.getText());
                    ps.setString(4, eStok.getText());
                    ps.setString(5, eId.getText());
                }
                ps.executeUpdate();
            } catch (SQLException ex){
                System.err.println(ex);
            }
        }
    });
}

}
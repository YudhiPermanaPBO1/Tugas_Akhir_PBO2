package frame.pet;

import db.Koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.management.Query;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.pet;

public class PetTampilFrame1 extends javax.swing.JFrame {

    JLabel jLabel = new JLabel("Cari");
    JTextField eCari = new JTextField();
    JButton bCari = new JButton("Cari");
    
    String header [] = {"id","Nama_Hewan","Kategori","Harga","Stok"};
    TableModel tableModel = new DefaultTableModel(header, 0);
    JTable tPet = new JTable(tableModel);
    JScrollPane jScrollPane = new JScrollPane(tPet);
    
    JButton bTambah = new JButton("Tambah");
    JButton bUbah = new JButton("Ubah");
    JButton bHapus = new JButton("Hapus");
    JButton bBatal = new JButton("Batal");
    JButton bTutup = new JButton("Tutup");
    
    pet pet;
    
    public void setKomponen(){
    getContentPane().setLayout(null);
    getContentPane().add(jLabel);
    getContentPane().add(eCari);
    getContentPane().add(jScrollPane);
    getContentPane().add(bCari);
    getContentPane().add(bTambah);
    getContentPane().add(bUbah);
    getContentPane().add(bHapus);
    getContentPane().add(bBatal);
    getContentPane().add(bTutup);
    
    jLabel.setBounds(10,10,50,25);
    eCari.setBounds(60,10,330,25);
    bCari.setBounds(400,10,70,25);
    bTutup.setBounds(400,220,70,25);
    bTambah.setBounds(10,220,80,25);
    bUbah.setBounds(95,220,70,25);
    bHapus.setBounds(170,220,70,25);
    bBatal.setBounds(245,220,70,25);
    jScrollPane.setBounds(10,45,460,160);
    
    resetTable("");
    setListener();
    setVisible(true);
       
}
    
    public PetTampilFrame1() {
    setSize(500,300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setKomponen();
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    public static void main(String args[]) {
       PetTampilFrame1 petTampilFrame1 = new PetTampilFrame1();
    }
    
    public ArrayList<pet> getPetList(String keyword){
    ArrayList<pet> petList = new ArrayList<pet>();
    Koneksi koneksi = new Koneksi();
    Connection connetion = koneksi.getConnection();
    
    String query = "SELECT * FROM tb_pet "+keyword;
    Statement statement;
    ResultSet resultSet;
    
    try {
        statement = connetion.createStatement();
        resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            pet = new pet(resultSet.getInt("id"),
                    resultSet.getString("nama_hewan"),
                    resultSet.getString("kategori"),
                    resultSet.getString("harga"),
                    resultSet.getString("stok"));
            petList.add(pet);
        }
    } catch (SQLException | NullPointerException ex){
        System.err.println("Koneksi Null Gagal");
    }
    return petList;
} 
    
    public final void selectPet(String keyword){
    ArrayList<pet> list = getPetList(keyword);
    DefaultTableModel model = (DefaultTableModel)tPet.getModel();
    Object[] row = new Object[2];
    
    for (int i = 0; i < list.size(); i++){
        row[0] = list.get(i).getId();
        row[1] = list.get(i).getNama_hewan();
        row[2] = list.get(i).getKategori();
        row[3] = list.get(i).getHarga();
        row[4] = list.get(i).getStok();
        
        model.addRow(row);
    }
}
    
 public final void resetTable(String keyword){
    DefaultTableModel model = (DefaultTableModel)tPet.getModel();
    model.setRowCount(0);
    selectPet(keyword);
}

public void setListener(){
    bTutup.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();        }
    });
    
    bCari.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            resetTable(" WHERE nama_hewan like '%"+eCari.getText()+"%'");
        }   
    });

    bBatal.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            resetTable("");
        }
    });
    
    bHapus.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        int i = tPet.getSelectedRow();
        int pilihan = JOptionPane.showConfirmDialog(null,
                       "yakin mau hapus ?",
                       "konfirmasi hapus",
                       JOptionPane.YES_NO_OPTION);
        if(pilihan==0){
            if(i>=0){
            try {
                TableModel model = tPet.getModel();
                Koneksi koneksi = new Koneksi();
                Connection con = koneksi.getConnection();
                String executeQuery = "delete from tb_pet where id =?";
                PreparedStatement ps = con.prepareStatement(executeQuery);
                ps.setString(1, model.getValueAt(i,0).toString());
                ps.executeUpdate();
            } catch (SQLException ex){
                System.err.println(ex);
            }
            }else{
                JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus");      
            }
        }
        resetTable("");
        }
    });
    
    bUbah.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = tPet.getSelectedRow();
            if (i>=0){
                TableModel model = tPet.getModel();
                pet = new pet();
                pet.setId(Integer.parseInt(model.getValueAt(i, 0).toString()));
                pet.setNama_hewan(model.getValueAt(i, 1).toString());
                PetTambahFrame1 petTambahFrame1 = new PetTambahFrame1(pet);
            }else{
                JOptionPane.showMessageDialog(null, "Pilih data yang ingin diubah");
            }
        }
    });
    
    bTambah.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            PetTambahFrame1 petTambahFrame1= new PetTambahFrame1();
        }
    });
    
    addWindowListener(new java.awt.event.WindowAdapter(){
        public void windowActivated(java.awt.event.WindowEvent evt){
            resetTable("");
        }
    });
}                     
}

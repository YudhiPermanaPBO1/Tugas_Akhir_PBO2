package model;

public class pet {
    
    private int id;
    private String nama_hewan;
    private String kategori;
    private String harga;
    private String stok;
    
    public pet(){
    
    }
    
    public pet(int id,String nama_hewan){
        this.id = id;
        this.nama_hewan = nama_hewan;
    }

    public pet(int id, String nama_hewan, String kategori, String harga, String stok) {
        this.id = id;
        this.nama_hewan = nama_hewan;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama_hewan(String namaHewan) {
        this.nama_hewan = nama_hewan;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
    
    public void setStok(String stok) {
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public String getNama_hewan() {
        return nama_hewan;
    }

    public String getKategori() {
        return kategori;
    }

    public String getHarga() {
        return harga;
    }
    
    public String getStok() {
        return stok;
    }
    
}

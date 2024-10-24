package qlsach;

import java.io.Serializable;

public class Sach implements Serializable {
    // Data fields
    private String maSach;
    private String tuaSach;
    private String tacGia;
    private int namXB;
    private String nhaXB;
    private int soTrang;
    private double donGia;
    private String isbn;

    // Constructor with all fields
    public Sach(String maSach, String tuaSach, String tacGia, int namXB,
                String nhaXB, int soTrang, double donGia, String isbn) {
        this.maSach = maSach;
        this.tuaSach = tuaSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.nhaXB = nhaXB;
        this.soTrang = soTrang;
        this.donGia = donGia;
        this.isbn = isbn;
    }

    // Constructor with only maSach
    public Sach(String maSach) {
        this(maSach, "tua sach", "tac gia", 0, "nha xb", 0, 0.0, "isbn");
    }

    // Getters and setters
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTuaSach() {
        return tuaSach;
    }

    public void setTuaSach(String tuaSach) {
        this.tuaSach = tuaSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // hashCode method
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((maSach == null) ? 0 : maSach.hashCode());
        return result;
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sach other = (Sach) obj;
        if (maSach == null) {
            if (other.maSach != null)
                return false;
        } else if (!maSach.equalsIgnoreCase(other.maSach))
            return false;
        return true;
    }

    // toString method
    @Override
    public String toString() {
        return maSach + ";" + tuaSach + ";" + tacGia + ";" + namXB + ";" + nhaXB + ";" + soTrang + ";" + donGia + ";" + isbn;
    }
}

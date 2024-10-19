package ngoLeMinhPhuong_21074851;

import java.io.Serializable;
import java.util.Objects;

public class NhanVien implements Serializable{
	private String maNV;  // Đổi từ int sang String
	private String ho;
	private String ten; 
	private boolean phai;
	private int tuoi;
	private String phong;
	private double tienLuong;
	
	// Constructor đầy đủ tham số
	public NhanVien (String maNV, String ho, String ten, boolean phai, int tuoi, String phong,
	double tienLuong) {
		super();
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.phai = phai;
		this.tuoi = tuoi;
		this.phong = phong;
		this.tienLuong = tienLuong;
	}
	
	// Constructor chỉ có maNV
	public NhanVien (String maNV) {
		this(maNV, "", "", true, 0, "", 0.0);
	}
	
	// Constructor mặc định
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// Getters và Setters
	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public String getPhong() {
		return phong;
	}

	public void setPhong(String phong) {
		this.phong = phong;
	}

	public double getTienLuong() {
		return tienLuong;
	}

	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}
	
	// hashCode sử dụng maNV (kiểu String)
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	
	// equals so sánh maNV (kiểu String)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	
	// toString
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", phai=" + phai + ", tuoi=" + tuoi
				+ ", phong=" + phong + ", tienLuong=" + tienLuong + "]";
	}
	
	}
	

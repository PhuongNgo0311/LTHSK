package ngoLeMinhPhuong_21074851;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachNhanVien implements Serializable {
	private ArrayList<NhanVien> list;

	// Constructor mặc định
	public DanhSachNhanVien() {
		list = new ArrayList<NhanVien>();
	}

	// Thêm nhân viên vào danh sách
	public boolean themNhanVien(NhanVien nv) {
		if(list.contains(nv))
			return false;
		list.add(nv);
		return true;
	}

	// Xóa nhân viên theo mã nhân viên
	public boolean xoaNhanVien(String maNV) {
		NhanVien nv = new NhanVien(maNV);
		if(list.contains(nv)) {
			list.remove(nv);
			return true;
		}
		return false;
	}

	// Tìm kiếm nhân viên theo mã nhân viên (kiểu String)
	public NhanVien timKiemNV(String maNV) {
		for (NhanVien nv : list) {
			if (nv.getMaNV().equals(maNV)) {
				return nv;
			}
		}
		return null;
	}

	// Sửa thông tin của nhân viên dựa vào mã nhân viên
	public boolean suaNhanVien(String maNV, String hoMoi, String tenMoi, boolean phaiMoi, int tuoiMoi, String phongMoi, double tienLuongMoi) {
		NhanVien nv = timKiemNV(maNV);
		if (nv != null) {
			nv.setHo(hoMoi);
			nv.setTen(tenMoi);
			nv.setPhai(phaiMoi);
			nv.setTuoi(tuoiMoi);
			nv.setPhong(phongMoi);
			nv.setTienLuong(tienLuongMoi);
			return true;
		}
		return false;
	}

	// Lấy nhân viên theo vị trí trong danh sách
	public NhanVien getNhanVien(int i) {
		if(i >= 0 && i < list.size())
			return list.get(i);
		return null;
	}

	// Lấy toàn bộ danh sách nhân viên
	public ArrayList<NhanVien> getList(){
		return list;
	}

	// Lấy tổng số nhân viên
	public int tong() {
		return list.size();
	}


}

package ngoLeMinhPhuong_21074851;

public class Main {
	public static void main(String[] args) {
		DanhSachNhanVien dao = new DanhSachNhanVien();
		new GuiNhanVien(dao).setVisible(true);
	}
} 

package ngoLeMinhPhuong_21074851;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GuiNhanVien extends JFrame implements ActionListener, MouseListener {

	private DanhSachNhanVien dao;
	private List<NhanVien> list;
	private JRadioButton radNu;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private JButton btLuu;
	private JButton btnSua;
	JTextField txtMaNV, txtHo, txtTen, txtTuoi, txtTienLuong;
	private JLabel lblPhong;
	private JComboBox cboPhong;
	private DanhSachNhanVien dsnv;
	private fileDocGhi fi;

	private static final String tenfile = "data//NhanVien";

	public GuiNhanVien(DanhSachNhanVien dao) {
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		this.dao = dao;
		list = dao.getList();

		JPanel pnNorth;
		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		JLabel lblTieuDe;
		pnNorth.add(lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN"));
		lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
		lblTieuDe.setBackground(Color.CYAN);

		Box b = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5;
		JLabel lblMaNV, lblHo, lblTen, lblTuoi, lblPhai, lblTienLuong;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMaNV = new JLabel("Mã nhân viên:"));
		b1.add(txtMaNV = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblHo = new JLabel("Họ:"));
		b2.add(txtHo = new JTextField());
		b2.add(lblTen = new JLabel("Tên nhân viên:"));
		b2.add(txtTen = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblTuoi = new JLabel("Tuổi:"));
		b3.add(txtTuoi = new JTextField());
		b3.add(lblPhai = new JLabel("Phái:"));
		b3.add(radNu = new JRadioButton("Nữ"));

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblPhong = new JLabel("Phòng:"));
		String[] phong = { "Phòng tổ chức", "Phòng kỹ thuật", "Phòng nhân sự", "Phòng tài vụ" };
		b4.add(cboPhong = new JComboBox(phong));
		b4.add(lblTienLuong = new JLabel("Tiền lương:"));
		b4.add(txtTienLuong = new JTextField());

		lblHo.setPreferredSize(lblMaNV.getPreferredSize());
		lblPhai.setPreferredSize(lblMaNV.getPreferredSize());
		lblPhong.setPreferredSize(lblMaNV.getPreferredSize());
		lblTuoi.setPreferredSize(lblMaNV.getPreferredSize());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		String[] headers = "MaNV;Họ;Tên;Phái;Tuổi;Phòng;Tiền Lương".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);

		b5.add(scroll);
		add(b, BorderLayout.CENTER);

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(split, BorderLayout.SOUTH);
		JPanel pnlLeft, pnlRight;
		split.add(pnlLeft = new JPanel());
		split.add(pnlRight = new JPanel());

		pnlLeft.add(new JLabel("Nhập mã số cần tìm: "));
		pnlLeft.add(txtTim = new JTextField(10));
		pnlLeft.add(btnTim = new JButton("Tìm"));
		pnlRight.add(btnThem = new JButton("Thêm"));
		pnlRight.add(btnXoaTrang = new JButton("Xóa trắng"));
		pnlRight.add(btnXoa = new JButton("Xóa"));
		pnlRight.add(btnSua = new JButton("Sửa")); // Button sửa
		pnlRight.add(btLuu = new JButton("Lưu"));

		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btLuu.addActionListener(this);
		btnSua.addActionListener(this); // Thêm action listener cho nút sửa
		table.addMouseListener(this);

		dsnv = new DanhSachNhanVien();
		fi = new fileDocGhi();
		try {
			dsnv = (DanhSachNhanVien) fi.readToFile(tenfile);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Không tìm thấy file");
		}
		hienTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem))
			themActions();
		if (o.equals(btnXoa))
			xoaActions();
		if (o.equals(btnXoaTrang))
			xoaTrangActions();
		if (o.equals(btnTim))
			timActions();
		if (o.equals(btnSua)) // Xử lý khi nhấn nút sửa
			suaActions();
		if (o.equals(btLuu))
			fi = new fileDocGhi();
		try {
			fi.writeToFile(dsnv, tenfile);
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println("aa");
		}
	}

	// Hiển thị dữ liệu lên bảng
	public void hienTable() {
		for (int i = 0; i < dsnv.tong(); i++) {
			NhanVien nv = dsnv.getNhanVien(i);
			String[] dataRow = { nv.getMaNV() + "", nv.getHo(), nv.getTen(), nv.isPhai() ? "Nữ" : "Nam",
					nv.getTuoi() + "", nv.getPhong(), nv.getTienLuong() + "" };
			tableModel.addRow(dataRow);
		}
	}

	private void xoaTrangActions() {
		txtMaNV.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtTuoi.setText("");
		txtTim.setText("");
		txtTienLuong.setText("");
		radNu.setSelected(false);
		txtMaNV.requestFocus();
	}

	private void themActions() {
		try {
			String maNV = txtMaNV.getText();
			String ho = txtHo.getText();
			String ten = txtTen.getText();
			boolean phai = (radNu.isSelected()) ? true : false;
			int tuoi = Integer.parseInt(txtTuoi.getText());
			String phong = (String) cboPhong.getSelectedItem();
			double tienLuong = Double.parseDouble(txtTienLuong.getText());
			NhanVien nv = new NhanVien(maNV, ho, ten, phai, tuoi, phong, tienLuong);
			if (dsnv.themNhanVien(nv)) {
				String[] row = { nv.getMaNV() + "", nv.getHo(), nv.getTen(), phai ? "Nữ" : "Nam", nv.getTuoi() + "",
						nv.getPhong(), nv.getTienLuong() + "" };
				tableModel.addRow(row);
				xoaTrangActions();
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã nhân viên");
				txtMaNV.selectAll();
				txtMaNV.requestFocus();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
		}
	}

	private void suaActions() {
		try {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Chọn một dòng để sửa");
				return;
			}
			// Lấy dữ liệu từ các text field
			String maNV = txtMaNV.getText();
			String ho = txtHo.getText();
			String ten = txtTen.getText();
			boolean phai = radNu.isSelected();
			int tuoi = Integer.parseInt(txtTuoi.getText());
			String phong = cboPhong.getSelectedItem().toString();
			double tienLuong = Double.parseDouble(txtTienLuong.getText());

			// Cập nhật đối tượng NhanVien
			NhanVien nv = dsnv.getNhanVien(row);
			nv.setHo(ho);
			nv.setTen(ten);
			nv.setPhai(phai);
			nv.setTuoi(tuoi);
			nv.setPhong(phong);
			nv.setTienLuong(tienLuong);

			// Cập nhật lại bảng
			table.setValueAt(maNV, row, 0);
			table.setValueAt(ho, row, 1);
			table.setValueAt(ten, row, 2);
			table.setValueAt(phai ? "Nữ" : "Nam", row, 3);
			table.setValueAt(tuoi, row, 4);
			table.setValueAt(phong, row, 5);
			table.setValueAt(tienLuong, row, 6);

			xoaTrangActions();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi khi sửa");
		}
	}

	private void timActions() {
		String maNV = txtTim.getText();
		if (maNV.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nhập mã nhân viên cần tìm");
			return;
		}
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 0).toString().equals(maNV)) {
				table.setRowSelectionInterval(i, i);
				break;
			}
		}
	}

	private void xoaActions() {
	    int row = table.getSelectedRow();
	    if (row != -1) {
	        // Lấy mã nhân viên từ cột đầu tiên của dòng được chọn
	        String maNV = table.getValueAt(row, 0).toString();
	        
	        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa không?", "Xác nhận xóa",
	                JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            // Xóa khỏi danh sách nhân viên dựa trên mã nhân viên
	            if (dsnv.xoaNhanVien(maNV)) {
	                // Xóa khỏi table
	                tableModel.removeRow(row);
	                JOptionPane.showMessageDialog(null, "Đã xóa nhân viên thành công");
	            } else {
	                JOptionPane.showMessageDialog(null, "Không tìm thấy mã nhân viên để xóa");
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa");
	    }
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaNV.setText(table.getValueAt(row, 0).toString());
		txtHo.setText(table.getValueAt(row, 1).toString());
		txtTen.setText(table.getValueAt(row, 2).toString());
		radNu.setSelected(table.getValueAt(row, 3).toString().equals("Nữ"));
		txtTuoi.setText(table.getValueAt(row, 4).toString());
		cboPhong.setSelectedItem(table.getValueAt(row, 5).toString());
		txtTienLuong.setText(table.getValueAt(row, 6).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

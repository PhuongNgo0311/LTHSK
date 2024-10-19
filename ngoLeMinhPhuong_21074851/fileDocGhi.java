package ngoLeMinhPhuong_21074851;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class fileDocGhi {
	public static void writeToFile(DanhSachNhanVien dsnv, String file) throws Exception{
		ObjectOutputStream out = null;
		out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(dsnv);
		out.close();
	}
	
	public Object readToFile(String file) throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Object list = ois.readObject();
		ois.close();
		return list;
	}
}

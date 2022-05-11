package Lecture02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class stockPrice3 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		// stockPrice ���̺� �ְ������͸� �Է��ϴ� ������ �ۼ��Ѵ�. PreparedStatement�� ����ϹǷ� values�� ?ǥ��
		String QueryTxt = "insert into stockPrice shrn_isc, bsop_date, stck_prpr, stck_oprc, stck_hgpr,"
				+ "stck_lwpr, acml_vol, acml_tr_pbmn)" + "values (?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(QueryTxt);
		// �����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		File f = new File("C:\\Users\\local pc\\Desktop\\�ǽ�������\\day_data.StockDailyPrice.csv");
		// ���� ��ü ���� �� �������
		BufferedReader br = new BufferedReader(new FileReader(f));
		// bufferedreader ��ü ����

		String readtxt; // ���� �о ������ string ���� ����



		br.close(); // �ݱ�
		pstmt.close(); // �ݱ�
		conn.close(); // �ݱ�

	}
}

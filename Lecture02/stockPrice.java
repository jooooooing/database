package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class stockPrice {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		Statement stmt = conn.createStatement();
		// �����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		stmt.execute("create table stockPrice(" + // seongnam_stockPrice ���̺� ����
				"shrn_iscd	varchar(50)," + // �����ڵ�
				"bsop_date int, " + // ����				
				"stck_prpr int, " + // ����
				"stck_oprc int, " + // �ð�
				"stck_hgpr int, " + // �ֽ� �ְ�
				"stck_lwpr int, " + // �ֽ� ������
				"acml_vol long," + // ���� �ŷ���
				"acml_tr_pbmn long," + // ���� �ŷ����	
				"PRIMARY KEY (shrn_iscd, bsop_date)" + // PK ����
				") DEFAULT CHARSET=utf8; ");

//		stmt.execute("drop table stockPrice;");
//		stmt.execute("delete from stockPrice;");

		stmt.close(); // �ݱ�
		conn.close(); // �ݱ�
	}
}

package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Wifi1d {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		Statement stmt = conn.createStatement();
		// �����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		stmt.execute("drop table freewifi;");
//		stmt.execute("delete from freewifi;");

		stmt.close(); // �ݱ�
		conn.close(); // �ݱ�
	}
}

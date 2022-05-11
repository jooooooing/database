package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Parkinglot {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		Statement stmt = conn.createStatement();
		// �����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		stmt.execute("create table seongnam_Parkinglot(" + // seongnam_Parkinglot ���̺� ����
				"idnumber	 varchar(50)," + // �����������ȣ
				"name varchar(50), " + // �������
				"classification varchar(50), " + // ����â ����
				"type varchar(50)," + // ����������
				"place_addr_road varchar(200), " + // ���������θ� �ּ�
				"compartment varchar(50), " + // ������ȹ��
				"rankclass varchar(50), " + // ��������
				"partsystem varchar(50), " + // �������౸��
				"operationDay varchar(50), " + // �����
				"weekdayOpenTime varchar(50), " + // ���Ͻ��۽ð�
				"weekdayCloseTime varchar(50), " + // ��������ð�
				"rateInfo varchar(50), " + // �������
				"latitude double, " + // ����
				"longitude double, " + // �浵
				"CONSTRAINT PK PRIMARY KEY (idnumber)" + // PK ����
				") DEFAULT CHARSET=utf8; ");

//		stmt.execute("drop table seongnam_Parkinglot;");
//		stmt.execute("delete from seongnam_Parkinglot;");

		stmt.close(); // �ݱ�
		conn.close(); // �ݱ�
	}
}

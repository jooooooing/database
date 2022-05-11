package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Wifi {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		Statement stmt = conn.createStatement();
		// �����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		stmt.execute("create table freewifi(" + // freewifi ���̺� ����
				"inst_place	 varchar(50)," + // ��ġ��Ҹ�
				"inst_place_detail varchar(50), " + // ��ġ��һ�
				"inst_city varchar(50), " + // ��ġ�õ���
				"inst_country varchar(50)," + // ��ġ�ñ�����
				"inst_place_flag varchar(50), " + // ��ġ�ü�����
				"service_provider varchar(50), " + // �����������
				"wifi_ssid varchar(100), " + // wifi ssid
				"inst_date varchar(50), " + // ��ġ���
				"place_addr_road varchar(200), " + // ���������θ� �ּ�
				"place_addr_land varchar(200), " + // �����������ּ�
				"manage_office varchar(50), " + // ���������
				"manage_office_phone varchar(50), " + // ���������ȭ��ȣ
				"latitude double, " + // ����
				"longitude double, " + // �浵
//				"write_date date " + // �����ͱ�������
				"write_date date, " + //�����ͱ�������
				"CONSTRAINT PK PRIMARY KEY (inst_place, inst_place_detail, wifi_ssid, inst_date, manage_office,latitude)" + //PK ���� (5��)
				") DEFAULT CHARSET=utf8; ");

//		stmt.execute("drop table freewifi;");
//		stmt.execute("delete from freewifi;");

		stmt.close(); // �ݱ�
		conn.close(); // �ݱ�
	}
}

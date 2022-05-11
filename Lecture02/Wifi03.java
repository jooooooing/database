package Lecture02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Wifi03 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		Statement stmt = conn.createStatement();
		// �����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		double lat = 37.3860521; // ������ġ ����
		double lng = 127.1214038; // ������ġ �浵

		String QueryTxt; // Querytxt ���� ����
		QueryTxt = String.format(
				"select * from freewifi where " + "SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2) ) = "
						+ "(select MIN( SQRT( POWER( latitude-%f,2) + POWER(longitude-%f,2 ) ) ) from freewifi);",
				lat, lng, lat, lng); // query�� ��ɹ� ����

//		QueryTxt = "select * from freewifi";
//		QueryTxt = "select * from freewifi where service_provider ='SKT'"; //���� �����簡 SKT�� �͸� ��ȸ
//		QueryTxt = "select * from freewifi where inst_country ='������'"; //��ġ�õ����� �������ΰ͸� ��ȸ

		ResultSet rset = stmt.executeQuery(QueryTxt); // resultset ����
		int iCnt = 0; // �� ����

		while (rset.next()) { // while �ݺ�
			System.out.printf("*(%d)***************************\n", iCnt++);
			System.out.printf("��ġ��Ҹ� : %s\n", rset.getString(1));
			System.out.printf("��ġ��һ� : %s\n", rset.getString(2));
			System.out.printf("��ġ�õ��� : %s\n", rset.getString(3));
			System.out.printf("��ġ�ñ����� : %s\n", rset.getString(4));
			System.out.printf("��ġ�ü����� : %s\n", rset.getString(5));
			System.out.printf("����������� : %s\n", rset.getString(6));
			System.out.printf("��������SSID : %s\n", rset.getString(7));
			System.out.printf("��ġ��� : %s\n", rset.getString(8));
			System.out.printf("���������θ��ּ� : %s\n", rset.getString(9));
			System.out.printf("�����������ּ� : %s\n", rset.getString(10));
			System.out.printf("��������� : %s\n", rset.getString(11));
			System.out.printf("���������ȭ��ȣ : %s\n", rset.getString(12));
			System.out.printf("���� : %f\n", rset.getFloat(13));
			System.out.printf("�浵 : %f\n", rset.getFloat(14));
			System.out.printf("�����ͱ������� : %s\n", rset.getString(15));
			System.out.printf("***********************************\n");
		}
		rset.close(); // �ݱ�
		stmt.close(); // �ݱ�
		conn.close(); // �ݱ�

	}

}

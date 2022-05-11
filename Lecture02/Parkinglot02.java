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

public class Parkinglot02 {

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
				"select * from seongnam_Parkinglot where " + "SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2) ) = "
						+ "(select MIN( SQRT( POWER( latitude-%f,2) + POWER(longitude-%f,2 ) ) ) from seongnam_Parkinglot);",lat, lng, lat, lng); // query�� ��ɹ� ����

		ResultSet rset = stmt.executeQuery(QueryTxt); // resultset ����
		
		int iCnt = 0; // �� ����

		while (rset.next()) { // while �ݺ�
			System.out.printf("*(%d)***************************\n", iCnt++);
			System.out.printf("�����������ȣ�� : %s\n", rset.getString(1));
			System.out.printf("������� : %s\n", rset.getString(2));
			System.out.printf("����â ���� : %s\n", rset.getString(3));
			System.out.printf("���������� : %s\n", rset.getString(4));
			System.out.printf("���������θ� �ּ� : %s\n", rset.getString(5));
			System.out.printf("������ȹ�� : %s\n", rset.getString(6));
			System.out.printf("�������� : %s\n", rset.getString(7));
			System.out.printf("�������౸�� : %s\n", rset.getString(8));
			System.out.printf("����� : %s\n", rset.getString(9));
			System.out.printf("���Ͻ��۽ð� : %s\n", rset.getString(10));
			System.out.printf("��������ð� : %s\n", rset.getString(11));
			System.out.printf("������� : %s\n", rset.getString(12));
			System.out.printf("���� : %f\n", rset.getFloat(13));
			System.out.printf("�浵 : %f\n", rset.getFloat(14));
			System.out.printf("***********************************\n");
		}
		
		rset.close(); // �ݱ�
		stmt.close(); // �ݱ�
		conn.close(); // �ݱ�

	}
}

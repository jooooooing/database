package Lecture01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); //DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31"); 
		//����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)
		
		Statement stmt = conn.createStatement(); //�����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����
		ResultSet rset = stmt.executeQuery("show databases;"); //����� SQL���� �����ϰ� resultset��ü�� ��ȯ

		while (rset.next()) { //
			System.out.println("�� : " + rset.getString(1)); //��� 
		}

		rset.close(); //�ݱ�
		stmt.close(); //�ݱ�
		conn.close(); //�ݱ�

	}

}

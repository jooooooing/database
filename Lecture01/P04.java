package Lecture01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P04 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		 
		Class.forName("com.mysql.jdbc.Driver"); //DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		//����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)
		
		Statement stmt = conn.createStatement();  //�����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����
		ResultSet rset = stmt.executeQuery("select * from examtable;"); //����� SQL���� �����ϰ� resultset��ü�� ��ȯ
		
		System.out.println("  �̸�  �й� ���� ���� ����"); //��� ���
		while(rset.next()) { //resultset �ݺ�
			System.out.printf("%4s %6d %3d %3d %3d \n", rset.getString(1),rset.getInt(2),rset.getInt(3),rset.getInt(4),rset.getInt(5)); //���
		}		
		
		rset.close(); //�ݱ�
		stmt.close(); //�ݱ�
		conn.close(); //�ݱ�
	}

}

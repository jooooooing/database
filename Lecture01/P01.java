package Lecture01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); //DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		//����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)
		
		Statement stmt = conn.createStatement(); //�����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		stmt.execute( "create table examtable1(" + "name varchar(20)," + "studentid int not null primary key,"
				+ "kor int," + "eng int," + "mat int)" + "DEFAULT CHARSET=utf8;");		
		//��� examtable�� ����� 20�� name, studentid int�̰� ���� �־���ϰ� primary key�� ����, kor int, eng int, mat int�� ������ 

	}

}

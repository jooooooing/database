package Lecture01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P03 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		
		Class.forName("com.mysql.jdbc.Driver"); //DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		//����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)
		
		Statement stmt = conn.createStatement(); //�����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����
		
		
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('����', 209901, 95,100,95);"); //����~���� �� �Է�
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('����', 209902, 100,100,100);"); //����~���� �� �Է�
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('���', 209903, 100,95,100);"); //����~���� �� �Է�
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('�糪', 209904, 100,95,90);"); //����~���� �� �Է�
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('��ȿ', 209905, 80,100,70);"); //����~���� �� �Է�
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('�̳�', 209906, 95,90,95);");  //����~���� �� �Է�
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('����', 209907, 100,90,100);"); //����~���� �� �Է�
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('ä��', 209908, 100,75,90);"); //����~���� �� �Է�
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('����', 209909, 100,100,70);"); //����~���� �� �Է�
		 
		stmt.close(); //�ݱ�
		conn.close(); //�ݱ�
	}

}

package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class reportCard3 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub		
		
		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		Statement stmt = conn.createStatement();
		// �����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		
		int LineCnt = 0; //���� ī��Ʈ ���� ����
			
			String QueryTxt; //�����ؽ�Ʈ String ���� ����			
			//�����ؽ�Ʈ = ���� ���� �� ����,  ���� ����
			QueryTxt = String.format("select *, kor+eng+math, (kor+eng+math)/3 from reportCard limit 0, 30" +
									"select avg(kor), avg(eng), avg(math);");			
			stmt.execute(QueryTxt); //�����ؽ�Ʈ�� ������	
			ResultSet rset = stmt.executeQuery(QueryTxt); // resultset ����				
//		
//		QueryTxt = String.format(
//				"select * from seongnam_Parkinglot where " + "SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2) ) = "
//						+ "(select MIN( SQRT( POWER( latitude-%f,2) + POWER(longitude-%f,2 ) ) ) from seongnam_Parkinglot);",lat, lng, lat, lng); // query�� ��ɹ� ����
			
			int iCnt = 0; // �� ����

		while (rset.next()) { // while �ݺ�
			System.out.printf("*(%d)***************************\n", iCnt++);
			System.out.printf("�й� : %s\n", rset.getString(1));
			System.out.printf("�̸� : %s\n", rset.getString(2));
			System.out.printf("���� : %d\n", rset.getInt(3));
			System.out.printf("���� : %d\n", rset.getInt(4));
			System.out.printf("���� : %d\n", rset.getInt(5));
			System.out.printf("3���� �հ� : %.0f\n", rset.getFloat(6));
			System.out.printf("3���� ��� : %.0f\n", rset.getFloat(7));
			System.out.printf("********************************\n");
		}			
		
		rset.close(); // �ݱ�
		stmt.close(); //�ݱ�
		conn.close(); //�ݱ�
	}
}

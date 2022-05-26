package Lecture03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class P01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Class.forName("com.mysql.jdbc.Driver");
		//����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "koposw31");
		//�����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����
		Statement stmt = conn.createStatement();
		Statement stmt2 = conn.createStatement(); 	
		Statement stmt3 = conn.createStatement();
		
		String sql = "select count(age), (select count(id) from hubotable) from tupyotable;";
		String sql2 = "select * from hubotable;";
		String sql3 = "select a.id, name, ifnull(tcount,0) from hubotable as a left outer join (select id, count(*) as tcount from tupyotable group by id) tcount on (a.id=tcount.id);";
		
		
		ResultSet rset1 = stmt.executeQuery(sql);// 1��ü ��ǥ��, 2��ü �ο�
		ResultSet rset2 = stmt2.executeQuery(sql2);// 1���� ��ȣ 2���� �ĺ��� �̸�. 
		ResultSet rset3 = stmt3.executeQuery(sql3);// 1���� �̸�, 2�� �ĺ��� ��ǥ��
		
		int totalVote = 0; //��ü ��ǥ��
		int totalPeople = 0; //�� �ο�
		String kiho;
		String name;
		String name2;
		int pickNum = 0;
		while (rset1.next()) {
			totalVote = rset1.getInt(1);        
			totalPeople = rset1.getInt(2);    	
		}
		 rset1.close(); //�ݱ�


		while (rset2.next()) {
			kiho = rset2.getString(1);        
			name = rset2.getString(2);   		
		}

		rset2.close(); //�ݱ�
		
		while (rset3.next()) {		
				System.out.println("<tr>");			
				System.out.println("<td width = 75><p align = center><a href='C_02.jsp?kiho="+rset3.getInt(1)+"&name="+rset3.getString(2)+"'> " +rset3.getInt(1)+ "��" + rset3.getString(2) + "</a></p></td>");
				int pickRate = (int)((float)rset3.getInt(3)/(float)totalVote*100.0);
				System.out.println(rset3.getInt(3));
				System.out.println(totalVote);
				System.out.println(pickRate);
				
				System.out.println("<td width = 500><p align = left><img src ='bar.jpg' width = "+350*(pickRate/100)+" height=20> " + rset3.getInt(3) + " (" + (pickRate) +"%) </p></td>");
				System.out.println("</tr>");
				System.out.println("---");

	        }	
		rset3.close(); //�ݱ�
		
		stmt.close(); //�ݱ�
		stmt2.close(); //�ݱ�
		stmt3.close(); //�ݱ�
		conn.close(); //�ݱ�
		System.out.println("</table>");	
		System.out.println("<h3> �� ��ǥ�� : " + totalVote + " ǥ </h3>");	
}
	

}

package Lecture03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class P01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Class.forName("com.mysql.jdbc.Driver");
		//드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "koposw31");
		//데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성
		Statement stmt = conn.createStatement();
		Statement stmt2 = conn.createStatement(); 	
		Statement stmt3 = conn.createStatement();
		
		String sql = "select count(age), (select count(id) from hubotable) from tupyotable;";
		String sql2 = "select * from hubotable;";
		String sql3 = "select a.id, name, ifnull(tcount,0) from hubotable as a left outer join (select id, count(*) as tcount from tupyotable group by id) tcount on (a.id=tcount.id);";
		
		
		ResultSet rset1 = stmt.executeQuery(sql);// 1전체 득표수, 2전체 인원
		ResultSet rset2 = stmt2.executeQuery(sql2);// 1번이 기호 2번이 후보자 이름. 
		ResultSet rset3 = stmt3.executeQuery(sql3);// 1번이 이름, 2번 후보별 득표수
		
		int totalVote = 0; //전체 투표수
		int totalPeople = 0; //총 인원
		String kiho;
		String name;
		String name2;
		int pickNum = 0;
		while (rset1.next()) {
			totalVote = rset1.getInt(1);        
			totalPeople = rset1.getInt(2);    	
		}
		 rset1.close(); //닫기


		while (rset2.next()) {
			kiho = rset2.getString(1);        
			name = rset2.getString(2);   		
		}

		rset2.close(); //닫기
		
		while (rset3.next()) {		
				System.out.println("<tr>");			
				System.out.println("<td width = 75><p align = center><a href='C_02.jsp?kiho="+rset3.getInt(1)+"&name="+rset3.getString(2)+"'> " +rset3.getInt(1)+ "번" + rset3.getString(2) + "</a></p></td>");
				int pickRate = (int)((float)rset3.getInt(3)/(float)totalVote*100.0);
				System.out.println(rset3.getInt(3));
				System.out.println(totalVote);
				System.out.println(pickRate);
				
				System.out.println("<td width = 500><p align = left><img src ='bar.jpg' width = "+350*(pickRate/100)+" height=20> " + rset3.getInt(3) + " (" + (pickRate) +"%) </p></td>");
				System.out.println("</tr>");
				System.out.println("---");

	        }	
		rset3.close(); //닫기
		
		stmt.close(); //닫기
		stmt2.close(); //닫기
		stmt3.close(); //닫기
		conn.close(); //닫기
		System.out.println("</table>");	
		System.out.println("<h3> 총 투표수 : " + totalVote + " 표 </h3>");	
}
	

}

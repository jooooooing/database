package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class reportCard4 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub		
		
		Class.forName("com.mysql.cj.jdbc.Driver"); // DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// 드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)

		Statement stmt = conn.createStatement();
		// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성
		
		ResultSet rset = stmt.executeQuery("select *, kor+eng+math, (kor+eng+math)/3 from reportCard limit 0, 30;");
		
		System.out.println("  학번    이름   국어   영어   수학  총점  평균 \n");
		
		while (rset.next()) {
			System.out.printf("%6d   %5s  %3d  %3d  %3d  %4d  %3.2f \n", rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getInt(4), rset.getInt(5), rset.getInt(6), rset.getFloat(7));
		}

		
		ResultSet rset2 = stmt.executeQuery(" select sum(kor),sum(eng),sum(math),sum(kor+eng+math), sum((kor+eng+math)/3.0) from (select kor,eng,math from reportCard limit 0,30)as sum;");
	      if (rset2.next()) {
	         System.out.println("====================================================");
	         System.out.printf("   페이지 합계:  %5d  %5d  %5d  %5d  %5.2f \n" , rset2.getInt(1) , rset2.getInt(2) ,rset2.getInt(3),rset2.getInt(4),rset2.getFloat(5));
	      }
	      rset2.close();
	      
	      ResultSet rset3 = stmt.executeQuery("select avg(kor),avg(eng),avg(math),avg(kor+eng+math), avg((kor+eng+math)/3.0) from (select kor,eng,math from reportCard limit 0,30)as avg;");
	      if (rset3.next()) {
	         System.out.printf("   페이지 평균:  %5.2f  %5.2f  %5.2f  %5.2f  %5.2f\n" , rset3.getFloat(1) , rset3.getFloat(2) ,rset3.getFloat(3),rset3.getFloat(4),rset3.getFloat(5));
	      }
	      rset3.close();

	      ResultSet rset4 = stmt.executeQuery("select sum(kor),sum(eng),sum(math),sum(kor+eng+math), sum((kor+eng+math)/3.0) from reportCard;");
	      if (rset4.next()) {
	         System.out.println("====================================================");
	         System.out.printf("   누적 합계:  %5d  %5d  %5d  %5d  %5.2f \n" , rset4.getInt(1) , rset4.getInt(2) ,rset4.getInt(3),rset4.getInt(4),rset4.getFloat(5));
	      }
	      rset4.close();
	      
	      ResultSet rset5 = stmt.executeQuery("select avg(kor),avg(eng),avg(math),avg(kor+eng+math), avg((kor+eng+math)/3.0) from reportCard;");
	      if (rset5.next()) {
	         System.out.printf("   누적 평균:  %5.2f  %5.2f  %5.2f  %5.2f  %5.2f\n" , rset5.getFloat(1) , rset5.getFloat(2) ,rset5.getFloat(3),rset5.getFloat(4),rset5.getFloat(5));
	      }
	      rset5.close();
	      
		stmt.close(); //닫기
		conn.close(); //닫기
	}
}

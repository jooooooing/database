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
		
	      String QueryTxt;
	      
	      for(int i = 0; i < 1000; i=i+30) { //0부터 999번까지의 성적을 추출하기 위해 반복문 실행
	         QueryTxt = String.format(" select *, kor+eng+math, (kor+eng+math)/3 from reportCard limit %d, 30;", i); //쿼리 문 작성 전체와 총합과 평균값을 30까지 제한을 두고 출력
	         
	          ResultSet rset = stmt.executeQuery(QueryTxt); // 첫번째 결과 값 출력 
	          //30개씩 페이지 나누기
	          System.out.printf("******************************************************\n");   //구분선 출력
	           System.out.printf("%s %4s %6s %4s %4s %4s %4s\n", "학번", "이름", "국어", "영어", "수학", "합계", "평균");
	          // 명령을 실행한 결과를 출력하기 위한 while문 선언, 빈값이 들어오면 while문 종료
	           while (rset.next()) {//첫번째 결과 출력하기 위한 구문 작성
	              System.out.printf("%04d %6s %5d %6d %6d %6d  %6.2f \n", rset.getInt(1),rset.getString(2),rset.getInt(3),rset.getInt(4),rset.getInt(5),rset.getInt(6),rset.getDouble(7));   
	          }
	          System.out.printf("***************************************************\n");   //구분선 출력
	          
	          
	          //현재 페이지 과목별 총합과 평균값 출력
	          System.out.printf("현재 페이지\n");   //구분선 출력
	          QueryTxt = String.format(" select sum(kor), sum(eng), sum(math), sum(kor+eng+math), sum((kor+eng+math)/3), " //
	                + "avg(kor), avg(eng), avg(math), avg(kor+eng+math), avg((kor+eng+math)/3) from (select kor,eng,math from reportCard limit %d,30)as sum;", i);
	          
	          ResultSet rset2 = stmt.executeQuery(QueryTxt);
	          //현재페이지의 결과값을 2번으로 받아서 출력한다.
	          while (rset2.next()) {
	              System.out.printf("              %5d %6d %6d %6d  %6.2f \n", rset2.getInt(1),rset2.getInt(2),rset2.getInt(3),rset2.getInt(4),rset2.getDouble(5));
	              System.out.printf("              %5.2f %6.2f %6.2f %6.2f  %6.2f \n", rset2.getDouble(6),rset2.getDouble(7),rset2.getDouble(8),rset2.getDouble(9),rset2.getDouble(10));   
	          }
	          
	          System.out.printf("누적 페이지\n");   //구분선 출력
	          //누적 페이지의 과목별 총합과 평균값 출력
	          if (i == 990) {
	             i=970;
	          }
	          QueryTxt = String.format(" select sum(kor), sum(eng), sum(math), sum(kor+eng+math), sum((kor+eng+math)/3), "
	                + "avg(kor), avg(eng), avg(math), avg(kor+eng+math), avg((kor+eng+math)/3) from (select kor,eng,math from reportCard limit 0, %d)as avg;", (i+30));
	          //누적 페이지의 결과값을 3번으로 받아서 출력한다.
	          ResultSet rset3 = stmt.executeQuery(QueryTxt);
	          
	          while (rset3.next()) {
	              System.out.printf("              %5d %6d %6d %6d  %6.2f \n", rset3.getInt(1),rset3.getInt(2),rset3.getInt(3),rset3.getInt(4),rset3.getDouble(5));
	              System.out.printf("              %5.2f %6.2f %6.2f %6.2f  %6.2f \n", rset3.getDouble(6),rset3.getDouble(7),rset3.getDouble(8),rset3.getDouble(9),rset3.getDouble(10));   
	          }
	              
	          rset.close(); //ResultSet1 종료 
	          rset2.close();//ResultSet2 종료 
	          rset3.close();//ResultSet3 종료 
	      }
	       

	        stmt.close(); //Statement 종료
	        conn.close(); //Connection 종료
	      }

}

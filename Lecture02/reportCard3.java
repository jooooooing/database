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
		
		Class.forName("com.mysql.cj.jdbc.Driver"); // DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// 드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)

		Statement stmt = conn.createStatement();
		// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성

		
		int LineCnt = 0; //라인 카운트 변수 선언
			
			String QueryTxt; //쿼리텍스트 String 변수 선언			
			//쿼리텍스트 = 변수 선언 및 저장,  형식 지정
			QueryTxt = String.format("select *, kor+eng+math, (kor+eng+math)/3 from reportCard limit 0, 30" +
									"select avg(kor), avg(eng), avg(math);");			
			stmt.execute(QueryTxt); //쿼리텍스트를 실행함	
			ResultSet rset = stmt.executeQuery(QueryTxt); // resultset 정의				
//		
//		QueryTxt = String.format(
//				"select * from seongnam_Parkinglot where " + "SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2) ) = "
//						+ "(select MIN( SQRT( POWER( latitude-%f,2) + POWER(longitude-%f,2 ) ) ) from seongnam_Parkinglot);",lat, lng, lat, lng); // query에 명령문 정의
			
			int iCnt = 0; // 행 숫자

		while (rset.next()) { // while 반복
			System.out.printf("*(%d)***************************\n", iCnt++);
			System.out.printf("학번 : %s\n", rset.getString(1));
			System.out.printf("이름 : %s\n", rset.getString(2));
			System.out.printf("국어 : %d\n", rset.getInt(3));
			System.out.printf("영어 : %d\n", rset.getInt(4));
			System.out.printf("수학 : %d\n", rset.getInt(5));
			System.out.printf("3과목 합계 : %.0f\n", rset.getFloat(6));
			System.out.printf("3과목 평균 : %.0f\n", rset.getFloat(7));
			System.out.printf("********************************\n");
		}			
		
		rset.close(); // 닫기
		stmt.close(); //닫기
		conn.close(); //닫기
	}
}

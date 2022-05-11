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

		ResultSet rset = stmt.executeQuery("select *, kor+eng+math, (kor+eng+math)/3 from reportCard limit 0, 30;"); //resultSet 설정 = 전체 읽기, 3과목합, 3과목 평균을 30개씩 출력 쿼리

		System.out.println("  학번    이름   국어   영어   수학  총점  평균 \n"); //헤더 출력

		while (rset.next()) {
			System.out.printf("%6d   %5s  %3d  %3d  %3d  %4d  %3.2f \n", rset.getInt(1), rset.getString(2), // 칼럼 1 ~ 7까지의 값들을 rset으로 불러온 후 출력
					rset.getInt(3), rset.getInt(4), rset.getInt(5), rset.getInt(6), rset.getFloat(7));
		}

		ResultSet rset2 = stmt.executeQuery( //rset2 설정 = 페이지 합계 출력 쿼리문
				" select sum(kor),sum(eng),sum(math),sum(kor+eng+math), sum((kor+eng+math)/3.0) from (select kor,eng,math from reportCard limit 0,30)as sum;"); 
					// 과목별 합계, 3과목합계, 과목평균 쿼리
		if (rset2.next()) {
			System.out.println("===================================================="); //구분선 출력
			System.out.printf("   페이지 합계:  %5d  %5d  %5d  %5d  %5.2f \n", rset2.getInt(1), rset2.getInt(2), // 칼럼 1 ~ 7까지의 값들을 rset2로 불러온 후 출력
					rset2.getInt(3), rset2.getInt(4), rset2.getFloat(5));
		}
		rset2.close(); //rset2 닫기

		ResultSet rset3 = stmt.executeQuery( //rset3 설정 = 페이지 평균 출력 쿼리문
				"select avg(kor),avg(eng),avg(math),avg(kor+eng+math), avg((kor+eng+math)/3.0) from (select kor,eng,math from reportCard limit 0,30)as avg;");
				//과목별 평균, 3과목평균, 3과목 평균의 평균 쿼리
		if (rset3.next()) {
			System.out.printf("   페이지 평균:  %5.2f  %5.2f  %5.2f  %5.2f  %5.2f\n", rset3.getFloat(1), rset3.getFloat(2), //칼럼 1~7까지 값들을 rset3으로 불러옴
					rset3.getFloat(3), rset3.getFloat(4), rset3.getFloat(5));
		}
		rset3.close(); //rset3 닫기

		ResultSet rset4 = stmt.executeQuery( //rset4 설정 = 누적 합계 출력
				"select sum(kor),sum(eng),sum(math),sum(kor+eng+math), sum((kor+eng+math)/3.0) from reportCard;"); // 누적 과목별 합계, 3과목 합계, 3과목 평균 쿼리
		if (rset4.next()) {
			System.out.println("===================================================="); //구분선 출력
			System.out.printf("   누적 합계:  %5d  %5d  %5d  %5d  %5.2f \n", rset4.getInt(1), rset4.getInt(2), //칼럼 1~7까지 값들을 rset4으로 불러옴
					rset4.getInt(3), rset4.getInt(4), rset4.getFloat(5));
		}
		rset4.close(); //rset4 닫기

		ResultSet rset5 = stmt.executeQuery( //rset5 설정 = 누적 평균 출력 쿼리문
				"select avg(kor),avg(eng),avg(math),avg(kor+eng+math), avg((kor+eng+math)/3.0) from reportCard;");  //누적 과목별 평균, 3과목평균, 3과목 평균의 평균 쿼리
		if (rset5.next()) {
			System.out.printf("   누적 평균:  %5.2f  %5.2f  %5.2f  %5.2f  %5.2f\n", rset5.getFloat(1), rset5.getFloat(2), // 1~7까지 값들을 rset5으로 불러옴
					rset5.getFloat(3), rset5.getFloat(4), rset5.getFloat(5));
		}
		rset5.close(); //rset5 닫기

		stmt.close(); // 닫기
		conn.close(); // 닫기
	}
}

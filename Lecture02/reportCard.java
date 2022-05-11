package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class reportCard {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// 드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)

		Statement stmt = conn.createStatement();
		// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성

		stmt.execute("create table reportCard(" + // repoertCard 테이블 생성
				"idnumber	 varchar(50)," + // 학번
				"name varchar(50), " + // 이름
				"kor int, " + // 국어
				"eng int," + // 영어
				"math int, " + // 수학
				"CONSTRAINT PK PRIMARY KEY (idnumber)" + // PK 설정
				") DEFAULT CHARSET=utf8; ");

//		stmt.execute("drop table reportCard;"); //테이블 완전 삭제
//		stmt.execute("delete from reportCard;"); //테이블 내용 삭제

		stmt.close(); // 닫기
		conn.close(); // 닫기
	}
}

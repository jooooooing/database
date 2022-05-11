package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Parkinglot {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// 드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)

		Statement stmt = conn.createStatement();
		// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성

		stmt.execute("create table seongnam_Parkinglot(" + // seongnam_Parkinglot 테이블 생성
				"idnumber	 varchar(50)," + // 주차장관리번호
				"name varchar(50), " + // 주차장명
				"classification varchar(50), " + // 주자창 구분
				"type varchar(50)," + // 주차장유형
				"place_addr_road varchar(200), " + // 소재지도로명 주소
				"compartment varchar(50), " + // 주차구획수
				"rankclass varchar(50), " + // 급지구분
				"partsystem varchar(50), " + // 부제시행구분
				"operationDay varchar(50), " + // 운영요일
				"weekdayOpenTime varchar(50), " + // 평일시작시간
				"weekdayCloseTime varchar(50), " + // 평일종료시간
				"rateInfo varchar(50), " + // 요금정보
				"latitude double, " + // 위도
				"longitude double, " + // 경도
				"CONSTRAINT PK PRIMARY KEY (idnumber)" + // PK 설정
				") DEFAULT CHARSET=utf8; ");

//		stmt.execute("drop table seongnam_Parkinglot;");
//		stmt.execute("delete from seongnam_Parkinglot;");

		stmt.close(); // 닫기
		conn.close(); // 닫기
	}
}

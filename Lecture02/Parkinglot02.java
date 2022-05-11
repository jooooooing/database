package Lecture02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Parkinglot02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// 드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)

		Statement stmt = conn.createStatement();
		// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성

		double lat = 37.3860521; // 현재위치 위도
		double lng = 127.1214038; // 현재위치 경도

		String QueryTxt; // Querytxt 변수 지정
		QueryTxt = String.format(
				"select * from seongnam_Parkinglot where " + "SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2) ) = "
						+ "(select MIN( SQRT( POWER( latitude-%f,2) + POWER(longitude-%f,2 ) ) ) from seongnam_Parkinglot);",lat, lng, lat, lng); // query에 명령문 정의

		ResultSet rset = stmt.executeQuery(QueryTxt); // resultset 정의
		
		int iCnt = 0; // 행 숫자

		while (rset.next()) { // while 반복
			System.out.printf("*(%d)***************************\n", iCnt++);
			System.out.printf("주차장관리번호명 : %s\n", rset.getString(1));
			System.out.printf("주차장명 : %s\n", rset.getString(2));
			System.out.printf("주자창 구분 : %s\n", rset.getString(3));
			System.out.printf("주차장유형 : %s\n", rset.getString(4));
			System.out.printf("소재지도로명 주소 : %s\n", rset.getString(5));
			System.out.printf("주차구획수 : %s\n", rset.getString(6));
			System.out.printf("급지구분 : %s\n", rset.getString(7));
			System.out.printf("부제시행구분 : %s\n", rset.getString(8));
			System.out.printf("운영요일 : %s\n", rset.getString(9));
			System.out.printf("평일시작시간 : %s\n", rset.getString(10));
			System.out.printf("평일종료시간 : %s\n", rset.getString(11));
			System.out.printf("요금정보 : %s\n", rset.getString(12));
			System.out.printf("위도 : %f\n", rset.getFloat(13));
			System.out.printf("경도 : %f\n", rset.getFloat(14));
			System.out.printf("***********************************\n");
		}
		
		rset.close(); // 닫기
		stmt.close(); // 닫기
		conn.close(); // 닫기

	}
}

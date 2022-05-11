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

public class Wifi03 {

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
				"select * from freewifi where " + "SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2) ) = "
						+ "(select MIN( SQRT( POWER( latitude-%f,2) + POWER(longitude-%f,2 ) ) ) from freewifi);",
				lat, lng, lat, lng); // query에 명령문 정의

//		QueryTxt = "select * from freewifi";
//		QueryTxt = "select * from freewifi where service_provider ='SKT'"; //서비스 제공사가 SKT인 것만 조회
//		QueryTxt = "select * from freewifi where inst_country ='수원시'"; //설치시도명이 수원시인것만 조회

		ResultSet rset = stmt.executeQuery(QueryTxt); // resultset 정의
		int iCnt = 0; // 행 숫자

		while (rset.next()) { // while 반복
			System.out.printf("*(%d)***************************\n", iCnt++);
			System.out.printf("설치장소명 : %s\n", rset.getString(1));
			System.out.printf("설치장소상세 : %s\n", rset.getString(2));
			System.out.printf("설치시도명 : %s\n", rset.getString(3));
			System.out.printf("설치시군구명 : %s\n", rset.getString(4));
			System.out.printf("설치시설구분 : %s\n", rset.getString(5));
			System.out.printf("서비스제공사명 : %s\n", rset.getString(6));
			System.out.printf("와이파이SSID : %s\n", rset.getString(7));
			System.out.printf("설치년월 : %s\n", rset.getString(8));
			System.out.printf("소재지도로명주소 : %s\n", rset.getString(9));
			System.out.printf("소재지지번주소 : %s\n", rset.getString(10));
			System.out.printf("관리기관명 : %s\n", rset.getString(11));
			System.out.printf("관리기관전화번호 : %s\n", rset.getString(12));
			System.out.printf("위도 : %f\n", rset.getFloat(13));
			System.out.printf("경도 : %f\n", rset.getFloat(14));
			System.out.printf("데이터기준일자 : %s\n", rset.getString(15));
			System.out.printf("***********************************\n");
		}
		rset.close(); // 닫기
		stmt.close(); // 닫기
		conn.close(); // 닫기

	}

}

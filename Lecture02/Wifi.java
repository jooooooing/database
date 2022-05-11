package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Wifi {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// 드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)

		Statement stmt = conn.createStatement();
		// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성

		stmt.execute("create table freewifi(" + // freewifi 테이블 생성
				"inst_place	 varchar(50)," + // 설치장소명
				"inst_place_detail varchar(50), " + // 설치장소상세
				"inst_city varchar(50), " + // 설치시도명
				"inst_country varchar(50)," + // 설치시군구명
				"inst_place_flag varchar(50), " + // 설치시설구분
				"service_provider varchar(50), " + // 서비스제공사명
				"wifi_ssid varchar(100), " + // wifi ssid
				"inst_date varchar(50), " + // 설치년월
				"place_addr_road varchar(200), " + // 소재지도로명 주소
				"place_addr_land varchar(200), " + // 소재지지번주소
				"manage_office varchar(50), " + // 관리기관명
				"manage_office_phone varchar(50), " + // 관리기관전화번호
				"latitude double, " + // 위도
				"longitude double, " + // 경도
//				"write_date date " + // 데이터기준일자
				"write_date date, " + //데이터기준일자
				"CONSTRAINT PK PRIMARY KEY (inst_place, inst_place_detail, wifi_ssid, inst_date, manage_office,latitude)" + //PK 설정 (5개)
				") DEFAULT CHARSET=utf8; ");

//		stmt.execute("drop table freewifi;");
//		stmt.execute("delete from freewifi;");

		stmt.close(); // 닫기
		conn.close(); // 닫기
	}
}

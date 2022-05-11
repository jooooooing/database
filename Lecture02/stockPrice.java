package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class stockPrice {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// 드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)

		Statement stmt = conn.createStatement();
		// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성

		stmt.execute("create table stockPrice(" + // seongnam_stockPrice 테이블 생성
				"shrn_iscd	varchar(50)," + // 단축코드
				"bsop_date int, " + // 일자				
				"stck_prpr int, " + // 종가
				"stck_oprc int, " + // 시가
				"stck_hgpr int, " + // 주식 최고가
				"stck_lwpr int, " + // 주식 최저가
				"acml_vol long," + // 누적 거래량
				"acml_tr_pbmn long," + // 누적 거래대금	
				"PRIMARY KEY (shrn_iscd, bsop_date)" + // PK 설정
				") DEFAULT CHARSET=utf8; ");

//		stmt.execute("drop table stockPrice;");
//		stmt.execute("delete from stockPrice;");

		stmt.close(); // 닫기
		conn.close(); // 닫기
	}
}

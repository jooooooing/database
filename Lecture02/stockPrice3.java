package Lecture02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class stockPrice3 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// 드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)

		// stockPrice 테이블에 주가데이터를 입력하는 쿼리를 작성한다. PreparedStatement를 사용하므로 values에 ?표시
		String QueryTxt = "insert into stockPrice shrn_isc, bsop_date, stck_prpr, stck_oprc, stck_hgpr,"
				+ "stck_lwpr, acml_vol, acml_tr_pbmn)" + "values (?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(QueryTxt);
		// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성

		File f = new File("C:\\Users\\local pc\\Desktop\\실습데이터\\day_data.StockDailyPrice.csv");
		// 파일 객체 생성 및 경로지정
		BufferedReader br = new BufferedReader(new FileReader(f));
		// bufferedreader 객체 생성

		String readtxt; // 파일 읽어서 저장할 string 변수 지정



		br.close(); // 닫기
		pstmt.close(); // 닫기
		conn.close(); // 닫기

	}
}

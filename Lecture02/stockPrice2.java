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

public class stockPrice2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// 드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)

		// stockPrice 테이블에 주가데이터를 입력하는 쿼리를 작성한다. PreparedStatement를 사용하므로 values에 ?표시
		String QueryTxt = "insert into stockPrice (shrn_iscd, bsop_date, stck_prpr, stck_oprc, stck_hgpr, stck_lwpr, acml_vol, acml_tr_pbmn)" + "values (?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(QueryTxt);
		// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성

		File f = new File("C:\\Users\\local pc\\Desktop\\실습데이터\\day_data.StockDailyPrice.csv");
		// 파일 객체 생성 및 경로지정
		BufferedReader br = new BufferedReader(new FileReader(f));
		// bufferedreader 객체 생성

		String readtxt; // 파일 읽어서 저장할 string 변수 지정

		if ((readtxt = br.readLine()) == null) { // 첫줄이 null이면 빈파일이므로
			System.out.println("empty file"); // 오류 메세지 출력
			return;
		}

		String[] field_name = readtxt.split(","); // 파일을 쉼표(,)로 구분하여 읽어서 열 이름을 저장할 배열 선언

		int LineCnt = 0; // 라인 카운트 변수 선언
		conn.setAutoCommit(false); // insert 속도 향상을 위해서 AutoCommit을 false로 설정한다.
		long startTime = System.currentTimeMillis(); // 시작시간을 ms 단위로 저장

		while ((readtxt = br.readLine()) != null) { // 읽은 파일의 라인이 null이 아니라면 계속 읽는다.
			String[] field = readtxt.split(","); // 파일을 쉼표(,) 구분하여 읽어서 열 값을 저장할 배열 선언

			pstmt.setString(1, field[2]); // 첫번째 ?에 2번필드 단축코드 입력.
			pstmt.setString(2, field[1]); // 두번째 ?에 1번필드 주식 영업일자 입력.
			pstmt.setString(3, field[3]); // 세번째 ?에 3번필드 주식종가 입력.
			pstmt.setString(4, field[4]); // 네번째 ?에 4번필드 주식시가 입력.
			pstmt.setString(5, field[5]); // 다섯번째 ?에 1번필드 주식 최고가 입력.
			pstmt.setString(6, field[6]); // 여섯번째 ?에 1번필드 주식 최저가 입력.
			pstmt.setString(7, field[11]); // 일곱번째 ?에 1번필드 누적거래량 입력.
			pstmt.setString(8, field[12]); // 여덟번째 ?에 1번필드 누적거래대금 입력.
			pstmt.addBatch(); // addBatch가 성공했음을 출력한다.

			System.out.printf("%d번째 항목 Insert ok\n", LineCnt); // 콘솔에 출력 항목수출력
			pstmt.clearParameters(); // PreparedStatement의 파라미터를 비운다.
			LineCnt++; // 라인 카운트 증가

			try {
				if ((LineCnt & 10000) == 0) { // 메모리 관리를 위해 10000줄씩 읽을때 마다
					pstmt.executeBatch(); // executeBatch()를 호출해서 캐시에 담긴 SQL문장 실행
					conn.commit(); // commit()을 호출해서 데이터를 영구히 저장
				}
			} catch (Exception e) { // 실행중에 에외가 발생하면
				e.printStackTrace(); // 오류 메시지 출력
			}
			
//			if (LineCnt == 100) {
//				break;
//			}
		}
	

		try {
			pstmt.executeBatch();
		} catch (Exception e) { // 실행중에 에외가 발생하면
			e.printStackTrace(); // 오류 메시지 출력
		}

		conn.commit();
		conn.setAutoCommit(true);
		long endTime = System.currentTimeMillis();

		System.out.println("Insert End"); // insert종료 출력
		System.out.println("total : " + LineCnt); // insert종료 출력
		System.out.println("time : " + (endTime - startTime)); // 실행시간 출력

		br.close(); // 닫기
		pstmt.close(); // 닫기
		conn.close(); // 닫기

	}
}

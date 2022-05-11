package Lecture02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Wifi02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver"); //DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		//드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)
		
		Statement stmt = conn.createStatement();
		//데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성
		
		File f = new File("C:\\Users\\local pc\\Desktop\\데이터베이스프로그래밍(v2022)\\전국무료와이파이표준데이터.txt");
		//파일 객체 생성 및 경로지정
		BufferedReader br = new BufferedReader(new FileReader(f));
		//bufferedreader 객체 생성
		
		String readtxt; //파일 읽어서 저장할 string 변수 지정
		
		if((readtxt = br.readLine()) == null) {
			System.out.println("empty file");
			return;			
		}
		String[] field_name = readtxt.split("\t"); //파일을 탭으로 구분하여 읽어서 열 이름을 저장할 배열 선언
		
		int LineCnt = 0; //라인 카운트 변수 선언
		while((readtxt = br.readLine()) != null) {
			String[] field = readtxt.split("\t"); //파일을 탭으로 구분하여 읽어서 열 값을 저장할 배열 선언
			String QueryTxt; //쿼리텍스트 String 변수 선언
			
			//쿼리텍스트 = 변수 선언 및 저장,  형식 지정
			QueryTxt = String.format("insert into freewifi (" 
					+ "inst_place, inst_place_detail, inst_city, inst_country, inst_place_flag,"
					+ "service_provider, wifi_ssid, inst_date, place_addr_road,place_addr_land,"
					+ "manage_office,manage_office_phone,latitude,longitude,write_date)"
					+ "values ("
					+ "'%s', '%s', '%s', '%s', '%s',"
					+ "'%s', '%s', '%s', '%s', '%s',"
					+ "'%s', '%s', %s, %s, '%s');",
					field[0], field[1], field[2], field[3], field[4], //field 인덱스
					field[5], field[6], field[7], field[8], field[9], //field 인덱스
					field[10], field[11], field[12], field[13], field[14]); //field 인덱스
			
			stmt.execute(QueryTxt); //쿼리텍스트를 실행함
			System.out.printf("%d번째 항목 Insert Ok [%s]\n", LineCnt, QueryTxt); //콘솔에 출력 항목수, 쿼리텍스트 내용
			
			LineCnt++;	//라인 카운트 증가				
		}
		br.close(); //닫기
		
		stmt.close(); //닫기
		conn.close(); //닫기

	}

}

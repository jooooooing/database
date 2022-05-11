package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class reportCard2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub		
		Random random = new Random(); //random 객체 생성
		int[] ranKor = new int[1000]; //random kor 배열 생성
		int[] ranEng = new int[1000]; //random eng 배열 생성
		int[] ranMath = new int[1000]; //random math 배열 생성
		String[] idNumber = new String[1000]; //id 배열 생성
		String[] name = new String[1000]; //name 배열 생성
		int cnt = 1; //배열라인 카운트
		
		for (int i = 0; i < 1000; i++) { //반복문 
			if (cnt < 10) { //1의 자리 학번
				idNumber[i] = ("20000" + (i + 1) + "");
			} else if (cnt < 100) { //10의자리 학번
				idNumber[i] = ("2000" + (i + 1) + "");
			} else if (cnt < 1000) { //100의 자리 학번
				idNumber[i] = ("200" + (i + 1) + "");
			} else { //1000의 자리 학번
				idNumber[i] = ("20" + (i + 1) + "");
			}
			name[i] = "홍길" + (i + 1) + "동"; //학생이름 배열 입력
			ranKor[i] = random.nextInt(100); //랜덤 값 배열 입력
			ranEng[i] = random.nextInt(100); //랜덤 값 배열 입력
			ranMath[i] = random.nextInt(100); //랜덤 값 배열 입력
			cnt++; //카운트 +1
		}
		
		Class.forName("com.mysql.cj.jdbc.Driver"); // DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// 드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)

		Statement stmt = conn.createStatement();
		// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성

		
		int LineCnt = 1; //라인 카운트 변수 선언
		for (int i =0 ; i < 1000; i++) {
			
			String QueryTxt; //쿼리텍스트 String 변수 선언
			
			//쿼리텍스트 = 변수 선언 및 저장,  형식 지정
			QueryTxt = String.format("insert into reportCard (" 
					+ "idnumber, name, kor, eng, math)"
					+ "values ("
					+ "'%s', '%s', %d, %d, %d);",
					idNumber[i], name[i], ranKor[i], ranEng[i], ranMath[i]); //field 인덱스			
			
			stmt.execute(QueryTxt); //쿼리텍스트를 실행함
			System.out.printf("%d번째 항목 Insert Ok [%s]\n", LineCnt, QueryTxt); //콘솔에 출력 항목수, 쿼리텍스트 내용			
			LineCnt++;	//라인 카운트 증가				
		}		
		stmt.close(); //닫기
		conn.close(); //닫기
	}
}

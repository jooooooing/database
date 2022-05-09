package Lecture01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P04 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		 
		Class.forName("com.mysql.jdbc.Driver"); //DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		//드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)
		
		Statement stmt = conn.createStatement();  //데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성
		ResultSet rset = stmt.executeQuery("select * from examtable;"); //저장된 SQL문을 실행하고 resultset개체를 반환
		
		System.out.println("  이름  학번 국어 영어 수학"); //헤더 출력
		while(rset.next()) { //resultset 반복
			System.out.printf("%4s %6d %3d %3d %3d \n", rset.getString(1),rset.getInt(2),rset.getInt(3),rset.getInt(4),rset.getInt(5)); //출력
		}		
		
		rset.close(); //닫기
		stmt.close(); //닫기
		conn.close(); //닫기
	}

}

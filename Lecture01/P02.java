package Lecture01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		
		Class.forName("com.mysql.jdbc.Driver"); //DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		//드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)
		
		Statement stmt = conn.createStatement();
		//데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성
		
		stmt.execute("drop table examtable1;");
		
		//명령 examtable을 완전 삭제함
		
		stmt.close(); //닫기
		conn.close(); //닫기
	}
}

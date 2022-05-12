package Lecture01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); //DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		//드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)
		
		Statement stmt = conn.createStatement(); //데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성

		stmt.execute( "create table examtable1(" + "name varchar(20)," + "studentid int not null primary key,"
				+ "kor int," + "eng int," + "mat int)" + "DEFAULT CHARSET=utf8;");		
		//명령 examtable을 만들고 20자 name, studentid int이고 값이 있어야하고 primary key로 설정, kor int, eng int, mat int를 생성함 

	}

}

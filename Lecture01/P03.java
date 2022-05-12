package Lecture01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P03 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		
		Class.forName("com.mysql.jdbc.Driver"); //DB와 연결하는 드라이버 클래스를 찾아 메모리에 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		//드라이버서버에 접속할 수 있는 커넥션 객체를 가져옴 (url, id , pw)
		
		Statement stmt = conn.createStatement(); //데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 생성
		
		
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('나연', 209901, 95,100,95);"); //나연~쯔위 값 입력
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('정연', 209902, 100,100,100);"); //나연~쯔위 값 입력
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('모모', 209903, 100,95,100);"); //나연~쯔위 값 입력
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('사나', 209904, 100,95,90);"); //나연~쯔위 값 입력
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('지효', 209905, 80,100,70);"); //나연~쯔위 값 입력
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('미나', 209906, 95,90,95);");  //나연~쯔위 값 입력
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('다현', 209907, 100,90,100);"); //나연~쯔위 값 입력
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('채영', 209908, 100,75,90);"); //나연~쯔위 값 입력
		stmt.execute("insert into examtable1 (name, studentid, kor, eng, mat) values ('쯔위', 209909, 100,100,70);"); //나연~쯔위 값 입력
		 
		stmt.close(); //닫기
		conn.close(); //닫기
	}

}

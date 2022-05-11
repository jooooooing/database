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
		Random random = new Random(); //random ��ü ����
		int[] ranKor = new int[1000]; //random kor �迭 ����
		int[] ranEng = new int[1000]; //random eng �迭 ����
		int[] ranMath = new int[1000]; //random math �迭 ����
		String[] idNumber = new String[1000]; //id �迭 ����
		String[] name = new String[1000]; //name �迭 ����
		int cnt = 1; //�迭���� ī��Ʈ
		
		for (int i = 0; i < 1000; i++) { //�ݺ��� 
			if (cnt < 10) { //1�� �ڸ� �й�
				idNumber[i] = ("20000" + (i + 1) + "");
			} else if (cnt < 100) { //10���ڸ� �й�
				idNumber[i] = ("2000" + (i + 1) + "");
			} else if (cnt < 1000) { //100�� �ڸ� �й�
				idNumber[i] = ("200" + (i + 1) + "");
			} else { //1000�� �ڸ� �й�
				idNumber[i] = ("20" + (i + 1) + "");
			}
			name[i] = "ȫ��" + (i + 1) + "��"; //�л��̸� �迭 �Է�
			ranKor[i] = random.nextInt(100); //���� �� �迭 �Է�
			ranEng[i] = random.nextInt(100); //���� �� �迭 �Է�
			ranMath[i] = random.nextInt(100); //���� �� �迭 �Է�
			cnt++; //ī��Ʈ +1
		}
		
		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		Statement stmt = conn.createStatement();
		// �����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		
		int LineCnt = 1; //���� ī��Ʈ ���� ����
		for (int i =0 ; i < 1000; i++) {
			
			String QueryTxt; //�����ؽ�Ʈ String ���� ����
			
			//�����ؽ�Ʈ = ���� ���� �� ����,  ���� ����
			QueryTxt = String.format("insert into reportCard (" 
					+ "idnumber, name, kor, eng, math)"
					+ "values ("
					+ "'%s', '%s', %d, %d, %d);",
					idNumber[i], name[i], ranKor[i], ranEng[i], ranMath[i]); //field �ε���			
			
			stmt.execute(QueryTxt); //�����ؽ�Ʈ�� ������
			System.out.printf("%d��° �׸� Insert Ok [%s]\n", LineCnt, QueryTxt); //�ֿܼ� ��� �׸��, �����ؽ�Ʈ ����			
			LineCnt++;	//���� ī��Ʈ ����				
		}		
		stmt.close(); //�ݱ�
		conn.close(); //�ݱ�
	}
}

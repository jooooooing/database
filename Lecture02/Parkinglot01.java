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
import java.util.Random;

public class Parkinglot01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); //DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		//����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)
		
		Statement stmt = conn.createStatement();
		//�����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����
		
		File f = new File("C:\\Users\\local pc\\Desktop\\�����ͺ��̽����α׷���(v2022)\\��⵵_������_����������.txt");
		//���� ��ü ���� �� �������
		BufferedReader br = new BufferedReader(new FileReader(f));
		//bufferedreader ��ü ����
		
		String readtxt; //���� �о ������ string ���� ����
		
		if((readtxt = br.readLine()) == null) {
			System.out.println("empty file");
			return;			
		}
		
		String[] field_name = readtxt.split("\t"); //������ ��ǥ(,)�� �����Ͽ� �о �� �̸��� ������ �迭 ����
		
		int LineCnt = 0; //���� ī��Ʈ ���� ����
		while((readtxt = br.readLine()) != null) {
			String[] field = readtxt.split("\t"); //������ ��ǥ(,) �����Ͽ� �о �� ���� ������ �迭 ����
			String QueryTxt; //�����ؽ�Ʈ String ���� ����
			
			//�����ؽ�Ʈ = ���� ���� �� ����,  ���� ����
			QueryTxt = String.format("insert into seongnam_Parkinglot (" 
					+ "idnumber, name, classification, type, place_addr_road,"
					+ "compartment, rankclass, partsystem, operationDay, weekdayOpenTime,"
					+ "weekdayCloseTime, rateInfo, latitude,longitude)"
					+ "values ("
					+ "'%s', '%s', '%s', '%s', '%s',"
					+ "'%s', '%s', '%s', '%s', '%s',"
					+ "'%s', '%s', %s, %s);",
					field[0], field[1], field[2], field[3], field[4], //field �ε���
					field[5], field[6], field[7], field[8], field[9],//field �ε���
					field[10],field[11], field[12], field[13]); //field �ε���		
			
			stmt.execute(QueryTxt); //�����ؽ�Ʈ�� ������
			System.out.printf("%d��° �׸� Insert Ok [%s]\n", LineCnt, QueryTxt); //�ֿܼ� ��� �׸��, �����ؽ�Ʈ ����
			
			LineCnt++;	//���� ī��Ʈ ����				
		}
		
		br.close(); //�ݱ�		
		stmt.close(); //�ݱ�
		conn.close(); //�ݱ�

	}
}

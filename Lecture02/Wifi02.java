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
		
		Class.forName("com.mysql.cj.jdbc.Driver"); //DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		//����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)
		
		Statement stmt = conn.createStatement();
		//�����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����
		
		File f = new File("C:\\Users\\local pc\\Desktop\\�����ͺ��̽����α׷���(v2022)\\���������������ǥ�ص�����.txt");
		//���� ��ü ���� �� �������
		BufferedReader br = new BufferedReader(new FileReader(f));
		//bufferedreader ��ü ����
		
		String readtxt; //���� �о ������ string ���� ����
		
		if((readtxt = br.readLine()) == null) {
			System.out.println("empty file");
			return;			
		}
		String[] field_name = readtxt.split("\t"); //������ ������ �����Ͽ� �о �� �̸��� ������ �迭 ����
		
		int LineCnt = 0; //���� ī��Ʈ ���� ����
		while((readtxt = br.readLine()) != null) {
			String[] field = readtxt.split("\t"); //������ ������ �����Ͽ� �о �� ���� ������ �迭 ����
			String QueryTxt; //�����ؽ�Ʈ String ���� ����
			
			//�����ؽ�Ʈ = ���� ���� �� ����,  ���� ����
			QueryTxt = String.format("insert into freewifi (" 
					+ "inst_place, inst_place_detail, inst_city, inst_country, inst_place_flag,"
					+ "service_provider, wifi_ssid, inst_date, place_addr_road,place_addr_land,"
					+ "manage_office,manage_office_phone,latitude,longitude,write_date)"
					+ "values ("
					+ "'%s', '%s', '%s', '%s', '%s',"
					+ "'%s', '%s', '%s', '%s', '%s',"
					+ "'%s', '%s', %s, %s, '%s');",
					field[0], field[1], field[2], field[3], field[4], //field �ε���
					field[5], field[6], field[7], field[8], field[9], //field �ε���
					field[10], field[11], field[12], field[13], field[14]); //field �ε���
			
			stmt.execute(QueryTxt); //�����ؽ�Ʈ�� ������
			System.out.printf("%d��° �׸� Insert Ok [%s]\n", LineCnt, QueryTxt); //�ֿܼ� ��� �׸��, �����ؽ�Ʈ ����
			
			LineCnt++;	//���� ī��Ʈ ����				
		}
		br.close(); //�ݱ�
		
		stmt.close(); //�ݱ�
		conn.close(); //�ݱ�

	}

}

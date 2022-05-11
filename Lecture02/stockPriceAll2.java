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

public class stockPriceAll2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		// stockPrice ���̺� �ְ������͸� �Է��ϴ� ������ �ۼ��Ѵ�. PreparedStatement�� ����ϹǷ� values�� ?ǥ��
		String QueryTxt = "insert into stockPrice (bsop_date, shrn_iscd, stck_prpr, stck_oprc, stck_hgpr, stck_lwpr, acml_vol, acml_tr_pbmn)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(QueryTxt);
		// �����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		File f = new File("C:\\Users\\local pc\\Desktop\\�ǽ�������\\day_data.StockDailyPrice.csv");
		// ���� ��ü ���� �� �������
		BufferedReader br = new BufferedReader(new FileReader(f));
		// bufferedreader ��ü ����

		String readtxt; // ���� �о ������ string ���� ����

		if ((readtxt = br.readLine()) == null) { // ù���� null�̸� �������̹Ƿ�
			System.out.println("empty file"); // ���� �޼��� ���
			return;
		}
		
		String[] field_name = readtxt.split(","); // ������ ��ǥ(,)�� �����Ͽ� �о �� �̸��� ������ �迭 ����

		int LineCnt = 0; // ���� ī��Ʈ ���� ����
		conn.setAutoCommit(false); // insert �ӵ� ����� ���ؼ� AutoCommit�� false�� �����Ѵ�.
		long startTime = System.currentTimeMillis(); // ���۽ð��� ms ������ ����
		
		while ((readtxt = br.readLine()) != null) { // ���� ������ ������ null�� �ƴ϶�� ��� �д´�.
			String[] field = readtxt.split(","); // ������ ��ǥ(,) �����Ͽ� �о �� ���� ������ �迭 ����
			
			//1~99��° 
			pstmt.setString(1,field[0]);
			pstmt.setString(2,field[1]);
			pstmt.setString(3,field[2]);
			pstmt.setString(4,field[3]);
			pstmt.setString(5,field[4]);
			pstmt.setString(6,field[5]);
			pstmt.setString(7,field[6]);
			pstmt.setString(8,field[7]);
			pstmt.setString(9,field[8]);
			pstmt.setString(10,field[9]);
			pstmt.setString(11,field[10]);
			pstmt.setString(12,field[11]);
			pstmt.setString(13,field[12]);
			pstmt.setString(14,field[13]);
			pstmt.setString(15,field[14]);
			pstmt.setString(16,field[15]);
			pstmt.setString(17,field[16]);
			pstmt.setString(18,field[17]);
			pstmt.setString(19,field[18]);
			pstmt.setString(20,field[19]);
			pstmt.setString(21,field[20]);
			pstmt.setString(22,field[21]);
			pstmt.setString(23,field[22]);
			pstmt.setString(24,field[23]);
			pstmt.setString(25,field[24]);
			pstmt.setString(26,field[25]);
			pstmt.setString(27,field[26]);
			pstmt.setString(28,field[27]);
			pstmt.setString(29,field[28]);
			pstmt.setString(30,field[29]);
			pstmt.setString(31,field[30]);
			pstmt.setString(32,field[31]);
			pstmt.setString(33,field[32]);
			pstmt.setString(34,field[33]);
			pstmt.setString(35,field[34]);
			pstmt.setString(36,field[35]);
			pstmt.setString(37,field[36]);
			pstmt.setString(38,field[37]);
			pstmt.setString(39,field[38]);
			pstmt.setString(40,field[39]);
			pstmt.setString(41,field[40]);
			pstmt.setString(42,field[41]);
			pstmt.setString(43,field[42]);
			pstmt.setString(44,field[43]);
			pstmt.setString(45,field[44]);
			pstmt.setString(46,field[45]);
			pstmt.setString(47,field[46]);
			pstmt.setString(48,field[47]);
			pstmt.setString(49,field[48]);
			pstmt.setString(50,field[49]);
			pstmt.setString(51,field[50]);
			pstmt.setString(52,field[51]);
			pstmt.setString(53,field[52]);
			pstmt.setString(54,field[53]);
			pstmt.setString(55,field[54]);
			pstmt.setString(56,field[55]);
			pstmt.setString(57,field[56]);
			pstmt.setString(58,field[57]);
			pstmt.setString(59,field[58]);
			pstmt.setString(60,field[59]);
			pstmt.setString(61,field[60]);
			pstmt.setString(62,field[61]);
			pstmt.setString(63,field[62]);
			pstmt.setString(64,field[63]);
			pstmt.setString(65,field[64]);
			pstmt.setString(66,field[65]);
			pstmt.setString(67,field[66]);
			pstmt.setString(68,field[67]);
			pstmt.setString(69,field[68]);
			pstmt.setString(70,field[69]);
			pstmt.setString(71,field[70]);
			pstmt.setString(72,field[71]);
			pstmt.setString(73,field[72]);
			pstmt.setString(74,field[73]);
			pstmt.setString(75,field[74]);
			pstmt.setString(76,field[75]);
			pstmt.setString(77,field[76]);
			pstmt.setString(78,field[77]);
			pstmt.setString(79,field[78]);
			pstmt.setString(80,field[79]);
			pstmt.setString(81,field[80]);
			pstmt.setString(82,field[81]);
			pstmt.setString(83,field[82]);
			pstmt.setString(84,field[83]);
			pstmt.setString(85,field[84]);
			pstmt.setString(86,field[85]);
			pstmt.setString(87,field[86]);
			pstmt.setString(88,field[87]);
			pstmt.setString(89,field[88]);
			pstmt.setString(90,field[89]);
			pstmt.setString(91,field[90]);
			pstmt.setString(92,field[91]);
			pstmt.setString(93,field[92]);
			pstmt.setString(94,field[93]);
			pstmt.setString(95,field[94]);
			pstmt.setString(96,field[95]);
			pstmt.setString(97,field[96]);
			pstmt.setString(98,field[97]);
			pstmt.setString(99,field[98]);			
			pstmt.addBatch(); // addBatch�� ���������� ����Ѵ�.

			System.out.printf("%d��° �׸� Insert ok\n", LineCnt); // �ֿܼ� ��� �׸�����
			pstmt.clearParameters(); // PreparedStatement�� �Ķ���͸� ����.
			LineCnt++; // ���� ī��Ʈ ����

			try {
				if ((LineCnt & 10000) == 0) { // �޸� ������ ���� 10000�پ� ������ ����
					pstmt.executeBatch(); // executeBatch()�� ȣ���ؼ� ĳ�ÿ� ��� SQL���� ����
					conn.commit(); // commit()�� ȣ���ؼ� �����͸� ������ ����
				}
			} catch (Exception e) { // �����߿� ���ܰ� �߻��ϸ�
				e.printStackTrace(); // ���� �޽��� ���
			}
			
			if (LineCnt == 100) {
				break;
			}
		}	
		
		try {
			pstmt.executeBatch();
		} catch (Exception e) { // �����߿� ���ܰ� �߻��ϸ�
			e.printStackTrace(); // ���� �޽��� ���
		}
		
		conn.commit();
		conn.setAutoCommit(true);
		long endTime = System.currentTimeMillis();

		System.out.println("Insert End"); // insert���� ���
		System.out.println("total : " + LineCnt); // insert���� ���
		System.out.println("time : " + (endTime - startTime)); // ����ð� ���

		br.close(); // �ݱ�
		pstmt.close(); // �ݱ�
		conn.close(); // �ݱ�
	}
}

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

public class stockPrice2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		// stockPrice ���̺� �ְ������͸� �Է��ϴ� ������ �ۼ��Ѵ�. PreparedStatement�� ����ϹǷ� values�� ?ǥ��
		String QueryTxt = "insert into stockPrice (shrn_iscd, bsop_date, stck_prpr, stck_oprc, stck_hgpr, stck_lwpr, acml_vol, acml_tr_pbmn)" + "values (?, ?, ?, ?, ?, ?, ?, ?)";

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

			pstmt.setString(1, field[2]); // ù��° ?�� 2���ʵ� �����ڵ� �Է�.
			pstmt.setString(2, field[1]); // �ι�° ?�� 1���ʵ� �ֽ� �������� �Է�.
			pstmt.setString(3, field[3]); // ����° ?�� 3���ʵ� �ֽ����� �Է�.
			pstmt.setString(4, field[4]); // �׹�° ?�� 4���ʵ� �ֽĽð� �Է�.
			pstmt.setString(5, field[5]); // �ټ���° ?�� 1���ʵ� �ֽ� �ְ� �Է�.
			pstmt.setString(6, field[6]); // ������° ?�� 1���ʵ� �ֽ� ������ �Է�.
			pstmt.setString(7, field[11]); // �ϰ���° ?�� 1���ʵ� �����ŷ��� �Է�.
			pstmt.setString(8, field[12]); // ������° ?�� 1���ʵ� �����ŷ���� �Է�.
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
			
//			if (LineCnt == 100) {
//				break;
//			}
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

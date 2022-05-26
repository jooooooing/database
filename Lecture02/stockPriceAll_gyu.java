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
import java.text.SimpleDateFormat;

public class stockPriceAll_gyu {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
	      //SimpleDateForm�� �̿��Ͽ� ������ �������ش� 
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        // ǥ�ؽð�(���� ����� (UTC))���� ���̿� �߻��ϴ� ���� UTC 1970�� 1�� 1�� 00:00:00.000 �� ���������� ���� �ð��� ���̸� long������ ��ȯ
        //���α׷� ���� �ð�

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)
		Statement stmt = conn.createStatement();// Connection ��ü�� createStatement() �޼��带 ȣ���Ͽ�, ������ ������ �� �ִ� Statement
												// ��ü(k29_stmt)�� ��´�.

		String QueryTxt = "insert into stocktable (stnd_iscd, bsop_date, shrn_iscd, stck_prpr, stck_oprc, stck_hgpr, stck_lwpr, prdy_vrss_sign, prdy_vrss, prdy_ctrt, " // 10
				+ "prdy_vol, acml_vol, acml_tr_pbmn, askp1, bidp1, total_askp_rsqn, total_bidp_rsqn, seln_cntg_smtn, shnu_cntg_smtn, seln_tr_pbmn, " // 10
				+ "shnu_tr_pbmn, seln_cntg_csnu, shnu_cntg_csnu, w52_hgpr, w52_lwpr, w52_hgpr_date, w52_lwpr_date, ovtm_untp_bsop_hour , ovtm_untp_prpr, ovtm_untp_prdy_vrss, ovtm_untp_prdy_vrss_sign," // 10
				+ "ovtm_untp_askp1, ovtm_untp_bidp1, ovtm_untp_vol, ovtm_untp_tr_pbmn, ovtm_untp_oprc, ovtm_untp_hgpr, ovtm_untp_lwpr, mkob_otcp_vol, mkob_otcp_tr_pbmn, mkfa_otcp_vol," // 10
				+ "mkfa_otcp_tr_pbmn, mrkt_div_cls_code,  pstc_dvdn_amt, lstn_stcn, stck_sdpr, stck_fcam, wghn_avrg_stck_prc, issu_limt_rate, frgn_limt_qty, oder_able_qty," // 10
				+ "frgn_limt_exhs_cls_code, frgn_hldn_qty, frgn_hldn_rate, hts_frgn_ehrt, itmt_last_nav, prdy_last_nav, trc_errt, dprt, ssts_cntg_qty,ssts_tr_pbmn," // 10
				+ "frgn_ntby_qty ,flng_cls_code, prtt_rate, acml_prtt_rate, stdv, beta_cfcn, crlt_cfcn, bull_beta,  bear_beta, bull_dvtn," // 10
				+ "bear_dvtn, bull_crlt, bear_crlt, stck_mxpr, stck_llam, icic_cls_code, itmt_vol, itmt_tr_pbmn, fcam_mod_cls_code, revl_issu_reas_code, " // 10
				+ "orgn_ntby_qty, adj_prpr, fn_oprc, fn_hgpr, fn_lwpr, fn_prpr, fn_acml_vol, fn_acml_tr_pbmn, fn_prtt_rate, fn_flng_cls_code," // 10
				+ "buyin_nor_prpr, buyin_nor_prdy_vrss, buyin_nor_vol, buyin_nor_tr_pbmn, buyin_tod_prpr, buyin_tod_prdy_vrss, buyin_tod_vol, buyin_tod_tr_pbmn)" // 10
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
			

		PreparedStatement pstmt = conn.prepareStatement(QueryTxt);
		// PreparedStatement���� ����: ���ڰ����� ������ �����ϴ�.�������� ����.���� Statement ���� ������ ����.
		// File�� ���ؼ� �ش� ��ġ�� StockDailyPrice ������ �о�´�
		File f = new File("C:\\Users\\local pc\\Desktop\\�ǽ�������\\day_data.StockDailyPrice.csv");
		BufferedReader br = new BufferedReader(new FileReader(f));
		// �����͸� ��Ƶ״ٰ� �ѹ��� ������ �ξ��� ȿ�����̴�. �����ͷ��� �����Ƿ� BufferedReader���
		String readtxt;// readtext�� ���� �����Ѵ�.

		if ((readtxt = br.readLine()) == null) { // ���� readLine()�� ��� (�������� ���� ������)
			System.out.println("�� �����Դϴ�"); // �ش系�� ���
			return;
         } // ���ǹ� �ݴ´�.
         
         
			String[] field_name = readtxt.split(","); // csv�����̹Ƿ� �ĸ��������� ���ڿ��� ���� field_Name���� ���ڿ� ����.

			int lineCnt = 0; // ���μ��� ī��Ʈ�ϱ� ���� ������ ������ �����ϰ� 0���� �ʱ�ȭ�Ѵ�.
			conn.setAutoCommit(false); // setAutoCommit(false) : commit ������ ������ �߻���d��, ����ڰ� ���� commit/rollback�� �ϰڴ�
			long start = System.currentTimeMillis(); // �ð��� �и����� ������ �ҷ��´�.

			int numforField = 0; // �о�帰 �ʵ� ����

			while ((readtxt = br.readLine()) != null) { // �о�帰 readline�� null�� �ƴϸ� ��� �ݺ����� �����Ѵ�.
				String[] field = readtxt.split(","); // readtext�� ������ ���ڿ� �迭 field�� ������� �ο��Ѵ�.
				try {

					for (int i = 0; i < 99; i++) {

						pstmt.setString(i + 1, field[i]);
					}

					pstmt.addBatch(); // ��Ƶ� ������ �ѹ��� DB������ ������.
					System.out.printf("%d��° �׸� addBatch OK\n", lineCnt);
					pstmt.clearParameters();
				} catch (ArrayIndexOutOfBoundsException e) {
					// e.printStackTrace(); // �ַ������� ����Ѵ�.
				} finally {
					lineCnt++;// ������ 1�� �����ش�.
					numforField++;
					try {
						if (lineCnt % 10000 == 0) {// 10000���� ������ ��������
							pstmt.executeBatch();// ��Ƶ� ������ �ѹ��� DB������ ������.
							conn.commit();// �ڵ�Ŀ���� ��Ȱ��ȭ�Ͽ��⶧���� ������ commit�� ������Ѵ�.
						} // ���ǹ� �ݴ´�.
					} catch (Exception e) {// try���� ������� ������ �ش� ���� ����Ѵ�.
						e.printStackTrace(); // �ַ������� ����Ѵ�.
					}
				}

			}

			try {
				pstmt.executeBatch();
			} catch (Exception e) {
				e.printStackTrace();
			}

			conn.commit();
			conn.setAutoCommit(true);
			long end = System.currentTimeMillis(); // ���α׷� ���� �ð�

			System.out.println("start : " + time.format(start)); // ���۽ð��� �и�������
			System.out.println("end : " + time.format(end)); // ����ð��� �и�������
			System.out.println("���� �ð� : " + (end - start) / 1000.0 + "��"); // ���� ����ð�
			br.close();
			stmt.close(); // Statement ����
			conn.close(); // Connection ����
		}
	}

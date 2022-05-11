package Lecture02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class stockPriceAll {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); // DB�� �����ϴ� ����̹� Ŭ������ ã�� �޸𸮿� �ε�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.102:3306/kopoctc", "root", "koposw31");
		// ����̹������� ������ �� �ִ� Ŀ�ؼ� ��ü�� ������ (url, id , pw)

		Statement stmt = conn.createStatement();
		// �����ͺ��̽��� SQL ���� ������ ���� SQLServerStatement ��ü�� ����

		stmt.execute("create table stockPriceAll(" + // seongnam_stockPrice ���̺� ����				
				"stnd_iscd varchar(50), "+      //0 NOT_NULL ǥ�� �����ڵ�
				"bsop_date int , "+      //1 NOT_NULL �ֽ� ���� ����
				"shrn_iscd varchar(50) , "+      //2 NOT_NULL �������� ���� �����ڵ�
				"stck_prpr int , "+      //3 �ֽ� ����
				"stck_oprc int , "+      //4 �ֽ� �ð�
				"stck_hgpr int , "+      //5 �ֽ� �ְ�
				"stck_lwpr int , "+      //6 �ֽ� ������
				"prdy_vrss_sign varchar(50) , "+      // 7 ���� ��� ��ȣ
				"prdy_vrss int , "+      // 8 ���� ���
				"prdy_ctrt varchar(50) , "+      // 9 ���� �����
				"prdy_vol long , "+      //10 ���� �ŷ���
				"acml_vol long , "+      //11 ���� �ŷ���
				"acml_tr_pbmn long , "+      //12 ���� �ŷ� ���
				"askp1 int , "+      //13 �ŵ�ȣ��1
				"bidp1 int , "+      //14 �ż�ȣ��1
				"total_askp_rsqn long , "+      //15 �� �ŵ�ȣ�� �ܷ�
				"total_bidp_rsqn long , "+      //16 �� �ż�ȣ�� �ܷ�
				"seln_cntg_smtn long , "+      //17 �ŵ� ü�� �հ�
				"shnu_cntg_smtn long , "+      //18 �ż� ü�� �հ�
				"seln_tr_pbmn long , "+      //19 �ŵ� �ŷ� ���(����)
				"shnu_tr_pbmn long , "+      //20 �ż� �ŷ� ���(����)
				"seln_cntg_csnu int , "+      //21 �ŵ� ü�� �Ǽ�
				"shnu_cntg_csnu int , "+      //22 �ż� ü�� �Ǽ�
				"w52_hgpr int , "+      //23 52���� �ְ�
				"w52_lwpr int , "+      //24 52���� ������
				"w52_hgpr_date int , "+      //25 52���� �ְ� ����
				"w52_lwpr_date int , "+      //26 52���� ������ ����
				"ovtm_untp_bsop_hour int , "+      //27 �ð��� ���ϰ� ���� �ð�
				"ovtm_untp_prpr int , "+      //28 �ð��� ���ϰ� ���簡
				"ovtm_untp_prdy_vrss int , "+      //29 �ð��� ���ϰ� ���� ���
				"ovtm_untp_prdy_vrss_sign varchar(50) , "+      //30 �ð��� ���ϰ� ���� ��� ��ȣ
				"ovtm_untp_askp1 int , "+      //31 �ð��� ���ϰ� �ŵ�ȣ��1
				"ovtm_untp_bidp1 int , "+      //32 �ð��� ���ϰ� �ż�ȣ��1
				"ovtm_untp_vol long , "+      //33 �ð��� ���ϰ� �ŷ���
				"ovtm_untp_tr_pbmn long , "+      //34 �ð��� ���ϰ� �ŷ� ���
				"ovtm_untp_oprc int , "+      //35 �ð��� ���ϰ� �ð�
				"ovtm_untp_hgpr  int      , "+      //36 �ð��� ���ϰ� �ְ� 
				"ovtm_untp_lwpr int , "+      //37 �ð��� ���ϰ� ������
				"mkob_otcp_vol long , "+      //38 �尳���� �ð������� �ŷ���
				"mkob_otcp_tr_pbmn long , "+      //39 �尳���� �ð������� �ŷ� ���
				"mkfa_otcp_vol long , "+      //40 �������� �ð������� �ŷ���
				"mkfa_otcp_tr_pbmn long , "+      //41 �������� �ð������� �ŷ� ���
				"mrkt_div_cls_code varchar(50) , "+      //42 ���� �з� ���� �ڵ�
				"pstc_dvdn_amt long , "+      //43 �ִ� ��� �ݾ�
				"lstn_stcn long , "+      //44 ���� �ּ�
				"stck_sdpr int , "+      //45 �ֽ� ���ذ�
				"stck_fcam float , "+      //46 �ֽ� �׸鰡
				"wghn_avrg_stck_prc double , "+      //47 ���� ��� �ֽ� ����
				"issu_limt_rate varchar(50) , "+      //48 ���� �ѵ� ����
				"frgn_limt_qty long , "+      //49 �ܱ��� �ѵ� ����
				"oder_able_qty long , "+      //50 �ֹ� ���� ����
				"frgn_limt_exhs_cls_code varchar(50) , "+      //51 �ܱ��� �ѵ� ���� ���� �ڵ�
				"frgn_hldn_qty long , "+      //52 �ܱ��� ���� ����
				"frgn_hldn_rate varchar(50) , "+      //53 �ܱ��� ���� ����
				"hts_frgn_ehrt varchar(50) , "+      //54 HTS �ܱ��� ������
				"itmt_last_nav varchar(50) , "+      //55 ���� ���� NAV
				"prdy_last_nav varchar(50) , "+      //56 ���� ���� NAV
				"trc_errt float , "+      //57 ���� ������
				"dprt float , "+      //58 ������
				"ssts_cntg_qty long , "+      // 59 ���ŵ��������Ǹŵ�ü�����
				"ssts_tr_pbmn long , "+      //60 ���ŵ��������Ǹŵ��ŷ����
				"frgn_ntby_qty long , "+      //61 �ܱ��� ���ż�
				"flng_cls_code varchar(50) , "+      //62 ������ �ڵ�
				"prtt_rate float , "+      //63 ���� ����
				"acml_prtt_rate varchar(50) , "+      //64 ���� ���� ����
				"stdv float , "+      //65 ��ü�����ܰ����
				"beta_cfcn float , "+      //66 ��Ÿ ���(90��)
				"crlt_cfcn float , "+      //67 DEL ��� ���
				"bull_beta float , "+      //68 DEL ���� ���
				"bear_beta float , "+      //69 DEL �༼ ���
				"bull_dvtn  float , "+      //70*  DEL ���� ����
				"bear_dvtn  float , "+      //71*  DEL �༼ ����
				"bull_crlt  float , "+      //72*  DEL ���� ������
				"bear_crlt  float , "+      //73*  DEL �༼ ������
				"stck_mxpr  int , "+      //74 *  �ֽ� ���Ѱ�
				"stck_llam  int , "+      //75 *  �ֽ� ���Ѱ�
				"icic_cls_code varchar(50) , "+      //76 *  ���� ���� �ڵ�
				"itmt_vol  long , "+      //77 *  ���� �ŷ���
				"itmt_tr_pbmn  long , "+      //78 *  ���� �ŷ����
				"fcam_mod_cls_code varchar(50) , "+      //79 �׸鰡 ���� ���� �ڵ�
				"revl_issu_reas_code varchar(50)     , "+      //80 ���� ���� ���� �ڵ�
				"orgn_ntby_qty long , "+      //81 ����� ���ż�
				"adj_prpr int , "+      //82 �����ְ� (����:fnguide������ �߰�)
				"fn_oprc int , "+      //83 �ֽ� �ð�
				"fn_hgpr int , "+      //84 �ֽ� �ְ�
				"fn_lwpr int , "+      //85 �ֽ� ������
				"fn_prpr int , "+      //86 �ֽ� ����
				"fn_acml_vol long , "+      //87 ���� �ŷ���
				"fn_acml_tr_pbmn long , "+      //88 ���� �ŷ� ���
				"fn_prtt_rate float , "+      //89 ���� ����
				"fn_flng_cls_code varchar(50) , "+      //90 ������ �ڵ�
				"buyin_nor_prpr int , "+      //91 Buy-in �Ϲ� ü�ᰡ
				"buyin_nor_prdy_vrss int , "+      //92 Buy-in �Ϲ� ���� ���
				"buyin_nor_vol long , "+      //93 Buy-in �Ϲ� ü�ᷮ
				"buyin_nor_tr_pbmn long , "+      //94 Buy-in �Ϲ� ü�� ���
				"buyin_tod_prpr int , "+      //95 Buy-in ���� ü�ᰡ
				"buyin_tod_prdy_vrss int , "+      //96 Buy-in ���� ���� ���
				"buyin_tod_vol long , "+      //97 Buy-in ���� ü�ᷮ
				"buyin_tod_tr_pbmn long , "+      //98 Buy-in ���� ü�� ���
				
				"CONSTRAINT PK PRIMARY KEY (shrn_iscd, bsop_date)" + // PK ����				
				") DEFAULT CHARSET=utf8; ");

//		stmt.execute("drop table seongnam_stockPriceAll;");
//		stmt.execute("delete from seongnam_stockPriceAll;");

		stmt.close(); // �ݱ�
		conn.close(); // �ݱ�
	}
}

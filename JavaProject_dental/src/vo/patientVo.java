package vo;

import lombok.Data;

@Data
public class patientVo {
	//TRETMANT 테이블에서 가져옴
	 private String tr_no;
	 private String tr_remark;
	 private String tr_date;
	 private String pt_no;
	 private String emp_no;
	 //PATIENT 테이블에서 가져옴
	 private String pt_name;
	 private String pt_gen;
	 private String pt_address;
	 private int pt_rrn1;
	 private int pt_rrn2;
	 private String pt_telno;
	 private String pt_str;
	 private String pt_disease;
	 //SCHEDULE 테이블에서가져옴
	 private String sc_no;
	 private String sc_remark;
	 private String sc_date;
	 private String sc_regupdate;
	 private String sc_ck;
	 private String sc_come;
	 private String sc_cancel;
	 private String sub_no;
	 //emp
	 private String emp_name;
	 private String emp_id;
	 private String emp_pw;

}

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

}

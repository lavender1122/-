package util;

import java.math.BigDecimal;
import java.util.Map;

import service.patientService;

public class Print {
	patientService patientservice = patientService.getInstance();
	 public static void main(String[] args) {
		
	}
	 public void printpatient_old( Map<String, Object>ptlist) {
			String no =(String)ptlist.get("PT_NO");
			String name =(String)ptlist.get("PT_NAME");
			String gen =(String)ptlist.get("PT_GEN");
			BigDecimal rrn1 =(BigDecimal)ptlist.get("PT_RRN1");
			BigDecimal rrn2 =(BigDecimal)ptlist.get("PT_RRN2");
			String str =(String)ptlist.get("PT_STR");
			String dis =(String)ptlist.get("PT_DISEASE");
			System.out.println(no +"\t"+name +"\t"+gen +"\t"+ rrn1 +"\t"+rrn2 +"\t"+ str+"\t"+dis);
	 }
	 
	
	 
}

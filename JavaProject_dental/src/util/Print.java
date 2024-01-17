package util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import controller.MainController;
import service.patientService;
import vo.patientVo;

public class Print {
	patientService patientservice = patientService.getInstance();
	 public static void main(String[] args) {
		
	}
	 public void printMain() {
		 
	 }
	 public void printpatient_old( Map<String, Object>ptlist) {//출력문
			String no =(String)ptlist.get("PT_NO");
			String name =(String)ptlist.get("PT_NAME");
		    MainController.sessionStorage.put("ptName",name);
			String gen =(String)ptlist.get("PT_GEN");
			BigDecimal rrn1 =(BigDecimal)ptlist.get("PT_RRN1");
			BigDecimal rrn2 =(BigDecimal)ptlist.get("PT_RRN2");
			String str =(String)ptlist.get("PT_STR");
			String dis =(String)ptlist.get("PT_DISEASE");
			System.out.println(no +"\t"+name +"\t"+gen +"\t"+ rrn1 +"\t"+rrn2 +"\t"+ str+"\t"+dis);
	 }
	 public void printtrSearchList(List<patientVo> list) {//출력문
		 for (patientVo patientvo : list) {
		 String trDate = patientvo.getTr_date();
			String trRemark = patientvo.getTr_remark();
			String subNo = patientvo.getSub_no();
			String empName = patientvo.getEmp_name();
			System.out.println(trDate+trRemark+subNo+empName);
		 }
	 }
	 public void printscheduleSearch(List<patientVo> list) {//출력문
		 for (patientVo patientvo : list) {
			String scDate = patientvo.getSc_date();
			String scRemark = patientvo.getSc_remark();
			String scCk = patientvo.getSc_ck();
			String scCome = patientvo.getSc_come();
			String scCancal = patientvo.getSc_cancel();
			String subNo = patientvo.getSub_no();
			String empName = patientvo.getEmp_name();
			System.out.println(scDate + scRemark+subNo+empName+scCk+scCome+scCancal);
			}
	 }
	 public void printvar() {
		 System.out.println("========================================================================================================");
	   }
	 
	 public void printcalendar() {
		 Calendar cal = Calendar.getInstance();
		 System.out.println("-----------------------"+cal.get(Calendar.YEAR)+"년 "+ (cal.get(Calendar.MONTH)+1)+"월 ---------------");
			int month = cal.get(Calendar.MONTH)+1; // 캘린더 월은 순번이 0 부터 시작해서 +1 부터 시작해야함
			System.out.println("일\t 월 \t화\t 수\t 목\t 금\t 토");
			System.out.println("---------------------------------------------------------");
			// 해당 주에 현재요일
			// SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, and SATURDAY.
			//  1 ~7
			cal.set(Calendar.DATE,1); //1일부터 시작
			int day = cal.get(Calendar.DAY_OF_WEEK);
			
		
			// 해당 월에 마지막 일
			int monthLastDay = cal.getActualMaximum(Calendar.DATE);
			
			//for문
			//공백
			
			for (int i = 1; i <day; i++) {					
				System.out.print("\t\t");
			}
			
			
			for (int i = 1; i <= monthLastDay; i++) {
				System.out.print(i+"\t\t"); //날짜 출력
//				System.out.print("예약");
				if(cal.get(Calendar.DAY_OF_WEEK) == 7) {
					System.out.println();
					System.out.println();
				}
				cal.add(Calendar.DATE,1); // 요일
			}
			
			
	 }
	 
}

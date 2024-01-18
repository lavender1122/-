package controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.sasl.SaslClient;

import dao.patientDao;
import service.freeService;
import service.patientService;
import util.Print;
import util.ScanUtil;
import util.View;
import vo.freeVo;
import vo.patientVo;

public class MainController extends Print {
	freeService freeservice =freeService.getInstance();
	patientService patientservice =patientService.getInstance();
	static public Map<String, Object> sessionStorage = new HashMap<>();
	public static void main(String[] args) {
		new MainController().start();
	}
	
	private void start() {
		//!!나중에 login으로 바꿔야함!!!
		View view = View.LOGIN;
		while (true) {
			switch (view) {
			case LOGIN:
				view = login();
				break;
			case MAIN:
				view = home();
				break;
			case DATE_SEARCH:
				view = dateSearch();
				break;
			case CALENDAR_SEARCH:
				view = calendarSearch();
				break;
			case PATIENT:
				view = patient();
				break;
			case PATIENT_INSERT:
				view = patientInsert();
				break;
			case PATIENT_OLD:
				view = patient_old();
				break;
			case PATIENT_UPDATE:
				view = patientUpdate();
				break;
			case PATIENT_SEARCH:
				view = scheduleSearch();
				break;
			case SCHEDULE:
				view = schedule();
				break;
			case SCHEDULE_INSERT:
				view = scheduleInsert();
				break;
			case SCHEDULE_UPDATE:
				view = scheduleUpdate();
				break;
			case SCHEDULE_SEARCH:
				view = scheduleSearch();
				break;
			case SCHEDULE_CANCAL:
				view = scheduleCancal();
				break;
			case TREATMENT:
				view = treatment();
				break;
			case TREATMENT_INSERT:
				view = treatmentInsert();
				break;
			case TREATMENT_UPDATE:
				view = treatmentUpdate();
				break;
			case TREATMENT_SEARCH:
				view = treatmentSearch();
				break;
			case SEARCH:
				view = search();
				break;
			default:
				view = home();
				break;
			}
		}
	}
	   private View calendarSearch() {
		      // 원하는 날짜 넣어서 조회하기
		      // 달력넣어서 조회하기
		   System.out.println("날짜별 예약조회");
		      printcalendar();
		      String dateSearch = ScanUtil.nextLine("날짜 입력하기");
		      List<Object> param = new ArrayList();
		      param.add(dateSearch);
		      List<patientVo> list =  patientservice.calendarSearch(param);
		      printcalendarSearch(list);
		    
		      System.out.println("1.예약하기");
		      System.out.println("2.다");
		      return View.SCHEDULE;
		   }

	private View dateSearch() {
	      System.out.println("당일예약조회");
	      printvar();
	      List<patientVo> list = patientservice.dateSearch();
	      System.out.println("환자번호 \t\t환자이름\t\t예약내용\t\t진료과\t\t전날확인\t\t전화번호" );
	      String date = "";
	      
	      for (patientVo patientvo : list) {
	    	  String no = patientvo.getPt_no(); 
	    	  String name = patientvo.getPt_name();
	    	  String remark =patientvo.getSc_remark();
	    	  String telno =patientvo.getPt_telno();
	    	  String subNo = patientvo.getSub_no();
	    	  String ck = patientvo.getSc_ck();
	    	  date = patientvo.getSc_date();
		
	      System.out.println   
	      (no + "\t\t"  + name + "\t\t" + remark +"\t\t"  + 
	      subNo + "\t\t" + ck + "\t\t" + telno + "\t\t"  );
	      }
	      
	      System.out.println("\n오늘은\t" + date + "입니다.");
	         printvar()   ;
	      return View.MAIN;
	   }

	private View scheduleCancal() {
		//해당하는 환자예약리스트 출력 /
		String ptNo =(String)sessionStorage.get("pt_no");
		
		Map<String, Object>map =patientservice.SC(ptNo); //환자번호,이름,전화번호,특이사항, 예약취소 표시
		
		System.out.println(map);
		
		List<Map<String, Object>> list =patientservice.SCL(ptNo); 
		System.out.println(list);
		
		List<Object> param = new ArrayList();
		
		String scContent = ScanUtil.nextLine("예약취소한 이유적으시오");
		param.add(scContent);
		param.add(ptNo);
		String scNo = ScanUtil.nextLine("예약취소할 예약번호를 입력하세요");
		param.add(scNo);
		
		patientservice.scCancel(param);
		
		
		//몇번 예약취소됬는지 표시
		Map<String, Object>ck = patientservice.scCancalCk(param);
		System.out.println(ck);
		
		System.out.println("예약취소 되었습니다.");
		System.out.println("1 다시 예약등록 2 예약취소 3 등록된 환자");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.SCHEDULE_INSERT;
		case 2:
			return View.SCHEDULE_CANCAL;
		case 3:

		default:
			return View.SCHEDULE_CANCAL;
		}
	}




	private View treatmentSearch() {
		System.out.println("진료조회");
		String ptNo = (String) sessionStorage.get("pt_no");
		String ptName = (String) sessionStorage.get("ptName");
		System.out.println("진료번호 \t 환자이름");
		System.out.println(ptNo +"\t"+ptName);
		List<Object> param = new ArrayList();
		
		param.add(ptNo);//첫번째값 환자번호
		System.out.println("1날짜 검색 2 내용검색 3과검색 ");
		int select = ScanUtil.menu();//번호 입력
		if(select==1) {
			System.out.println("날짜 검색");
			String mon = ScanUtil.nextLine("월 입력하시오");
			param.add(mon);//두번째값 월
			String strDay = ScanUtil.nextLine("시작일 입력하시오");
			param.add(strDay);//세번째값 월
			String endDay = ScanUtil.nextLine("마지막 입력하시오");
			param.add(endDay);//네번째값 월
			
		}
		if(select==2) {
			System.out.println("내용 검색");
			String content = ScanUtil.nextLine("찾고 싶은내용 검색하시오");
			param.add(content);//두번째
		}
		if(select==3) {
			System.out.println("과검색");
			String sub = ScanUtil.nextLine("찾고 과 검색하시오");
			param.add(sub);//두번째
		}
		
		List<patientVo> list = patientservice.trSearchList(param,select);
		
		printtrSearchList(list);
			
		System.out.println("1진료조회 2 예약조회 3 등록된환자");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.TREATMENT_SEARCH;
		case 2:
			return View.SCHEDULE_SEARCH;
		case 3:
			return View.PATIENT_OLD;

		default:
			return View.TREATMENT_SEARCH;
		}
	}


	private View scheduleSearch() {
		System.out.println("예약조회");
		String ptNo = (String) sessionStorage.get("pt_no");
		String ptName = (String) sessionStorage.get("ptName");
		System.out.println("진료번호 \t 환자이름");
		System.out.println(ptNo +"\t"+ptName);
		List<Object> param = new ArrayList();
		
		param.add(ptNo);//첫번째값 환자번호
		System.out.println("1날짜 검색 2 내용검색 3과검색 ");
		int select = ScanUtil.menu();//번호 입력
		if(select==1) {
			System.out.println("날짜 검색");
			String mon = ScanUtil.nextLine("월 입력하시오");
			param.add(mon);//두번째값 월
			String strDay = ScanUtil.nextLine("시작일 입력하시오");
			param.add(strDay);//세번째값 일
			String endDay = ScanUtil.nextLine("마지막 입력하시오");
			param.add(endDay);//네번째값 일
			
		}
		if(select==2) {
			System.out.println("내용 검색");
			String content = ScanUtil.nextLine("찾고 싶은내용 검색하시오");
			param.add(content);//두번째
		}
		if(select==3) {
			System.out.println("과검색");
			String sub = ScanUtil.nextLine("찾고 과 검색하시오");
			param.add(sub);//두번째
		}
		
		List<patientVo> list = patientservice.scSearchList(param,select);
		printscheduleSearch(list);
		
		System.out.println("1진료조회 2 예약조회 3 등록된환자");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.TREATMENT_SEARCH;
		case 2:
			return View.SCHEDULE_SEARCH;
		case 3:
			return View.PATIENT_OLD;

		default:
			return View.TREATMENT_SEARCH;
		}
	}

	private View scheduleUpdate() {
		System.out.println("예약수정 페이지");
		//SC_REMARK 쓰기
		//SC_REGDATE로 수정
		//SUB_NO :session 이용
		//pt_no : session
		System.out.println("예약수정할 번호 고르시오");
		System.out.println("1.예약내용 변경");
		System.out.println("2.예약일자 변경");
		System.out.println("3.예약일자, 내용 변경");
		int select = ScanUtil.menu();
		List<Object> param  = new ArrayList(); 
		if(select == 1 || select == 3) {
			String scRemark = ScanUtil.nextLine("예약변경내용 적으세요");
			param.add(scRemark);
		}
		if(select == 2 || select == 3) {
			String scRegDate = ScanUtil.nextLine("예약날짜 시간적으세요");
			param.add(scRegDate);
		
		}
		String ptNo = (String)sessionStorage.get("pt_no");
			param.add(ptNo);
			
			patientservice.SU(param,select);
			//수정된 내용 출력
			Map<String, Object> list = patientservice.SUL(ptNo);
			String scRemark = (String) list.get("SC_REMARK");
			Date scRegDate = (Date) list.get("SC_DATE");
			System.out.println(ptNo+scRemark+scRegDate);
			
			
		System.out.println("1다시 예약수정 2등록된환자");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.SCHEDULE_UPDATE;  
		case 2:
			return View.PATIENT_OLD;  
		default:
			return View.SCHEDULE_UPDATE;  
		}
	}

	private View treatmentUpdate() {
		System.out.println("진료수정 페이지입니다.");
		List<Object>param = new ArrayList();
			String content = ScanUtil.nextLine("진료내용 변경 쓰시오");
			param.add(content);
			
		patientservice.TU(param);
		
		System.out.println("수정된내용 출력");
		
		 Map<String, Object> list = patientservice.TUL();
		 System.out.println(list);
		 System.out.println("수정된 내용 출력 완료됬습니다.");
		 
		//수정된 리스트  출력
		System.out.println("1다시 진료수정   2등록된환자");
		int sel = ScanUtil.nextInt("선택");
		switch (sel) {
		case 1:
			return View.TREATMENT_UPDATE;  
		case 2:
			return View.PATIENT_OLD;  
		default:
			return View.TREATMENT_UPDATE;  
		}
	}

	private View treatmentInsert() {
		System.out.println("진료등록");
		//의사 번호 자동화하기 위해서 로그인에서 가져옴
		freeVo dr =(freeVo)sessionStorage.get("Dr");
		//진료등록 tr_no,tr_date 자동등록하게 하기
		String remark = ScanUtil.nextLine("진료내용 등록");
		String emp_no =(String)dr.getEmp_no();
		String pt_no = (String)sessionStorage.get("pt_no");

		List<Object> param = new ArrayList();
		
		param.add(remark);//진료내역
		param.add(pt_no);//환자번호
		//날짜는 오늘 날짜로 함
		param.add(emp_no);//의사번호
		
		 patientservice.treatmentInsert(param);
		
		//등록된 내용 출력
		System.out.println("진료등록 되었습니다.");
		 Map<String, Object>list = patientservice.TIL(pt_no);
		System.out.println(list);
		System.out.println("1 진료수정 2 등록된환자 ");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.TREATMENT_UPDATE;
		case 2:
			return View.PATIENT_OLD;
		default:
			return View.TREATMENT_INSERT;
		}
				
	}

	private View patient_old() {
		
		String ptNo = (String) sessionStorage.get("pt_no");
		System.out.println(ptNo+"차트번호입니다.");//확인용
		//해당 환자 정보 출력
	    Map<String, Object>ptlist = patientservice.ptoList(ptNo);
	    
	    printpatient_old(ptlist); //print 메소드에서 환자이름 넣음

	    
		System.out.println("1환자수정,2진료,3예약,4조회, 5홈");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PATIENT_UPDATE;
		case 2:
			return View.TREATMENT;
		case 3:
			return View.SCHEDULE;
		case 4:
			return View.SEARCH;
		case 5:
			return View.MAIN;
		default:
			return View.PATIENT_OLD;
		}
	}

	private View search() {
		System.out.println("환자, 예약, 진료 조회");
		System.out.println("1, 진료, 2,예약");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.TREATMENT_SEARCH;
		case 2:
			return View.SCHEDULE_SEARCH;
		

		default:
			return View.SEARCH;
		}
	}

	private View scheduleInsert() {
		System.out.println("예약등록");
		//예약번호 -> sql에서
		//sc_remark 작성해서 보내기
		//sc date 여기서 등록
		//sub_no session("dr") 이용
		//pt_no session("pt_no") 이용
		freeVo dr = (freeVo)sessionStorage.get("Dr");
		String scRemark = ScanUtil.nextLine("예약내용 적으세요");
		String scDate = ScanUtil.nextLine("예약일 적으세요");
		String subNo = dr.getSub_no();
		String ptNo =(String)sessionStorage.get("pt_no");
		System.out.println("출력확인");
		System.out.println(subNo);
		System.out.println(ptNo);
		System.out.println("------------");
		
		List<Object> param = new ArrayList();
		param.add(scRemark);
		param.add(scDate);
		param.add(subNo);
		param.add(ptNo);
		
		patientservice.SI(param);
		
		System.out.println("등록완료");
		Map<String, Object>list = patientservice.SIL(ptNo);
		System.out.println(list);
		
		System.out.println("예약등록 완료되었습니다.");
		System.out.println("예약등록한 내용출력");
		System.out.println("1예약수정 2예약취소 3구환");
		int sel =ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.SCHEDULE_UPDATE;
		case 2:
			return View.SCHEDULE_CANCAL;
		case 3:
			return View.PATIENT_OLD;

		default:
			return View.SCHEDULE_INSERT;
		}
	}

	private View patientUpdate() {
		System.out.println("환자수정");
		String patientno = (String) sessionStorage.get("pt_no");
	      System.out.println("환자수정");
	      System.out.println("1. 이름");
	      System.out.println("2. 주소");
	      System.out.println("3. 전화번호");
	      System.out.println("4. 특이사항");
	      System.out.println("5. 전신질환");
	      System.out.println("6. 이전페이지");
	      System.out.println("7. 등록된환자");

	      int sel = ScanUtil.menu();
	      if (sel == 6) {
	         return View.PATIENT_UPDATE;
	      }
	      if (sel == 7) {
	    	  return View.PATIENT_OLD;
	      }
	      List<Object> param = new ArrayList();
	      if (sel == 1) {
	         String name = ScanUtil.nextLine("이름변경 : ");
	         param.add(name);
	      }
	      if (sel == 2) {
	         String address = ScanUtil.nextLine("주소변경 : ");
	         param.add(address);
	      }
	      if (sel == 3) {
	         String telno = ScanUtil.nextLine("전화번호변경 : ");
	         param.add(telno);
	      }
	      if (sel == 4) {
	         String str = ScanUtil.nextLine("특이사항 : ");
	         param.add(str);
	      }
	      if (sel == 5) {
	         String disease = ScanUtil.nextLine("전신질환 : ");
	         param.add(disease);
	      }
	      param.add(patientno);
	      patientservice.patientUpdate(param, sel);
	      System.out.println("정보가 변경되었습니다.");

		//이름 주소 전화번호, 전신질환
		System.out.println("환자수정 리스트 출력");
		System.out.println("1 다시 환자수정 2 등록된환자");
		int select = ScanUtil.menu();
		switch (select) {
		case 1:
			return View.PATIENT_UPDATE;
		case 2:
			return View.PATIENT_OLD;

		default:
			return View.PATIENT_UPDATE;
		}
	}

	private View patientInsert() {
		System.out.println("신규환자등록");
	      
	      String name = ScanUtil.nextLine("환자이름 : ");
	      String gender = ScanUtil.nextLine("환자 성별 : ");
	      String address = ScanUtil.nextLine("주소 : ");
	      int rrn1 = ScanUtil.nextInt("주민등록번호 앞자리 : ");
	      int rrn2 = ScanUtil.nextInt("주민등록번호 뒷자리 : ");
	      String telno = ScanUtil.nextLine("전화번호 : ");
	      String str = ScanUtil.nextLine("특이사항 : ");
	      String disease = ScanUtil.nextLine("전신질환 : ");
	      
	      List <Object> param = new ArrayList();
	      
	      param.add(name);
	      param.add(gender);
	      param.add(address);
	      param.add(rrn1);
	      param.add(rrn2);
	      param.add(telno);
	      param.add(str);
	      param.add(disease);
	      
	      patientservice.patientInsert(param);
	      System.out.println("등록 완료되었습니다.");
	      
	      Map<String, Object>map =patientservice.PIL();
	      String ptNo = (String) map.get("PT_NO");
	      String ptName = (String) map.get("ptName");
	      sessionStorage.put("pt_no", ptNo);
	      sessionStorage.put("ptName", ptName);
	     System.out.println(ptNo);

		return View.PATIENT_OLD;
	}

	private View treatment() {
		System.out.println("진료");
		System.out.println("1등록 2수정 3홈");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.TREATMENT_INSERT;
		case 2:
			return View.TREATMENT_UPDATE;
		case 3:
			return View.MAIN;
		default:
			return View.TREATMENT;
		}
	}


	private View schedule() {
		System.out.println("예약");
		System.out.println("1예약등록,2 예약수정,3예약취소 4홈");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.SCHEDULE_INSERT;
		case 2:
			return View.SCHEDULE_UPDATE;
		case 3:
			return View.SCHEDULE_CANCAL;
		case 4:
			return View.PATIENT_OLD;

		default:
			return View.SCHEDULE;
		}
	}

	private View patient() {
		//환자 이름 등록해서 출력 확인
		String patientSearch = ScanUtil.nextLine("환자이름 입력하기");
		List<Object> param = new ArrayList();
		param.add(patientSearch);
		List<Map<String, Object>> list = (List<Map<String, Object>>)patientservice.patientNOSearch(param);
		if(list == null) {
			//환자 정보가 없으면(null)이면 환자등록메소드로 이동
			System.out.println("등록된 정보가 없습니다. 환자등록으로 이동합니다.");
			return View.PATIENT_INSERT;
			
		}
		   for (Map<String, Object> map : list) {
		         String no = (String) map.get("PT_NO");
		         String name = (String) map.get("PT_NAME");
		         String address = (String) map.get("PT_ADDRESS");
		         String rrn1 = (String) map.get("PT_RRN1");
		         String rrn2 = (String) map.get("PT_RRN2");
		         String telno = (String) map.get("PT_TELNO");
		         String str = (String) map.get("PT_STR");
		         String disease = (String) map.get("PT_DISEASE");

		         System.out.println(no + "\t\t" + name + "\t\t" + telno + "\t\t" + rrn1 + "-" + rrn2 + "\t\t" + address
		               + "\t\t" + str + "\t\t");

		      }
			System.out.println("1. 등록된 환자");
			System.out.println("2. 신규 환자");
			int sel = ScanUtil.menu();
			switch (sel) {
			case 1:
				//환자 정보가 있으면 patient_old 로 이동
				
				String pt_no = ScanUtil.nextLine("차트번호 입력하시오");
					//환자 차트 입력해서 sessionStorage에 담음
					sessionStorage.put("pt_no",pt_no);
					return View.PATIENT_OLD;
				
			case 2:
				//환자정보가 없으면 환자등록
				return View.PATIENT_INSERT;

			default:
				return View.PATIENT;
			
			
		}
		
	}

	private View login() {
		System.out.println("로그인 페이지");
		//의사 아이디
		// id , pass ,no-> map 타입 저장하고
		// 진료 직원번호
		String id = ScanUtil.nextLine("id입력");
		String pass =ScanUtil.nextLine("pass입력");
		
		
		List<Object> param =new ArrayList();
		param.add(id);
		param.add(pass);
		
		
		boolean login = freeservice.login(param);
		if(!login) {//로그인 실패시
			System.out.println("로그인실패했습니다.");
			return View.LOGIN;
		}else {//로그인 성공시
			freeVo Dr_name = (freeVo)sessionStorage.get("Dr");
			System.out.println(Dr_name.getEmp_name()+"님 환영합니다.");
			
		}
		
		return View.MAIN;
	}

	private View home() {
		//출력문
		System.out.println("1환자, 2당일날짜조회,3원하는 날짜 조회");
		//선택문
		int sel = ScanUtil.nextInt("선택 하시오 ");
		switch (sel) {
		case 1:
			return View.PATIENT;
		case 2:
			return View.DATE_SEARCH;
		case 3:
			return View.CALENDAR_SEARCH;
		default:
			return View.MAIN;
		}
	}
}

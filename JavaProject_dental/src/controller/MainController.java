package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
//		View view = View.MAIN;
		while (true) {
			switch (view) {
			case LOGIN:
				view = login();
				break;
			case MAIN:
				view = home();
				break;
			case ADMIN:
				view = admin();
				break;
			case DATE_SEARCH:
				view = dateSearch();
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
				view = patientSearch();
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
			case PAY:
				view = pay();
				break;
			case PAY_SEARCH:
				view = paySearch();
				break;
			case PAY_INSERT:
				view = payInsert();
				break;
			case PAY_UPDATE:
				view = payUpdate();
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

	private View dateSearch() {
		System.out.println("날짜조회");
		return View.MAIN;
	}

	private View scheduleCancal() {
		System.out.println("예약취소");
		//몇번 예약취소됬는지 표시
		System.out.println("몇일날 예약취소 되었습니다.");
		System.out.println("1 다시 예약등록 2 예약취소 3 등록된 환자");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.SCHEDULE_INSERT;
		case 2:
			return View.SCHEDULE_CANCAL;
		case 3:
			return View.PATIENT_OLD;

		default:
			return View.SCHEDULE_CANCAL;
		}
	}

	private View payUpdate() {
		System.out.println("결제수정");
		System.out.println("결제 수정된 내용 출력");
		System.out.println("1.다시결제수정 2 결제 3 등록된 환자");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PAY_UPDATE;			
		case 2:
			return View.PAY;
		case 3:
			return View.PATIENT_OLD;
		default:
			return View.PAY_UPDATE;
		}
	}

	private View payInsert() {
		System.out.println("결제등록");
		System.out.println("결제등록한 내용 출력");
		System.out.println("1다시 결제 수정 2.등록한환자");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PAY_UPDATE;
		case 2:
			return View.PATIENT_OLD;

		default:
			return View.PAY_INSERT;
		}
	}

	private View paySearch() {
		System.out.println("결제조회");
		System.out.println("1 결제수정 2구환");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PAY_UPDATE;
		case 2:
			return View.PATIENT_OLD;

		default:
			return View.PAY_SEARCH;
		}
		
	}

	private View treatmentSearch() {
		System.out.println("진료조회");
		//등록된 내용 출력
		System.out.println("등록된 내용 출력");
		System.out.println("1진료수정 2 등록된환자");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.TREATMENT_UPDATE;
		case 2:
			return View.PATIENT_OLD;

		default:
			return View.TREATMENT_SEARCH;
		}
	}

	private View patientSearch() {
		System.out.println("환자조회");
		System.out.println("1 환자수정, 2구환");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PATIENT_UPDATE;
		case 2:
			return View.PATIENT_OLD;

		default:
			return View.PATIENT_SEARCH;
		}
	}

	private View scheduleSearch() {
		System.out.println("예약조회");
		return View.PATIENT_OLD;
	}

	private View scheduleUpdate() {
		System.out.println("예약수정");
		System.out.println("1다시 예약수정 2등록된환자");
		//수정된 내용 출력
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
		System.out.println("진료수정");
		//수정된 리스트  출력
		System.out.println("수정된내용 출력");
		System.out.println("1다시 진료수정   2등록된환자");
		int sel = ScanUtil.menu();
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
		String no =(String)list.get("PT_NO");
		String name =(String)list.get("PT_NAME");
		String mark =(String)list.get("TR_REMARK");
		String date =(String)list.get("TR_DATE");
		System.out.println(no +"\t"+name +"\t"+mark+"\t"+date);
		
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
	    printpatient_old(ptlist);
		System.out.println("1환자수정,2진료,3결제4예약,5조회, 6홈");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PATIENT_UPDATE;
		case 2:
			return View.TREATMENT;
		case 3:
			return View.PAY;
		case 4:
			return View.SCHEDULE;
		case 5:
			return View.SEARCH;
		case 6:
			return View.MAIN;
		default:
			return View.PATIENT_OLD;
		}
	}

	private View admin() {
		System.out.println("품목 , 진료과,직원 조회");
		System.out.println("1 품목조회 2직원조회 3의사조회 4진료과조회 5홈");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.ITEM;
		case 2:
			return View.EMPLOYEE;
		case 3:
			return View.DOCTOR;
		case 4:
			return View.SUBJECT;
		case 5:
			return View.MAIN;

		default:
			return View.ADMIN;
		}
	}

	private View search() {
		System.out.println("환자, 예약, 진료 조회");
		System.out.println("1 환자,2, 진료, 3,예약4 결제 조회");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PATIENT_SEARCH;
		case 2:
			return View.TREATMENT_SEARCH;
		case 3:
			return View.SCHEDULE_SEARCH;
		case 4:
			return View.PAY_SEARCH;

		default:
			return View.SEARCH;
		}
	}

	private View scheduleInsert() {
		System.out.println("예약등록");
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
		//이름 주소 전화번호, 전신질환
		System.out.println("환자수정 리스트 출력");
		System.out.println("1 다시 환자수정 2 등록된환자");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PATIENT_UPDATE;
		case 2:
			return View.PATIENT_OLD;

		default:
			return View.PATIENT_UPDATE;
		}
	}

	private View patientInsert() {
		System.out.println("환자등록");
		
		//짝궁님 코드

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

	private View pay() {
		System.out.println("결제목록");
		System.out.println("1 결제등록,2 결제수정 3결제조회");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PAY_INSERT;
		case 2:
			return View.PAY_UPDATE;
		case 3:
			return View.PAY_SEARCH;

		default:
			return View.PAY;
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
		
		System.out.println(list);//출력확인용
		
		if(list == null) {
			//환자 정보가 없으면(null)이면 환자등록메소드로 이동
			System.out.println("등록된 정보가 없습니다. 환자등록으로 이동합니다.");
			return View.PATIENT_INSERT;
			
		}else {
			//환자 정보가 있으면 patient_old 로 이동
			String pt_no = ScanUtil.nextLine("차트번호 입력하시오");
			//환자 차트 입력해서 sessionStorage에 담음
			sessionStorage.put("pt_no",pt_no);
			return View.PATIENT_OLD;
			
		}
		
	}

	private View login() {
		System.out.println("로그인 페이지");
		//의사 아이디
		// id , pass ,no-> map 타입 저장하고
		// 진료 직원번호
		String id = ScanUtil.nextLine("id입력");
		String pass = ScanUtil.nextLine("pass입력");
		
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
		System.out.println("환자, 날짜조회, 관리자");
		//선택문
		int sel = ScanUtil.nextInt("선택 하시오 ");
		switch (sel) {
		case 1:
			return View.PATIENT;
		case 2:
			return View.DATE_SEARCH;
		case 3: 
			return View.ADMIN;
		default:
			return View.MAIN;
		}
	}
}

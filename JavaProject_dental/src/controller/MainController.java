package controller;

import java.util.HashMap;
import java.util.Map;

import dao.patientDao;
import service.freeService;
import service.patientService;
import util.Print;
import util.ScanUtil;
import util.View;

public class MainController extends Print {
	freeService freeservice =freeService.getInstance();
	patientService patientDao =patientService.getInstance();
	static public Map<String, Object> sessionStorage = new HashMap<>();
	public static void main(String[] args) {
		new MainController().start();
	}
	
	private void start() {
		View view = View.LOGIN;
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
		System.out.println("1 다시 예약취소 2 예약 3 등록된 환자");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.SCHEDULE_CANCAL;
		case 2:
			return View.SCHEDULE;
		case 3:
			return View.PATIENT_OLD;

		default:
			return View.PATIENT_OLD;
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
			return View.PAY_SEARCH;
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
		//등록된 내용 출력
		System.out.println("등록된 내용출력");
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
		//해당 환자 정보 출력
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
		System.out.println("1 환자,2, 진료, 3,예약");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PATIENT_SEARCH;
		case 2:
			return View.TREATMENT_SEARCH;
		case 3:
			return View.SCHEDULE_SEARCH;

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
//		if() {
//			
//		}
		//등록하기 전에   주민등록 false true
		//등록가능하면 등록 가능한 환자입니다. 
		return View.PATIENT_OLD;
	}

	private View treatment() {
		System.out.println("진료");
		System.out.println("1등록 2수정 3결제등록3홈");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.TREATMENT_INSERT;
		case 2:
			return View.TREATMENT_UPDATE;
		case 3:
			return View.PAY_INSERT;
		case 4:
			return View.MAIN;
		default:
			return View.TREATMENT;
		}
	}

	private View pay() {
		System.out.println("결제목록");
		System.out.println("1 결제조회,2 결제등록 3결제 수정");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.PAY_SEARCH;
		case 2:
			return View.PAY_INSERT;
		case 3:
			return View.PAY_UPDATE;

		default:
			return View.PAY;
		}
		
	}

	private View schedule() {
		System.out.println("예약");
		System.out.println("1예약등록,2 예약수정,3예약조회,4예약취소 5홈");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.SCHEDULE_INSERT;
		case 2:
			return View.SCHEDULE_UPDATE;
		case 3:
			return View.SCHEDULE_SEARCH;
		case 4:
			return View.SCHEDULE_CANCAL;
		case 5:
			return View.PATIENT_OLD;

		default:
			return View.SCHEDULE;
		}
	}

	private View patient() {
		//어떤 환자인지 조회부터하는거 신환 구환 구분
		//구환이면 map타입 이용해서 넣기
		System.out.println("신환 등록된 환자 ");
		if(true) { //신환이면 바로 환자등록으로 가기
			return View.PATIENT_INSERT;
		}else {    // 구환이면 구환 홈으로 감
			//map 타입사용
			return View.PATIENT_OLD;
		}
	}

	private View login() {
		System.out.println("로그인 페이지");
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

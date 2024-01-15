package util;

public enum View {
	LOGIN, //로그인
	ADMIN, // 관리자
	
	MAIN, // 홈
	
	PATIENT, //환자
	PATIENT_INSERT, //신환 등록
	PATIENT_OLD, //등록된 환자
	PATIENT_UPDATE, //환자수정
	PATIENT_SEARCH, //환자조회
	
	SCHEDULE, //예약
	SCHEDULE_INSERT, //예약등록
	SCHEDULE_UPDATE, //예약수정
	SCHEDULE_SEARCH, //예약조회
	SCHEDULE_CANCAL, //예약취소
	
	TREATMENT,//진료
	TREATMENT_INSERT,//진료등록
	TREATMENT_UPDATE,//진료수정
	TREATMENT_SEARCH,//진료조회
	
	SEARCH,//조회
	
	DATE_SEARCH,//날짜로 인해서 진료&예약 조회
	ITEM, //품목
	
	PAY,//결제
	PAY_SEARCH,//결제조회
	PAY_INSERT,//결제등록
	PAY_UPDATE,//결제수정
	
	EMPLOYEE,//직원
	DOCTOR,//직원
	
	SUBJECT//진료과목
}

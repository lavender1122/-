package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.patientDao;
import vo.patientVo;

public class patientService {
	private static patientService instance =null;
		
		private patientService() {
			
		}
		
		public static patientService getInstance() {
			if(instance == null) {
				instance = new patientService();
			}
			return instance;
		}
		
	patientDao patientdao = patientDao.getInstance();




	public List<Map<String, Object>> patientNOSearch(List<Object> param) {
		return patientdao.patientNOSearch(param);
	}

	public Map<String, Object> ptoList(String ptNo) {
		return patientdao.ptoList(ptNo);
	}

	
	
	public void treatmentInsert(List<Object> param) {//진료등록 넣은값
		 patientdao.treatmentInsert(param);
	}

	public Map<String, Object> TIL(String pt_no) {
		return patientdao.TIL(pt_no);
	}

	
	public void TU(List<Object> param) {
		patientdao.TU(param);
	}

	public Map<String, Object> TUL() {
		return patientdao.TUL();
	}
	public void patientInsert(List<Object> param) {      
		patientdao.patientInsert(param);
	   }

	public Map<String, Object> PIL() {
		
		return patientdao.PIL();
	}

	public void SI(List<Object> param) {
		patientdao.SI(param);
	}

	public Map<String, Object> SIL(String ptNo) {
		return patientdao.SIL(ptNo);
	}

	public Object SU(List<Object> param, int select) {
		return patientdao.SU(param,select);
	}

	public Map<String, Object> SUL(String ptNo) {
		return patientdao.SUL(ptNo);
	}

	public void patientUpdate(List<Object> param, int sel) {
	      patientdao.patientupdate(param,sel);
	   }

	public Map<String, Object> SC(String ptNo) {
		
		return patientdao.SC(ptNo);
	}

	public List<Map<String, Object>> SCL(String ptNo) {
		return patientdao.SCL(ptNo);
	}

	public void scCancel(List<Object> param) {
		patientdao.scCancel(param);
	}

	

	public Map<String, Object> scCancalCk(List<Object> param) {
		return patientdao.scCancalCk(param);
	}
	   public List<Map<String, Object>> dateSearch() {
		      return patientdao.dateSearch();
		   }



	public List<patientVo> trSearchList(List<Object> param, int select) {
		return patientdao.trSearchList(param,select);
	}

	public List<patientVo> scSearchList(List<Object> param, int select) {
		return patientdao.scSearchList(param, select);
	}

	public List<Map<String, Object>> calendarSearch(List<Object> param) {
		 return patientdao.calendarsearch(param);
	}
	
	
}

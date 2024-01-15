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

	
	public void TU(List<Object> param, int select) {
		patientdao.TU(param,select);
	}

	public Map<String, Object> TUL() {
		return patientdao.TUL();
	}
	public void patientInsert(List<Object> param) {      
		patientdao.patientInsert(param);
	   }

	public void PIL() {
		patientdao.PIL();
	}

	
}

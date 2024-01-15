package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.freeDao;
import vo.freeVo;

public class freeService {
	private static freeService instance =null;
		
		private freeService() {
			
		}
		
		public static freeService getInstance() {
			if(instance == null) {
				instance = new freeService();
			}
			return instance;
		}
		
	freeDao freedao =freeDao.getInstance();
	
	public  boolean login(List<Object> param) {
		freeVo map = freedao.login(param);
		if(map ==null) { // 로그인 일치하면 true 이랑 map 타입이용해서  이름,emp_no 저장하기
			return false ;
		}else {
			MainController.sessionStorage.put("Dr",map);
			return true ;
		}
	}

}

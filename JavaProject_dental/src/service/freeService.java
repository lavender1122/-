package service;

import dao.freeDao;

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

}

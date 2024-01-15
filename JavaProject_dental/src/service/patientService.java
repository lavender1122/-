package service;

import dao.patientDao;

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

}

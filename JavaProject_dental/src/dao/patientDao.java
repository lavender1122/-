package dao;

import util.JDBCUtil;

public class patientDao {
	private static patientDao instance =null;
		
		private patientDao() {
			
		}
		
		public static patientDao getInstance() {
			if(instance == null) {
				instance = new patientDao();
			}
			return instance;
		}
		
		JDBCUtil jdbc = JDBCUtil.getInstance();
	

}

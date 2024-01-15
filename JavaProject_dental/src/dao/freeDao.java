package dao;

import util.JDBCUtil;

public class freeDao {
	private static freeDao instance =null;
		
		private freeDao() {
			
		}
		
		public static freeDao getInstance() {
			if(instance == null) {
				instance = new freeDao();
			}
			return instance;
		}
		
	JDBCUtil jdbc = JDBCUtil.getInstance();

}

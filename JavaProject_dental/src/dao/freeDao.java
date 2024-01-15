package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.freeVo;

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
	
	public  freeVo login(List<Object> param) {
		String sql = "SELECT *\r\n" + 
				" FROM EMPLOYEE\r\n" + 
				" WHERE EMP_ID = ?\r\n" + 
				"    AND EMP_PW=?";
		return jdbc.selectOne(sql, param,freeVo.class);
	}

}

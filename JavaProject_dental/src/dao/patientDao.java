package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.patientVo;

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

		

		public List<Map<String, Object>> patientNOSearch(List<Object> param) {
			String sql = "SELECT PT_NO,PT_NAME,PT_GEN,PT_ADDRESS,to_char(PT_RRN1) PT_RRN1,to_char(PT_RRN2) PT_RRN2,PT_TELNO,PT_STR,PT_DISEASE\r\n" + 
					" FROM PATIENT\r\n" + 
					" WHERE PT_NAME LIKE '%'||?||'%'   ";
			return jdbc.selectList(sql,param);
		}

		public Map<String, Object> ptoList(String ptNo) {
			String sql = "SELECT PT_NO, PT_NAME, PT_GEN,PT_RRN1,PT_RRN2,PT_STR,PT_DISEASE \r\n" + 
					" FROM PATIENT\r\n" + 
					" WHERE PT_NO = '" +ptNo + "'";
			return jdbc.selectOne(sql);
		}
		
		public  void treatmentInsert(List<Object> param) {
			String sql="INSERT INTO TREATMENT\r\n" + 
					"VALUES('T'|| PATIENT_SEQUENCE.NEXTVAL,?,SYSDATE,?,?)";
			jdbc.update(sql, param);
		}

		public Map<String, Object> TIL(String pt_no) {//등록된 환자 내용출력
			String sql ="SELECT T.PT_NO,T1.PT_NAME, T.TR_REMARK,TO_CHAR(TR_DATE,'YYYY-MM-DD') TR_DATE\r\n" + 
					" FROM TREATMENT T, PATIENT T1\r\n" + 
					" WHERE T. PT_NO=T1. PT_NO \r\n" + 
					"        AND ROWNUM =1 \r\n" + 
					"        AND  T.PT_NO ='"+ pt_no +"'\r\n" + 
					"ORDER BY T.TR_NO DESC";
			return jdbc.selectOne(sql);
		}
	

}

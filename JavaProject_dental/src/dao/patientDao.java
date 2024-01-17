package dao;

import java.util.ArrayList;
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
			String sql="    INSERT INTO TREATMENT\r\n" + 
					"    VALUES(\r\n" + 
					"    (SELECT 'T'||LPAD(MAX(TO_NUMBER(SUBSTR(TR_NO,2)))+1,3,'0')FROM TREATMENT),\r\n" + 
					"    ?,\r\n" + 
					"    SYSDATE,\r\n" + 
					"    ?,\r\n" + 
					"    ?\r\n" + 
					"    )";
			jdbc.update(sql, param);
		}

		public Map<String, Object> TIL(String pt_no) {//등록된 환자 내용출력
			String sql ="SELECT \r\n" + 
					"    T.PT_NO,\r\n" + 
					"    P.PT_NAME,\r\n" + 
					"    T.TR_REMARK,\r\n" + 
					"    T.TR_DATE\r\n" + 
					"    FROM TREATMENT T, PATIENT P\r\n" + 
					"    WHERE\r\n" + 
					"    T.PT_NO = P.PT_NO\r\n" + 
					"    AND P.PT_NO = '"+pt_no+"'\r\n" + 
					"    AND TO_NUMBER(SUBSTR(T.TR_NO,2))IN  (SELECT MAX(TO_NUMBER(SUBSTR(TR_NO,2))) \r\n" + 
					"                                                        FROM TREATMENT)";
			return jdbc.selectOne(sql);
		}

		public void TU(List<Object> param) {
			String sql ="UPDATE TREATMENT\r\n" + 
					" SET\r\n" + 
					" TR_REMARK = ?\r\n" + 
					" WHERE \r\n" + 
					" SUBSTR(TR_NO,2,6) IN (SELECT MAX(SUBSTR(TR_NO,2,6))\r\n" + 
					" FROM TREATMENT)";
			jdbc.update(sql,param);
			
		}

		public Map<String, Object> TUL() {
			String sql = "SELECT *\r\n" + 
					" FROM TREATMENT\r\n" + 
					" WHERE SUBSTR(TR_NO,2,6) IN (SELECT MAX(SUBSTR(TR_NO,2,6))\r\n" + 
					" FROM TREATMENT)";
			return jdbc.selectOne(sql);
		}

		public void patientInsert(List<Object> param) {
			String sql = "INSERT INTO PATIENT\r\n" + 
					"  VALUES((SELECT 'P'||LPAD(MAX(TO_NUMBER(SUBSTR(PT_NO,2)))+1,3,'0') \r\n" + 
					"                FROM PATIENT),\r\n" + 
					"                ?,\r\n" + 
					"                ? ,\r\n" + 
					"                ? ,\r\n" + 
					"                ? ,\r\n" + 
					"                ? ,\r\n" + 
					"                ? ,\r\n" + 
					"                ? ,\r\n" + 
					"                ? \r\n" + 
					"                )  ";  
		               
		         jdbc.update(sql, param);
		}

		public Map<String, Object> PIL() {
			String sql = "SELECT *\r\n" + 
					" FROM PATIENT\r\n" + 
					" WHERE TO_NUMBER(SUBSTR(PT_NO,2)) IN (SELECT MAX(TO_NUMBER(SUBSTR(PT_NO,2))) FROM PATIENT)";
			return jdbc.selectOne(sql);
			
		}

		
		public void SI(List<Object> param) {
			String sql = "INSERT INTO SCHEDULE\r\n" + 
					"    VALUES(\r\n" + 
					"    (SELECT 'S'||LPAD(MAX(TO_NUMBER(SUBSTR(SC_NO,2)))+1,3,'0')FROM SCHEDULE),\r\n" + 
					"    ?,\r\n" + 
					"     to_date(?,'MM-DD HH24:MI'),\r\n" + 
					"    null,\r\n" + 
					"    'N',\r\n" + 
					"    'Y',\r\n" + 
					"    null,\r\n" + 
					"    ?,\r\n" + 
					"    ?\r\n" + 
					")";
			jdbc.update(sql,param);
		}

		public Map<String, Object> SIL(String ptNo) {
			String sql = "SELECT *\r\n" + 
					"FROM SCHEDULE\r\n" + 
					"WHERE PT_NO = '"+ptNo+"'\r\n" + 
					"    AND TO_NUMBER(SUBSTR(SC_NO,2)) IN (SELECT MAX(TO_NUMBER(SUBSTR(SC_NO,2))) FROM SCHEDULE)";
			return jdbc.selectOne(sql);
		}

		public Object SU(List<Object> param, int select) {
			String sqlFront = "UPDATE SCHEDULE \r\n" + 
					"SET ";
			String sql = sqlFront;
			if(select ==1 ) {
				sql += "SC_REMARK = ?\r\n" + 
						"	WHERE\r\n" + 
						" TO_NUMBER(SUBSTR(SC_NO,2)) IN (SELECT MAX(TO_NUMBER(SUBSTR(SC_NO,2)))\r\n" + 
						"                                                        FROM SCHEDULE WHERE PT_NO=?)";
			}
			if(select ==2 ) {
				sql +="SC_DATE = TO_DATE(?,'MM-DD HH24:MI')\r\n" + 
						"	WHERE\r\n" + 
						" TO_NUMBER(SUBSTR(SC_NO,2)) IN (SELECT MAX(TO_NUMBER(SUBSTR(SC_NO,2)))\r\n" + 
						"                                                        FROM SCHEDULE WHERE PT_NO=?)";
			}
			if(select == 3 )
			sql += " SC_REMARK = ?,\r\n" + 
					" SC_DATE = TO_DATE(?,'MM-DD HH24:MI')\r\n" + 
					"	WHERE\r\n" + 
					" TO_NUMBER(SUBSTR(SC_NO,2)) IN (SELECT MAX(TO_NUMBER(SUBSTR(SC_NO,2)))\r\n" + 
					"                                                        FROM SCHEDULE WHERE PT_NO= ?)";
			return jdbc.update(sql, param);
			
		}

		public Map<String, Object> SUL(String ptNo) {
			String sql = "    SELECT *\r\n" + 
					" FROM SCHEDULE\r\n" + 
					" WHERE \r\n" + 
					"     TO_NUMBER(SUBSTR(SC_NO, 2)) IN (SELECT MAX(TO_NUMBER(SUBSTR(SC_NO, 2))) \r\n" + 
					"                                         FROM SCHEDULE WHERE PT_NO='"+ptNo+"')";
			return jdbc.selectOne(sql);
		}

		public void patientupdate(List<Object> param, int sel) {
			 String format = " UPDATE PATIENT\r\n" + 
		               " SET %s = ?\r\n" + 
		               " WHERE PT_NO = ? ";
		         
		         String sql = "";
		         if (sel == 1) {
		            sql = String.format(format, "PT_NAME");
		         }
		         if (sel == 2) {
		            sql = String.format(format, "PT_ADDRESS");
		         }
		         if (sel == 3) {
		            sql = String.format(format, "PT_TELNO");
		         }
		         if (sel == 4) {
		            sql = String.format(format, "PT_STR");
		         }
		         if (sel == 5) {
		            sql = String.format(format, "PT_DISEASE");
		         }
		         jdbc.update(sql, param);

		}

		public Map<String, Object> SC(String ptNo) {
			String sql =" SELECT P.PT_NO, P.PT_NAME,P.PT_TELNO,P.PT_STR,(SELECT COUNT(*)\r\n" + 
					"                                                    FROM SCHEDULE\r\n" + 
					"                                                    WHERE SC_COME ='N'\r\n" + 
					"                                                      AND PT_NO = '"+ptNo+"') SC_COME\r\n" + 
					" FROM PATIENT P\r\n" + 
					" WHERE P.PT_NO = '"+ptNo+"'";
			return jdbc.selectOne(sql);
		}

		public List<Map<String, Object>> SCL(String ptNo) {
			String sql = "SELECT  S.SC_NO, \r\n" + 
					"    TO_CHAR(S.SC_DATE, 'MM-DD HH24:MI') SC_DATE ,\r\n" + 
					"    S.SC_REMARK ,\r\n" + 
					"    S.SUB_NO ,\r\n" + 
					"    E.EMP_NAME\r\n" + 
					" FROM SCHEDULE S, EMPLOYEE E\r\n" + 
					" WHERE SC_DATE>SYSDATE\r\n" + 
					"    AND E.EMP_NO LIKE 'D%'\r\n" + 
					"    AND S.PT_NO = '"+ptNo+"'\r\n" + 
					"    ORDER BY SC_DATE";
		 	return jdbc.selectList(sql);
		}

		public void scCancel(List<Object> param) {
			String sql ="  UPDATE SCHEDULE\r\n" + 
					"SET \r\n" + 
					" SC_COME = 'N',\r\n" + 
					" SC_CANCEL='까먹음'\r\n" + 
					" WHERE\r\n" + 
					" PT_NO = 'P2'\r\n" + 
					" and SC_NO = 'S2'";
			jdbc.update(sql, param);
		}

		

		public Map<String, Object> scCancalCk(List<Object> param) {
			String sql = "SELECT\r\n" + 
					"    TO_CHAR(S.SC_DATE, 'MM-DD HH24:MI') SC_DATE ,\r\n" + 
					"    S.SC_REMARK ,\r\n" + 
					"    S.SUB_NO ,\r\n" + 
					"    E.EMP_NAME\r\n" + 
					" FROM SCHEDULE S, EMPLOYEE E\r\n" + 
					" WHERE\r\n" + 
					" S.PT_NO=?\r\n" + 
					" AND S.SC_COME='N' \r\n" + 
					" and  S.SC_NO = ?\r\n" + 
					" AND S.SUB_NO =E.SUB_NO\r\n" + 
					" AND E.EMP_NO LIKE 'D%'";
			return jdbc.selectOne(sql);
		}
		   public List<Map<String, Object>> dateSearch() {
			   String sql = " SELECT DISTINCT P.PT_NO, P.PT_NAME, S.SC_CK , P.PT_DISEASE, \r\n" + 
		               " S.SC_REMARK,P.PT_STR , TO_CHAR(S.SC_DATE, 'YY/MM/DD') SC_DATE, T.EMP_NO \r\n" + 
		               " FROM SCHEDULE S, PATIENT P, TREATMENT T\r\n" + 
		               " WHERE S.PT_NO = P.PT_NO \r\n" + 
		               " AND SC_DATE = TO_CHAR(SYSDATE)";
		         return jdbc.selectList(sql);
		      }

		

		public List<patientVo> trSearchList(List<Object> param, int select) {
			String sqlFront = " SELECT \r\n" + 
					"     T.TR_NO," +
					"TO_CHAR(T.TR_DATE,'MM-DD HH24:MI') TR_DATE,\r\n" + 
					"   T.TR_REMARK,\r\n" + 
					"   E.SUB_NO,\r\n" + 
					"   E.EMP_NAME\r\n" + 
					"   FROM TREATMENT T, EMPLOYEE E\r\n" + 
					"   WHERE\r\n" + 
					"   T.PT_NO = ? \r\n" + 
					"   AND T.EMP_NO=E.EMP_NO";
			String sql = sqlFront;
			if(select == 1) {
				sql +=	"   AND TO_CHAR(T.TR_DATE, 'MM') = LPAD(?,2,0)\r\n" + 
						"   AND TO_CHAR(T.TR_DATE, 'DD') BETWEEN LPAD(?,2,0) AND LPAD(?,2,0)\r\n" ;
						
			}
			if(select == 2) {
				sql +=  "   AND T.EMP_NO=E.EMP_NO\r\n" + 
						"   AND T.TR_REMARK LIKE '%'|| ? ||'%'";
			}
			if(select == 3) {
				sql +=	"   AND T.EMP_NO=E.EMP_NO\r\n" + 
						"   AND  E.SUB_NO LIKE '%'|| ? ||'%'";
			}
			sql+="  ORDER BY TR_DATE";
		
			return jdbc.selectList(sql,param,patientVo.class);
		}

		public List<patientVo> scSearchList(List<Object> param, int select) {
			String sqlFront = "SELECT \r\n" + 
					"   S.SC_NO," +
					"TO_CHAR(S.SC_DATE,'MM-DD HH24:MI') SC_DATE,\r\n" + 
					"   S.SC_REMARK,\r\n" + 
					"   S.SC_CK ,\r\n" + 
					"   S.SC_COME,\r\n" + 
					"   S.SC_CANCEL,\r\n" + 
					"   S.SUB_NO,\r\n" + 
					"   E.EMP_NAME\r\n" + 
					"   FROM SCHEDULE S, EMPLOYEE E\r\n" + 
					"   WHERE\r\n" + 
					"   PT_NO =?\r\n" + 
					"   AND E.SUB_NO = S.SUB_NO";
			String sql = sqlFront;
			if(select == 1) {
				sql += " AND E.SUB_NO = S.SUB_NO\r\n" + 
						"   AND TO_CHAR(S.SC_DATE, 'MM') = LPAD(?,2,0)\r\n" + 
						"   AND TO_CHAR(S.SC_DATE, 'DD') BETWEEN LPAD(?,2,0) AND LPAD(?,2,0)\r\n" + 
						"   and E.EMP_NO LIKE '%D%'";
			}
			if(select ==2) {
				sql += " AND S.SC_REMARK LIKE '%'|| ? ||'%'";
			}
			if(select ==3) {
				sql += " AND S.SUB_NO LIKE '%'|| ? ||'%'";
			}
			sql+=" and E.EMP_NO LIKE '%D%'\r\n" + 
					"   ORDER BY SC_DATE";
			return jdbc.selectList(sql,param,patientVo.class);
		}

		public List<Map<String, Object>> calendarsearch(List<Object> param) {
	         String sql =" SELECT DISTINCT P.PT_NO, P.PT_NAME, P.PT_TELNO, S.SC_REMARK, S.SC_CK, T.EMP_NO\r\n" + 
	                 " FROM PATIENT P, SCHEDULE S, TREATMENT T \r\n" + 
	                 " WHERE S.PT_NO = P.PT_NO\r\n" + 
	                 " AND TO_CHAR(SC_DATE, 'DD') = LPAD(?,2,0)";
	           
	           return jdbc.selectList(sql,param);
		}
	

}

package multiboard;

import common.DBConnPool;

public class MultiBoardDAO extends DBConnPool {
	public MultiBoardDAO() {
		super();
	}
	
	public int insertMultiBoard(MultiBoardDTO dto) {
		int applyResult = 0;
		try {
			String query = "INSERT INTO multiboard (idx, user_id, title, content, ofile, sfile) "
							+ " VALUES (seq_multib_num.nextval, 'admin', ?, ?, ?, ?)";
			
			
		}catch(Exception e){}
		return applyResult;
	}
	

}

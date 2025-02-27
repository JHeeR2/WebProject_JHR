package qna;

import common.DBConnPool;

public class AnswerDAO extends DBConnPool{
	
    public AnswerDAO() {
        super();
    }
    


    // 댓글 삭제
    public int deleteAnswer(int answerId) {
        int result = 0;
        try {
            String query = "DELETE FROM answers WHERE a_idx = ?";
            psmt = con.prepareStatement(query);
            psmt.setInt(1, answerId);
            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

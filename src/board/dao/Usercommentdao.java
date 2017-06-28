package board.dao;

import static board.utils.Closeableutil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.beans.Usercomment;
import board.exception.SQLRuntimeException;

public class Usercommentdao {
	public List<Usercomment> getUserMessages(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_comments ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Usercomment> ret = toUsercommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Usercomment> toUsercommentList(ResultSet rs)
			throws SQLException {

		List<Usercomment> ret = new ArrayList<Usercomment>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int userId = rs.getInt("user_id");
				String postId = rs.getString("post_id");
				String branch_id = rs.getString("branch_id");
				String department_id = rs.getString("department_id");
				String text = rs.getString("comment");
				String insert_datetime = rs.getString("insert_datetime");
				Usercomment comment = new Usercomment();

				comment.setId(id);
				comment.setName(name);
				comment.setUserId(userId);
				comment.setPostId(postId);
				comment.setBranch_id(branch_id);
				comment.setDepartment_id(department_id);
				comment.setText(text);
				comment.setInsert_datetime(insert_datetime);
				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}

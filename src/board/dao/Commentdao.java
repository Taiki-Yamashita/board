package board.dao;

import static board.utils.Closeableutil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import board.beans.Comment;
import board.exception.SQLRuntimeException;

public class Commentdao {
	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("user_id");
			sql.append(", post_id");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", comment");
			sql.append(", insert_datetime");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, comment.getUserId());
			ps.setString(2, comment.getPostId());
			ps.setString(3, comment.getBranch_id());
			ps.setString(4, comment.getDepartment_id());
			ps.setString(5, comment.getComment());
			//ps.setString(3, comment.getInsert_datetime());
//System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void delete(Connection connection, int comment_id) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM  comments where id = ?";

			//sql.append("DELETE FROM  users where id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment_id);

			ps.executeUpdate();
		} catch (SQLException e){
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}

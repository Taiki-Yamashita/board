package board.dao;

import static board.utils.Closeableutil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import board.beans.Post;
import board.exception.SQLRuntimeException;

public class Postdao {

	public void insert(Connection connection, Post post) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO posts ( ");
			sql.append("user_id");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", title");
			sql.append(", text");
			sql.append(", category");
			sql.append(", insert_datetime");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, post.getUserId());
			ps.setString(2, post.getBranch_id());
			ps.setString(3, post.getDepartment_id());
			ps.setString(4, post.getTitle());
			ps.setString(5, post.getText());
			ps.setString(6, post.getCategory());
			//ps.setString(7, post.getInsert_datetime());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void delete(Connection connection, int post_id) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM  posts where id = ?";

			//sql.append("DELETE FROM  users where id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, post_id);

			ps.executeUpdate();
		} catch (SQLException e){
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}

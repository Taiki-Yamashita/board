package board.dao;

import static board.utils.Closeableutil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import board.beans.User;
import board.exception.NoRowsUpdatedRuntimeException;
import board.exception.SQLRuntimeException;

public class Reversedao {
	public void update(Connection connection,User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" is_stop = 0");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, user.getId());

			System.out.println(user.getId());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}

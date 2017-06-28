package board.service;

import static board.utils.Closeableutil.*;
import static board.utils.DButil.*;

import java.sql.Connection;

import board.beans.User;
import board.dao.Reversedao;

public class Reverseservice {
	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			Reversedao reverseDao = new Reversedao();
			reverseDao.update(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}

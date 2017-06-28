package board.service;

import static board.utils.Closeableutil.*;
import static board.utils.DButil.*;

import java.sql.Connection;

import board.beans.User;
import board.dao.Stopdao;

public class Stopservice {
	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			Stopdao stopDao = new Stopdao();
			stopDao.update(connection, user);

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

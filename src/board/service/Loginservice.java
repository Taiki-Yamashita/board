package board.service;

import static board.utils.Closeableutil.*;
import static board.utils.DButil.*;

import java.sql.Connection;

import board.beans.User;
import board.dao.Userdao;
import board.utils.Cipherutil;

public class Loginservice {
	public User login(String account, String password, boolean is_stop) {

		Connection connection = null;
		try {
			connection = getConnection();

			Userdao userDao = new Userdao();
			String encPassword = Cipherutil.encrypt(password);
			User user = userDao
					.getUser(connection, account, encPassword, is_stop);

			commit(connection);

			return user;
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

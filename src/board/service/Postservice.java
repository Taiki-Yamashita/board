package board.service;

import static board.utils.Closeableutil.*;
import static board.utils.DButil.*;

import java.sql.Connection;
import java.util.List;

import board.beans.Post;
import board.beans.Userpost;
import board.dao.Postdao;
import board.dao.Userpostdao;

public class Postservice {

	public void register(Post post) {

		Connection connection = null;
		try {
			connection = getConnection();

			Postdao messageDao = new Postdao();
			messageDao.insert(connection, post);

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

	private static final int LIMIT_NUM = 1000;

	public List<Userpost> getPost() {

		Connection connection = null;
		try {
			connection = getConnection();

			Userpostdao messageDao = new Userpostdao();
			List<Userpost> ret = messageDao.getUserMessages(connection, LIMIT_NUM);

			commit(connection);

			return ret;
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

	public void delete(int post_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			Postdao postDao = new Postdao();
			postDao.delete(connection, post_id);

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


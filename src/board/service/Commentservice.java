package board.service;

import static board.utils.Closeableutil.*;
import static board.utils.DButil.*;

import java.sql.Connection;
import java.util.List;

import board.beans.Comment;
import board.beans.Usercomment;
import board.dao.Commentdao;
import board.dao.Usercommentdao;

public class Commentservice {
	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();

			Commentdao commentDao = new Commentdao();
			commentDao.insert(connection, comment);

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

	public List<Usercomment> getComment() {

		Connection connection = null;
		try {
			connection = getConnection();

			Usercommentdao messageDao = new Usercommentdao();
			List<Usercomment> ret = messageDao.getUserMessages(connection, LIMIT_NUM);

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

	public void delete(int comment_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			Commentdao commentDao = new Commentdao();
			commentDao.delete(connection, comment_id);

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

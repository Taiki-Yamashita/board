package board.service;

import static board.utils.Closeableutil.*;
import static board.utils.DButil.*;

import java.sql.Connection;
import java.util.List;

import board.beans.User;
import board.beans.Userpost;
import board.dao.Userdao;
import board.dao.Userpostdao;
import board.utils.Cipherutil;

public class Userservice {

	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = Cipherutil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			Userdao userDao = new Userdao();
			userDao.insert(connection, user);

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

	public void update(User user, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = Cipherutil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			Userdao userDao = new Userdao();
			userDao.update(connection, user, password);

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

	public void delete(int id) {

		Connection connection = null;
		try {
			connection = getConnection();

			Userdao userDao = new Userdao();
			userDao.delete(connection, id);

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

	public User getUser(int Id) {

		Connection connection = null;
		try {
			connection = getConnection();

			Userdao userDao = new Userdao();
			User user = userDao.getUser(connection, Id);

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

	public List<User> getManage() {

		Connection connection = null;
		try {
			connection = getConnection();

			Userdao userdao = new Userdao();
			List<User> ret = userdao.getAllUser(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch(Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<User> select(String account) {

		Connection connection = null;
		try {
			connection = getConnection();

			Userdao userdao = new Userdao();
			List<User> ret = userdao.getSelectedUser(connection, account);


			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch(Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Userpost> getCategory() {
		Connection connection = null;
		try {
			connection = getConnection();

			Userpostdao postdao = new Userpostdao();
			List<Userpost> ret = postdao.getCategory(connection);


			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch(Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Userpost> getInsert_datetime(String categorybox, String startcalender, String endcalender) {
		Connection connection = null;
		try {
			connection = getConnection();

			Userpostdao postdao = new Userpostdao();
			List<Userpost> ret = postdao.getInsert_datetime(connection, categorybox, startcalender, endcalender);


			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch(Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
		//return null;
	}


}

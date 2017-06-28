package board.service;

import static board.utils.Closeableutil.*;
import static board.utils.DButil.*;

import java.sql.Connection;
import java.util.List;

import board.beans.Branch;
import board.dao.Branchdao;

public class Branchservice {

	public List<Branch> getBranch() {

		Connection connection = null;
		try {
			connection = getConnection();

			Branchdao branchDao = new Branchdao();
			List<Branch> branch = branchDao.getBranch(connection);

			commit(connection);

			return branch;
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

	public List<Branch> getManage() {

		Connection connection = null;
		try {
			connection = getConnection();

			Branchdao branchdao = new Branchdao();
			List<Branch> ret = branchdao.getBranch(connection );

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

}

package board.service;

import static board.utils.Closeableutil.*;
import static board.utils.DButil.*;

import java.sql.Connection;
import java.util.List;

import board.beans.Department;
import board.dao.Departmentdao;


public class Departmentservice {

	public List<Department> getDepartment() {

		Connection connection = null;
		try {
			connection = getConnection();

			Departmentdao departmentDao = new Departmentdao();
			List<Department> department = departmentDao.getDepartment(connection);

			commit(connection);

			return department;
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

	public List<Department> getManage() {

		Connection connection = null;
		try {
			connection = getConnection();

			Departmentdao departmentdao = new Departmentdao();
			List<Department> ret = departmentdao.getDepartment(connection);

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

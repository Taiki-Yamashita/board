package board.dao;

import static board.utils.Closeableutil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.beans.Department;
import board.exception.SQLRuntimeException;

public class Departmentdao {

//	public List<Department> getDepartment(Connection connection, int id) {
//
//		PreparedStatement ps = null;
//		try {
//			String sql = "SELECT * FROM departments WHERE id = ?";
//
//			ps = connection.prepareStatement(sql);
//			ps.setInt(1, id);
//
//			ResultSet rs = ps.executeQuery();
//			List<Department> departmentList = toDepartmentmanageList(rs);
//			if (departmentList.isEmpty() == true) {
//				return null;
//			} else if (2 <= departmentList.size()) {
//				throw new IllegalStateException("2 <= departmentList.size()");
//			} else {
//				return departmentList.get(0);
//			}
//		} catch (SQLException e) {
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//	}
	public List<Department> getDepartment(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM departments ");


			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Department> ret = toDepartmentmanageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Department> toDepartmentmanageList(ResultSet rs)
			throws SQLException {

		List<Department> ret = new ArrayList<Department>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String departmentname = rs.getString("name");

				Department department = new Department();
				department.setId(id);
				department.setDepartmentName(departmentname);

				ret.add(department);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}

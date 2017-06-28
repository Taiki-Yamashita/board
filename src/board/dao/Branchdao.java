package board.dao;

import static board.utils.Closeableutil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.beans.Branch;
import board.exception.SQLRuntimeException;

public class Branchdao {

	public Branch getBranch(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM branches WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<Branch> branchList = toBranchmanageList(rs);
			if (branchList.isEmpty() == true) {
				return null;
			} else if (2 <= branchList.size()) {
				throw new IllegalStateException("2 <= branchList.size()");
			} else {
				return branchList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<Branch> getBranch(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM branches ");


			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Branch> ret = toBranchmanageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Branch> toBranchmanageList(ResultSet rs)
			throws SQLException {

		List<Branch> ret = new ArrayList<Branch>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String branchname = rs.getString("name");

				Branch branch = new Branch();
				branch.setId(id);
				branch.setBranchName(branchname);

				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}

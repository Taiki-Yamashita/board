package board.dao;

import static board.utils.Closeableutil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import board.beans.User;
import board.exception.NoRowsUpdatedRuntimeException;
import board.exception.SQLRuntimeException;

public class Userdao {

		public User getUser(Connection connection, String account,
				String password, boolean is_stop) {

			PreparedStatement ps = null;
			try {
				String sql = "SELECT * FROM users WHERE account = ? AND password = ? AND is_stop = ?";

				ps = connection.prepareStatement(sql);
				ps.setString(1, account);
				ps.setString(2, password);
				ps.setBoolean(3, is_stop);

				ResultSet rs = ps.executeQuery();
				List<User> userList = toUserList(rs);
				if (userList.isEmpty() == true) {
					return null;
				} else if (2 <= userList.size()) {
					throw new IllegalStateException("2 <= userList.size()");
				} else {
					return userList.get(0);
				}
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

		private List<User> toUserList(ResultSet rs) throws SQLException {

			List<User> ret = new ArrayList<User>();
			try {
				while (rs.next()) {
					int id = rs.getInt("id");
					String account = rs.getString("account");
					String password = rs.getString("password");
					String name = rs.getString("name");
					String branch_id = rs.getString("branch_id");
					String department_id = rs.getString("department_id");
					boolean is_stop = rs.getBoolean("is_stop");

					User user = new User();
					user.setId(id);
					user.setAccount(account);
					user.setPassword(password);
					user.setName(name);
					user.setBranch_id(branch_id);
					user.setDepartment_id(department_id);
					user.setIs_stop(is_stop);

					ret.add(user);
				}
				return ret;
			} finally {
				close(rs);
			}
		}

		public void insert(Connection connection, User user) {

			PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();

				sql.append("INSERT INTO users(");
				sql.append(" account");
				sql.append(", password");
				sql.append(", name");
				sql.append(", branch_id");
				sql.append(", department_id");
				sql.append(", is_stop");

				sql.append(")VALUES(");
				sql.append("?");
				sql.append(", ?");
				sql.append(", ?");
				sql.append(", ?");
				sql.append(", ?");
				sql.append(", ?)");

				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, user.getAccount());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setString(4, user.getBranch_id());
				ps.setString(5, user.getDepartment_id());
				ps.setBoolean(6, user.getIs_stop());

				ps.executeUpdate();
			} catch (SQLException e){
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

		public void update(Connection connection,User user, String password) {

			PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE users SET");
				sql.append(" account = ?");
				sql.append(", name = ?");
				sql.append(", branch_id = ?");
				sql.append(", department_id = ?");
				sql.append(", is_stop = ?");
				if(!StringUtils.isEmpty(password)) {
					sql.append(", password = ?");
				}
				sql.append(" WHERE");
				sql.append(" id = ?");

				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, user.getAccount());
				ps.setString(2, user.getName());
				ps.setString(3, user.getBranch_id());
				ps.setString(4, user.getDepartment_id());
				ps.setBoolean(5, user.getIs_stop());

				if(!StringUtils.isEmpty(password)) {
					ps.setString(6, user.getPassword());
					ps.setInt(7, user.getId());
				} else {
					ps.setInt(6, user.getId());
				}
				System.out.println(user.getId());

				int count = ps.executeUpdate();
				if (count == 0) {
					throw new NoRowsUpdatedRuntimeException();
				}
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

		public void delete(Connection connection, int id) {

			PreparedStatement ps = null;
			try {
				String sql = "DELETE FROM  users where id = ?";

				//sql.append("DELETE FROM  users where id = ?");

				ps = connection.prepareStatement(sql.toString());

				ps.setInt(1, id);

				ps.executeUpdate();
			} catch (SQLException e){
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

		public User getUser(Connection connection, int id) {

			PreparedStatement ps = null;
			try {
				String sql = "SELECT * FROM users WHERE id = ?";

				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);

				ResultSet rs = ps.executeQuery();
				List<User> userList = toUserList(rs);
				if (userList.isEmpty() == true) {
					return null;
				} else if (2 <= userList.size()) {
					throw new IllegalStateException("2 <= userList.size()");
				} else {
					return userList.get(0);
				}
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

		public List<User> getAllUser(Connection connection) {

			PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM users order by branch_id asc, department_id asc ");

				ps = connection.prepareStatement(sql.toString());
				ResultSet rs = ps.executeQuery();
				List<User> ret = toUsermanageList(rs);
				return ret;
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

		public List<User> getSelectedUser(Connection connection, String account) {

			PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM users WHERE account = ?");

				ps = connection.prepareStatement(sql.toString());
				ps.setString(1, account);

				ResultSet rs = ps.executeQuery();
				List<User> ret = toUsermanageList(rs);

				if(ret == null) return null;
				else return ret;

			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

		private List<User> toUsermanageList(ResultSet rs)
				throws SQLException {

			List<User> ret = new ArrayList<User>();
			try {
				while (rs.next()) {
					int id = rs.getInt("id");
					String account = rs.getString("account");
					String name = rs.getString("name");
					String branch_id = rs.getString("branch_id");
					String department_id = rs.getString("department_id");
					boolean is_stop = rs.getBoolean("is_stop");

					User user = new User();
					User confirmedaccount = new User();
					user.setId(id);
					user.setAccount(account);
					confirmedaccount.setAccount(account);

					user.setName(name);
					user.setBranch_id(branch_id);
					user.setDepartment_id(department_id);
					user.setIs_stop(is_stop);

					ret.add(user);

				}
				return ret;
			} finally {
				close(rs);
			}
		}
}

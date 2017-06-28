package board.dao;

import static board.utils.Closeableutil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import board.beans.Userpost;
import board.exception.SQLRuntimeException;

public class Userpostdao {

	public List<Userpost> getUserMessages(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_posts ");
			//sql.append("ORDER BY  DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Userpost> ret = toUserpostList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Userpost> toUserpostList(ResultSet rs)
			throws SQLException {

		List<Userpost> ret = new ArrayList<Userpost>();
		try {
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String branch_id = rs.getString("branch_id");
				String department_id = rs.getString("department_id");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				String insert_datetime = rs.getString("insert_datetime");

				Userpost post = new Userpost();

				List<String> searchpost = new ArrayList<>();
				searchpost.add(category);
				Set<String> set = new HashSet<>(searchpost);
				List<String> searchposts2 = new ArrayList<>(set);
				System.out.println(searchposts2);
				post.setName(name);
				post.setId(id);
				post.setUserId(userId);
				post.setBranch_id(branch_id);
				post.setDepartment_id(department_id);
				post.setTitle(title);
				post.setText(text);
				post.setCategory(category);



				post.setInsert_datetime(insert_datetime);

				ret.add(post);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Userpost> getCategory(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_posts GROUP BY category");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Userpost> ret = toUserpostList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public List<Userpost> getInsert_datetime(Connection connection, String categorybox, String startcalender, String endcalender) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_posts");
			sql.append(" where insert_datetime ");
			sql.append( "between ? and ?");

			if(!StringUtils.isEmpty(categorybox)) {
				sql.append(" and ");
				sql.append(" category=");
				sql.append("?");
				sql.append(" ORDER BY insert_datetime DESC");
			}else {
				sql.append(" ORDER BY insert_datetime DESC");
			}

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, startcalender);
			ps.setString(2, endcalender);

			if(!StringUtils.isEmpty(categorybox)) {
				ps.setString(3, categorybox);
			}
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			List<Userpost> ret = toUserpostList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
		//return null;
	}

}
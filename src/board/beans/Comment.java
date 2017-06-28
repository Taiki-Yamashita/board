package board.beans;

import java.io.Serializable;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int comment_id;
	private int userId;
	private String postid;
	private String branch_id;
	private String department_id;
	private String name;
	private String comment;
	private String insert_datetime;
	public int getId() {
		return comment_id;
	}

	public void setId(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPostId() {
		return postid;
	}

	public void setPostId(String postid) {
		this.postid = postid;
	}
	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getInsert_datetime() {
		return insert_datetime;
	}

	public void setInsert_datetime(String insert_datetime) {
		this.insert_datetime = insert_datetime;
	}
}

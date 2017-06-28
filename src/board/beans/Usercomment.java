package board.beans;

import java.io.Serializable;

public class Usercomment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String postId;
	private String branch_id;
	private String department_id;
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

	private int userId;
	private String text;
	private String insert_datetime;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId=  postId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getInsert_datetime() {
		return insert_datetime;
	}

	public void setInsert_datetime(String insert_datetime) {
		this.insert_datetime = insert_datetime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

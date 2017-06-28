package board.beans;

import java.io.Serializable;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	private int post_id;
	private int userId;
	private String branch_id;
	private String department_id;
	private String name;
	private String title;
	private String text;
	private String category;
	private String insert_datetime;
	public int getId() {
		return post_id;
	}

	public void setId(int post_id) {
		this.post_id = post_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id){
		this.department_id = department_id;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id){
		this.branch_id = branch_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInsert_datetime() {
		return insert_datetime;
	}

	public void setInsert_datetime(String insert_datetime) {
		this.insert_datetime = insert_datetime;
	}

}

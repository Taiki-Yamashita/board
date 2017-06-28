package board.beans;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String account;
	private String password;
	private String confirmedpassword;
	private String name;
	private String branch_id;
	private String department_id;
	private String branches_name;
	private String departments_name;
	public String getBranches_name() {
		return branches_name;
	}

	public void setBranches_name(String branches_name) {
		this.branches_name = branches_name;
	}

	public String getDepartments_name() {
		return departments_name;
	}

	public void setDepartments_name(String departments_name) {
		this.departments_name = departments_name;
	}

	private boolean is_stop;
	private String delete;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmedPassword() {
		return confirmedpassword;
	}

	public void setConfirmedPassword(String confirmedpassword) {
		this.confirmedpassword = confirmedpassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean getIs_stop() {
		return is_stop;
	}

	public void setIs_stop(boolean is_stop) {
		this.is_stop = is_stop;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

}

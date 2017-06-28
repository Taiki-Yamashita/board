package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import board.beans.Branch;
import board.beans.Department;
import board.beans.User;
import board.service.Branchservice;
import board.service.Departmentservice;
import board.service.Userservice;

@WebServlet(urlPatterns = { "/signup" })
public class Signupservlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Branch> branches = new Branchservice().getBranch();
		request.setAttribute("branches", branches);

		List<Department> departments = new Departmentservice().getDepartment();
		request.setAttribute("departments", departments);

		request.getRequestDispatcher("/signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		List<User> confirmedaccount =  new Userservice().select(request.getParameter("account"));

		HttpSession session = request.getSession();
		if (isValid(request, messages, confirmedaccount) == true) {

			User user = new User();
			user.setName(request.getParameter("name"));
			user.setAccount(request.getParameter("account"));
			user.setPassword(request.getParameter("password"));
			user.setBranch_id(request.getParameter("branch_id"));
			user.setDepartment_id(request.getParameter("department_id"));

			new Userservice().register(user);

			response.sendRedirect("./home");
		} else {
			session.setAttribute("errorMessages", messages);

			List<Branch> branches = new Branchservice().getBranch();
			request.setAttribute("branches", branches);

			List<Department> departments = new Departmentservice().getDepartment();
			request.setAttribute("departments", departments);

			User newUser = getNewUser(request);
			request.setAttribute("newUser", newUser);

			request.getRequestDispatcher("/signup.jsp").forward(request, response);;
		}
	}
	private User getNewUser(HttpServletRequest request)
			throws IOException, ServletException {
		User newUser = new User();;

		//newUser.setId(Integer.parseInt(request.getParameter("id")));
		newUser.setName(request.getParameter("name"));
		newUser.setAccount(request.getParameter("account"));
		newUser.setPassword(request.getParameter("password"));
		newUser.setConfirmedPassword(request.getParameter("confirmedpassword"));
		newUser.setBranch_id(request.getParameter("branch_id"));
		newUser.setDepartment_id(request.getParameter("department_id"));
		return newUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages, List<User> confirmedaccount) {
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String confirmedpassword = request.getParameter("confirmedpassword");
		String branch_id = request.getParameter("branch_id");
		String department_id = request.getParameter("department_id");
		if(Integer.parseInt(branch_id) !=  1 &&  Integer.parseInt(department_id) <= 2) {
			messages.add("支店と部署の組み合わせが一致しません");
		}
		if(Integer.parseInt(branch_id) == 1 && Integer.parseInt(department_id) > 2) {
			messages.add("支店と部署の組み合わせが一致しません");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		} else if (name.length() > 10) {
			messages.add("10文字以下で入力してください");
		}
		if (StringUtils.isEmpty(account) == true) {
			messages.add("ログインIDを入力してください");
		} else if ( !account.matches("\\w{6,20}$") ) {
			messages.add("ログインIDは半角英数字6文字以上20文字以下で入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		} else if ( !password.matches("\\w{6,255}$") ) {
			messages.add("パスワードは半角英数字6文字以上255文字以下で入力してください");
		}
		if (!password.matches(confirmedpassword)) {
			messages.add("パスワードとパスワード確認が不一致です");
		}
		if (StringUtils.isEmpty(branch_id) == true) {
			messages.add("支店を入力してください");
		}
		if (StringUtils.isEmpty(department_id) == true) {
			messages.add("部署を入力してください");
		}
		if (confirmedaccount.size() != 0) {
			messages.add("既に存在しているログインIDです");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}

package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

@WebServlet(urlPatterns = { "/useredit" })
@MultipartConfig(maxFileSize = 100000)
public class Usereditservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<User> users  = new Userservice().getManage();
		if(request.getParameter("id") == null){
			List<String> messages = new ArrayList<String>();
			messages.add("指定されたIDは存在しません");
			request.getSession().setAttribute("errorMessages", messages);
			response.sendRedirect("./usermanage");
			return ;
		}

		for(User userId : users){
			if(request.getParameter("id").equals(String.valueOf(userId.getId())) && !StringUtils.isBlank(request.getParameter("id"))) {
				User editUser1 = new Userservice().getUser(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("editUser", editUser1);

				List<Branch> branches = new Branchservice().getBranch();
				request.setAttribute("branches", branches);

				List<Department> departments = new Departmentservice().getDepartment();
				request.setAttribute("departments", departments);

				request.getRequestDispatcher("useredit.jsp").forward(request, response);
				return ;
			}
		}

		List<String> messages = new ArrayList<String>();
		messages.add("指定されたIDは存在しません");
		request.getSession().setAttribute("errorMessages", messages);
		response.sendRedirect("./usermanage");

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();
		List<User> confirmedaccount =  new Userservice().select(request.getParameter("account"));

		User editUser2 = new Userservice().getUser(Integer.parseInt(request.getParameter("id")));
		String editaccount = editUser2.getAccount();

		HttpSession session = request.getSession();
		User editUser = getEditUser(request);
		if (isValid(request, messages, confirmedaccount, editaccount)) {
			new Userservice().update(editUser, request.getParameter("password"));

			response.sendRedirect("usermanage");
			return;
		}
			session.setAttribute("errorMessages", messages);

			request.setAttribute("editUser", editUser);

			List<Branch> branches = new Branchservice().getBranch();
			request.setAttribute("branches", branches);
			List<Department> departments = new Departmentservice().getDepartment();
			request.setAttribute("departments", departments);

			request.getRequestDispatcher("useredit.jsp").forward(request, response);

	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {
		User editUser = new User();;
		//User user = new User();;
		System.out.println(editUser.getPassword());
		editUser.setId(Integer.parseInt(request.getParameter("id")));
		editUser.setName(request.getParameter("name"));
		editUser.setAccount(request.getParameter("account"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setBranch_id(request.getParameter("branch_id"));
		editUser.setDepartment_id(request.getParameter("department_id"));
		return editUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages, List<User> confirmedaccount, String editaccount) {
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		if(!StringUtils.isBlank(request.getParameter("password"))) {
			String password = request.getParameter("password");
			String confirmedpassword = request.getParameter("confirmedpassword");
			if (StringUtils.isBlank(password) == true) {
				messages.add("パスワードを入力してください");
			} else if ( !password.matches("\\w{6,255}$") ) {
				messages.add("パスワードは半角数字6文字以上255文字以下で入力してください");
			}
			if (!password.matches(confirmedpassword)) {
				messages.add("パスワードとパスワード確認が不一致です");
			}
		}
		String branch_id = request.getParameter("branch_id");
		String department_id = request.getParameter("department_id");

		if(Integer.parseInt(branch_id) !=  1 &&  Integer.parseInt(department_id) <= 2) {
			messages.add("支店と部署の組み合わせが一致しません");
		}
		if(Integer.parseInt(branch_id) == 1 && Integer.parseInt(department_id) > 2) {
			messages.add("支店と部署の組み合わせが一致しません");
		}
		if (StringUtils.isBlank(name) == true) {
			messages.add("名前を入力してください");
		} else if (name.length() > 10) {
			messages.add("10文字以下で入力してください");
		}
		if (StringUtils.isBlank(account) == true) {
			messages.add("ログインIDを入力してください");
		} else if ( !account.matches("\\w{6,20}$") ) {
			messages.add("ログインIDは半角数字6文字以上20文字以下で入力してください");
		}
		if ( !account.matches(editaccount) && confirmedaccount.size() != 0 ) {
			messages.add("既に存在しているアカウントです");
		}

		if (StringUtils.isBlank(branch_id) == true) {
			messages.add("支店を入力してください");
		}
		if (StringUtils.isBlank(department_id) == true) {
			messages.add("部署を入力してください");
		}
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}

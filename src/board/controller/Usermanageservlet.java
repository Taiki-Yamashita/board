package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.beans.Branch;
import board.beans.Department;
import board.beans.User;
import board.service.Branchservice;
import board.service.Departmentservice;
import board.service.Userservice;

@WebServlet(urlPatterns = {"/usermanage"})
public class Usermanageservlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		User user = (User) request.getSession().getAttribute("loginUser");

		request.setAttribute("user", user);
		//System.out.println(user.getDepartment_id());
		boolean isShowManageForm = true;
		if (user != null) {
			isShowManageForm = true;
		} else {
			isShowManageForm = false;
		}

		List<User> usermanage = new Userservice().getManage();
		request.setAttribute("usermanage", usermanage);
		List<Branch> branches = new Branchservice().getBranch();
		request.setAttribute("branches", branches);
		List<Department> departments = new Departmentservice().getDepartment();
		request.setAttribute("departments", departments);



		request.setAttribute("isShowManageForm", isShowManageForm);
		request.getRequestDispatcher("usermanage.jsp").forward(request, response);
	}

}

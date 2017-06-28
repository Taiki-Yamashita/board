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

import board.beans.User;
import board.service.Loginservice;

@WebServlet(urlPatterns = {"/login"})
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet  (HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost (HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String account = request.getParameter("account");
		String password = request.getParameter("password");
		boolean is_stop = false;

		Loginservice loginService = new Loginservice();
		User user = loginService.login(account,password,is_stop);

		HttpSession session = request.getSession();
		if (user != null) {
			System.out.println("ログイン成功");
			session.setAttribute("loginUser", user);
			response.sendRedirect("./home");
		} else if( Boolean.parseBoolean(request.getParameter("is_stop"))){
			List<String> messages = new ArrayList<String>();
			request.setAttribute("account", request.getParameter("account"));
			request.setAttribute("password", request.getParameter("password"));
			messages.add("ログインに失敗しました");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./login");
		} else {
			List<String> messages = new ArrayList<String>();
			request.setAttribute("account", request.getParameter("account"));
			request.setAttribute("password", request.getParameter("password"));
			messages.add("ログインに失敗しました");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./login");
		}
	}

//	private boolean isValid(HttpServletRequest request, List<String> messages) {
//		String account = request.getParameter("account");
//		String password = request.getParameter("password");
//
//		if (StringUtils.isEmpty(account) == true) {
//			messages.add("アカウント名を入力してください");
//		}
//		if(StringUtils.isEmpty(password) == true) {
//			messages.add("パスワードを入力してください");
//		}
//		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
//		if (messages.size() == 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
}

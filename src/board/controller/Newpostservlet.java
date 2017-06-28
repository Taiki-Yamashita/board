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

import board.beans.Post;
import board.beans.User;
import board.beans.Userpost;
import board.service.Postservice;
import board.service.Userservice;

@WebServlet(urlPatterns = {"/newpost"})
public class Newpostservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet (HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Userpost> categories = new Userservice().getCategory();
		request.setAttribute("categories", categories);



		request.getRequestDispatcher("newpost.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();
		User user = (User) session.getAttribute("loginUser");
		Post message = new Post();
		if (isValid(request, messages) == true) {
			message.setUserId(user.getId());
			message.setBranch_id(user.getBranch_id());
			message.setDepartment_id(user.getDepartment_id());
			message.setTitle(request.getParameter("title"));
			message.setText(request.getParameter("text"));
			message.setCategory(request.getParameter("category"));

			if(message.getCategory() == ""){
				message.setCategory(request.getParameter("category2"));
			}

			message.setInsert_datetime(request.getParameter("insert_datetime"));

			new Postservice().register(message);

			response.sendRedirect("./home");
		} else{
			session.setAttribute("errorMessages", messages);


			List<Userpost> categories = new Userservice().getCategory();
			request.setAttribute("categories", categories);
			request.setAttribute("category2", request.getParameter("category2"));
			message.setTitle(request.getParameter("title"));
			message.setText(request.getParameter("text"));

			//message.setCategory(request.getParameter("category2"));

			request.setAttribute("message", message);

			request.getRequestDispatcher("newpost.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");
		String category2 = request.getParameter("category2");

		if (StringUtils.isEmpty(title) == true) {
			messages.add("件名を入力してください");
		}
		if (50 < title.length()) {
			messages.add("50文字以下で入力してください");
		}
		if (StringUtils.isEmpty(text) == true) {
			messages.add("投稿を入力してください");
		}
		if (1000 < text.length()) {
			messages.add("1000文字以下で入力してください");
		}
		System.out.println(category2);
		if (StringUtils.isBlank(category) && StringUtils.isBlank(category2)) {
			messages.add("カテゴリーを入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}

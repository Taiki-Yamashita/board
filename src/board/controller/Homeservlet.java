package board.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import board.beans.Comment;
import board.beans.User;
import board.beans.Usercomment;
import board.beans.Userpost;
import board.service.Commentservice;
import board.service.Userservice;

@WebServlet(urlPatterns = {"/home"})
public class Homeservlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User user = (User) request.getSession().getAttribute("loginUser");
		user.getBranch_id();
		request.setAttribute("user", user);

		//List<Userpost> posts = new Postservice().getPost();
		List<Usercomment> comments = new Commentservice().getComment();

		List<Userpost> categories = new Userservice().getCategory();

		//request.setAttribute("posts", posts);
		request.setAttribute("comments", comments);

		request.setAttribute("categories", categories);

		String categorybox = request.getParameter("category");

		String startcalender = null;

		String endcalender = null;


		if(!StringUtils.isEmpty(request.getParameter("calender"))) {
			 startcalender = request.getParameter("calender");
		} else {
			 startcalender = "2017-4-01";
		}

		if(!StringUtils.isEmpty(request.getParameter("calender2"))) {
			 endcalender = request.getParameter("calender2");
		} else {
			 endcalender = currentDate();
		}

		List<Userpost> posts = new Userservice().getInsert_datetime(categorybox, startcalender, endcalender);

		request.setAttribute("posts", posts);

		request.setAttribute("categorybox", categorybox);
		request.setAttribute("startcalender", startcalender);
		request.setAttribute("endcalender", endcalender);


		request.getRequestDispatcher("/home.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

//		String startcalender = "2017-4-01";
//		String endcalender = currentDate();
//
//		if(request.getParameter("calender") != null) startcalender = request.getParameter("calender");
//		if(request.getParameter("calender") != null) endcalender = request.getParameter("calender");
//
//
//		List<Userpost> insert_datetimes = new Userservice().getInsert_datetime(startcalender, endcalender);
//
//		request.setAttribute("insert_datetimes", insert_datetimes);
//

		List<String> messages = new ArrayList<String>();
		Comment comment = new Comment();
		if (isValid(request, messages) == true) {

			User user = (User) session.getAttribute("loginUser");
			request.setAttribute("user", user);

			System.out.println(user);
			System.out.println(comment);
			comment.setUserId(user.getId());
			comment.setPostId(request.getParameter("post_id"));
			comment.setBranch_id(user.getBranch_id());
			comment.setDepartment_id(user.getDepartment_id());
			comment.setComment(request.getParameter("comment"));
			comment.setInsert_datetime(request.getParameter("insert_datetime"));

			new Commentservice().register(comment);

			response.sendRedirect("./home");
		} else{
//			List<Userpost> posts = new Postservice().getPost();
//			List<Usercomment> comments = new Commentservice().getComment();
//			List<Userpost> categories = new Userservice().getCategory();
//			request.setAttribute("categories", categories);
//			request.setAttribute("posts", posts);
//			request.setAttribute("comments", comments);

			session.setAttribute("errorMessages", messages);
			session.setAttribute("keepPostId", request.getParameter("post_id"));
			session.setAttribute("keepComment", request.getParameter("comment"));
			response.sendRedirect("./home");

		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String comment = request.getParameter("comment");


		if (StringUtils.isBlank(comment) == true) {
			messages.add("コメントを入力してください");
		}
		if (500 < comment.length()) {
			messages.add("500文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public String currentDate(){

		String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		return currentDate;
	}

}

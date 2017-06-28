package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.Commentservice;
import board.service.Postservice;
import board.service.Userservice;

@WebServlet(urlPatterns = {"/delete"})
public class Deleteservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException{

		if(request.getParameter("id") != null){
			new Userservice().delete(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect("./usermanage");
		} else if(request.getParameter("post_id") != null) {
			new Postservice().delete(Integer.parseInt(request.getParameter("post_id")));
			response.sendRedirect("./home");
		} else {
			new Commentservice().delete(Integer.parseInt(request.getParameter("comment_id")));
			response.sendRedirect("./home");
		}

		//response.sendRedirect("./");
	}


}

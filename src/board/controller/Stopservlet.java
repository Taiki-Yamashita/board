package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.beans.User;
import board.service.Reverseservice;
import board.service.Stopservice;

@WebServlet(urlPatterns = {"/stop"})
public class Stopservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {

			User stopingUser = new User();
			System.out.println(Boolean.parseBoolean(request.getParameter("0")));
			if(Boolean.parseBoolean(request.getParameter("1")) == true) {
				stopingUser.setId(Integer.parseInt(request.getParameter("id")));
				new Stopservice().update(stopingUser);
			} else {
				stopingUser.setId(Integer.parseInt(request.getParameter("id")));
				System.out.println(Integer.parseInt(request.getParameter("id")));
				new Reverseservice().update(stopingUser);
			}
			response.sendRedirect("./usermanage");
	}


}

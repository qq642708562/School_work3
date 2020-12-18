package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.User;

@WebServlet(urlPatterns = "/LogoutController.do")
public class LogoutController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = new User();
		HttpSession session = request.getSession();
		Cookie cookie1 = new Cookie("Cookie1", null);
		Cookie cookie2 = new Cookie("Cookie2", null);
		Cookie cookie3 = new Cookie("Cookie3", null);
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		cookie3.setMaxAge(0);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		session.setAttribute("userName", "");
		//System.out.println("out"+userName+"---"+password);
		response.sendRedirect("/excise1/login.html");
	}
}

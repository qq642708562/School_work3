package controller.Logincontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns="/LogoutController.do")
public class LogoutController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie cookie3 = new Cookie("name", null);
		Cookie cookie4 = new Cookie("pwd", null);
		cookie3.setMaxAge(0);
		cookie4.setMaxAge(0);
		cookie3.setPath("/");
		cookie4.setPath("/");		
		response.addCookie(cookie3);
		response.addCookie(cookie4);
		response.setContentType("text/html;charset=UTF-8");
		request.getSession().removeAttribute("currentuser");
		response.sendRedirect("/excise1/login.html");
	}

}

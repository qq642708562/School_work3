package controller.Logincontroller;

import dao.UserDao;
import vo.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/LoginController.do")
public class LoginController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		String checkbox = request.getParameter("checkbox");
		UserDao userDao = new UserDao();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		String saveVcode = (String) session.getAttribute("verifyCode");
		String forwardPath = "";
		if (!(vcode.equalsIgnoreCase(saveVcode))) {
			request.setAttribute("info", "��Ǹ,��֤�벻��ȷ!");
			forwardPath = "/error.jsp";
		} else {
			if (userDao.get(userName) == null) {
				request.setAttribute("info", "��Ǹ,��������û���������!");
				forwardPath = "/error.jsp";
			} else {
				User user = userDao.get(userName);
				if (!user.getPassword().equals(password)) {
					request.setAttribute("info", "��Ǹ,����������벻��ȷ!");
					forwardPath = "/error.jsp";
				} else {
					if (checkbox != null) {
						Cookie cookie1 = new Cookie("name", userName);
						Cookie cookie2 = new Cookie("pwd", password);
						cookie1.setMaxAge(60 * 60 * 24 * 7);
						cookie2.setMaxAge(60 * 60 * 24 * 7);
						response.addCookie(cookie1);
						response.addCookie(cookie2);
					}
					session.setAttribute("currentUser", userDao.get(userName)
							.getUserName());
					session.setAttribute("chrName", userDao.get(userName)
							.getChrName());
					session.setAttribute("user", userDao.get(userName));
					forwardPath = "/main.jsp";
				}
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}
}

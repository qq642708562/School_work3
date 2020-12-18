package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.User;

import com.google.gson.Gson;

import dao.UserDao;
@WebServlet(urlPatterns="/AjaxLoginCheck.do")
public class AjaxLoginCheck extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		String checkbox = request.getParameter("checkbox");
		HttpSession session = request.getSession();
		String saveVcode = (String) session.getAttribute("verifyCode");
		Map<String,Object>map = new HashMap<String,Object>();
		UserDao dao = new UserDao();
		int num = dao.isRoot(userName);
		String role = null;
		if(vcode==null){
			map.put("code",4);
			map.put("info", "��֤�벻��Ϊ��!");
		}
		if(!vcode.equalsIgnoreCase(saveVcode)){
			map.put("code",1);
			map.put("info", "��֤�벻��ȷ!");
		}else{
			UserDao userDao = new UserDao();
			User user = userDao.get(userName);
			if(user==null){
				map.put("code", 2);
				map.put("info", "�û���������");
			}else{
				if(!user.getPassword().equals(password)){
					map.put("code", 3);
					map.put("info", "���벻��ȷ");
				}else{
					if(num!=10)
					{
						{
							if(num==0){
								role=dao.getrole(num);
							}else{
								role=dao.getrole(num);
							}
						}
					}else{
						map.put("code", 2);
						map.put("info", "�û���������");
					}
					session.setAttribute("userName", userDao.get(userName)
							.getUserName());
					session.setAttribute("chrName", userDao.get(userName)
							.getChrName());
					session.setAttribute("user", userDao.get(userName));
					session.setAttribute("role", role);
					if(checkbox!=null){
						Cookie cookie1 = new Cookie("Cookie1", userName);
						Cookie cookie2 = new Cookie("Cookie2", password);
						Cookie cookie3 = new Cookie("Cookie3", role);
						cookie1.setMaxAge(60 * 60 * 24 * 7);
						cookie2.setMaxAge(60 * 60 * 24 * 7);
						cookie3.setMaxAge(60 * 60 * 24 * 7);
						response.addCookie(cookie1);
						response.addCookie(cookie2);
						response.addCookie(cookie3);
					}
					map.put("code", 0);
					map.put("info", "��¼�ɹ�!");
				}
			}
		}
		String jsonStr = new Gson().toJson(map);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	} 


}

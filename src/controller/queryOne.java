package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import vo.User;
import dao.UserDao;

@WebServlet(urlPatterns = "/queryOne.do")
public class queryOne extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		Map<String,Object>map = new HashMap<String,Object>();
		UserDao dao = new UserDao();
		User user = dao.get(userName);
		//System.out.println(user);
		if(user!=null){
			map.put("user", user);
		}else{
			map.put("user", "false");
		}
		String jsonStr = new Gson().toJson(map);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}
}

package controller.Logincontroller;

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

import dao.UserDao;

@WebServlet(urlPatterns="/deleteUser.do")
public class deleteUser extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
//		for(String a:result){
//			System.out.println(a);
//		}
		UserDao dao = new UserDao();
		boolean flag = dao.delete(ids);
		Map<String,Object>map = new HashMap<String,Object>();
		
		if(flag){
			map.put("code", 0);
			map.put("info","É¾³ý³É¹¦!");
		}else{
			map.put("code",1);
			map.put("info", "É¾³ýÊ§°Ü!");
		}
		String jsonStr = new Gson().toJson(map);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}
}

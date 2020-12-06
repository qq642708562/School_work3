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

import vo.User;

import com.google.gson.Gson;

import dao.UserDao;

@WebServlet(urlPatterns = "/registerCheck.do")
public class registerCheck extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String chrName = request.getParameter("chrName");
		String mail = request.getParameter("mail");
		int provinceCode = Integer.parseInt(request.getParameter("provinceCode"));
		int cityCode = Integer.parseInt(request.getParameter("cityCode"));
		String provinceName = request.getParameter("provinceName");
		String cityName = request.getParameter("cityName");
		User user = new User(userName,password,chrName,mail,provinceCode,cityCode,provinceName,cityName);
		UserDao dao = new UserDao();
		boolean result = false;
		Map<String,Object>map = new HashMap<String,Object>();
		
		if("true".equals(flag)){
			result = dao.insert(user);
			map.put("flag", result+"");
		}else{
			map.put("flag", "false");
		}
		
		String jsonStr = new Gson().toJson(map);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		
	}

}

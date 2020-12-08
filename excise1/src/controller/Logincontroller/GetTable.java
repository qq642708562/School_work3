package controller.Logincontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import vo.Download;
import dao.DownloadDao;
import dao.UserDao;

@WebServlet(urlPatterns="/GetTable.do")
public class GetTable extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String,Object>map = new HashMap<String,Object>();
		UserDao dao = new UserDao();
		int num = 0;
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		num = dao.isRoot(userName);
		System.out.println("userName:"+userName+"  num:"+num);
		if(num!=0){
			request.setAttribute("info", "抱歉,当前用户没有访问该资源的权限");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/error.jsp");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/table.jsp");
			rd.forward(request, response);
		}
		

		
	}

}

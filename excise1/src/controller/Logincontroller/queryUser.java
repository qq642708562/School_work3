package controller.Logincontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Page;
import vo.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.UserDao;


@WebServlet(urlPatterns="/queryUser.do")
public class queryUser extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String queryParams = request.getParameter("queryParams");
		String pageParams = request.getParameter("pageParams");
		System.out.println("查询参数:"+pageParams);
		System.out.println("分页参数:"+queryParams);
		User user = new User();
		UserDao dao = new UserDao();
		//将json字符串参数值转换为java对象
		Gson gson = new GsonBuilder().serializeNulls().create();
		HashMap<String,Object> mapPage = gson.fromJson(pageParams,HashMap.class);
		Page page = Page.getPageParams(mapPage);
		//System.out.println("page:"+page);
		
		if(queryParams!=null){
			user = gson.fromJson(queryParams,User.class);
		}
		ArrayList<User>rows = dao.query(user, page);
		int total = dao.count(user,page);
		
		HashMap<String,Object>mapReturn = new HashMap<String,Object>();
		mapReturn.put("rows", rows);
//		System.out.println("rows:"+rows);
		mapReturn.put("total", total);
		System.out.println("total:"+total);
		String jsonStr = gson.toJson(mapReturn);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//System.out.println(jsonStr);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}

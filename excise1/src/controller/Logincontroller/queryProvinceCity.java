package controller.Logincontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProvinceCityDao;

@WebServlet(urlPatterns = "/queryProvinceCity.do")
public class queryProvinceCity extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String provinceCode = request.getParameter("provinceCode");
		String jsonStr = "";
		ProvinceCityDao dao = new ProvinceCityDao();
		if(provinceCode==null){
			Map<String,Object> map = dao.queryProvince();
//			 for (Map.Entry<String, Object> entry : map.entrySet()) {
//		            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
//		        }
			jsonStr = new Gson().toJson(map);
		}else{
			int num = Integer.parseInt(provinceCode);
			Map<String,String> map = dao.queryCity(num);
			jsonStr = new Gson().toJson(map);
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//System.out.println(jsonStr);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}

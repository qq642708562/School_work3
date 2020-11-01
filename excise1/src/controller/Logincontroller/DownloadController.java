package controller.Logincontroller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DownloadDao;
import vo.Download;

public class DownloadController extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		String id = request.getParameter("id");
		DownloadDao dao = new DownloadDao();
		Download download = dao.get(Integer.parseInt(id));
		String path = request.getServletContext().getRealPath("/WEB_INF/"+download.getPath());
		String fileName = path.substring(path.lastIndexOf("\\")+1);
		response.setHeader("content-disposition", "attachment;filename="
				+ URLEncoder.encode(fileName,"UTF-8"));
		
		InputStream in = new FileInputStream(path);
		int len = 0;
		byte[] buffer = new byte[1024];
		ServletOutputStream out = response.getOutputStream();
		while((len=in.read(buffer))!=-1){
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
	}
}

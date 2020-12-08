package controller.Logincontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DownloadDao;
import vo.Download;

@WebServlet(urlPatterns="/GetDownloadList.do")
public class GetDownloadListController extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		DownloadDao dao = new DownloadDao();
		ArrayList<Download> list = dao.query();
//		System.out.println(list);
		request.setAttribute("downloadlist", list);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/download.jsp");
		rd.forward(request, response);
	}
}

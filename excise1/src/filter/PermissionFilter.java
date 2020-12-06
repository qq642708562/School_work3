package filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.User;
import dao.UserDao;

public class PermissionFilter implements Filter {
	private String notCheckUri;

	public void destroy() {

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		notCheckUri = config.getInitParameter("notCheckUri");

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String path = request.getServletPath();
//		 System.out.println("ÇëÇóµØÖ·url-pattern:"+path);
		UserDao userDao = new UserDao();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		ArrayList<User> list = userDao.getAll();
		
//		if(cookies!=null){
//			for(Cookie cookie:cookies){
//				System.out.println(cookie.getName()+"---"+cookie.getValue());
//			}
//		}

		String cname=null;
		String cpwd=null;
		boolean result=false;
		if (cookies != null) {
			for(Cookie cookie:cookies){
				if("Cookie1".equals(cookie.getName())){
					cname=cookie.getValue();
				}
				if("Cookie2".equals(cookie.getName())){
					cpwd=cookie.getValue();
				}
			}
		}
		if(cname != null && cpwd != null){
			for(User user:list){
				String uname=user.getUserName();
				String upwd=user.getPassword();
				if(cname.equals(uname)&&cpwd.equals(upwd)){
					result=true;
				}
			}
		}
		

			if (result) {
				session.setAttribute("currentUser", userDao.get(cname)
						.getUserName());
				session.setAttribute("chrName", userDao.get(cname).getChrName());
				session.setAttribute("user", userDao.get(cname));
				chain.doFilter(req, resp);
			} else if (notCheckUri.indexOf(path) == -1) {
			// HttpSession session = request.getSession();
			if (session.getAttribute("currentUser") == null) {
				request.setAttribute("info", "±§Ç¸£¬ÄúÉÐÎ´µÇÂ¼");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request,
						resp);
			} else {
				chain.doFilter(req, resp);
			}
		} else {
			chain.doFilter(req, resp);
		}
	}

}



//
//boolean nameresult = false;
//boolean pwdresult = false;
//String rname = null;
//String rpwd = null ;


//if (cookies != null) {
//	for (Cookie a : cookies) {
//
//		for (User u : list) {
//			 String name = u.getUserName();
//			 String password = u.getPassword();
//			 if((name+"name").equals(a.getName())){
//				 if(a.getValue().equals(name)){
//					 nameresult = true;
//					 rname=name;
//				 }
//			 }
//			 if((name+"pwd").equals(a.getName())){
//				 if(a.getValue().equals(password)){
//					 pwdresult = true;
//					 rpwd = password;
//				 }
//			 }
//		}
//
//	}
//}


//if (nameresult != false && pwdresult !=false) {
//String name = null;
//String password = null;
////for (Cookie c : cookies) {
////	if (c.getName().equals("name")) {
////		name = c.getValue();
////	}
////}
//for (Cookie c : cookies) {
//	if (c.getName().equals(rname+"name")) {
//		name = c.getValue();
//	}
//	if (c.getName().equals(rname+"pwd")) {
//		password = c.getValue();
//	}
//}
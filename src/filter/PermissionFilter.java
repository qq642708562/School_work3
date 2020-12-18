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

		String cname=null;
		String cpwd=null;
		String crole=null;
		boolean result=false;
		boolean iscookienull = false;
		boolean iscookienull1 = false;
		boolean iscookienull2 = false;
		boolean iscookienull3 = false;
			for(Cookie cookie:cookies){
				if("Cookie1".equals(cookie.getName())){
					if(cookie.getValue()!=null){
						iscookienull1=true;
					}
				}
				if("Cookie2".equals(cookie.getName())){
					if(cookie.getValue()!=null){
						iscookienull2=true;
					}
				}
				if("Cookie3".equals(cookie.getName())){
					if(cookie.getValue()!=null){
						iscookienull3=true;
					}
				}
			}
			if(iscookienull1&&iscookienull2&&iscookienull3){
				iscookienull=true;
			}
	
		if (iscookienull) {
			for(Cookie cookie:cookies){
				if("Cookie1".equals(cookie.getName())){
					cname=cookie.getValue();
				}
				if("Cookie2".equals(cookie.getName())){
					cpwd=cookie.getValue();
				}
				if("Cookie3".equals(cookie.getName())){
					crole=cookie.getValue();
				}
			}
		}else{
			session.setAttribute("cName", null);
		}
		if(cname != null && cpwd != null && crole != null){
			for(User user:list){
				String uname=user.getUserName();
				String upwd=user.getPassword();
				if(cname.equals(uname)&&cpwd.equals(upwd)){
					result=true;
				}
			}
		}
		
			String userName = (String) session.getAttribute("userName");
			UserDao dao = new UserDao();
			int num = dao.isRoot(userName);
			String role = null;
			if(num!=10)
			{
				{
					if(num==0){
						role=dao.getrole(num);
					}else{
						role=dao.getrole(num);
					}
				}
			}
			if (result) {
				if(userName!=null && !"".equals(userName)){
					session.setAttribute("chrName", userDao.get(userName).getChrName());
					session.setAttribute("role", role);
				}else{
					session.setAttribute("chrName", userDao.get(cname).getChrName());
					session.setAttribute("role", crole);
				}
				session.setAttribute("cName", userDao.get(cname).getUserName());
				session.setAttribute("user", userDao.get(cname));
				chain.doFilter(req, resp);
			} else if (notCheckUri.indexOf(path) == -1) {
			if (!iscookienull&&userName==null) {
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

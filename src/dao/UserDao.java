package dao;

import vo.Page;
import vo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.mysql.jdbc.Statement;

public class UserDao extends HttpServlet {
	private static final String URL = "jdbc:mysql://localhost:3306/excise";
	private static final String userName1 = "root";
	private static final String pwd = "123";

	public String getrole(int num){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String roleName = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, userName1, pwd);
			String sql = "select roleName from t_role where roleId = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, num);
			rs = pst.executeQuery();
			if (rs.next()) {
				roleName = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return roleName;
	}
	public int isRoot(String userName){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int num = 10;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, userName1, pwd);
			String sql = "select roleId from t_user_role where username = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, userName);
			rs = pst.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return num;
	}
	
	
	public User get(String userName) {
		User user = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, userName1, pwd);
			String sql = "select * from t_user where username = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, userName);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString("userName"),
						rs.getString("password"), rs.getString("chrName"),
						rs.getString("mail"), rs.getInt("provinceCode"),
						rs.getInt("cityCode"), rs.getString("provinceName"),
						rs.getString("cityName"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public Boolean insert(User user) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, userName1, pwd);
			String sql = "insert into t_user(userName,password,chrName,mail,"
					+ "provinceCode,cityCode,provinceName,cityName)"
					+ "values('";
			sql = sql+user.getUserName()+"','";
			sql = sql+user.getPassword()+"','";
			sql = sql+user.getChrName()+"','";
			sql = sql+user.getMail()+"',";
			sql = sql+user.getProvinceCode()+",";
			sql = sql+user.getCityCode()+",'";
			sql = sql+user.getProvinceName()+"','";
			sql = sql+user.getCityName()+"')";
			System.out.println(sql);
			pst = con.prepareStatement(sql);			
			int result = pst.executeUpdate();
			if (result > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean delete(String result) {
		Connection con = null;
		PreparedStatement pst = null;
		String sql = null;
		int num = 0;
		try {
			System.out.println("Result:"+result);
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, userName1, pwd);
			sql = "delete from t_user where userName in(";
			sql = sql+result+")";
			pst = con.prepareStatement(sql);
			//System.out.println(sql);
			num = pst.executeUpdate();
			System.out.println(num);
			if (num > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public boolean update(User user) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, userName1, pwd);
			String sql = "update t_user set";
			
			sql = sql+" password='"+user.getPassword()+"',chrName='"+user.getChrName()+"',mail='"+user.getMail()
			+"',provinceCode="+user.getProvinceCode()+",cityCode="+user.getCityCode()+",provinceName='"+
					user.getProvinceName()+"',cityName='"+user.getCityName()+"' where userName in('";		
			sql = sql+user.getUserName()+"')";
			pst = con.prepareStatement(sql);
			System.out.println(sql);
			int result = pst.executeUpdate();
			if (result > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int count(User user, Page page) {
		int num = 0;
		ArrayList<User> list = new ArrayList<User>(); // 存放查询结果的集合
		StringBuffer condition = new StringBuffer();// 查询条件
		Connection con = null;
		Statement pst = null;
		ResultSet rs = null;
		if (user.getUserName() != null && !"".equals(user.getUserName())) {
			condition.append(" and userName like '%")
					.append(user.getUserName()).append("%'");
		}
		if (user.getChrName() != null && !"".equals(user.getChrName())) {
			condition.append(" and chrName like '%").append(user.getChrName())
					.append("%'");
		}
		if (user.getMail() != null && !"".equals(user.getMail())) {
			condition.append(" and mail like '%").append(user.getMail())
					.append("%'");
		}
		if (user.getProvinceName() != null
				&& !"".equals(user.getProvinceName())) {
			condition.append(" and provinceName like '%")
					.append(user.getProvinceName()).append("%'");
		}
		if (user.getCityName() != null && !"".equals(user.getCityName())) {
			condition.append(" and cityName like '%")
					.append(user.getCityName()).append("%'");
		}

		int begin = page.getPageSize() * (page.getPageNumber() - 1);
		String sql = "select COUNT(userName)";
		sql = sql + " from t_user A left join t_province B ";
		sql = sql
				+ " on A.provinceCode = B.province_id left join t_city C on A.cityCode = C.city_id ";
		sql = sql + " where  1=1 ";
		sql = sql + condition + " order by " + page.getSort() + " "
				+ page.getSortOrder();

		System.out.println("count sql:" + sql);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, userName1, pwd);
			pst = (Statement) con.createStatement();
			rs = pst.executeQuery(sql);
			if (rs.next()) {
				num = rs.getInt("COUNT(userName)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}// 6.关闭连接
		}
		return num;
	}

	public ArrayList<User> getAll() {
		ArrayList<User> list = new ArrayList<User>();
		User user = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, userName1, pwd);
			String sql = "select * from t_user";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString("userName"),
						rs.getString("password"), rs.getString("chrName"),
						rs.getString("mail"), rs.getInt("provinceCode"),
						rs.getInt("cityCode"), rs.getString("provinceName"),
						rs.getString("cityName"));
				list.add(user);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<User> query(User user, Page page) {
		ArrayList<User> list = new ArrayList<User>(); // 存放查询结果的集合
		StringBuffer condition = new StringBuffer();// 查询条件
		Connection con = null;
		Statement pst = null;
		ResultSet rs = null;
		if (user.getUserName() != null && !"".equals(user.getUserName())) { // 判断是否有该查询条件
			condition.append(" and userName like '%")
					.append(user.getUserName()).append("%'");
		}
		if (user.getChrName() != null && !"".equals(user.getChrName())) { //
			condition.append(" and chrName like '%").append(user.getChrName())
					.append("%'");
		}
		if (user.getMail() != null && !"".equals(user.getMail())) { //
			condition.append(" and mail like '%").append(user.getMail())
					.append("%'");
		}
		if (user.getProvinceName() != null
				&& !"".equals(user.getProvinceName())) { //
			condition.append(" and provinceName like '%")
					.append(user.getProvinceName()).append("%'");
		}
		if (user.getCityName() != null && !"".equals(user.getCityName())) { //
			condition.append(" and cityName like '%")
					.append(user.getCityName()).append("%'");
		}

		int begin = page.getPageSize() * (page.getPageNumber() - 1);
		String sql = "select userName,password,chrName,mail,A.provinceCode provinceCode,";
		sql = sql
				+ " B.province_name province_name,A.cityCode cityCode,C.city_name city_name ";
		sql = sql + " from t_user A left join t_province B ";
		sql = sql
				+ " on A.provinceCode = B.province_id left join t_city C on A.cityCode = C.city_id  ";
		sql = sql + " where  1=1 ";
		sql = sql + condition + " order by " + page.getSort() + " "
				+ page.getSortOrder() + " limit " + begin + ","
				+ page.getPageSize();

		System.out.println(sql);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, userName1, pwd);
			pst = (Statement) con.createStatement();
			rs = pst.executeQuery(sql);
			while (rs.next()) {
				user = new User(rs.getString(1), rs.getString(3),
						rs.getString(4), rs.getString(6), rs.getString(8));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}// 6.关闭连接
		}

		return list;
	}

}

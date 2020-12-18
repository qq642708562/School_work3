package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ProvinceCityDao {
	private static final String URL = "jdbc:mysql://localhost:3306/excise";
	private static final String userName = "root";
	private static final String pwd = "123";
	public static Connection connection = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null; 
	Map<String,Object>map = new HashMap<String,Object>();
	Map<String,Object>map2 = new HashMap<String,Object>();
	
	public Map queryProvince(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection)DriverManager.getConnection(URL,userName,pwd);
			String sql = "select * from t_province";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				String sid = Integer.toString(id);
				String name = rs.getString(2);
				map.put(sid, name);
			}
			return map;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map queryCity(int num){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection)DriverManager.getConnection(URL,userName,pwd);
			String sql = "select * from t_city where province_id=?";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				String sid = Integer.toString(id);
				String name = rs.getString(2);
				map2.put(sid, name);
			}
			return map2;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

package vo;

import javax.servlet.http.HttpServlet;

public class User extends HttpServlet {
	private String userName;
	private String password;
	private String chrName;
	private String mail;
	private int provinceCode;
	private int cityCode;
	private String provinceName;
	private String cityName;
	
	
	
	public User() {
		super();
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public User(String userName, String password, String chrName) {
		super();
		this.userName = userName;
		this.password = password;
		this.chrName = chrName;
	}
	

	public User(String userName, String chrName, String mail,
			String provinceName) {
		super();
		this.userName = userName;
		this.chrName = chrName;
		this.mail = mail;
		this.provinceName = provinceName;
	}

	public User(String userName, String chrName, String mail,
			String provinceName, String cityName) {
		super();
		this.userName = userName;
		this.chrName = chrName;
		this.mail = mail;
		this.provinceName = provinceName;
		this.cityName = cityName;
	}

	public User(String userName, String password, String chrName, String mail,
			int provinceCode, int cityCode, String provinceName, String cityName) {
		super();
		this.userName = userName;
		this.password = password;
		this.chrName = chrName;
		this.mail = mail;
		this.provinceCode = provinceCode;
		this.cityCode = cityCode;
		this.provinceName = provinceName;
		this.cityName = cityName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getChrName() {
		return chrName;
	}

	public void setChrName(String chrName) {
		this.chrName = chrName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(int provinceCode) {
		this.provinceCode = provinceCode;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String toString(){
		return "userName="+this.userName+"  password="+this.password+"  chrName="+this.chrName
				+"  mail="+this.mail+"  provinceCode="+provinceCode+"  cityCode="+cityCode+" provinceName="+
				provinceName+"  cityName="+cityName;
	}
}

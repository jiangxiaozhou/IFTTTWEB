package model;

public class Account {
	private int userid;
	private String username;
	private String password;
	String emailaddress;
	private int money = 1000;
	private int level = 0;

	public Account(String username, String password, String email) {
		this.setUsername(username);
		this.setPassword(password);
		this.setEmailaddress(email);
	}

	public Account(int userid, String username, String password, String emailaddress, int money, int level) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.emailaddress = emailaddress;
		this.money = money;
		this.level = level;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

}

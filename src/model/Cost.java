package model;

import java.text.DateFormat;
import java.util.Date;

public class Cost {
	int costid;
	int money;
	String username;
	String time;

	public Cost(int costid, int money, String username) {
		super();
		this.costid = costid;
		this.money = money;
		this.username = username;
		Date now = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		time = dateFormat.format(now);
	}

	public Cost(int costid, int money, String username, String time) {
		super();
		this.costid = costid;
		this.money = money;
		this.username = username;
		this.time = time;
	}

	public int getCostid() {
		return costid;
	}

	public void setCostid(int costid) {
		this.costid = costid;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}

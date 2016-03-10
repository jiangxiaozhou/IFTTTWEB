package model;

import java.text.DateFormat;
import java.util.Date;

public class Message {
	int mid;
	String sourceid;
	String receiveid;
	String content;
	boolean isAdminMessage;
	String time;
	int attribute;

	public Message(int mid, String sourceid, String receiveid, String content, boolean isAdminMessage, int attribute) {
		super();
		this.mid = mid;
		this.sourceid = sourceid;
		this.receiveid = receiveid;
		this.content = content;
		this.isAdminMessage = isAdminMessage;
		Date now = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
		time = dateFormat.format(now);
		this.attribute = attribute;
	}

	public Message(int mid, String sourceid, String receiveid, String content, boolean isAdminMessage, String time,
			int attribute) {
		super();
		this.mid = mid;
		this.sourceid = sourceid;
		this.receiveid = receiveid;
		this.content = content;
		this.isAdminMessage = isAdminMessage;
		this.time = time;
		this.attribute = attribute;
	}

	private static final int NEW = 1;
	private static final int READ = 2;
	private static final int SEND = 3;

	public int getAttribute() {
		return attribute;
	}

	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getReceiveid() {
		return receiveid;
	}

	public void setReceiveid(String receiveid) {
		this.receiveid = receiveid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isAdminMessage() {
		return isAdminMessage;
	}

	public void setAdminMessage(boolean isAdminMessage) {
		this.isAdminMessage = isAdminMessage;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}

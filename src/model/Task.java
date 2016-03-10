package model;

import Trigger.TimeTrigger;
import Trigger.Trigger;

import java.io.IOException;

import javax.mail.MessagingException;

import Action.Action;
import Database.DatabaseTask;

public class Task implements Runnable {

	private int tid;
	private String username;
	private String taskName;
	private int status;
	private Action action;
	private Trigger trigger;
	private int thistype;
	private int thattype;
	private String thatId; // weiboid mailid
	private String thatPass; // weibopassword mailpassword
	private String thatContent; // weibocontent mailcontent
	private String thatRec; // mailRec
	private String thatSub; // mailSub
	private String thisstr1; // Date weiboid mailid weiboid
	private String thisstr2; // time weibocontent mailpassword duringTime

	private static final int INIT = -1;
	private static final int RUNNING = 1;
	private static final int PAUSE = 3;
	private static final int STOP = 2;

	private static final int sendWeibo = 0;
	private static final int sendMail = 1;

	private static final int timeTrigger = 0;
	private static final int mailTrigger = 1;
	private static final int weiboContentTrigger = 2;
	private static final int weiboTimeTrigger = 3;

	public String getThatId() {
		return thatId;
	}

	public void setThatId(String thatId) {
		this.thatId = thatId;
	}

	public String getThatPass() {
		return thatPass;
	}

	public void setThatPass(String thatPass) {
		this.thatPass = thatPass;
	}

	public String getThatContent() {
		return thatContent;
	}

	public void setThatContent(String thatContent) {
		this.thatContent = thatContent;
	}

	public String getThatRec() {
		return thatRec;
	}

	public void setThatRec(String thatRec) {
		this.thatRec = thatRec;
	}

	public String getThatSub() {
		return thatSub;
	}

	public void setThatSub(String thatSub) {
		this.thatSub = thatSub;
	}

	public String getThisstr1() {
		return thisstr1;
	}

	public void setThisstr1(String thisstr1) {
		this.thisstr1 = thisstr1;
	}

	public String getThisstr2() {
		return thisstr2;
	}

	public void setThisstr2(String thisstr2) {
		this.thisstr2 = thisstr2;
	}

	public Task(int tid, String username, String taskname) {
		// TODO Auto-generated constructor stub
		this.tid = tid;
		this.username = username;
		this.taskName = taskname;
		this.status = RUNNING;
	}

	public Task(int tid, String username, String taskName, int status, int thistype, int thattype, String thatId,
			String thatPass, String thatContent, String thatRec, String thatSub, String thisstr1, String thisstr2) {
		super();
		this.tid = tid;
		this.username = username;
		this.taskName = taskName;
		this.status = status;
		this.thistype = thistype;
		this.thattype = thattype;
		this.thatId = thatId;
		this.thatPass = thatPass;
		this.thatContent = thatContent;
		this.thatRec = thatRec;
		this.thatSub = thatSub;
		this.thisstr1 = thisstr1;
		this.thisstr2 = thisstr2;
	}

	public String getInfo() {
		String sta = "运行";
		if (status == 2)
			sta = "结束";
		if (status == 3)
			sta = "暂停";
		String thisType="定时发送";
		if(thistype==1)thisType="收到邮件";
		if(thistype==2)thisType="微博内容";
		if(thistype==3)thisType="微博持续时间未发";
		String thatType="邮件";
		if(thattype==0)thatType="微博";
		String Info = "任务名：" + taskName + "</li><li><pre>    </pre></li><li>" + "所属用户：" + username
				+ "</li><li><pre>    </pre></li><li>" + "状态：" + sta +"</li><li><pre>    </pre></li><li>THIS:"  + thisType +"</li><li><pre>    </pre></li><li>THAT:" + thatType + "</li><li><pre>    </pre></li><li>相关内容：" + thatContent +"</li></ul></div>";
		// + trigger.getInfo() + action.getInfo();
		return Info;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public int getThistype() {
		return thistype;
	}

	public void setThistype(int thistype) {
		this.thistype = thistype;
	}

	public int getThattype() {
		return thattype;
	}

	public void setThattype(int thattype) {
		this.thattype = thattype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (status == RUNNING) {
				// System.out.println("RUNNING!");

				try {
					if (trigger.THIS()) {
						action.THAT();
						if (this.trigger instanceof TimeTrigger) {
							status = STOP;
							DatabaseTask.changeTaskStatus(tid, STOP);
						}
					}
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (status == PAUSE) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (status == STOP)
				break;
		}
	}
}

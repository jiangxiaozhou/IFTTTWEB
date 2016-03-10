package Trigger;

import javax.mail.MessagingException;

public class MailTrigger extends Trigger {
	private String MailId;
	private String MailPassword;
	private int MailCount;

	public MailTrigger(String MailId, String MailPassword) {
		// TODO Auto-generated constructor stub
		this.MailId = MailId;
		this.MailPassword = MailPassword;
		this.type = mailTrigger;
		try {
			this.MailCount = SimpleStoreMails.count(MailId, MailPassword);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String Info = "ListenedMailID:" + MailId + "\n" + "password:" + MailPassword + "\n";
		return Info;
	}

	@Override
	public boolean THIS() {
		// TODO Auto-generated method stub
		System.out.println("check mail");
		int count = 0;
		try {
			count = SimpleStoreMails.count(MailId, MailPassword);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (this.MailCount < count) {
			this.MailCount = count;
			return true;
		}

		return false;
	}

}

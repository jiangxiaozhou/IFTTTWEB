package Action;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailAction extends Action {
	private String MailId;
	private String Mailpass;
	private String Mailrec;
	private String Mailsub;
	private String Mailcontent;

	public MailAction(String MailId, String Mailpass, String Mailrec, String Mailcontent, String Mailsub) {
		// TODO Auto-generated constructor stub
		this.MailId = MailId;
		this.Mailpass = Mailpass;
		this.Mailrec = Mailrec;
		this.Mailcontent = Mailcontent;
		this.Mailsub = Mailsub;
		this.type = sendMail;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String Info = "MailID:" + MailId + "\n" + "Mailpass:" + Mailpass + "\n" + "Mailrec:" + Mailrec + "\n"
				+ "Mailsub:" + Mailsub + "\n" + "Mailcontent:" + Mailcontent + "\n";
		return Info;
	}

	@Override
	public void THAT() throws MessagingException {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true"); //
		props.put("mail.smtp.host", "smtp.qq.com"); //
		props.put("mail.user", MailId); //
		props.put("mail.password", Mailpass); //
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() { //
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		Session mailSession = Session.getInstance(props, authenticator); //
		MimeMessage message = new MimeMessage(mailSession); //
		InternetAddress form;
		form = new InternetAddress(props.getProperty("mail.user"));
		message.setFrom(form);
		InternetAddress to = new InternetAddress(Mailrec);
		message.setRecipient(RecipientType.TO, to); //
		message.setSubject(Mailsub);
		message.setContent(Mailcontent, "text/html;charset=UTF-8"); 
		Transport.send(message); 
	}

}

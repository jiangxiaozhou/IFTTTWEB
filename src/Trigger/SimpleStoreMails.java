package Trigger;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;

public class SimpleStoreMails {
	
	
	public static int count(String username, String password) throws MessagingException {
		String host = "pop.qq.com";			

		Properties props = new Properties();
		props.put("mail.pop3.ssl.enable", true);	
		
		Session session = Session.getDefaultInstance(props, null);

		Store store = session.getStore("pop3");
		store.connect(host, username, password);		

		Folder folder = store.getFolder("INBOX");		
		folder.open(Folder.READ_ONLY);

		Message message[] = folder.getMessages();
		System.out.println(message.length);
		System.out.println(message[message.length-1].getFrom());
	
		folder.close(false);
		store.close();
		return message.length;		
	}

}
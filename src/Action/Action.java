package Action;

import javax.mail.MessagingException;

public abstract class Action {
	protected int type;
	protected static final int sendWeibo = 0;
	protected static final int sendMail = 1;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public abstract String getInfo();

	public abstract void THAT() throws MessagingException;
}

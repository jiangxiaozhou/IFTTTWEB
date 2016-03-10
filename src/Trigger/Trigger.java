package Trigger;

import java.io.IOException;

public abstract class Trigger {
	protected int type;
	protected static final int timeTrigger = 0;
	protected static final int mailTrigger = 1;
	protected static final int weiboContentTrigger = 2;
	protected static final int weiboTimeTrigger = 3;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public abstract String getInfo();
	public abstract boolean THIS() throws IOException;
}

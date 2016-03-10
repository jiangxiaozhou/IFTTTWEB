package Action;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.examples.timeline.UpdateStatus;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;


public class WeiboAction extends Action {
	private String WeiboID;
	private String WeiboPossword;
	private String WeiboContent;
	
	public WeiboAction(String WeiboID, String WeiboPassword, String WeiboContent) {
		// TODO Auto-generated constructor stub
		this.WeiboID = WeiboID;
		this.WeiboContent = WeiboContent;
		this.WeiboPossword = WeiboPassword;
		this.type = sendWeibo;
	}
	public String getInfo() {
		String Info = "WeiboID:" + WeiboID + "\n"
					+ "WeiboPossword:" + WeiboPossword + "\n"
					+ "Weibocontent:" + WeiboContent + "\n";
		return Info;

	}
	
	public void THAT() {
		String statuses = WeiboContent;
		Timeline tm = new Timeline("2.00Cd3PBGaSPbRE3782bcc7b1mzlIYD");
		try {
			Status status = tm.updateStatus(statuses);
			Log.logInfo(status.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}	
	}
}

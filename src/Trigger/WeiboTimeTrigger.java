package Trigger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

public class WeiboTimeTrigger extends Trigger {
	private String WeiboId;
	private String during;
	private Date FinalTime;
	private boolean hasUpdateStatus;
	private boolean timeHasPass;

	public WeiboTimeTrigger(String WeiboId, String during) {
		// TODO Auto-generated constructor stub
		this.during = during;
		this.WeiboId = WeiboId;
		this.hasUpdateStatus = false;
		this.timeHasPass = false;
		this.type = weiboTimeTrigger;
		String[] timeDicide = during.split(":");
		int hour = Integer.parseInt(timeDicide[0]);
		int minute = Integer.parseInt(timeDicide[1]);
		int second = Integer.parseInt(timeDicide[2]);
		Calendar temp = Calendar.getInstance();
		temp.add(Calendar.SECOND, second);
		temp.add(Calendar.MINUTE, minute);
		temp.add(Calendar.HOUR_OF_DAY, hour);
		FinalTime = temp.getTime();

	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String Info = "ListenedWeiboID:" + WeiboId + "\n" + "During:" + during + "\n";
		return Info;
	}

	@Override
	public boolean THIS() throws IOException {
		// TODO Auto-generated method stub
		if (timeHasPass || hasUpdateStatus) return false;
		try {
			URL url = new URL(
					"https://api.weibo.com/2/statuses/home_timeline.json?access_token=2.00Cd3PBGaSPbRE3782bcc7b1mzlIYD&count=4");
			java.util.Scanner input = new java.util.Scanner(url.openStream());
			String temp1 = "";
			while (input.hasNext()) {
				temp1 += input.nextLine();
			}
			try {
				for (int i = 0; i < 4; i++) {
					JSONObject jsStr = new JSONObject(temp1);
					JSONArray temp = (JSONArray) jsStr.get("statuses");
					JSONObject statues = (JSONObject) temp.get(0);
					JSONObject usrid = (JSONObject) statues.get("user");
					System.out.println(statues.toString());
					String tempWeiboId = new String(WeiboId.getBytes("GB2312"), "GBK");
					if (usrid.getString("screen_name").equals(tempWeiboId)) {
						hasUpdateStatus = true;
					}
				}
				Date now = new Date();
				if (!hasUpdateStatus && now.compareTo(FinalTime) >= 0) {
					timeHasPass = true;
					return true;
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.print(jsStr.get("text"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
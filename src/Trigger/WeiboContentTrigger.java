package Trigger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.examples.timeline.*;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

public class WeiboContentTrigger extends Trigger {

	private String WeiboId;
	private String pattern;
	private String idstr;
	private boolean flag;

	public WeiboContentTrigger(String WeiboId, String pattern) {
		// TODO Auto-generated constructor stub
		this.WeiboId = WeiboId;
		this.pattern = pattern;
		this.flag = false;
		this.type = weiboContentTrigger;
		this.idstr = "";
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String Info = "ListenedWeiboID:" + WeiboId + "\n" + "pattern:" + pattern + "\n";
		return Info;
	}

	public boolean THIS() throws IOException {
		// TODO Auto-generated method stub
		try {
			URL url = new URL(
					"https://api.weibo.com/2/statuses/home_timeline.json?access_token=2.00Cd3PBGaSPbRE3782bcc7b1mzlIYD&count=4");
			java.util.Scanner input = new java.util.Scanner(url.openStream());
			String temp1 = "";
			while (input.hasNext()) {
				temp1 += input.nextLine();
			}
			try {
				JSONObject jsStr = new JSONObject(temp1);
				JSONArray temp = (JSONArray) jsStr.get("statuses");
				for (int i = 0; i < 4; i++) {
					JSONObject statues = (JSONObject) temp.get(i);
					JSONObject usrid = (JSONObject) statues.get("user");
					String tempWeiboId = new String(WeiboId.getBytes("GB2312"), "GBK");
					System.out.println("Same Screen_name");
					System.out.println(statues.getString("text"));
					if (usrid.getString("screen_name").equals(WeiboId)) {
						if (statues.getString("text").indexOf(pattern) != -1) {
							
							if (idstr.equals(statues.getString("idstr"))) {
								continue;
							} 
							else {
								idstr = statues.getString("idstr");
								return true;
							}
						}
					}
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

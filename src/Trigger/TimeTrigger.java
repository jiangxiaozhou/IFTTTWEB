package Trigger;

import java.util.Calendar;
import java.util.Date;

public class TimeTrigger extends Trigger {

	private Calendar time;
	private String time1;
	private String date;

	public TimeTrigger(String Date, String Time) {
		// TODO Auto-generated constructor stub
		this.type = timeTrigger;
		this.date = Date;
		this.time1 = Time;

		String[] dataDivide = date.split("-");
		String[] timeDicide = time1.split(":");
		int year = Integer.parseInt(dataDivide[0]);
		int month = Integer.parseInt(dataDivide[1]);
		int day = Integer.parseInt(dataDivide[2]);
		int hour = Integer.parseInt(timeDicide[0]);
		int minute = Integer.parseInt(timeDicide[1]);
		int second = Integer.parseInt(timeDicide[2]);
		time = Calendar.getInstance();
		time.set(Calendar.YEAR, year);
		time.set(Calendar.MONTH, month - 1);
		time.set(Calendar.DAY_OF_MONTH, day);
		time.set(Calendar.HOUR_OF_DAY, hour);
		time.set(Calendar.MINUTE, minute);
		time.set(Calendar.SECOND, second);

	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String Info = "ListenedDate:" + date + "\n" + "Time:" + time1 + "\n";
		return Info;
	}

	@Override
	public boolean THIS() {
		// TODO Auto-generated method stub

		Date now = new Date();
		Date time1 = this.time.getTime();
		System.out.println(now.toString() + " " + time1.toString());
		if (now.compareTo(time1) >= 0) {
			return true;
		}

		return false;
	}

}

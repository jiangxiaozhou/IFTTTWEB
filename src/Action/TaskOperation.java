package Action;

import java.util.ArrayList;

import Database.DatabaseAccount;
import Database.DatabaseCost;
import Database.DatabaseTask;
import model.Cost;
import model.Task;
import Trigger.Trigger;
import Action.Action;
import Action.*;
import Trigger.*;

public class TaskOperation {
	public static ArrayList<Task> taskList = new ArrayList<Task>();

	private static final int INIT = -1;
	private static final int RUNNING = 1;
	private static final int STOP = 2;
	private static final int PAUSE = 3;

	protected static final int sendWeibo = 0;
	protected static final int sendMail = 1;

	protected static final int timeTrigger = 0;
	protected static final int mailTrigger = 1;
	protected static final int weiboContentTrigger = 2;
	protected static final int weiboTimeTrigger = 3;

	public static void deleteTask(int tid) {
		synchronized (taskList) {
			for (int i = 0; i < taskList.size(); i++) {
				if (taskList.get(i).getTid() == tid) {
					taskList.get(i).setStatus(STOP);
					taskList.remove(i);
					break;
				}
			}
		}
		DatabaseTask.deleteTask(tid);
	}

	public static void pauseTask(int tid) {
		synchronized (taskList) {
			for (int i = 0; i < taskList.size(); i++) {
				if (taskList.get(i).getTid() == tid) {
					if (taskList.get(i).getStatus() != STOP) {
						taskList.get(i).setStatus(PAUSE);
						DatabaseTask.changeTaskStatus(taskList.get(i).getTid(), PAUSE);
					}
					break;
				}
			}
		}
	}

	public static Task newTaskActionTrigger(Task task) {
		int thistype = task.getThistype();
		int thattype = task.getThattype();
		Action action = null;
		Trigger trigger = null;
		if (thattype == sendMail) {
			action = new MailAction(task.getThatId(), task.getThatPass(), task.getThatRec(), task.getThatSub(),
					task.getThatContent());
		} else {
			action = new WeiboAction(task.getThatId(), task.getThatPass(), task.getThatContent());
		}

		if (thistype == timeTrigger) {
			trigger = new TimeTrigger(task.getThisstr1(), task.getThisstr2());
		} else if (thistype == mailTrigger) {
			trigger = new MailTrigger(task.getThisstr1(), task.getThisstr2());
		} else if (thistype == weiboContentTrigger) {
			trigger = new WeiboContentTrigger(task.getThisstr1(), task.getThisstr2());
		} else {
			trigger = new WeiboTimeTrigger(task.getThisstr1(), task.getThisstr2());
		}

		task.setAction(action);
		task.setTrigger(trigger);
		return task;
	}

	// 由于添加了任务，在这里扣款和添加消费记录
	public static void addTask(Task task) {
		taskList.add(task);
		Thread thread = new Thread(task);
		System.out.println(task.getTaskName() + " ADD!");
		String username = task.getUsername();
		int money = 22 - DatabaseAccount.getLevel(username) * 2;
		DatabaseAccount.costMoney(username, money);
		Cost cost = new Cost(DatabaseCost.getCostid() + 201500, money, username);
		DatabaseCost.addCost(cost);
		DatabaseTask.addTask(task);
		thread.start();
	}

	public static void runTask(int tid) {
		synchronized (taskList) {
			for (int i = 0; i < taskList.size(); i++) {
				if (taskList.get(i).getTid() == tid) {
					if (taskList.get(i).getStatus() != STOP) {
						taskList.get(i).setStatus(RUNNING);
						String username = DatabaseTask.getTask(tid).getUsername();
						int money = 11 - DatabaseAccount.getLevel(username);
						// 第二处扣款
						DatabaseAccount.costMoney(username, money);
						Cost cost = new Cost(DatabaseCost.getCostid() + 201500, money, username);
						DatabaseCost.addCost(cost);
						System.out.println("RUN TASK!");
						DatabaseTask.changeTaskStatus(taskList.get(i).getTid(), RUNNING);
						break;
					}
				}
			}
		}
	}

	public static void stopTask(int tid) {
		Task task = getTask(tid);
		if (task.getStatus() != STOP) {
			task.setStatus(STOP);
			DatabaseTask.changeTaskStatus(task.getTid(), STOP);
		}
	}

	public static Task getTask(int tid) {
		synchronized (taskList) {
			for (int i = 0; i < taskList.size(); i++) {
				if (taskList.get(i).getTid() == tid) {
					return taskList.get(i);
				}
			}
		}
		return null;
	}

	public static int size() {
		return taskList.size();
	}
}

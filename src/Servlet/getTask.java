package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import Action.MailAction;
import Action.TaskOperation;
import Action.WeiboAction;
import Database.DatabaseAccount;
import Trigger.MailTrigger;
import Trigger.TimeTrigger;
import Trigger.Trigger;
import Trigger.WeiboContentTrigger;
import Trigger.WeiboTimeTrigger;
import model.Account;
import model.Task;

/**
 * Servlet implementation class getTask
 */
@WebServlet("/getTask")
public class getTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session1 = request.getSession();
		String username = (String) session1.getAttribute("username");
		String password = (String) session1.getAttribute("password");
		System.out.println(username + " " + password);

		Account temp = new Account(username, password, "");
		Account account = DatabaseAccount.search(temp);
		int thistype = Integer.parseInt((String) request.getParameter("this"));
		int thattype = Integer.parseInt((String) request.getParameter("that"));
		Task task = getTaskInfo(request, thistype, thattype, username);
		
		String srcURL = request.getHeader("Referer");
		String []tem = srcURL.split("[/?=]");
		if (tem.length >= 7) {
			int tid = Integer.parseInt(tem[tem.length - 1]);
			task.setTid(tid);
			task.setTaskName(TaskOperation.getTask(tid).getTaskName());
			TaskOperation.deleteTask(tid);
			TaskOperation.addTask(task);
			response.sendRedirect("LookTaskInfo.jsp");
		}
		else {
			TaskOperation.addTask(task);
			response.sendRedirect("LookTaskInfo.jsp");
		}

		//doGet(request, response);
	}

	protected ArrayList<Object> getTrigger(HttpServletRequest request, int thistype) {
		ArrayList<Object> temp = new ArrayList<Object>();
		String thisstr1 = null;
		String thisstr2 = null;
		Trigger trigger = null;
		if (thistype == 0) {
			thisstr1 = request.getParameter("Date");
			thisstr2 = request.getParameter("Time");
			trigger = new TimeTrigger(thisstr1, thisstr2);
		} else if (thistype == 1) {
			thisstr1 = request.getParameter("MailId1");
			thisstr2 = request.getParameter("MailPass1");
			trigger = new MailTrigger(thisstr1, thisstr2);
		} else if (thistype == 2) {
			thisstr1 = request.getParameter("WeiboId1");
			thisstr2 = request.getParameter("weiboPattern");
			trigger = new WeiboContentTrigger(thisstr1, thisstr2);
		} else {
			thisstr1 = request.getParameter("WeiboId2");
			thisstr2 = request.getParameter("during");
			trigger = new WeiboTimeTrigger(thisstr1, thisstr2);
		}
		temp.add(trigger);
		temp.add(thisstr1);
		temp.add(thisstr2);
		return temp;
	}

	protected ArrayList<Object> getAction(HttpServletRequest request, int thattype) {
		ArrayList<Object> temp = new ArrayList<Object>();
		Action action = null;
		String thatId; 
		String thatPass; 
		String thatContent; 
	    String thatRec = null; 
		String thatSub = null; 
		if (thattype == 0) {
			thatId = request.getParameter("weiboid");
			thatPass = request.getParameter("weibopassword");
			thatContent = request.getParameter("weibocontent");
			action = new WeiboAction(thatId, thatPass, thatContent);
		} else {
			thatId = request.getParameter("MailId");
			thatPass = request.getParameter("Mailpass");
			thatRec = request.getParameter("Mailrec");
			thatSub = request.getParameter("Mailsub");
			thatContent = request.getParameter("Mailcontent");
			action = new MailAction(thatId, thatPass, thatRec, thatContent, thatSub);
		}
		temp.add(action);
		temp.add(thatId);
		temp.add(thatPass);
		temp.add(thatContent);
		temp.add(thatRec);
		temp.add(thatSub);
		return temp;
	}

	protected Task getTaskInfo(HttpServletRequest request, int thistype, int thattype, String username) {
		String taskName = request.getParameter("taskname");
		Task task = new Task(0, username, taskName);
		task.setThistype(thistype);
		task.setThattype(thattype);
		Action action = (Action) getAction(request, thattype).get(0);
		task.setThatId((String)getAction(request, thattype).get(1));
		task.setThatPass((String)getAction(request, thattype).get(2));
		task.setThatContent((String)getAction(request, thattype).get(3));
		task.setThatRec((String)getAction(request, thattype).get(4));
		task.setThatSub((String)getAction(request, thattype).get(5));
		Trigger trigger = (Trigger) getTrigger(request, thistype).get(0);
		task.setThisstr1((String)getTrigger(request, thistype).get(1));
		task.setThisstr2((String)getTrigger(request, thistype).get(2));
		task.setAction(action);
		task.setTrigger(trigger);
		task.setTid(task.hashCode());
		return task;
	}
}

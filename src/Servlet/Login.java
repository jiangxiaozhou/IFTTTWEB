package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.TaskOperation;
import Database.DatabaseAccount;
import Database.DatabaseTask;
import model.Account;
import model.Task;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		System.out.println(username + " " + password);
		/*
		 * TODO:这里的account应该与数据库建立联系 没有将邮箱地址传入此时的account 实现方法有待讨论
		 */
		Account account = new Account(username, password, "");
		if (DatabaseAccount.search(account) != null) {
			if (DatabaseAccount.getLevel(username) > 9) {
				int[] tid = DatabaseTask.getAllTask();
				for (int i = 0; i < tid.length; i++) {
					if (tid[i] != 0) {
						Task task = DatabaseTask.getTask(tid[i]);
						task = TaskOperation.newTaskActionTrigger(task);
						if (TaskOperation.getTask(tid[i]) == null) {
							TaskOperation.taskList.add(task);
							Thread thread = new Thread(task);
							System.out.println(task.getTaskName() + " ADD!");
							thread.start();
						}
					}
				}
				response.sendRedirect("Administrator.jsp");
			} else {
				int[] tid = DatabaseTask.getTaskOfUserid(username);
				for (int i = 0; i < tid.length; i++) {
					if (tid[i] != 0) {
						Task task = DatabaseTask.getTask(tid[i]);
						task = TaskOperation.newTaskActionTrigger(task);
						if (TaskOperation.getTask(tid[i]) == null) {
							TaskOperation.taskList.add(task);
							Thread thread = new Thread(task);
							System.out.println(task.getTaskName() + " ADD!");
							thread.start();
						}
					}
				}
				response.sendRedirect("/IFTTT/PersonalHome.jsp");
			}
		} else
			response.getWriter().append("Login failed!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import Action.TaskOperation;
import Database.DatabaseTask;

/**
 * Servlet implementation class TaskController
 */
@WebServlet("/TaskController")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskController() {
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
		int tid = Integer.parseInt(request.getParameter("task"));
		System.out.println(tid);
		int status = Integer.parseInt(request.getParameter("status"));
		System.out.println("Status: " + status);
		if (status == 1) {
			TaskOperation.runTask(tid);
		} else if (status == 2) {
			TaskOperation.stopTask(tid);
		} else if (status == 3) {
			TaskOperation.pauseTask(tid);
		} else {
			TaskOperation.deleteTask(tid);
		}
		response.sendRedirect("LookTaskInfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int tid = Integer.parseInt(request.getParameter("task"));
		System.out.println(tid);
		int status = Integer.parseInt(request.getParameter("status"));
		System.out.println("Status: " + status);
		if (status == 1) {
			TaskOperation.runTask(tid);
		} else if (status == 2) {
			TaskOperation.stopTask(tid);
		} else if (status == 3) {
			TaskOperation.pauseTask(tid);
		} else {
			TaskOperation.deleteTask(tid);
		}
		// response.getWriter().append("Operation success!");
		response.sendRedirect("LookTaskInfo.jsp");
	}

}

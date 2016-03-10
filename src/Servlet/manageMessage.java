package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Database.DatabaseMessage;
import model.Message;

/**
 * Servlet implementation class manageMessage
 */
@WebServlet("/manageMessage")
public class manageMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public manageMessage() {
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
//		HttpSession session1 = request.getSession();
//		String username = (String) session1.getAttribute("username");
		int mid = Integer.parseInt(request.getParameter("message"));
		DatabaseMessage.deleteMessage(mid);
		response.sendRedirect("ADMessageManage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int mid = Integer.parseInt(request.getParameter("message"));
		DatabaseMessage.deleteMessage(mid);
		response.sendRedirect("MessageManagement.jsp");
	}

}

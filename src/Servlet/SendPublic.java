package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseAccount;
import Database.DatabaseMessage;
import model.Message;

/**
 * Servlet implementation class SendPublic
 */
@WebServlet("/SendPublic")
public class SendPublic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendPublic() {
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
		HttpSession session1 = request.getSession();
		String username = (String) session1.getAttribute("username");
		boolean isAD = DatabaseAccount.getLevel(username) == 10 ? true : false;
		String content = request.getParameter("content");
		content = new String(content.getBytes("ISO-8859-1"), "gbk");
		System.out.println(content);
		Message message = new Message(DatabaseMessage.getmid() + 15001, username, request.getParameter("receive"),
				content, isAD, 3);
		DatabaseMessage.addMessage(message);
		response.sendRedirect("ADMessageManage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session1 = request.getSession();
		String username = (String) session1.getAttribute("username");
		boolean isAD = DatabaseAccount.getLevel(username) == 10 ? true : false;
		String content = request.getParameter("content");
		content = new String(content.getBytes("ISO-8859-1"), "gbk");
		System.out.println(content);
		Message message = new Message(DatabaseMessage.getmid() + 15001, username, request.getParameter("receive"),
				content, isAD, 10);
		DatabaseMessage.addMessage(message);
		response.sendRedirect("ADMessageManage.jsp");
	}

}

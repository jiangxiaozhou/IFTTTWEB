package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseAccount;
import model.Account;

/**
 * Servlet implementation class ChangeInfo
 */
@WebServlet("/ChangeInfo")
public class ChangeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String level = request.getParameter("level");
		int l = level.equals("") ? 1 : Integer.parseInt(level);
		Account a = DatabaseAccount.getAccount(username);
		if (a == null) {
			System.err.println("There is not an account called" + username);
			response.sendRedirect("ChangeAccountLevel.jsp");
		} else {
			DatabaseAccount.setLevel(username, l);
			response.sendRedirect("ADAccountManage.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session1 = request.getSession();
		String username = (String) session1.getAttribute("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		Account a = DatabaseAccount.getAccount(username);
		if (!password2.equals(password)) {
			System.err.println("The two passwords are not same");
			response.getWriter().append("The two passwords are not same!");
			response.sendRedirect("ChangeInfo.jsp");
		} else {
			if (password.equals(""))
				password = a.getPassword();
			if (email.equals(""))
				email = a.getEmailaddress();
			DatabaseAccount.changeInfo(username, password, email);
			response.sendRedirect("PersonalInfo.jsp");
		}
	}

}

package ua.library.login.input;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.library.db.DBManager;

@WebServlet(urlPatterns = "/InputLogin", loadOnStartup = 0)
public class InputLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("Init");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/jsp");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		String login = request.getParameter("login");   // (login.jsp)
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		session.setAttribute("login", login);
		session.setAttribute("firstName", firstName);
		session.setAttribute("lastName", lastName);
		try {
			String[] info = DBManager.findUser(login, password);
			if (info == null) {
				response.sendRedirect("notCorrectInfo.jsp");
			} else {
				if (info[0] != null && info[1] != null && Integer.parseInt(info[2]) == 0) {
					response.sendRedirect("adminRoom.jsp");
				} else if (info[0] != null && info[1] != null && Integer.parseInt(info[2]) == 1) {
					response.sendRedirect("librarianRoom.jsp");
				} else if (info[0] != null && info[1] != null && Integer.parseInt(info[2]) == 2) {
					response.sendRedirect("userRoom.jsp");
				} else {
					response.sendRedirect("notCorrectInfo.jsp");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

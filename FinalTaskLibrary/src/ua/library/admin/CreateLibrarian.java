package ua.library.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.library.db.DBManager;

@WebServlet("/CreateLibrarian")
public class CreateLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/jsp");
		response.setCharacterEncoding("UTF-8");

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		try {
			DBManager.createLibrarian(login, password, firstName, lastName);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect("adminRoom.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

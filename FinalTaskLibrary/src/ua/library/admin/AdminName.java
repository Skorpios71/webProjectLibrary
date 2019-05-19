package ua.library.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.library.db.DBManager;

@WebServlet("/AdminName")
public class AdminName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/jsp");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			String login = request.getParameter("login");
			String firstLastName = DBManager.findFirstLastName(login);
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			session.setAttribute("firstLastName", firstLastName);
		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

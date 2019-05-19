package ua.library.librarian;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.library.db.DBManager;

@WebServlet("/IssueBook")
public class IssueBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/jsp");
		response.setCharacterEncoding("UTF-8");

		try {
			String name = request.getParameter("name");
			String author = request.getParameter("author");
			String edition = request.getParameter("edition");
			String dataEdition = request.getParameter("dataEdition");
			String login = request.getParameter("myLogin");
			String giveIn = request.getParameter("giveIn");
			String data = request.getParameter("data");
			if (DBManager.findUser(login)) { 
				DBManager.issueBook(name.trim(), author.trim(), edition.trim(), dataEdition.trim(), login.trim(), giveIn.trim(), data.trim());
				DBManager.decrementBook(name.trim(), author.trim(), edition.trim(), dataEdition.trim());
				DBManager.removeOrder(name.trim(), author.trim(), edition.trim(), dataEdition.trim(), login.trim());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect("librarianRoom.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

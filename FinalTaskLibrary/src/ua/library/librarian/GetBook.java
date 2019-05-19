package ua.library.librarian;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.library.db.DBManager;

@WebServlet("/GetBook")
public class GetBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/jsp");
		response.setCharacterEncoding("UTF-8");

		try {
			String name = request.getParameter("name");
			String author = request.getParameter("author");
			String edition = request.getParameter("edition");
			int dataEdition = Integer.parseInt(request.getParameter("dataEdition"));
			String login = request.getParameter("myLogin");
			String giveIn = request.getParameter("giveIn");
			String data = request.getParameter("data");
			if (DBManager.findUser(login)) { 
				//DBManager.insertBook(name, author, edition, dataEdition, 1);
				DBManager.incrementCountBook(name, author, edition, dataEdition);
				DBManager.removeBookInUser(name, author, edition, dataEdition);
			} else {
				response.sendRedirect("notCorrectInputInfo.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("login/notCorrectInputInfo.jsp");
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			response.sendRedirect("notCorrectInputInfo.jsp");
			System.out.println(e.getMessage());
		}
		response.sendRedirect("librarianRoom.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

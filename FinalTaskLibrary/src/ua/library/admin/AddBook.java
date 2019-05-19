package ua.library.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.library.db.DBManager;

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/jsp");
		response.setCharacterEncoding("UTF-8");

		String nameBook = request.getParameter("nameBook");
		String authorBook = request.getParameter("authorBook");
		String edition = request.getParameter("edition");
		int dataEdition = Integer.parseInt(request.getParameter("dataEdition"));
		int count = Integer.parseInt(request.getParameter("count"));
		try {
			if (DBManager.findBook(nameBook, authorBook, edition, dataEdition)) {
				DBManager.incrementCountBook(nameBook, authorBook, edition, dataEdition);
			} else {
				DBManager.insertBook(nameBook, authorBook, edition, dataEdition, count);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("adminRoom.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

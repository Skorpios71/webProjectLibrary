package ua.library.catalog;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.library.db.DBManager;

@WebServlet("/CatalogBooksEdition")
public class CatalogBooksEdition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/jsp");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		String login = request.getParameter("login");
		HttpSession session = request.getSession();
		
		try {
			List<String> books = DBManager.findBooksSortEdition();
			out.println(books);
			System.out.println(books);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

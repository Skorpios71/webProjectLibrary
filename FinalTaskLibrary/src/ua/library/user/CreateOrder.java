package ua.library.user;

import java.io.IOException;
import java.sql.SQLData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.library.db.DBManager;

@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
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
				DBManager.createOrder(name, author, edition, dataEdition, login, giveIn, data);
				//response.sendRedirect("catalog.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect("catalog.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

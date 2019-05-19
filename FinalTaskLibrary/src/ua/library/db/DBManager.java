package ua.library.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DBManager {
	// CONNECT
	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "password";
	// INSERT
	private static final String SQL_INSERT_USER_BLOCK = "INSERT INTO users_block VALUES (DEFAULT, ?, ?, ?, ?, 2)";
	private static final String SQL_INSERT_USER_UNBLOCK = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, 2)";
	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, 2)";
	private static final String SQL_INSERT_BOOK = "INSERT INTO catalog VALUES (DEFAULT, ?, ?, ?, ?, ?)";
	private static final String SQL_CREATE_LIBRARIAN = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, 1)";
	private static final String SQL_CREATE_ORDER = "INSERT INTO orders VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_ISSUE_BOOK = "INSERT INTO issued_orders VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, NULL)";
	// SELECT
	private static final String SQL_SEARCH_USER = "SELECT * FROM users WHERE login=? and password=?";
	private static final String SQL_SEARCH_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	private static final String SQL_SEARCH_FIRST_LAST_NAME = "SELECT first_name, last_name FROM users WHERE login=?";/////
	private static final String SQL_SEARCH_USERS = "SELECT id, login, password, first_name, last_name, role_id FROM users ORDER BY role_id";
	private static final String SQL_SEARCH_BLOCK_USERS = "SELECT * FROM users_block";
	private static final String SQL_SEARCH_BLOCK_USER_BY_LOGIN = "SELECT * FROM users_block WHERE login=?";
	private static final String SQL_SEARCH_BOOKS = "SELECT * FROM catalog WHERE id > 0";
	private static final String SQL_SEARCH_BOOK = "SELECT * FROM catalog WHERE name=? and author = ? and edition = ? and year_edition = ?";
	private static final String SQL_SEARCH_BOOKS_NAME = "SELECT id, name, author, edition, year_edition, count FROM catalog ORDER BY name";
	private static final String SQL_SEARCH_BOOKS_AUTHOR = "SELECT id, name, author, edition, year_edition, count FROM catalog ORDER BY author";/////////
	private static final String SQL_SEARCH_BOOKS_NAME_OR_AUTHOR = "SELECT id, name, author, edition, year_edition, count FROM catalog WHERE name=? OR author=?";//////////////
	private static final String SQL_SEARCH_BOOKS_EDITION = "SELECT id, name, author, edition, year_edition, count FROM catalog ORDER BY edition";
	private static final String SQL_SEARCH_BOOKS_DATA_EDITION = "SELECT id, name, author, edition, year_edition, count FROM catalog ORDER BY year_edition";
	private static final String SQL_SEARCH_ORDERS = "SELECT * FROM orders";
	private static final String SQL_SEARCH_BOOKS_USER = "SELECT * FROM issued_orders";
	// DELETE
	private static final String SQL_REMOVE_BOOK = "DELETE FROM catalog WHERE name=? and author=? and edition=? and year_edition=? and count=?";
	private static final String SQL_REMOVE_BOOK_IN_USER = "DELETE FROM issued_orders WHERE nameBook=? and author=? and edition=? and year_edition=?";
	private static final String SQL_REMOVE_LIBRARIAN = "DELETE FROM users WHERE login=? AND role_id=1";
	private static final String SQL_REMOVE_USER = "DELETE FROM users WHERE login=?";
	private static final String SQL_REMOVE_USER_BLOCK = "DELETE FROM users_block WHERE login=?";
	private static final String SQL_REMOVE_ORDER = "DELETE FROM orders WHERE nameBook=? and author=? and edition=? and year_edition=? and login=?";
	// UPDATE
	private static final String SQL_INCREMENT_COUNT_BOOK = "update catalog set count = count + 1 where name = ? and author = ? and edition = ? and year_edition = ?";
	private static final String SQL_DECREMENT_COUNT_BOOK = "update catalog set count = count - 1 where name = ? and author = ? and edition = ? and year_edition = ?";
	private static final String SQL_UPDATE_FINE = "UPDATE issued_orders SET fine = '100' WHERE CURRENT_DATE() > date and give_in = 'card'";

	private static Connection con = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	public static String findFirstLastName(String login) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_FIRST_LAST_NAME);
		pstmt.setString(1, login);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getString("first_name").toString() + " " + rs.getString("last_name").toString();
		} else {
			return "not found";
		}
	}

	public static void removeBookInUser(String name, String author, String edition, int dataEdition)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_REMOVE_BOOK_IN_USER);
		pstmt.setString(1, name);
		pstmt.setString(2, author);
		pstmt.setString(3, edition);
		pstmt.setInt(4, dataEdition);
		pstmt.addBatch();
		pstmt.executeUpdate();
		con.close();
		pstmt.close();
	}

	public static void removeOrder(String name, String author, String edition, String dataEdition, String login)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_REMOVE_ORDER);
		pstmt.setString(1, name);
		pstmt.setString(2, author);
		pstmt.setString(3, edition);
		pstmt.setString(4, dataEdition);
		pstmt.setString(5, login);
		pstmt.addBatch();
		pstmt.executeUpdate();
		con.close();
		pstmt.close();
	}

	public static void issueBook(String name, String author, String edition, String dataEdition, String myLogin,
			String giveIn, String data) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_ISSUE_BOOK);
		pstmt.setString(1, name);
		pstmt.setString(2, author);
		pstmt.setString(3, edition);
		pstmt.setString(4, dataEdition);
		pstmt.setString(5, myLogin);
		pstmt.setString(6, giveIn);
		pstmt.setString(7, data);
		pstmt.addBatch();
		pstmt.executeUpdate();
		con.close();
		pstmt.close();
	}

	public static boolean blockUserByLogin(String login) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_USER_BY_LOGIN);
		pstmt.setString(1, login);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			pstmt = con.prepareStatement(SQL_INSERT_USER_BLOCK);
			pstmt.setString(1, rs.getString(2));
			pstmt.setString(2, rs.getString(3));
			pstmt.setString(3, rs.getString(4));
			pstmt.setString(4, rs.getString(5));
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
					+ " " + rs.getString(5) + " " + rs.getString(6));
			pstmt.addBatch();
			pstmt.executeUpdate();
			removeUser(login);
			return true;
		} else {
			return false;
		}
	}

	public static void removeUserByBlock(String login) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_REMOVE_USER_BLOCK);
		pstmt.setString(1, login);
		pstmt.addBatch();
		pstmt.executeUpdate();
		con.close();
		pstmt.close();
	}

	public static boolean unblockUserByLogin(String login) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_BLOCK_USER_BY_LOGIN);
		pstmt.setString(1, login);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT_USER_UNBLOCK);
			ps.setString(1, rs.getString(2));
			ps.setString(2, rs.getString(3));
			ps.setString(3, rs.getString(4));
			ps.setString(4, rs.getString(5));
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
					+ " " + rs.getString(5) + " " + rs.getString(6));
			ps.addBatch();
			ps.executeUpdate();
			removeUserByBlock(login);
			return true;
		} else {
			return false;
		}
	}

	public static void removeUser(String login) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_REMOVE_USER);
		pstmt.setString(1, login);
		pstmt.addBatch();
		pstmt.executeUpdate();
		con.close();
		pstmt.close();
	}

	public static List<String> findUserBooks(String login) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		rs = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_BOOKS_USER);
		rs = pstmt.executeQuery();
		List<String> resInfo = new ArrayList<String>();
		resInfo.add("<tr>" + "<td>" + "Name book " + "<td>" + "Author" + "<td>" + "Edition" + "<td>" + "Year edition"
				+ "<td>" + "Hall /  Card" + "<td>" + "Date" + "<td>" + "Fine" + "<tr>");
		while (rs.next()) {
			if (rs.getString("login").equals(login)) {
				Connection c = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
				Statement pst = c.createStatement();
				pst.executeUpdate(SQL_UPDATE_FINE);
				resInfo.add("<tr>" + "<td>" + rs.getString("nameBook") + " " + "<td>" + rs.getString("author") + " "
						+ "<td>" + rs.getString("edition") + " " + "<td>" + rs.getInt("year_edition") + " " + "<td>"
						+ rs.getString("give_in") + " " + "<td>" + rs.getString("date") + " "
						+ ("100".equals(rs.getString("fine"))
								? ("<td class=\"col1\">" + "<b>" + rs.getString("fine") + "</b>")
								: ("<td>" + "-"))
						+ "<tr>");
			} else {
				continue;
			}
		}
		return resInfo;
	}

	public static List<String> findOrders() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		rs = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_ORDERS);
		rs = pstmt.executeQuery();
		List<String> resInfo = new ArrayList<String>();
		resInfo.add("<tr>" + "<td>" + "ID " + "<td>" + "Name book " + "<td>" + "Author" + "<td>" + "Edition" + "<td>"
				+ "Year edition" + "<td>" + "User login" + "<td>" + "Hall/Card" + "<td>" + "Date" + "<tr>");
		while (rs.next()) {
			resInfo.add((("<tr>" + "<td>" + rs.getInt("id") + " " + "<td>" + rs.getString("nameBook") + " " + "<td>"
					+ rs.getString("author") + " " + "<td>" + rs.getString("edition") + " " + "<td>"
					+ rs.getString("year_edition") + " " + "<td>" + rs.getString("login") + " " + "<td>"
					+ rs.getString("give_in") + " " + "<td>" + rs.getString("data") + "<tr>")).replaceAll(",", ""));
		}
		return resInfo;
	}

	public static void createOrder(String name, String author, String edition, String dataEdition, String myLogin,
			String giveIn, String data) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_CREATE_ORDER);
		pstmt.setString(1, name);
		pstmt.setString(2, author);
		pstmt.setString(3, edition);
		pstmt.setString(4, dataEdition);
		pstmt.setString(5, myLogin);
		pstmt.setString(6, giveIn);
		pstmt.setString(7, data);
		pstmt.addBatch();
		pstmt.executeUpdate();
		con.close();
		pstmt.close();
	}

	public static List<String> findBooksSortDataEdition() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		rs = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_BOOKS_DATA_EDITION);
		rs = pstmt.executeQuery();
		List<String> resInfo = new ArrayList<String>();
		resInfo.add("<tr>" + "<td>" + "Name book " + "<td>" + "Author" + "<td>" + "Edition" + "<td>" + "Year edition"
				+ "<td>" + "Count" + "<tr>");
		while (rs.next()) {
			resInfo.add((("<tr>" + "<td>" + rs.getInt("id") + " " + "<td>" + rs.getString("name") + " " + "<td>"
					+ rs.getString("author") + " " + "<td>" + rs.getString("edition") + " " + "<td>"
					+ rs.getInt("year_edition") + " " + "<td>" + rs.getInt("count") + "<tr>")));
		}
		return resInfo;
	}

	public static List<String> findBooksSortEdition() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		rs = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_BOOKS_EDITION);
		rs = pstmt.executeQuery();
		List<String> resInfo = new ArrayList<String>();
		resInfo.add("<tr>" + "<td>" + "Name book " + "<td>" + "Author" + "<td>" + "Edition" + "<td>" + "Year edition"
				+ "<td>" + "Count" + "<tr>");
		while (rs.next()) {
			resInfo.add((("<tr>" + "<td>" + rs.getInt("id") + " " + "<td>" + rs.getString("name") + " " + "<td>"
					+ rs.getString("author") + " " + "<td>" + rs.getString("edition") + " " + "<td>"
					+ rs.getInt("year_edition") + " " + "<td>" + rs.getInt("count") + "<tr>")).replaceAll(",", ""));
		}
		return resInfo;
	}

	public static List<String> searchBooksSortNameOrAuthor(String search) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		rs = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_BOOKS_NAME_OR_AUTHOR);
		pstmt.setString(1, search);
		pstmt.setString(2, search);
		rs = pstmt.executeQuery();
		List<String> resInfo = new ArrayList<String>();
		resInfo.add("<tr bgcolor=\"#87CEEB\">" + "<td>" + "ID " + "<td>" + "Name book " + "<td>" + "Author" + "<td>"
				+ "Edition" + "<td>" + "Year edition" + "<td>" + "count" + "</tr>");
		while (rs.next()) {
			resInfo.add((("<tr>" + "<td>" + rs.getInt("id") + " " + "<td>" + rs.getString("name") + " " + "<td>"
					+ rs.getString("author") + " " + "<td>" + rs.getString("edition") + " " + "<td>"
					+ rs.getInt("year_edition") + " " + "<td>" + rs.getInt("count") + "<tr>")));
		}
		return resInfo;
	}

	public static List<String> findBooksSortAuthor() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		rs = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_BOOKS_AUTHOR);
		rs = pstmt.executeQuery();
		List<String> resInfo = new ArrayList<String>();
		resInfo.add("<tr bgcolor=\"#87CEEB\">" + "<td>" + "ID " + "<td>" + "Name book " + "<td>" + "Author" + "<td>"
				+ "Edition" + "<td>" + "Year edition" + "<td>" + "count" + "</tr>");
		while (rs.next()) {
			resInfo.add((("<tr>" + "<td>" + rs.getInt("id") + " " + "<td>" + rs.getString("name") + " " + "<td>"
					+ rs.getString("author") + " " + "<td>" + rs.getString("edition") + " " + "<td>"
					+ rs.getInt("year_edition") + " " + "<td>" + rs.getInt("count") + "<tr>")));
		}
		return resInfo;
	}

	public static List<String> findBooksSortName() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		rs = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_BOOKS_NAME);
		rs = pstmt.executeQuery();
		List<String> resInfo = new ArrayList<String>();
		resInfo.add("<tr bgcolor=\"#87CEEB\">" + "<td>" + "ID " + "<td>" + "Name book " + "<td>" + "Author" + "<td>"
				+ "Edition" + "<td>" + "Year edition" + "<td>" + "count" + "</tr>");
		while (rs.next()) {
			resInfo.add((("<tr>" + "<td>" + rs.getInt("id") + " " + "<td>" + rs.getString("name") + " " + "<td>"
					+ rs.getString("author") + " " + "<td>" + rs.getString("edition") + " " + "<td>"
					+ rs.getInt("year_edition") + " " + "<td>" + rs.getInt("count") + "<tr>")).replaceAll(",", ""));
		}
		return resInfo;
	}

	public static List<String> findBooks() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		rs = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_BOOKS);
		rs = pstmt.executeQuery();
		List<String> resInfo = new ArrayList<String>();
		resInfo.add("<tr bgcolor=\"#87CEEB\">" + "<td>" + "ID " + "<td>" + "Name book " + "<td>" + "Author" + "<td>"
				+ "Edition" + "<td>" + "Year edition" + "<td>" + "count" + "</tr>");
		while (rs.next()) {
			resInfo.add((("<tr>" + "<td>" + rs.getInt("id") + " " + "<td>" + rs.getString("name") + " " + "<td>"
					+ rs.getString("author") + " " + "<td>" + rs.getString("edition") + " " + "<td>"
					+ rs.getInt("year_edition") + " " + "<td>" + rs.getInt("count") + "<tr>")).replaceAll(",", ""));
		}
		return resInfo;
	}

	public static boolean findBook(String nameBook, String author, String edition, int dataEdition)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_BOOK);
		pstmt.setString(1, nameBook);
		pstmt.setString(2, author);
		pstmt.setString(3, edition);
		pstmt.setInt(4, dataEdition);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean incrementCountBook(String nameBook, String author, String edition, int dataEdition)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_INCREMENT_COUNT_BOOK);
		pstmt.setString(1, nameBook);
		pstmt.setString(2, author);
		pstmt.setString(3, edition);
		pstmt.setInt(4, dataEdition);
		pstmt.addBatch();
		if (!pstmt.execute()) {
			con.close();
			pstmt.close();
			return true;
		} else {
			con.close();
			pstmt.close();
			return false;
		}
	}

	public static boolean decrementBook(String name, String author, String edition, String dataEdition)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_DECREMENT_COUNT_BOOK);
		pstmt.setString(1, name);
		pstmt.setString(2, author);
		pstmt.setString(3, edition);
		pstmt.setString(4, dataEdition);
		pstmt.addBatch();
		if (!pstmt.execute()) {
			con.close();
			pstmt.close();
			return true;
		} else {
			con.close();
			pstmt.close();
			return false;
		}
	}

	public static List<String> findUsers() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		rs = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_USERS);
		rs = pstmt.executeQuery();
		List<String> resInfo = new ArrayList<String>();
		resInfo.add("<tr bgcolor=\"#90EE90\">" + "<td>" + "ID " + "<td>" + "LOGIN " + "<td>" + "PASSWORD" + "<td>"
				+ "FIRST NAME" + "<td>" + "LAST NAME" + "<td>" + "ROLE (0-admin <br> 1-librarian 2-user)" + "</tr>");
		while (rs.next()) {
			resInfo.add((("<tr>" + "<td>" + rs.getInt("id") + " " + "<td>" + rs.getString("login") + " " + "<td>"
					+ rs.getString("password") + " " + "<td>" + rs.getString("first_name") + " " + "<td>"
					+ rs.getString("last_name") + " " + "<td>" + rs.getInt("role_id") + "<tr>")));
		}
		return resInfo;
	}

	public static List<String> findBlockUsers() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		pstmt = null;
		rs = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_BLOCK_USERS);
		rs = pstmt.executeQuery();
		List<String> resInfo = new LinkedList<String>();
		resInfo.add("<tr bgcolor=\"#FA8072\">" + "<td>" + "ID " + "<td>" + "LOGIN " + "<td>" + "PASSWORD" + "<td>"
				+ "FIRST NAME" + "<td>" + "LAST NAME" + "<td>" + "ROLE" + "</tr>");
		while (rs.next()) {
			resInfo.add((("<tr>" + "<td>" + rs.getInt("id") + "<td>" + rs.getString("login") + "<td>"
					+ rs.getString("password") + "<td>" + rs.getString("first_name") + "<td>"
					+ rs.getString("last_name") + "<td>" + rs.getInt("role_id") + "<tr>")));
		}
		return resInfo;
	}

	public static boolean findUser(String login) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_USER_BY_LOGIN);
		pstmt.setString(1, login);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public static String[] findUser(String login, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_SEARCH_USER);
		pstmt.setString(1, login);
		pstmt.setString(2, password);
		rs = pstmt.executeQuery();
		String[] resInfo = null;
		if (rs.next()) {
			resInfo = new String[3];
			resInfo[0] = rs.getString(2);
			resInfo[1] = rs.getString(3);
			resInfo[2] = rs.getString(6);
			System.out.println(resInfo[0] + resInfo[1] + resInfo[2]);
			return resInfo;
		} else {
			return resInfo;
		}
	}

	public static void insertUser(String login, String password, String firstName, String lastName)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_INSERT_USER);
		pstmt.setString(1, login);
		pstmt.setString(2, password);
		pstmt.setString(3, firstName);
		pstmt.setString(4, lastName);
		pstmt.addBatch();
		pstmt.executeUpdate();
		con.close();
		pstmt.close();
	}

	public static void insertBook(String name, String author, String edition, int dataEdition, int count)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_INSERT_BOOK);
		pstmt.setString(1, name);
		pstmt.setString(2, author);
		pstmt.setString(3, edition);
		pstmt.setInt(4, dataEdition);
		pstmt.setInt(5, count);
		pstmt.addBatch();
		pstmt.executeUpdate();
		con.close();
		pstmt.close();
	}

	public static void removeBook(String name, String author, String edition, int dataEdition, int count)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
		pstmt = con.prepareStatement(SQL_REMOVE_BOOK);
		pstmt.setString(1, name);
		pstmt.setString(2, author);
		pstmt.setString(3, edition);
		pstmt.setInt(4, dataEdition);
		pstmt.setInt(5, count);
		pstmt.addBatch();
		pstmt.executeUpdate();
		con.close();
		pstmt.close();
	}

	public static void createLibrarian(String login, String password, String firstName, String lastName)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			con = null;
			con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL_CREATE_LIBRARIAN);
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			pstmt.setString(3, firstName);
			pstmt.setString(4, lastName);
			pstmt.addBatch();
			pstmt.executeUpdate();
			con.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void removeLibrarian(String login) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			con = null;
			con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL_REMOVE_LIBRARIAN);
			pstmt.setString(1, login);
			pstmt.addBatch();
			pstmt.executeUpdate();
			con.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AccountCheck;
import model.User;

/**
 * Servlet implementation class AutoLogin
 */
@WebServlet("/AutoLogin")
public class AutoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("------AutoLogin------");
		
    	// 取得用戶名單
		ServletContext sct = getServletContext();
		HashMap<String, User> map = (HashMap<String, User>) sct.getAttribute("userHashMap");
		AccountCheck accountCheck = new AccountCheck();
		
		String accountName = "";
		String password = "";
		
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie: cookies) {
				
				String name = cookie.getName();
				String value = cookie.getValue();
				
				System.out.println(name);
				System.out.println(value);
				if ("accountName".equals(name)) {
					accountName = value;
				}
				if ("password".equals(name)) {
					password = value;
				}
			}
			if ("".equals(accountName) || "".equals(password)) {
				System.out.println("no cookie");
			} else {
				if (accountCheck.checkPassword(accountName, password, map)) {
					User user = map.get(accountName);
					request.getSession().setAttribute("user", user);
					response.sendRedirect("welcome.jsp");
					return;
				}
			}
		}
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}

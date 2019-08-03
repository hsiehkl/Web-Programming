package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import model.AccountCheck;
import model.User;
import model.ToDo;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void setCookie(String accountName, String password, HttpServletResponse response) {
		
		System.out.println("set cookie");
		Cookie acnCookie = new Cookie("accountName", accountName);
		acnCookie.setMaxAge(7 * 24 * 60 * 60);
		response.addCookie(acnCookie);
		
		Cookie pasCookie = new Cookie("password", password);
		pasCookie.setMaxAge(7 * 24 * 60 * 60);
		response.addCookie(pasCookie);
	}
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("------Login------");

		String fromPage = request.getParameter("submit");
		
		// 如果是從新增帳戶來，須先清除session再轉到login畫面
		// 若是登入頁面點擊submit按鈕，則去確認是否成功登入
		if ("送出".equals(fromPage)) {

        	// 取得用戶名單
			ServletContext sct = getServletContext();
			HashMap<String, User> map = (HashMap<String, User>) sct.getAttribute("userHashMap");
//			HashMap<String, ArrayList<ToDo>> toDoMap = (HashMap<String, ArrayList<ToDo>>) sct.getAttribute("toDoHashMap");
			AccountCheck accountCheck = new AccountCheck();

    		String accountName = request.getParameter("accountName");
    		String password = request.getParameter("password");
    		
    		// 確認是否帳號密碼皆有輸入
    		if (accountName != null && !accountName.equals("") && password != null && !password.equals("")) {
    			// 確認帳號是否存在
        		if (accountCheck.checkAccountNameExistence(accountName, map)) {
        			// 確認密碼是否正確
            		if (accountCheck.checkPassword(accountName, password, map)) {
                		System.out.println("login successfully");
                		String needAutoLogin = request.getParameter("autoLogin");
 
                		if ("auto".equals(needAutoLogin)) {
                			System.out.println(needAutoLogin);
                			setCookie(accountName, password, response);
                		}
 
                		User user = map.get(accountName);
                		request.getSession().setAttribute("user", user);
                		response.sendRedirect("welcome.jsp");
                		return;
            		} else {
            			request.setAttribute("error", "wrongPassword");
            		}
            		
        		} else {
        			request.setAttribute("error", "noAccount");
        		}
        		
    		} else {
    			request.setAttribute("error", "nullInput");
    		}
		} else {
			if (request.getSession(false) != null) {
	        	System.out.println("invalid session!");
	            request.getSession().invalidate();
	        }
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
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

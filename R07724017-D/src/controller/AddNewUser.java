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
import java.util.HashMap;
import model.User;
import model.AccountCheck;

/**
 * Servlet implementation class AddNewUser
 */
@WebServlet("/AddNewUser")
public class AddNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 取得submit參數, 看Client端是第一次呼叫, 還是填寫完資料後的呼叫
		String page = request.getParameter("submit");
		HttpSession session= request.getSession();
		System.out.println("------AddUser------");
		if ("下一頁".equals(page)) {

			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			String education= request.getParameter("education");

	        session.setAttribute("name", name);
	        session.setAttribute("address", address);
	        session.setAttribute("phoneNumber", phoneNumber);
	        session.setAttribute("education", education);
			
			// forward到addUserPage2
	        request.setAttribute("toPage", "2");
		}
		else {
			if ("送出".equals(page)){
				
				// 取得用戶輸入資料
				String accountName = request.getParameter("accountName");
				String password = request.getParameter("password");
				
				// 取得用戶名單
				ServletContext sct = getServletContext();
				HashMap<String, User> map = (HashMap<String, User>) sct.getAttribute("userHashMap");
				AccountCheck accountCheck = new AccountCheck();
				
				if (accountCheck.checkAccountNameExistence(accountName, map)) {
					request.setAttribute("isRepeated", "true");
					request.setAttribute("toPage", "2");

				} else {
					System.out.println("valid accountName");
					session.setAttribute("accountName", accountName);
					session.setAttribute("password", password);
					accountCheck.addNewUser(
							(String)session.getAttribute("name"),
							(String)session.getAttribute("address"),
							(String)session.getAttribute("phoneNumber"),
							(String)session.getAttribute("education"),
							accountName, password, map
							);
					request.setAttribute("toPage", "3");
				}
			}
			else {
				// 若Client端是第一次呼叫此Servlet程式, 準備填寫資料
			}
		}
		
		//加上後續轉發到View的程式碼
		RequestDispatcher dispatcher = request.getRequestDispatcher("addUserPage.jsp");
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

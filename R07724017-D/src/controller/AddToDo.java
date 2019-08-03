package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import model.ToDo;
import model.User;
import model.ToDoService;

/**
 * Servlet implementation class AddToDo
 */
@WebServlet("/AddToDo")
public class AddToDo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		User user = (User)request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		String accountName = user.getAccountName();
		
	    ToDo toDo = new ToDo();
		
		String dateTimeStr = request.getParameter("dateTime");
		String location = request.getParameter("location");
		String note = request.getParameter("note");

		String id = "" + System.currentTimeMillis();
		toDo.setId(id);
	    toDo.setLocation(location);
	    toDo.setNote(note);
	    ToDoService.setToDoDate(toDo, dateTimeStr);
	    
	    ServletContext sct = getServletContext();
		ToDoService.addNewToDo(accountName, toDo, sct);

	    response.sendRedirect("welcome.jsp");
	}

}

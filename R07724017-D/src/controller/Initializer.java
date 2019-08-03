package controller;

import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContext;

import model.User;
import model.ToDo;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Application Lifecycle Listener implementation class Initializer
 *
 */
@WebListener
public class Initializer implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Initializer() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.print("context destroyed!!!!");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	
    	HashMap<String, ArrayList<ToDo>> toDomap = new HashMap<String, ArrayList<ToDo>>();
    	HashMap<String, User> userMap = new HashMap<String, User>();
    	ServletContext sct = sce.getServletContext();
    	sct.setAttribute("userHashMap", userMap);
    	sct.setAttribute("toDoHashMap", toDomap);
    	System.out.print("context init!!!!");
    }
}

package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;

public class ToDoService {
	
	public static ArrayList<ToDo> getTodosByUser(ServletContext sct, String accountName) {
		HashMap<String, ArrayList<ToDo>> map = (HashMap<String, ArrayList<ToDo>>) sct.getAttribute("toDoHashMap");
		ArrayList<ToDo> toDos = map.get(accountName);
		return toDos;
	}
	
	public static void addNewToDo(String accountName, ToDo toDo, ServletContext sct) {
		
		System.out.println("in addNewToDo service");
		
		HashMap<String, ArrayList<ToDo>> map = (HashMap<String, ArrayList<ToDo>>) sct.getAttribute("toDoHashMap");
		
		ArrayList<ToDo> newToDos = new ArrayList<ToDo>();
		ArrayList<ToDo> toDos = map.get(accountName);

		if (toDos == null) {
			System.out.println("dotos is null");
			newToDos.add(toDo);
		} else {
			System.out.println("dotos has values");
			newToDos = toDos;
			newToDos = ToDoService.sortToDo(newToDos, toDo);
		}
		
		map.put(accountName, newToDos);
	}
	
	public static void deleteToDo(String accountName, String toDoId, ServletContext sct) {
		
		System.out.println("in deleteToDo service");
		
		HashMap<String, ArrayList<ToDo>> map = (HashMap<String, ArrayList<ToDo>>) sct.getAttribute("toDoHashMap");

		ArrayList<ToDo> toDos = map.get(accountName);
		
		for (int i = 0; i < toDos.size(); i++) {  
			if (toDoId.equals(toDos.get(i).getId())) {
				System.out.println("id matched");
				toDos.remove(i);
				break;
			}
		}  
		map.put(accountName, toDos);
	}
	
	public static void setToDoDate(ToDo newToDo, String dateTimeStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		try {
			Date newDate = sdf.parse(dateTimeStr);
			newToDo.setDate(newDate);
	    } catch (java.text.ParseException e) {
	        e.printStackTrace();
	        newToDo.setDate(new Date());
	    }
		newToDo.setDateTimeStr(dateTimeStr.replace("T", " "));
	}
	
	public static ArrayList<ToDo> sortToDo(ArrayList<ToDo> existingToDos, ToDo newToDo) {
		
		ArrayList<ToDo> toDos = existingToDos;
		int toDosSize = toDos.size();
		int insertIndex = toDosSize; // default to insert at the end

		if (toDosSize < 2) {
			//目前只有一筆清單，且新的時間在原有的待辦前，insert index 設為 0
			if (newToDo.getDate().before(toDos.get(0).getDate())) {
				insertIndex = 0;
			}
		} else {
			
			for (int i = 0; i < toDosSize; i++) { 
				
				// 已經比較到最後一筆資料，在此之前已確定新資料時間沒有小於最後一筆資料，insert到最後
	        	if (i == toDosSize-1) {
	        		break;
	        	} else {
			        if (newToDo.getDate().after(toDos.get(i).getDate())) {
			        	System.out.println(newToDo.getDate());
			        	System.out.println(toDos.get(i+1).getDate());
			        	if (newToDo.getDate().before(toDos.get(i+1).getDate())) {
			        		insertIndex = i + 1;
			        		break;
			        	}
			        } else {
			        	insertIndex = 0;
			        	break;
			        }
	        	}	
			}
		}
		
		toDos.add(insertIndex, newToDo);

		return toDos;
	}

}

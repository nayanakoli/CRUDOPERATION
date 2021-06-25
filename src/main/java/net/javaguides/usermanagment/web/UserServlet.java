package net.javaguides.usermanagment.web;

import java.io.IOException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.usermanagment.dao.UserDAO;
import net.javaguides.usermanagment.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
    	this.userDAO =new UserDAO();
        
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String action =request.getServletPath();
	 switch (action) {
	 case "/new":
		 showNewForm(request,response);
		 break;
	 case "/insert":
		 try {
			insertUser(request,response);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 break;
	 case "/delete":
		 try {
			deleteUser(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 break;
	 case "/edit":
		try {
			showEditForm(request,response);
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 break;
	 case "/update":
		 try {
			updateUser(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 break;
	 default:
		 try {
			listUser(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 break;
	 }
	}
	
	private void listUser(HttpServletRequest request,HttpServletResponse response)
		    throws IOException, SQLException, ServletException{
            List<User> listUser = userDAO.selectAllUsers() ;
			request.setAttribute("listUser",listUser);
			RequestDispatcher dispatcher =request.getRequestDispatcher("user-list-jsp");
			dispatcher.forward(request,response);  
			
		  }
	
	 private void updateUser(HttpServletRequest request,HttpServletResponse response)
			    throws SQLException,IOException{
		         int id=Integer.parseInt(request.getParameter("id")); 
				  String name=request.getParameter("name");
				  Date dob=Date.valueOf(request.getParameter("dob"));
				  Date doj=Date.valueOf(request.getParameter("doj"));

				  User user =new User(id,name,dob,doj);
				  userDAO.insertUser(user);
				  response.sendRedirect("list");

			  }
	
	private void deleteUser(HttpServletRequest request,HttpServletResponse response)
		    throws IOException, SQLException{
			int id =Integer.parseInt(request.getParameter("id"));
			userDAO.deleteUser(id);
			  response.sendRedirect("list");

		  }
	
	private void showEditForm(HttpServletRequest request,HttpServletResponse response)
		    throws IOException, SQLException, ServletException{
			int id =Integer.parseInt(request.getParameter("id"));
			User existingUser = userDAO.selectUser(id);
			RequestDispatcher dispatcher =request.getRequestDispatcher("user-form-jsp");
			request.setAttribute("user",existingUser);
			dispatcher.forward(request,response);  
			
		  }
				  
			  
	
  private void showNewForm(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{
	  RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
	  dispatcher.forward(request,response); 
  }
  
  private void insertUser(HttpServletRequest request,HttpServletResponse response)
		    throws SQLException,IOException{
			  String name=request.getParameter("name");
			  Date dob=Date.valueOf(request.getParameter("dob"));
			  Date doj=Date.valueOf(request.getParameter("doj"));
			  User newUser =new User(name,dob,doj);
			  userDAO.insertUser(newUser);
			  response.sendRedirect("list");

		  }
}


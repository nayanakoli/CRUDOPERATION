package net.javaguides.usermanagment.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagment.model.User;

public class UserDAO {
	
	private String jdbcURL="jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername="root";
	private String jdbcPassword="Nayana@123";
	
	private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + "(name,doj,dob) VALUES" 
	+ "(?,?,?);";
	 
	private static final String SELECT_STUDENT_BY_ID ="select id,name,dob,doj from student where id=?";
	private static final String SELECT_ALL_STUDENT ="select * from student";
    private static final String DELETE_STUDENT_SQL ="delete from student where id=?;";
    private static final String UPDATE_STUDENT_SQL="update user set name = ?,dob=?,doj=? where id=?;";
    
    protected Connection getConnection() {
    	
    	Connection connection =null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return connection;
    }
    
    public void insertUser (User user) throws SQLException {
    	try (Connection connection=getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)){
    		preparedStatement.setString(1,user.getName());
    		preparedStatement.setDate(2,(Date) user.getDob());
    		preparedStatement.setDate(3,(Date) user.getDoj());
    		preparedStatement.executeUpdate();    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public boolean updateUser(User user) throws SQLException {
    	boolean rowUpdated;
    	 try (Connection connection =getConnection();
    			 PreparedStatement statement =connection.prepareStatement(UPDATE_STUDENT_SQL);){
    		   statement.setString(1, user.getName());
    		   statement.setDate(2,(Date) user.getDob());
    		   statement.setDate(3,(Date) user.getDoj());
    		   statement.setInt(4, user.getId());
    		   rowUpdated = statement.executeUpdate() >0;
    	 }
    			 return rowUpdated;
    } 
    
    public User selectUser (int id) {
    	User user=null;
    	try(Connection connection =getConnection();
    			PreparedStatement preparedStatement =connection.prepareStatement(SELECT_STUDENT_BY_ID);){
    		preparedStatement.setInt(1, id);
    		System.out.println(preparedStatement);
    		ResultSet rs = preparedStatement.executeQuery();
    		
    		while(rs.next()) {
    			String name =rs.getString("name");
    		    Date dob =rs.getDate("dob");
    		    Date doj=rs.getDate("doj");
    		    user =new User (id,name,dob,doj);
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    			return user;
    }

    
    public List<User> selectAllUsers () {
    	List<User> users = new ArrayList<>();
    	try(Connection connection =getConnection();
    			PreparedStatement preparedStatement =connection.prepareStatement(SELECT_ALL_STUDENT);){
    		System.out.println(preparedStatement);
    		ResultSet rs = preparedStatement.executeQuery();
    		
    		while(rs.next()) {
    			int id = rs.getInt("id");
    			String name =rs.getString("name");
    		    Date dob =rs.getDate("dob");
    		    Date doj=rs.getDate("doj");
    		    users.add(new User (id,name,dob,doj));
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    			return users;
    }
    
    public boolean deleteUser(int id) throws SQLException{
    	boolean rowDeleted;
    	try(Connection connection = getConnection();
    			PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);){
    		statement.setInt(1, id);
    		rowDeleted = statement.executeUpdate() > 0;
    	}
    	return rowDeleted;
    }
}























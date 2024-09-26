package com.uniqueDeveloper.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registrationServlet")
public class registrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String confirmPassword = request.getParameter("re_pass");
        String contact = request.getParameter("contact");
        
        String jdbcURL = "jdbc:mysql://localhost:3306/youtube?useSSL=false"; 
        String jdbcUsername = "root"; 
        String jdbcPassword = "Lingumysql@45"; 
        RequestDispatcher dispatcher = null;

        Connection connection = null;
		
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			String sql = "INSERT INTO users (name,email,pwd,cpwd,contact) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pre;
			try {
				pre = connection.prepareStatement(sql);
				pre.setString(1, name);  
	            pre.setString(2, email);  
	            pre.setString(3, password); 
	            pre.setString(4, confirmPassword);
	            pre.setString(5, contact);  
	            
	            int count=pre.executeUpdate();
	            dispatcher = request.getRequestDispatcher("registration.jsp");
	            if(count>0) {
	            	request.setAttribute("status", "success");
	            }
	            else {
	            	request.setAttribute("status", "failed");
	            }
	            dispatcher.forward(request, response);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}

            
            
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
        finally {
        	try {
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
        }

        
        
        
		
		
	}

}

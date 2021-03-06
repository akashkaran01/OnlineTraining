package com.lab.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class TrainingData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ONLINETRAINING","password");
			Statement stmt=con.createStatement();
			String sql="select * from training";
			ResultSet rs=stmt.executeQuery(sql);
			out.print("<table border=1>");
			out.print("<tr>");
			out.print("<td>Training Id</td>"); 
			out.print("<td>Training Name</td>"); 
			out.print("<td>Avalaible Seats</td>"); 
			out.print("<td>Enroll in training</td>"); 
			out.print("</tr>");
			while(rs.next())
			{ 
				out.print("<tr>");
				out.print("<td>"+rs.getInt("trainingId")+"</td>");
				out.print("<td>"+rs.getString("trainingName")+"</td>");
				out.print("<td>"+rs.getInt("AvailableSeats")+"</td>");
				out.print("<td><a href='Enrollment?trainid="+rs.getInt("trainingId")+"&availseats="+rs.getInt("AvailableSeats")+"&trainName="+rs.getString("trainingName")+"'>Enroll</a></td>");
				out.print("</tr>");
			}
			con.close();
			stmt.close();
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

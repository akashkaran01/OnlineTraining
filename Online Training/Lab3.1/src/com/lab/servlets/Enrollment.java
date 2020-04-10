package com.lab.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Enrollment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int trainingid =Integer.parseInt(request.getParameter("trainid"));
		int availableseats=Integer.parseInt(request.getParameter("availseats"));
		String trainingname= request.getParameter("trainName");
		PrintWriter out = response.getWriter();
		if(availableseats==0) 
		{
			out.print("<h1>Sorry ! No Seats Are Available.</h1>");
			out.print("</br><hr><a href='index.html'>BACK TO HOME</a>");
		}
		else{
			try {
				
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ONLINETRAINING","password");
				Statement stmt=con.createStatement();
				String sql="update training set AvailableSeats="+(availableseats-1)+" where trainingId="+trainingid;
				ResultSet rs=stmt.executeQuery(sql);
				con.close();
				stmt.close();
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<h1>Hi you have successfully enrolled for "+trainingname+" Training.</h1>");
			
		}
		
	}

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

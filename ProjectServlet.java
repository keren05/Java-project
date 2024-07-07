package projmgmt;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProjectServlet")

public class ProjectServlet extends HttpServlet {
	private final static String query="insert into project(name,subject,rollno,projectDescription) values(?,?,?,?)";


	 private static final long serialVersionUID = 1L;
@Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	doGet(request,response);
	       
	        PrintWriter pw=response.getWriter();
	        response.setContentType("text/html");
	        
	        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
	        
	        String name=request.getParameter("projName");
	        String subject=request.getParameter("subject");
	        String roll=request.getParameter("rollno");
	        String description=request.getParameter("projectDescription");
	        //JDBC DRIVER
	        try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	       
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        try{Connection con=DriverManager.getConnection("jdbc:mysql:///projmgmt","root","root");
	        PreparedStatement ps=con.prepareStatement(query);
	       
	  
	   {
		   ps.setString(1, name);
		   ps.setString(2, subject);
		   ps.setString(3,roll);
		   ps.setString(4,description);
		   int count=ps.executeUpdate();
		   pw.println(" <div class='card' style='margin:auto;width:300px;margin-top:100px'>");
		   if(count==1) {
			   pw.println("<h2 class='bg-danger text-light text-center'>Record Registered successfully</h2>");
		   }
		   else {
			   pw.println("<h2 class='bg-danger text-light text-center'>Record not registered</h2>");
		   }
	   }
	        }catch(SQLException se) {
	    	pw.println("<h2 class='bg-danger text-light text-center>"+se.getMessage()+"</h2>");
	    	se.printStackTrace();
	    }catch(Exception e) {
	    	
	    	e.printStackTrace();
	    }
	        pw.println("</div>");
	        pw.close();
}
@Override 
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	       doGet(request,response);
	    }
}

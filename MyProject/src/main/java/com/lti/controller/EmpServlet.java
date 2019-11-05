package com.lti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpServlet
 */
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
        InputStream is=getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        prop.load(is);
        String driver=(String)prop.get("db.driver");
        String url=(String)prop.get("db.url");
        String user=(String)prop.get("db.user");
        String pass=(String)prop.get("db.pass");
		String eid=request.getParameter("txtEmpId");  
		String ename = request.getParameter("txtEmpName");	
		try{  
				Class.forName(driver);  
				Connection con=DriverManager.getConnection(url,user,pass);
				System.out.println("connection is established");
				PreparedStatement ps=con.prepareStatement("insert into Employee values(?,?)");  
			
			
				ps.setString(1,eid); 
				ps.setString(2, ename);
	            ps.executeUpdate();
	     
				PreparedStatement pp=con.prepareStatement("select * from Employee");
				ResultSet rs=pp.executeQuery();
				
				
			              
			}catch (Exception e2) {e2.printStackTrace();}  
			          
			finally{out.close();}  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

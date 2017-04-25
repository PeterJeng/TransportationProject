package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

@WebServlet("/QueryPageController")
public class QueryPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public QueryPageController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection con = null;
		
			try{
			
			String queryText = request.getParameter("queryText");
			
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(queryText);
			
			Result result = ResultSupport.toResult(rs);
			
			request.setAttribute("result", result);
			
			RequestDispatcher rd = request.getRequestDispatcher("/QueryPage.jsp");
			
			rd.forward(request,response);
			
			con.close();
			
			} catch(SQLException | ClassNotFoundException ex) {
				throw new ServletException(ex);
			} //finally {
				//con.close();
			//}
	}
} 


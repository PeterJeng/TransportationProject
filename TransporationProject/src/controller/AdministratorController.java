package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AdministratorController")
public class AdministratorController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
	public AdministratorController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		String action = request.getParameter("action");
		
		if("Create System Support Staff Account".equals(action)){
			rd = request.getRequestDispatcher("/CreateStaffAccount.jsp");
		}
		else if ("Run Query On Statistics".equals(action)){
			rd = request.getRequestDispatcher("/QueryPage.jsp");
		}

		rd.forward(request, response);
	}
}

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SystemSupportStaffController")
public class SystemSupportStaffController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
	public SystemSupportStaffController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		String action = request.getParameter("action");
		
		if("Reset User Password".equals(action)){
			rd = request.getRequestDispatcher("/ManualPasswordReset.jsp");
		}
		else if ("Lock Out User".equals(action)){
			rd = request.getRequestDispatcher("/LockOutUser.jsp");
		}
		else if ("Add/Remove Advertisement".equals(action)){
			rd = request.getRequestDispatcher("/AdvertisementPage.jsp");
		}
		
		rd.forward(request, response);
	}
}

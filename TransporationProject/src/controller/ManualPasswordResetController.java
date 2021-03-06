package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Authenticator;

@WebServlet("/ManualPasswordResetController")
public class ManualPasswordResetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public ManualPasswordResetController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");

 
		Authenticator authenticator = new Authenticator();
		String result = authenticator.manualPasswordResetAuthenticator(username);
		if(result.equals("fail")){
			getServletContext().getRequestDispatcher("/Errors/ResetPasswordError/IdentificationError.jsp").forward(request, response);
		}
		else if (result.equals("success")) {
			getServletContext().getRequestDispatcher("/SystemSupportStaffPage.jsp").forward(request, response);
		} 
	}
}

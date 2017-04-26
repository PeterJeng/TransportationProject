package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Authenticator;

@WebServlet("/ResetPasswordController")
public class ResetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public ResetPasswordController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String RUID = request.getParameter("RUID");

 
		Authenticator authenticator = new Authenticator();
		String result = authenticator.passwordResetAuthenticator(username, RUID);
		if(result.equals("fail")){
			getServletContext().getRequestDispatcher("/Errors/ResetPasswordError/IdentificationError.jsp").forward(request, response);
		}
		else if (result.equals("success")) {
			getServletContext().getRequestDispatcher("/LoginPage.jsp").forward(request, response);
		} 
	}
}

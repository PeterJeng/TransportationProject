package controller;

import java.io.IOException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Authenticator;

@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
	public ForgotPasswordController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String RUID = request.getParameter("ruid");
 
		Authenticator authenticator = new Authenticator();
		String result = authenticator.resetPasswordAuthenticator(username, RUID);

		
		if(result.equals("success")){
			getServletContext().getRequestDispatcher("....jsp").forward(request, response);
		}
		else{
			getServletContext().getRequestDispatcher("/Errors/ResetPasswordError/IdentificationError.jsp").forward(request, response);
		}
		
	}
	
}

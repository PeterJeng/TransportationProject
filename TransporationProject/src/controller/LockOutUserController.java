package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Authenticator;

@WebServlet("/LockOutUserController")
public class LockOutUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public LockOutUserController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String comment = request.getParameter("comments");
		String action = request.getParameter("action");
 
		if("Lock User Account".equals(action)){
			Authenticator authenticator = new Authenticator();
			String result = authenticator.userLockAuthenticator(username, comment);
			if(result.equals("fail")){
				//getServletContext().getRequestDispatcher("/Errors/CreateAccountError/MissingField.jsp").forward(request, response);
				System.out.println("User not found");
			}
			else if (result.equals("success")) {
				//getServletContext().getRequestDispatcher("/Errors/CreateAccountError/IncorrectPassword.jsp").forward(request, response);
				System.out.println("Lock successful");
			} 
		}
		else if("Unlock User Account".equals(action)){
			Authenticator authenticator = new Authenticator();
			String result = authenticator.userLockAuthenticator(username, comment);
			if(result.equals("fail")){
				//getServletContext().getRequestDispatcher("/Errors/CreateAccountError/MissingField.jsp").forward(request, response);
				System.out.println("User not found");
			}
			else if (result.equals("success")) {
				//getServletContext().getRequestDispatcher("/Errors/CreateAccountError/IncorrectPassword.jsp").forward(request, response);
				System.out.println("Unlock successful");
			}
		}
		
	}
}

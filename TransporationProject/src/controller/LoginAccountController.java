package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Authenticator;
import model.User;
import sun.text.normalizer.ICUBinary.Authenticate;

@WebServlet("/LoginAccountController")
public class LoginAccountController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
	public LoginAccountController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Authenticator authenticator = new Authenticator();
		String result = authenticator.loginAuthenticator(username, password);
		
		RequestDispatcher rd = null;
		
		if (result.equals("success")) {
			User user = new User(username, password);
			request.setAttribute("user", user);
			rd = request.getRequestDispatcher("/AccountMainPage.jsp");
		} 
		else{
			System.out.println("failed to login");
		}
		
		rd.forward(request, response);
	}
}

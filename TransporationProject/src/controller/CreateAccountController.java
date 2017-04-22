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


@WebServlet("/CreateAccountController")
public class CreateAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public CreateAccountController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String RUID = request.getParameter("ruid");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		System.out.println(password);
		System.out.println(confirmPassword);
 
		Authenticator authenticator = new Authenticator();
		String result = authenticator.createAccount(username, password, confirmPassword, RUID, email, address);
		if(result.equals("missing fields")){
			System.out.println("Missing required data");
		}
		else if (result.equals("password does not match")) {
			System.out.println("password does not match");
			//rd = request.getRequestDispatcher("/success.jsp");
		} 
		else if(result.equals("duplicate username")){
			//rd = request.getRequestDispatcher("/error.jsp");
			System.out.println("duplicate username");
		}
		else{
			System.out.println("success");
		}
		//rd.forward(request, response);
	}
}

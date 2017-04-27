package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Authenticator;
import model.User;

import sun.text.normalizer.ICUBinary.Authenticate;


@WebServlet("/EditInformationController")
public class EditInformationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public EditInformationController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession userSession = request.getSession();
		String username = (String) userSession.getAttribute("username");
		
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
 
		Authenticator authenticator = new Authenticator();
		String result = authenticator.updateAccount(username, password, confirmPassword, email, address, fname, lname);
		if(result.equals("missing fields")){
			getServletContext().getRequestDispatcher("/Errors/CreateAccountError/MissingField.jsp").forward(request, response);
		}
		else if (result.equals("password does not match")) {
			getServletContext().getRequestDispatcher("/Errors/CreateAccountError/IncorrectPassword.jsp").forward(request, response);
		
		}
		else if(result.equals("success")){
			getServletContext().getRequestDispatcher("/LoginPage.jsp").forward(request, response);
		}
		else{
			getServletContext().getRequestDispatcher("/Errors/CreateAccountError/GenericError.jsp").forward(request, response);
			
		}
		
	}
}

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.ResetPassword;

@WebServlet("/ResetPasswordController")
public class ResetPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ResetPasswordController(){
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");

		HttpSession userSession = request.getSession();
		String username = (String) userSession.getAttribute("passwordResetUsername");
		String RUID = (String) userSession.getAttribute("passwordResetRUID");

		if(password.isEmpty()){
			//link to jsp
			getServletContext().getRequestDispatcher("/Errors/ResetPasswordError/EmptyPasswordError.jsp").forward(request, response);
		}
		else{
			ResetPassword temp = new ResetPassword();
			String result = temp.resetPassword(username, RUID, password);
			if(result.equals("success")){
				getServletContext().getRequestDispatcher("/LoginPage.jsp").forward(request, response);
			}
			else{
				System.out.println("fail");
			}
			
			
		}
	
		
	}
}

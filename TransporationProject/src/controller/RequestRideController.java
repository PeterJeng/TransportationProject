package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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


@WebServlet("/RequestRideController")
public class RequestRideController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public RequestRideController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	//	boolean hello = false;
		RequestDispatcher rd = null;
		String departure = request.getParameter("departure");
		String destination = request.getParameter("destination");
		String time = request.getParameter("time");
		String location = request.getParameter("location");
		
		HttpSession userSession = request.getSession();
		String username = (String) userSession.getAttribute("username");
		System.out.println(username);
		Authenticator authenticator = new Authenticator();
		String result = authenticator.requestridetable(username, departure, destination, time, location);
		if(result.equals("missing fields")){
			
			//need to change to popup window
			System.out.println("missing required data");
		}
		
		else if(result.equals("You can only request one ride!")){
			//need to change
			System.out.println("You can only request one ride!");
		}
		else{
			System.out.println("success!");
			userSession.setAttribute("RequestersDestination", destination);
			//hello = true;
			rd = request.getRequestDispatcher("/AvailableRides.jsp");
			rd.forward(request, response);
		}
		
		}
		}
		
		
	

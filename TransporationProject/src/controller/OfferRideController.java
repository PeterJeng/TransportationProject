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


@WebServlet("/OfferRideController")
public class OfferRideController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public OfferRideController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String departure = request.getParameter("departure");
		String destination = request.getParameter("destination");
		String time = request.getParameter("time");
		String seats = request.getParameter("seats");
		String location = request.getParameter("location");
		String additionalinformation = request.getParameter("additionalinformation");
		
		HttpSession userSession = request.getSession();
		String username = (String) userSession.getAttribute("username");
		System.out.println(username);
		Authenticator authenticator = new Authenticator();
		String result = authenticator.offerridetable(username, departure, destination, time, seats, location, additionalinformation);
		if(result.equals("missing fields")){
			rd = request.getRequestDispatcher("/Errors/RideErrors/MissingDataError.jsp");
			rd.forward(request, response);
		}
		
		else if(result.equals("You can only offer one ride!")){
			rd = request.getRequestDispatcher("/Errors/RideErrors/OneRideError.jsp");
			rd.forward(request, response);
		}
		else{
			System.out.println("success!");
			rd = request.getRequestDispatcher("/OfferingRides.jsp");
			rd.forward(request, response);
		}
		}
		
		
	}
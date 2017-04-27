package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Advertisement;

@WebServlet("/SelectAdvertisementController")
public class SelectAdvertisementController extends HttpServlet{
    private static final long serialVersionUID = 1L;


        public SelectAdvertisementController(){
            super();
        }
        
        
        protected void doPost(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException {
            
            String photoName = request.getParameter("photoName");
                    
            
            Advertisement ad = new Advertisement();
            String result = ad.selectAdToDisplay(photoName);
                
            if(result.equals("success")){
                getServletContext().getRequestDispatcher("/AdvertisementRedirect.jsp").forward(request, response);
            }
            else{
                getServletContext().getRequestDispatcher("/Errors/AdError/FileDNE.jsp").forward(request, response);
            }
            
            
        }
        
    }
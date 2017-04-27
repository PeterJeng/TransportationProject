package controller;


import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Advertisement;




@WebServlet("/UploadAdvertisementController")
@MultipartConfig(maxFileSize = 16177215)
public class UploadAdvertisementController extends HttpServlet{
    
    private static final long serialVersionUID = 1L;

    public UploadAdvertisementController(){
        super();
    }
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String photoName = request.getParameter("photoName");
                
        Part filePart = request.getPart("photo");
        
        InputStream inputStream = null;
        
        //check to see if a photo file has been uploaded
        if(filePart != null){
            //puts file into input stream
            inputStream = filePart.getInputStream();
            Advertisement ad = new Advertisement();
            String result = ad.saveAdToDatabase(photoName, inputStream);
            
            if(result.equals("success")){
                getServletContext().getRequestDispatcher("/AdvertisementRedirect.jsp").forward(request, response);
            }
            else{
                
            }
        }
        
        
    }
}
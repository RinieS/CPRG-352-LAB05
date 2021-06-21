
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author 840979
 */
public class LoginServlet extends HttpServlet {

  
    AccountService service = new AccountService();
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
       // String userName = (String)session.getAttribute("usernameEnetered");
        User user = (User)session.getAttribute("user");
        String logout = request.getParameter("logout");
        //check if 'Log out' link has been clicked
       
        if (logout != null){
            //if clicked -end session - invalidate 
            session.invalidate();
            request.setAttribute("msg","You have logged out succesfully");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
       
        //check is user logged in
        else if (user != null){
            
        response.sendRedirect("home");
        }
        else{
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        }
    }  

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userName = request.getParameter("usernameEntered");
        String userPassword = request.getParameter("passwordEntered");
        String name ;
        String pass ;
        
        if( userName != null || userPassword != null){
           name = userName;
           pass = userPassword;
        }
        else{
             request.setAttribute("msg", "Invalid username or password..");
              getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
      return;
        }
       
      
    // passing the user name and password parameters to the login() method of a service class AccountService
    
       User user = service.login(name, pass);
        
       // validate form entry
        if(user != null){
         session.setAttribute("userName", userName);
           session.setAttribute("user", user);
           response.sendRedirect("home");
           
            }
            else {
           request.setAttribute("msg", "Invalid username or password.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
      return;
       }
           
        
    }
   
    
   
}

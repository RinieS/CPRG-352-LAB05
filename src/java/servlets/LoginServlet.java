
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
        String userName = (String)session.getAttribute("usernameEnetered");
        
        String logout = request.getParameter("logout");
        //check if 'Log out' link has been clicked
        if (logout == null){
            logout = "";
        }
        if (logout.equals("true")){
            //if clicked -end session - invalidate 
            session.invalidate();
            request.setAttribute("msg","You have logged out succesfully");
        }
        else {
            response.sendRedirect("/home");
        }
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
            }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userName = request.getParameter("usernameEntered");
        String userPassword = request.getParameter("passwordEntered");
        
        //validate form entry
        if(userName == null || userPassword == null || userName.equals("") || userPassword.equals("")){
            request.setAttribute("msg", "Enter username and password.");
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
      return;
        }
      // passing the user name and password parameters to the login() method of a service class AccountService
       User user = service.login(userName, userPassword);
       //validate
       if (user == null){
           request.setAttribute("msg", "Invalid username or password.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
      return;
       }
       
       else {
           session.setAttribute("usernameEnetered", userName);
           response.sendRedirect("home");
           
       }
    }
   
    
   
}

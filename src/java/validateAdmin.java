
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author muskan
 */
public class validateAdmin extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 

        //fetch username and password from form
        String username = request.getParameter("admin_username");
        String password = request.getParameter("admin_password");

        // set of admin usernames and passwords
        String u1 = "user1";
        String p1 = "pass1";

        String u2 = "user2";
        String p2 = "pass2";

        String u3 = "user3";
        String p3 = "pass3";

        String u4 = "user4";
        String p4 = "pass4";

        //checking if admin username and password match with presets

        if(username.equals(u1) && password.equals(p1)){
            RequestDispatcher rd = request.getRequestDispatcher("EventManagement.html");
            rd.forward(request, response);
        }else if(username.equals(u2) && password.equals(p2)){
            RequestDispatcher rd = request.getRequestDispatcher("EventManagement.html");
            rd.forward(request, response);
        }else if(username.equals(u3) && password.equals(p3)){
            RequestDispatcher rd = request.getRequestDispatcher("EventManagement.html");
            rd.forward(request, response);
        }else if(username.equals(u4) && password.equals(p4)){
            RequestDispatcher rd = request.getRequestDispatcher("EventManagement.html");
            rd.forward(request, response);
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("AdminLogin.html");
            rd.include(request, response);
            out.println("<center><h2>Invalid credentials!! Please enter valid username and password to continue..</h2></center>");
        }
    }

}

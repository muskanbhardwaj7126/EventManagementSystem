
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author muskan
 */
public class StoreParticipantDetails extends HttpServlet {

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

        //The HttpServletRequest object can be used to retrieve incoming HTTP request headers and form data. The HttpServletResponse object can be used to set the HTTP response headers (e.g., content-type) and the response message body.
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        //fetching inputs of user from the form in Signup.html
        String name = request.getParameter("participant_name");
        String username = request.getParameter("participant_username");
        String password = request.getParameter("participant_password");
        String cpassword = request.getParameter("participant_confirmPassword");

        //checking if password matches confirm password
        if(!password.equals(cpassword)){
            out.println("<h3><center>Passwords do not match!! Kindly Enter same passwords...</center></h3>");
            RequestDispatcher rd = request.getRequestDispatcher("Signup.html");
            rd.include(request, response);
        }else{
//storing form data from Signup.html to database
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String conURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
                String db_username = "system";
                String db_password = "Muskan12345";

                //establishing connection to jdbc
                Connection con = DriverManager.getConnection(conURL, db_username, db_password);

                //Creates a Statement object for sending SQL statements to the database. SQL statements without parameters are normally executed using Statement objects.
                Statement statement = con.createStatement();

                String st = "Insert into Plogindetails values('" + username + "', '" + password + "', '" + name + "')";
                ResultSet rs = statement.executeQuery(st);

                RequestDispatcher rd = request.getRequestDispatcher("ParticipantLogin.html");
                rd.forward(request, response);

                con.close();


            }catch(Exception e){
                out.println(e);
            }
        }
    }

}

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author muskan
 */
public class validateParticipant extends HttpServlet {

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

        //fetching data from ParticipantLogin.html form
        String username = request.getParameter("participantUsername");
        String password = request.getParameter("participantPassword");

        if(validate(username, password, out)){
            RequestDispatcher rd = request.getRequestDispatcher("ParticipantEvent.html");
            rd.forward(request, response);
        }else{
            out.println("<h2><center>Invalid credentials!! Please login with valid participant username and password...</center></h2>");
            RequestDispatcher rd = request.getRequestDispatcher("ParticipantLogin.html");
            rd.include(request, response);
        }
        out.close();

    }

    private boolean validate(String username, String password, PrintWriter out){
        boolean status = false;
        try{

            //establishing connection with database
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String conURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
            Connection con = DriverManager.getConnection(conURL, "system", "Muskan12345");

            PreparedStatement st = con.prepareStatement("Select * from plogindetails where user_name = ? and pass_word = ? ");
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            //Moves the cursor forward one row from its current position. A ResultSet cursor is initially positioned before the first row; the first call to the method next makes the first row the current row; the second call makes the second row the current row, and so on.
            //When a call to the next method returns false, the cursor is positioned after the last row
            status = rs.next();
            con.close();

        }catch(Exception e){
            out.println(e);
        }
       return status;

    }

}

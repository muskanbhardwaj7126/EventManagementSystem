

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
 * @author muska
 */
public class CreateEvent extends HttpServlet {

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

            PrintWriter out = response.getWriter();
            response.setContentType("text,html");
            String ename = request.getParameter("eventName");
            String eno = request.getParameter("eventNumber");
            String coordname = request.getParameter("coordName");
            String edate = request.getParameter("date");

            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String conURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
                Connection con = DriverManager.getConnection(conURL, "system", "Muskan12345");
                Statement statement = con.createStatement();
                String st = "Insert into eventdetails values ('" + ename + "', '" + eno + "', '" + coordname + "', '" + edate + "')";
                ResultSet rs = statement.executeQuery(st);
                RequestDispatcher rd = request.getRequestDispatcher("CreateEvent.html");
                rd.include(request, response);
                
                con.close();

            }catch(Exception e){
                out.println(e);
            }
    }

}


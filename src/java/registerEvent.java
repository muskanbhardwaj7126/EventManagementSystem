

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
public class registerEvent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String ename = request.getParameter("eventname");
        String eno = request.getParameter("eventno");

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String conURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
            Connection con = DriverManager.getConnection(conURL, "system", "Muskan12345");
            PreparedStatement ps = con.prepareStatement("Select * from eventdetails where eventno = ? and eventname = ?");
            ps.setString(1,eno);
            ps.setString(2,ename);
            ResultSet rs = ps.executeQuery();
            RequestDispatcher rd = request.getRequestDispatcher("RegisterEvent.html");
            rd.include(request, response);
            if(rs.next()){
                out.println("<center><h3>Registered Successfully!!<h3></center>");

            }else{
                out.println("<center><h2>Please enter valid Event name and Event Number</h2></center>");
            }
        }catch(Exception e){
            out.println(e);
        }
    }

}

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author muskan
 */

public class ViewEvent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>View Events</title>");
        out.println("<link href=\"style.css\" rel=\"stylesheet\">"); //\is used as deliminiter to exclude the actual emaning of " 
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<h1>Events Available</h1>");
        out.println("<div class=\"box\">");

        out.println("<table>");

        out.println("<tr> <th>Event Number</th> <th>Event Name</th> <th>Coordinator Name</th> <th>Date of Event</th> </tr>");
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String conURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
            Connection con = DriverManager.getConnection(conURL, "system", "Muskan12345");
            String st = "Select * from EventDetails";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(st);
            while(rs.next()){
                String ename = rs.getString(1);
                String eno = rs.getString(2);
                String coordname = rs.getString(3);
                String date = rs.getString(4);
                out.println("<tr> <td>" + eno + "</td> <td>" + ename + "</td> <td>" + coordname + "</td> <td>" + date + "</td> </tr>");
            }


        }catch(Exception e){
            out.println(e);
        }
        out.println("</table>");
        out.println("<br>");
        out.println("<br>");
//        out.println("<a href=\"Events.html\">");
//        out.println("<button>Back</button>");
//        out.println("</a>");
        out.println("</div>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }

}

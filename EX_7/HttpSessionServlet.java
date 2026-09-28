import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HttpSessionServlet")
public class HttpSessionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the existing session without creating a new one (false parameter)
        HttpSession session = request.getSession(false);
        String userName = null;

        if (session != null) {
            userName = (String) session.getAttribute("user");
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>HttpSession Result</title>");
        out.println("<style>body{font-family:Arial;margin:60px;background:#f4f6f8;}");
        out.println(".box{background:#fff;padding:25px 35px;border-radius:8px;");
        out.println("box-shadow:0 0 10px rgba(0,0,0,0.15);max-width:400px;margin:auto;text-align:center;}");
        out.println("</style></head><body>");
        out.println("<div class='box'>");
        
        if (userName != null) {
            out.println("<h2>Hello, " + userName + "</h2>");
            out.println("<p>(Retrieved directly from the server-side <b>HttpSession</b> object using Session ID tracking.)</p>");
            out.println("<p>Session ID: " + session.getId() + "</p>");
        } else {
            out.println("<h2>No Active Session Found</h2>");
            out.println("<p>Please log in through the home page first.</p>");
        }
        
        out.println("</div></body></html>");
    }
}
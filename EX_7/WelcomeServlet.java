import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("uname");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        boolean isValid = name != null && !name.trim().isEmpty() && 
                          password != null && !password.trim().isEmpty();

        out.println("<!DOCTYPE html><html><head><title>Welcome</title>");
        out.println("<style>body{font-family:Arial;margin:60px;background:#f4f6f8;}");
        out.println(".box{background:#fff;padding:25px 35px;border-radius:8px;");
        out.println("box-shadow:0 0 10px rgba(0,0,0,0.15);max-width:450px;margin:auto;}");
        out.println("a{display:block;margin:10px 0;color:#2c3e50;font-weight:bold;}");
        out.println("input[type='submit'] { background-color: #2c3e50; color: #fff; border: none; padding: 8px 15px; border-radius: 5px; cursor: pointer; }");
        out.println("</style></head><body>");
        out.println("<div class='box'>");

        if (!isValid) {
            out.println("<h2 style='color:red;'>Validation Failed</h2>");
            out.println("<p>Name and Password cannot be empty.</p>");
            out.println("<a href='index.html'>Back to Login</a>");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", name);

            Integer visitorCount = (Integer) getServletContext().getAttribute("visitorCount");
            if (visitorCount == null) visitorCount = 0;

            out.println("<h2>Welcome, " + name + "</h2>");
            out.println("<p><b>Total Unique Visitors So Far: " + visitorCount + "</b></p>");

            // ---- Option 1: Hidden Form Field ----
            out.println("<h3>1. Hidden Form Field Demo</h3>");
            out.println("<form action='HiddenFieldServlet' method='post'>");
            out.println("<input type='hidden' name='hf' value='" + name + "'>");
            out.println("<input type='submit' value='go (Hidden Field)'>");
            out.println("</form>");

            // ---- Option 2: URL Rewriting ----
            out.println("<h3>2. URL Rewriting Demo</h3>");
            out.println("<a href='URLRewriteServlet?uname=" + name + "'>visit (URL Rewriting)</a>");

            // ---- Option 3: Cookies ----
            out.println("<h3>3. Cookie Demo</h3>");
            out.println("<a href='CookieServlet?uname=" + name + "'>visit (Cookies)</a>");

            // ---- Option 4: HttpSession Object ----
            out.println("<h3>4. HttpSession Demo</h3>");
            out.println("<a href='HttpSessionServlet'>visit (HttpSession Object)</a>");
        }

        out.println("</div></body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("Please log in on the home page first.");
    }
}
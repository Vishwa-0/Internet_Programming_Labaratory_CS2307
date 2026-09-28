import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieServlet")
public class CookiesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = "Guest";
        boolean cookieFound = false;

        // Retrieve all cookies sent by the browser
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userCookie".equals(cookie.getName())) {
                    userName = cookie.getValue();
                    cookieFound = true;
                    break;
                }
            }
        }

        // If cookie doesn't exist yet, create and send it to the browser
        if (!cookieFound) {
            String nameParam = request.getParameter("uname");
            if (nameParam != null && !nameParam.isEmpty()) {
                userName = nameParam;
            }
            Cookie newCookie = new Cookie("userCookie", userName);
            newCookie.setMaxAge(60 * 60 * 24); // Active for 1 day
            response.addCookie(newCookie);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Cookie Tracking Result</title>");
        out.println("<style>body{font-family:Arial;margin:60px;background:#f4f6f8;}");
        out.println(".box{background:#fff;padding:25px 35px;border-radius:8px;");
        out.println("box-shadow:0 0 10px rgba(0,0,0,0.15);max-width:400px;margin:auto;text-align:center;}");
        out.println("</style></head><body>");
        out.println("<div class='box'>");
        out.println("<h2>Hello, " + userName + "</h2>");
        out.println("<p>(This value was retrieved using an HTTP Cookie stored securely in your browser.)</p>");
        out.println("</div></body></html>");
    }
}
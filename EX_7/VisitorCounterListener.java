import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class VisitorCounterListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(">>> SESSION CREATED TRIGGERED! <<<");
        ServletContext context = se.getSession().getServletContext();

        Integer count = (Integer) context.getAttribute("visitorCount");
        if (count == null) {
            count = 0;
        }
        count = count + 1;

        context.setAttribute("visitorCount", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // No action needed
    }
}
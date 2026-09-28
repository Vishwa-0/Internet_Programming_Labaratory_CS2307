import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {

    // Define the question texts and correct option indices (0-3)
    private static final String[] QUESTIONS = {
        "1. What does HTML stand for?",
        "2. Which HTML element is used for the largest heading?",
        "3. What is the correct HTML element for inserting a line break?",
        "4. Which property is used to change the background color in CSS?",
        "5. How do you select an element with id 'demo' in CSS?",
        "6. Which CSS property controls the text size?",
        "7. Which HTML attribute specifies an alternative text for an image, if the image cannot be displayed?",
        "8. Which CSS property is used to change the text color of an element?",
        "9. How do you insert a comment in a CSS file?",
        "10. Which HTML tag is used to define an internal style sheet?"
    };

    // Correct option indices corresponding to the options array
    private static final int[] CORRECT_ANSWERS = {0, 2, 2, 1, 1, 2, 2, 2, 1, 2};

    // Full text options for display in the results table
    private static final String[][] OPTIONS_TEXT = {
        {"Hyper Text Markup Language", "Home Tool Markup Language", "Hyperlinks and Text Markup Language", "Hyperlinking Text Management Language"},
        {"<h6>", "<head>", "<h1>", "<heading>"},
        {"<lb>", "<break>", "<br>", "<hr>"},
        {"color", "background-color", "bgcolor", "canvas-color"},
        {".demo", "#demo", "*demo", "demo"},
        {"font-style", "text-size", "font-size", "text-style"},
        {"title", "src", "alt", "longdesc"},
        {"fgcolor", "text-color", "color", "font-color"},
        {"// this is a comment", "/* this is a comment */", "<!-- this is a comment -->", "' this is a comment"},
        {"<css>", "<script>", "<style>", "<design>"}
    };

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Optional: If you collect a student name field, retrieve it here (e.g., studentName)
        String studentName = request.getParameter("studentName");
        if (studentName == null || studentName.trim().isEmpty()) {
            studentName = "Candidate";
        }

        int score = 0;
        int totalQuestions = QUESTIONS.length;
        boolean[] results = new boolean[totalQuestions];
        int[] userChoices = new int[totalQuestions];

        // 1. Evaluate answers
        for (int i = 0; i < totalQuestions; i++) {
            String paramVal = request.getParameter("question_" + i);
            if (paramVal != null && !paramVal.trim().isEmpty()) {
                try {
                    userChoices[i] = Integer.parseInt(paramVal);
                } catch (NumberFormatException e) {
                    userChoices[i] = -1; // Invalid choice fallback
                }
            } else {
                userChoices[i] = -1; // Unanswered
            }

            if (userChoices[i] == CORRECT_ANSWERS[i]) {
                results[i] = true;
                score++;
            } else {
                results[i] = false;
            }
        }

        // 2. Build the result page
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Quiz Result</title>");
        out.println("<style>");
        out.println("body{font-family:Arial,sans-serif;background:#f4f6f8;padding:30px;}");
        out.println(".box{max-width:750px;margin:auto;background:#fff;padding:25px 30px;"
                + "border-radius:8px;box-shadow:0 2px 8px rgba(0,0,0,0.15);}");
        out.println("h2{color:#2c3e50;text-align:center;}");
        out.println("table{width:100%;border-collapse:collapse;margin-top:15px;}");
        out.println("td,th{border:1px solid #ddd;padding:8px;text-align:left;font-size:14px;}");
        out.println("th{background:#2c3e50;color:#fff;}");
        out.println(".correct{color:green;font-weight:bold;}");
        out.println(".wrong{color:red;font-weight:bold;}");
        out.println(".score{font-size:20px;text-align:center;margin-top:20px;color:#2c3e50;font-weight:bold;}");
        out.println("</style></head><body>");

        out.println("<div class='box'>");
        out.println("<h2>HTML & CSS Assessment Result</h2>");
        out.println("<p><b>Candidate:</b> " + escape(studentName) + "</p>");

        out.println("<table>");
        out.println("<tr><th>Question</th><th>Your Answer</th><th>Correct Answer</th><th>Result</th></tr>");

        for (int i = 0; i < totalQuestions; i++) {
            String userAnsStr = (userChoices[i] >= 0 && userChoices[i] < 4) ? OPTIONS_TEXT[i][userChoices[i]] : "No Answer";
            String correctAnsStr = OPTIONS_TEXT[i][CORRECT_ANSWERS[i]];
            printRow(out, QUESTIONS[i], userAnsStr, correctAnsStr, results[i]);
        }

        out.println("</table>");

        out.println("<div class='score'>Final Score: " + score + " / " + totalQuestions + "</div>");
        out.println("<div class='score'>Percentage: " + (score * 100 / totalQuestions) + "%</div>");

        out.println("</div></body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("Please submit the quiz form to see results.");
    }

    private void printRow(PrintWriter out, String question, String userAnswer,
                            String correctAnswer, boolean isCorrect) {
        out.println("<tr>");
        out.println("<td>" + escape(question) + "</td>");
        out.println("<td>" + escape(userAnswer) + "</td>");
        out.println("<td>" + escape(correctAnswer) + "</td>");
        out.println("<td class='" + (isCorrect ? "correct'>Correct" : "wrong'>Wrong") + "</td>");
        out.println("</tr>");
    }

    // Basic HTML-escaping to avoid rendering issues / XSS from user input
    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
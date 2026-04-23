package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // 🔐 SESSION CHECK
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            res.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");

out.println("<html><head><title>Dashboard</title></head>");
out.println("<body style='font-family:Segoe UI;background:#f4f6f8;'>");

out.println("<div style='width:400px;margin:80px auto;padding:25px;background:white;border-radius:10px;box-shadow:0 0 10px rgba(0,0,0,0.1);'>");

out.println("<h2 style='margin-bottom:10px;'>Welcome " + username + "</h2>");

out.println("<a href='logout' style='color:#e63946;text-decoration:none;'>Logout</a><br><br>");

out.println("<h3>Add Task</h3>");

out.println("<form action='task' method='post'>");

out.println("<input type='text' name='title' placeholder='Enter task' required "
        + "style='width:100%;padding:8px;margin-bottom:10px;'>");

out.println("<input type='date' name='deadline' required "
        + "style='width:100%;padding:8px;margin-bottom:10px;'>");

out.println("<button style='width:100%;padding:10px;background:#4361EE;color:white;border:none;'>Add Task</button>");

out.println("</form>");

out.println("<br><a href='task' style='text-decoration:none;color:#4361EE;'>View Tasks</a>");

out.println("</div></body></html>");

}
}
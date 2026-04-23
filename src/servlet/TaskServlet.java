package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import db.DBConnection;

public class TaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    HttpSession session = req.getSession(false);

    if (session == null || session.getAttribute("user_id") == null) {
        res.sendRedirect("login.html");
        return;
    }

    int userId = (int) session.getAttribute("user_id");

    try {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM tasks WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

out.println("<html><body style='font-family:Segoe UI;background:#f4f6f8;'>");

out.println("<div style='width:500px;margin:50px auto;background:white;padding:20px;border-radius:10px;'>");

out.println("<h2>Your Tasks</h2>");

while (rs.next()) {

    int id = rs.getInt("id");
    String title = rs.getString("title");
    String deadline = rs.getString("deadline");
    String status = rs.getString("status");

    out.println("<div style='padding:10px;border-bottom:1px solid #ddd;'>");

    out.println("<b>" + title + "</b><br>");
    out.println("Deadline: " + deadline + "<br>");
    out.println("Status: " + status + "<br>");

    out.println("<a href='delete?id=" + id + "' style='color:red;margin-right:10px;'>Delete</a>");

    if (!"Done".equals(status)) {
        out.println("<a href='complete?id=" + id + "' style='color:green;'>Mark Done</a>");
    }

    out.println("</div>");
}

out.println("<br><a href='dashboard'>Back</a>");
out.println("</div></body></html>");

} catch (Exception e) {
        e.printStackTrace();
    }
}
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String title = req.getParameter("title");
        String deadline = req.getParameter("deadline");

        int userId = 1; // temporary

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO tasks(user_id,title,deadline,status) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setString(2, title);
            ps.setString(3, deadline);
            ps.setString(4, "Pending");

            ps.executeUpdate();

            res.sendRedirect("task");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
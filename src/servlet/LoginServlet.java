package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import db.DBConnection;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // 🔥 GET USER ID FROM DB
                int userId = rs.getInt("id");

                // ✅ CREATE SESSION
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("user_id", userId); // ⭐ IMPORTANT

                // ✅ REDIRECT
                res.sendRedirect("dashboard");

            } else {

                PrintWriter out = res.getWriter();

            out.println("<html><body style='font-family:Segoe UI;background:#f4f6f8;'>");

            out.println("<div style='width:350px;margin:100px auto;padding:25px;background:white;border-radius:10px;box-shadow:0 0 10px rgba(0,0,0,0.1);text-align:center;'>");

            out.println("<h3>User not found</h3>");
            out.println("<p>This looks like a new user.</p>");
            out.println("<p>Would you like to create an account?</p>");

            out.println("<form action='register' method='post'>");

            out.println("<input type='hidden' name='username' value='" + username + "'>");
            out.println("<input type='hidden' name='password' value='" + password + "'>");

            out.println("<button style='padding:10px 20px;background:#4361EE;color:white;border:none;border-radius:5px;'>Create Account</button>");

            out.println("</form>");

            out.println("<br><a href='login.html' style='text-decoration:none;color:#555;'>Go Back</a>");

            out.println("</div></body></html>");

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
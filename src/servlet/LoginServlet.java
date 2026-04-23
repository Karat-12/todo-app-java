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
                out.println("<h2>Invalid Credentials ❌</h2>");
                out.println("<a href='login.html'>Try Again</a>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import db.DBConnection;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Connection conn = DBConnection.getConnection();

            // 🔍 CHECK IF USER ALREADY EXISTS
            String checkSql = "SELECT * FROM users WHERE username=?";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setString(1, username);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {

                // ❌ USER EXISTS
                out.println("<html><body style='font-family:Segoe UI;background:#f4f6f8;'>");
                out.println("<div style='width:350px;margin:100px auto;padding:20px;background:white;border-radius:10px;text-align:center;'>");

                out.println("<h3>Username already exists</h3>");
                out.println("<p>Please try a different username</p>");
                out.println("<a href='login.html'>Go Back</a>");

                out.println("</div></body></html>");
                return;
            }

            // ✅ INSERT NEW USER
            String insertSql = "INSERT INTO users(username, password) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();

            // 🔥 GET GENERATED USER ID
            ResultSet generatedKeys = ps.getGeneratedKeys();
            int userId = 0;

            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            }

            // 🔐 CREATE SESSION
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("user_id", userId);

            // 🎨 SUCCESS PAGE + REDIRECT
            out.println("<html><body style='font-family:Segoe UI;background:#f4f6f8;'>");

            out.println("<div style='width:350px;margin:100px auto;padding:20px;background:white;border-radius:10px;text-align:center;'>");

            out.println("<h3>Account created successfully</h3>");
            out.println("<p>Redirecting to dashboard...</p>");

            out.println("</div></body></html>");

            res.setHeader("Refresh", "2; URL=dashboard");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
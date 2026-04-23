package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import db.DBConnection;

public class CompleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String id = req.getParameter("id");

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE tasks SET status='Done' WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));

            ps.executeUpdate();

            // 🔁 redirect back to task page
            res.sendRedirect("task");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
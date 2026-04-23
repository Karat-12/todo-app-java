package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import db.DBConnection;

public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "DELETE FROM tasks WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            res.sendRedirect("task");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
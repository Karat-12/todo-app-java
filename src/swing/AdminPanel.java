package swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

import db.DBConnection;

public class AdminPanel {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Admin Panel");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 🔥 Title
        JLabel title = new JLabel("Task Management - Admin View", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.add(title, BorderLayout.NORTH);

        // 🔥 Table
        String[] columns = {"User", "Task", "Deadline"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);
        frame.add(pane, BorderLayout.CENTER);

        // 🔥 DB DATA
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Connected to DB");
            String sql = "SELECT users.username, tasks.title, tasks.deadline " +
                         "FROM tasks JOIN users ON tasks.user_id = users.id";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getDate("deadline")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔥 FINAL
        frame.setLocationRelativeTo(null); // center screen
        frame.setVisible(true);
    }
}
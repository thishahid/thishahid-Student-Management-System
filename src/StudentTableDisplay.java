import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class StudentTableDisplay extends JFrame {
    private JTable table;

    public StudentTableDisplay() {
        setTitle("Student Data");
        setBounds(300, 90, 600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 550, 300);
        add(sp);

        displayData();
        setVisible(true);
    }

    private void displayData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Roll No");
        model.addColumn("Marks");

        try (Connection connection = JDBCConnectionManager.getConnection()) {
            String query = "SELECT * FROM student_info";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(rs.getInt("id")));
                row.add(rs.getString("name"));
                row.add(rs.getString("roll_no"));
                row.add(String.valueOf(rs.getInt("marks")));
                model.addRow(row);
            }

            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentForm extends JFrame implements ActionListener {
    private Container container;
    private JLabel nameLabel, rollLabel, marksLabel;
    private JTextField nameField, rollField, marksField;
    private JButton submitButton, addButton, updateButton, deleteButton, displayButton;

    public StudentForm() {
        setTitle("Student Details");
        setBounds(300, 90, 500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        addButton = new JButton("Add");
        addButton.setBounds(20, 30, 80, 30);
        container.add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(20, 70, 80, 30);
        container.add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(20, 110, 80, 30);
        container.add(deleteButton);

        displayButton = new JButton("Display");
        displayButton.setBounds(20, 150, 80, 30);
        container.add(displayButton);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(150, 70, 100, 30);
        container.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(230, 70, 150, 30);
        container.add(nameField);

        rollLabel = new JLabel("Roll No:");
        rollLabel.setBounds(150, 110, 100, 30);
        container.add(rollLabel);

        rollField = new JTextField();
        rollField.setBounds(230, 110, 150, 30);
        container.add(rollField);

        marksLabel = new JLabel("Marks:");
        marksLabel.setBounds(150, 150, 100, 30);
        container.add(marksLabel);

        marksField = new JTextField();
        marksField.setBounds(230, 150, 150, 30);
        container.add(marksField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 200, 100, 30);
        container.add(submitButton);

        submitButton.addActionListener(this);
        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        displayButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Get the input values
            String name = nameField.getText();
            String roll = rollField.getText();
            String marksText = marksField.getText();  // Get marks as a string for validation

            // Validate that name, roll, and marks are not empty
            if (name.isEmpty() || roll.isEmpty() || marksText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;  // Stop further processing if fields are empty
            }

            // Validate that marks is a valid integer
            int marks;
            try {
                marks = Integer.parseInt(marksText);  // Attempt to parse the marks
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Marks must be a valid number.");
                return;  // Stop further processing if marks is not a valid number
            }

            // Create the student DTO object
            StudentDTO student = new StudentDTO(name, roll, marks);

            // Add the student to the database
            addToDatabase(student);
        } else if (e.getSource() == displayButton) {
            // Open the student table display window
            new StudentTableDisplay();
        }
    }


    private void addToDatabase(StudentDTO student) {
        try (Connection connection = JDBCConnectionManager.getConnection()) {
            String query = "INSERT INTO student_info (name, roll_no, marks) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setString(2, student.getRollNo());
            statement.setInt(3, student.getMarks());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Student added successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Could not add student.");
        }
    }

    public static void main(String[] args) {
        StudentForm form = new StudentForm();
        form.setVisible(true);
    }
}

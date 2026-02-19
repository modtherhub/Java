import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentManagementSystem extends JFrame {

    private JTextField idField, nameField;
    private JComboBox<String> courseBox, gradeBox;
    private JTable table;
    private DefaultTableModel tableModel;
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();

        setTitle("Student Management System");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));

        idField = new JTextField();
        nameField = new JTextField();

        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(nameField);

        JButton addBtn = new JButton("Add Student");
        JButton updateBtn = new JButton("Update Student");

        inputPanel.add(addBtn);
        inputPanel.add(updateBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Course"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Course & Grade Management"));

        courseBox = new JComboBox<>(new String[]{"Java", "Python", "Databases"});
        gradeBox = new JComboBox<>(new String[]{"A", "B", "C", "D", "F"});

        JButton enrollBtn = new JButton("Enroll Course");
        JButton gradeBtn = new JButton("Assign Grade");

        bottomPanel.add(new JLabel("Course:"));
        bottomPanel.add(courseBox);
        bottomPanel.add(enrollBtn);

        bottomPanel.add(new JLabel("Grade:"));
        bottomPanel.add(gradeBox);
        bottomPanel.add(gradeBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Event Handling

        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        enrollBtn.addActionListener(e -> enrollCourse());
        gradeBtn.addActionListener(e -> assignGrade());

        setVisible(true);
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();

            if (name.isEmpty()) {
                throw new Exception("Name cannot be empty");
            }

            Student student = new Student(id, name);
            students.add(student);
            tableModel.addRow(new Object[]{id, name, student.getCourse()});
            clearFields();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String name = nameField.getText();
            students.get(row).setCourse(tableModel.getValueAt(row, 2).toString());
            tableModel.setValueAt(name, row, 1);
        } else {
            JOptionPane.showMessageDialog(this, "Select a student first");
        }
    }

    private void enrollCourse() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String course = courseBox.getSelectedItem().toString();
            students.get(row).setCourse(course);
            tableModel.setValueAt(course, row, 2);
        } else {
            JOptionPane.showMessageDialog(this, "Select a student first");
        }
    }

    private void assignGrade() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String course = students.get(row).getCourse();
            String grade = gradeBox.getSelectedItem().toString();
            students.get(row).assignGrade(course, grade);
            JOptionPane.showMessageDialog(this, "Grade assigned successfully");
        } else {
            JOptionPane.showMessageDialog(this, "Select a student first");
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * StudentManagementSystem is a GUI-based application to manage students,
 * enroll them in courses, and assign grades. It provides a tabbed interface
 * for student management, course enrollment, and grade assignment.
 *
 * <p>This application demonstrates:
 * <ul>
 *     <li>Java Swing components and layouts</li>
 *     <li>Event handling for buttons and selections</li>
 *     <li>Dynamic table updates using DefaultTableModel</li>
 *     <li>Interaction with a controller to manage data</li>
 * </ul>
 * </p>
 *
 * @author Modther Abdlahag
 * @version 1.0
 */
public class StudentManagementSystem extends JFrame {

    private StudentController controller;

    // GUI Components
    private JTextField idField, nameField, searchField;
    private JComboBox<String> courseBox, gradeBox;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Constructs the main Student Management System window.
     * Sets up tabs, layout, and initializes all components.
     */
    public StudentManagementSystem() {
        controller = new StudentController();
        setTitle("Student Management System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabs for Students, Courses, Grades
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Students", createStudentPanel());
        tabs.add("Courses", createCoursePanel());
        tabs.add("Grades", createGradePanel());

        add(tabs, BorderLayout.CENTER);
        setVisible(true);
    }

    // ================= Student Tab =================

    /**
     * Creates the "Students" tab panel containing input fields, buttons, and table.
     *
     * @return JPanel representing the student management tab
     */
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Info"));

        idField = new JTextField();
        nameField = new JTextField();
        JButton addBtn = new JButton("Add Student");
        JButton updateBtn = new JButton("Update Student");

        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(nameField);
        inputPanel.add(addBtn);
        inputPanel.add(updateBtn);

        panel.add(inputPanel, BorderLayout.NORTH);

        // Table to display students
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Course"}, 0);
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        panel.add(scroll, BorderLayout.CENTER);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        searchPanel.add(new JLabel("Search by Name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        panel.add(searchPanel, BorderLayout.SOUTH);

        // Event Handlers
        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        searchBtn.addActionListener(e -> searchStudent());

        return panel;
    }

    // ================= Course Tab =================

    /**
     * Creates the "Courses" tab panel for enrolling students in courses.
     *
     * @return JPanel representing the course enrollment tab
     */
    private JPanel createCoursePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Course Enrollment"));

        courseBox = new JComboBox<>(new String[]{"Java", "Python", "Databases"});
        JButton enrollBtn = new JButton("Enroll Selected Student");

        panel.add(new JLabel("Course:"));
        panel.add(courseBox);
        panel.add(enrollBtn);

        enrollBtn.addActionListener(e -> enrollCourse());

        return panel;
    }

    // ================= Grade Tab =================

    /**
     * Creates the "Grades" tab panel for assigning grades to students.
     *
     * @return JPanel representing the grade assignment tab
     */
    private JPanel createGradePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Grade Assignment"));

        gradeBox = new JComboBox<>(new String[]{"A", "B", "C", "D", "F"});
        JButton gradeBtn = new JButton("Assign Grade");

        panel.add(new JLabel("Grade:"));
        panel.add(gradeBox);
        panel.add(gradeBtn);

        gradeBtn.addActionListener(e -> assignGrade());

        return panel;
    }

    // ================= Actions =================

    /**
     * Adds a new student using input fields and updates the table.
     */
    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            controller.addStudent(id, name);
            refreshTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Student added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates the selected student's information based on input fields.
     */
    private void updateStudent() {
        try {
            int row = table.getSelectedRow();
            if (row < 0) throw new Exception("Select a student first");
            int id = (int) table.getValueAt(row, 0);
            String name = nameField.getText();
            controller.updateStudent(id, name);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Student updated successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Enrolls the selected student in the chosen course.
     */
    private void enrollCourse() {
        try {
            int row = table.getSelectedRow();
            if (row < 0) throw new Exception("Select a student from Students tab");
            int id = (int) table.getValueAt(row, 0);
            String course = courseBox.getSelectedItem().toString();
            controller.enrollCourse(id, course);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Course enrolled successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Assigns a grade to the selected student for the selected course.
     */
    private void assignGrade() {
        try {
            int row = table.getSelectedRow();
            if (row < 0) throw new Exception("Select a student from Students tab");
            int id = (int) table.getValueAt(row, 0);
            String course = table.getValueAt(row, 2).toString();
            String grade = gradeBox.getSelectedItem().toString();
            controller.assignGrade(id, course, grade);
            JOptionPane.showMessageDialog(this, "Grade assigned successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Searches students by name and displays matching results in the table.
     */
    private void searchStudent() {
        String name = searchField.getText();
        tableModel.setRowCount(0);
        for (Student s : controller.getAllStudents()) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getCourse()});
            }
        }
    }

    /**
     * Refreshes the student table with current data from the controller.
     */
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Student s : controller.getAllStudents()) {
            tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getCourse()});
        }
    }

    /**
     * Clears input fields in the student tab.
     */
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
    }

    /**
     * Entry point of the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementSystem::new);
    }
}

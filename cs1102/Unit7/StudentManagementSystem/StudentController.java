import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private List<Student> students;

    public StudentController() {
        students = new ArrayList<>();
    }

    public void addStudent(int id, String name) throws Exception {
        if (name.isEmpty()) throw new Exception("Name cannot be empty");
        if (students.stream().anyMatch(s -> s.getId() == id))
            throw new Exception("Student ID already exists");
        students.add(new Student(id, name));
    }

    public void updateStudent(int id, String newName) throws Exception {
        Student s = findStudentById(id);
        if (s == null) throw new Exception("Student not found");
        s.setName(newName);
    }

    public void enrollCourse(int id, String course) throws Exception {
        Student s = findStudentById(id);
        if (s == null) throw new Exception("Student not found");
        s.setCourse(course);
    }

    public void assignGrade(int id, String course, String grade) throws Exception {
        Student s = findStudentById(id);
        if (s == null) throw new Exception("Student not found");
        s.assignGrade(course, grade);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student findStudentById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }
}
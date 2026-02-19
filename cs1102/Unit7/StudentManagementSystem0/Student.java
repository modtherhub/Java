import java.util.HashMap;
import java.util.Map;

public class Student {
    private int id;
    private String name;
    private String course;
    private Map<String, String> grades;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.course = "Not Enrolled";
        this.grades = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    public void assignGrade(String course, String grade) {
        grades.put(course, grade);
    }
}
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeDataService {

    // Simulates reading data from a dataset
    public static List<Employee> loadEmployees() {
        return Arrays.asList(
                new Employee("Ali", 29, "IT", 3600),
                new Employee("Nour", 35, "HR", 4300),
                new Employee("Khalid", 41, "Finance", 5800),
                new Employee("Mona", 32, "IT", 4900),
                new Employee("Yasir", 26, "Marketing", 3100),
                new Employee("Hassan", 38, "Engineering", 6200),
                new Employee("Salma", 27, "Design", 3400),
                new Employee("Othman", 45, "Management", 7500),
                new Employee("Rania", 33, "Quality", 4800),
                new Employee("Bilal", 31, "Support", 3900)
        );
    }

    // Function interface usage with composition
    public static Function<Employee, String> employeeFormatter() {

        Function<Employee, String> basicInfo =
                e -> e.getName() + " (" + e.getDepartment() + ")";

        Function<String, String> decoratedInfo =
                info -> "Employee Info: " + info;

        return basicInfo.andThen(decoratedInfo);
    }

    // Stream processing with filtering and mapping
    public static List<String> getEmployeesAboveAge(
            List<Employee> employees, int ageThreshold) {

        return employees.stream()
                .filter(e -> e.getAge() > ageThreshold)
                .map(employeeFormatter())
                .collect(Collectors.toList());
    }

    // Average salary calculation
    public static double calculateAverageSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }
}
import java.util.List;

public class EmployeeStreamApplication {

    public static void main(String[] args) {

        List<Employee> employees =
                EmployeeDataService.loadEmployees();

        List<String> seniorEmployees =
                EmployeeDataService.getEmployeesAboveAge(employees, 30);

        double averageSalary =
                EmployeeDataService.calculateAverageSalary(employees);

        System.out.println("Employees above age 30:");
        seniorEmployees.forEach(System.out::println);

        System.out.println("\nAverage Salary: " + averageSalary);
    }
}
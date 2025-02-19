import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    // Constructor to initialize employee details
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Method to display employee details
    public void displayDetails() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
    }

    // Method to update employee details
    public void updateDetails(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}

public class EmployeeManager {
    private ArrayList<Employee> employeeList;

    // Constructor to initialize the employee list
    public EmployeeManager() {
        employeeList = new ArrayList<>();
    }

    // Method to add a new employee
    public void addEmployee(int id, String name, double salary) {
        Employee employee = new Employee(id, name, salary);
        employeeList.add(employee);
        System.out.println("Employee added successfully!");
    }

    // Method to update an employee by ID
    public void updateEmployee(int id, String name, double salary) {
        for (Employee employee : employeeList) {
            if (employee.id == id) {
                employee.updateDetails(name, salary);
                System.out.println("Employee details updated!");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found!");
    }

    // Method to remove an employee by ID
    public void removeEmployee(int id) {
        for (Employee employee : employeeList) {
            if (employee.id == id) {
                employeeList.remove(employee);
                System.out.println("Employee removed successfully!");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found!");
    }

    // Method to search an employee by ID
    public void searchEmployee(int id) {
        for (Employee employee : employeeList) {
            if (employee.id == id) {
                employee.displayDetails();
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found!");
    }

    // Method to display all employees
    public void displayAllEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee employee : employeeList) {
                employee.displayDetails();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeManager manager = new EmployeeManager();
        int choice;

        while (true) {
            System.out.println("\nEmployee Management System:");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Employee Salary: ");
                    double salary = scanner.nextDouble();
                    manager.addEmployee(id, name, salary);
                    break;

                case 2:
                    System.out.print("Enter Employee ID to update: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter new Employee Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new Employee Salary: ");
                    salary = scanner.nextDouble();
                    manager.updateEmployee(id, name, salary);
                    break;

                case 3:
                    System.out.print("Enter Employee ID to remove: ");
                    id = scanner.nextInt();
                    manager.removeEmployee(id);
                    break;

                case 4:
                    System.out.print("Enter Employee ID to search: ");
                    id = scanner.nextInt();
                    manager.searchEmployee(id);
                    break;

                case 5:
                    manager.displayAllEmployees();
                    break;

                case 6:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

package studentmanagement;

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {

        loadFromFile();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Total Students Count");
            System.out.println("7. Sort Students By ID");
            System.out.println("8. Exit");

            System.out.print("Enter Choice: ");

            try {

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        viewStudents();
                        break;

                    case 3:
                        searchStudent();
                        break;

                    case 4:
                        updateStudent();
                        break;

                    case 5:
                        deleteStudent();
                        break;

                    case 6:
                        System.out.println("Total Students = " + students.size());
                        break;

                    case 7:
                        sortStudents();
                        break;

                    case 8:
                        saveToFile();
                        System.out.println("Thank You");
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice");
                }

            } catch (Exception e) {

                System.out.println("Invalid Input");
                sc.nextLine();
            }
        }
    }

    static void addStudent() {

        try {

            System.out.print("Enter ID: ");
            int id = sc.nextInt();

            for (Student s : students) {

                if (s.id == id) {

                    System.out.println("Duplicate ID Not Allowed");
                    return;
                }
            }

            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();

            students.add(new Student(id, name, marks));

            saveToFile();

            System.out.println("Student Added Successfully");

        } catch (Exception e) {

            System.out.println("Error Occurred");
        }
    }

    static void viewStudents() {

        if (students.isEmpty()) {

            System.out.println("No Students Found");
            return;
        }

        for (Student s : students) {

            System.out.println(
                    "ID: " + s.id +
                            " Name: " + s.name +
                            " Marks: " + s.marks
            );
        }
    }

    static void searchStudent() {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (Student s : students) {

            if (s.id == id) {

                System.out.println(
                        "ID: " + s.id +
                                " Name: " + s.name +
                                " Marks: " + s.marks
                );

                return;
            }
        }

        System.out.println("Student Not Found");
    }

    static void updateStudent() {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (Student s : students) {

            if (s.id == id) {

                System.out.print("Enter New Name: ");
                s.name = sc.next();

                System.out.print("Enter New Marks: ");
                s.marks = sc.nextInt();

                saveToFile();

                System.out.println("Updated Successfully");
                return;
            }
        }

        System.out.println("Student Not Found");
    }

    static void deleteStudent() {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {

            Student s = iterator.next();

            if (s.id == id) {

                iterator.remove();

                saveToFile();

                System.out.println("Deleted Successfully");
                return;
            }
        }

        System.out.println("Student Not Found");
    }

    static void sortStudents() {

        students.sort(Comparator.comparingInt(s -> s.id));

        System.out.println("Sorted By ID");

        viewStudents();
    }

    static void saveToFile() {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Student s : students) {

                bw.write(s.toString());
                bw.newLine();
            }

        } catch (IOException e) {

            System.out.println("File Save Error");
        }
    }

    static void loadFromFile() {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                students.add(
                        new Student(
                                Integer.parseInt(data[0]),
                                data[1],
                                Integer.parseInt(data[2])
                        )
                );
            }

        } catch (Exception e) {
            // First run - file may not exist
        }
    }
}

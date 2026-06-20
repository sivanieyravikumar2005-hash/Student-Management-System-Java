import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement {
public static void saveToFile(ArrayList<Student> students) {

    try {
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("students.txt"));

        for (Student s : students) {
            writer.write(s.id + "," + s.name + "," +
                    s.department + "," + s.age);
            writer.newLine();
        }

        writer.close();

    } catch (Exception e) {
        System.out.println("Error Saving File!");
    }
}

public static void loadFromFile(ArrayList<Student> students) {

    try {

        BufferedReader reader =
                new BufferedReader(new FileReader("students.txt"));

        String line;

        while ((line = reader.readLine()) != null) {

            String[] data = line.split(",");

            students.add(
                    new Student(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            Integer.parseInt(data[3])));
        }

        reader.close();

    } catch (Exception e) {
        System.out.println("No Previous Data Found.");
    }
}
    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();
        loadFromFile(students);
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Total Students");
            System.out.println("7.Sort Students By ID");
            System.out.println("8. Exit");

            int choice;

            try {
                System.out.print("Enter Choice: ");
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {

                case 1:

                    int id;

                    try {
                        System.out.print("Enter ID: ");
                        id = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid ID! Enter numbers only.");
                        sc.nextLine();
                        break;
                    }

                    boolean idExists = false;

                    for (Student s : students) {
                        if (s.id == id) {
                            idExists = true;
                            break;
                        }
                    }

                    if (idExists) {
                        System.out.println("Student ID already exists!");
                        break;
                    }

                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();

                    int age;

                    try {
                        System.out.print("Enter Age: ");
                        age = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid Age! Enter numbers only.");
                        sc.nextLine();
                        break;
                    }

                    students.add(new Student(id, name, dept, age));
                    saveToFile(students);

                    System.out.println("Student Added Successfully!");
                    break;

                case 2:

                    if (students.isEmpty()) {
                        System.out.println("No Students Found!");
                    } else {

                        for (Student s : students) {

                            System.out.println(
                                    "ID: " + s.id +
                                    " Name: " + s.name +
                                    " Department: " + s.department +
                                    " Age: " + s.age);
                        }
                    }
                    break;

                case 3:

                    System.out.print("Enter Student ID to Search: ");

                    int searchId;

                    try {
                        searchId = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid ID!");
                        sc.nextLine();
                        break;
                    }

                    boolean found = false;

                    for (Student s : students) {

                        if (s.id == searchId) {

                            System.out.println("Student Found!");
                            System.out.println("ID: " + s.id);
                            System.out.println("Name: " + s.name);
                            System.out.println("Department: " + s.department);
                            System.out.println("Age: " + s.age);

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Student Not Found!");
                    }

                    break;

                case 4:

                    System.out.print("Enter Student ID to Delete: ");

                    int deleteId;

                    try {
                        deleteId = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid ID!");
                        sc.nextLine();
                        break;
                    }

                    boolean deleted = false;

                    for (int i = 0; i < students.size(); i++) {

                        if (students.get(i).id == deleteId) {

                            students.remove(i);
                            saveToFile(students);

                            System.out.println("Student Deleted Successfully!");

                            deleted = true;
                            break;
                        }
                    }

                    if (!deleted) {
                        System.out.println("Student Not Found!");
                    }

                    break;

                case 5:

                    System.out.print("Enter Student ID to Update: ");

                    int updateId;

                    try {
                        updateId = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid ID!");
                        sc.nextLine();
                        break;
                    }

                    sc.nextLine();

                    boolean updated = false;

                    for (Student s : students) {

                        if (s.id == updateId) {

                            System.out.print("Enter New Name: ");
                            s.name = sc.nextLine();

                            System.out.print("Enter New Department: ");
                            s.department = sc.nextLine();

                            try {
                                System.out.print("Enter New Age: ");
                                s.age = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid Age!");
                                sc.nextLine();
                                break;
                            }

                            System.out.println("Student Updated Successfully!");
                            saveToFile(students);

                            updated = true;
                            break;
                        }
                    }

                    if (!updated) {
                        System.out.println("Student Not Found!");
                    }

                    break;

                case 6:

                    System.out.println("Total Students: " + students.size());
                    break;
                case 7:
                    

    if (students.isEmpty()) {

        System.out.println("No Students Found!");
        break;
    }

    for (int i = 0; i < students.size() - 1; i++) {

        for (int j = i + 1; j < students.size(); j++) {

            if (students.get(i).id > students.get(j).id) {

                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
    }

    System.out.println("Students Sorted By ID Successfully!");

    for (Student s : students) {

        System.out.println(
                "ID: " + s.id +
                " Name: " + s.name +
                " Department: " + s.department +
                " Age: " + s.age);
    }

    break;





                case 8:

                    System.out.println("Thank You!");
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice");
            }
        }
    }
}
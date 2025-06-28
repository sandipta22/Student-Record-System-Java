import java.util.*;
import java.io.*;

public class StudentRecordSystem {
    static Student[] students = new Student[100];
    static int count = 0;

    public static void addStudent(Scanner sc) {
        System.out.print("Enter Regd No: ");
        int regd_no = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (students[i].regd_no == regd_no) {
                System.out.println("Regd No already exists!\n");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Mark: ");
        float mark = sc.nextFloat();
        students[count++] = new Student(regd_no, name, mark);
        System.out.println("Student added successfully!\n");
    }

    public static void displayAll() {
        if (count == 0) {
            System.out.println("No student records found.\n");
            return;
        }
        System.out.println("All Student Records:");
        for (int i = 0; i < count; i++) {
            students[i].display();
        }
        System.out.println();
    }

    public static void deleteStudent(Scanner sc) {
        System.out.print("Enter Regd No to delete: ");
        int regd_no = sc.nextInt();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (students[i].regd_no == regd_no) {
                System.out.print("Are you sure? (Y/N): ");
                char ch = sc.next().charAt(0);
                if (ch == 'Y' || ch == 'y') {
                    for (int j = i; j < count - 1; j++) {
                        students[j] = students[j + 1];
                    }
                    students[--count] = null;
                    found = true;
                    System.out.println("Student deleted.\n");
                } else {
                    System.out.println("Deletion cancelled.\n");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.\n");
        }
    }

    public static void searchStudent(Scanner sc) {
        System.out.println("Search by:\n1. Regd No\n2. Name");
        int option = sc.nextInt();
        sc.nextLine();
        boolean found = false;

        if (option == 1) {
            System.out.print("Enter Regd No: ");
            int regd = sc.nextInt();
            for (int i = 0; i < count; i++) {
                if (students[i].regd_no == regd) {
                    students[i].display();
                    found = true;
                    break;
                }
            }
        } else if (option == 2) {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            for (int i = 0; i < count; i++) {
                if (students[i].name.equalsIgnoreCase(name)) {
                    students[i].display();
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
        System.out.println();
    }

    public static void updateStudent(Scanner sc) {
        System.out.print("Enter Regd No to update: ");
        int regd = sc.nextInt();
        sc.nextLine();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (students[i].regd_no == regd) {
                System.out.print("Enter new name: ");
                String name = sc.nextLine();
                System.out.print("Enter new mark: ");
                float mark = sc.nextFloat();
                students[i].name = name;
                students[i].mark = mark;
                System.out.println("Student updated.\n");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.\n");
        }
    }

    public static void sortByMarks() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (students[i].mark < students[j].mark) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
        System.out.println("Students sorted by marks (descending).\n");
    }

    public static void showStats() {
        if (count == 0) {
            System.out.println("No data for statistics.\n");
            return;
        }

        float total = 0;
        float maxMark = students[0].mark;
        String topper = students[0].name;

        for (int i = 0; i < count; i++) {
            total += students[i].mark;
            if (students[i].mark > maxMark) {
                maxMark = students[i].mark;
                topper = students[i].name;
            }
        }

        float avg = total / count;
        System.out.println("Total Students: " + count);
        System.out.println("Average Mark: " + avg);
        System.out.println("Topper: " + topper + " with " + maxMark + " marks\n");
    }

    public static void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter("student_records.txt");
            for (int i = 0; i < count; i++) {
                writer.println(students[i].regd_no + "," + students[i].name + "," + students[i].mark);
            }
            writer.close();
            System.out.println("Data saved to student_records.txt\n");
        } catch (Exception e) {
            System.out.println("Error saving to file.\n");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("------ Student Record System ------");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Update Student");
            System.out.println("6. Sort by Marks");
            System.out.println("7. Show Statistics");
            System.out.println("8. Save to File");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent(sc); break;
                case 2: displayAll(); break;
                case 3: deleteStudent(sc); break;
                case 4: searchStudent(sc); break;
                case 5: updateStudent(sc); break;
                case 6: sortByMarks(); break;
                case 7: showStats(); break;
                case 8: saveToFile(); break;
                case 9: System.out.println("Exiting... Thank you!"); break;
                default: System.out.println("Invalid choice.\n");
            }
        } while (choice != 9);

        sc.close();
    }
}

    


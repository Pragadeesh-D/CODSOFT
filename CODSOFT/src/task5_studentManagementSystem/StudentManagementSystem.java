package task5_studentManagementSystem;

import java.util.*;
import java.io.*;

// Class representing a single student's details
class Student{
    private String studentName;
    private int rollNo;
    private String grade;
    private String dept;

    // Constructor to initialize student data
    public Student(String studentName, String dept, int rollNo, String grade){
        this.studentName = studentName;
        this.dept = dept;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    // Returns the name of the student
    public String getStudentName(){
        return studentName;
    }

    // Updates the name of the student
    public void setStudentName(String studentName){
        this.studentName  = studentName;
    }

    // Provides the roll number
    public int getRollNo(){
        return rollNo;
    }

    // Returns the department name
    public String getDept(){
        return dept;
    }

    // Lets you change the department name
    public void setDept(String dept){
        this.dept = dept;
    }

    // Gives the current grade of the student
    public String getGrade(){
        return grade;
    }

    // Allows updating the grade
    public void setGrade(String grade){
        this.grade = grade;
    }
}

public class StudentManagementSystem {
    // List to store all student records
    List<Student> studentsList = new ArrayList<>();

    // Static Scanner for user input throughout the program
    static Scanner sc = new Scanner(System.in);

    // Variables to hold student data temporarily during input
    String name = null;
    String dept = null;
    String grade = null;
    int rollNo;

    // This method keeps asking the user until a valid name is entered
    private String getStudentName() {
        while (true) {
            name = sc.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name: ");
            } else if (!name.matches("[A-Za-z ]+")) {
                System.out.println("Name can only contain letters and spaces. Please enter a valid name: ");
            } else {
                return name;
            }
        }
    }

    // This method ensures the roll number is a positive integer
    private int getStudentRollNumber(){
        while (true) {
            if (sc.hasNextInt()){
                rollNo = sc.nextInt();
                if (rollNo <= 0) {
                    System.out.println("Roll number cannot be less than 1. Please enter a valid rol number: ");
                }
                else {
                    sc.nextLine(); // Clear leftover newline
                    return rollNo;
                }
            }
            else {
                System.out.println("Invalid input! Please enter a valid integer for roll number: ");
                sc.next(); // Skip invalid token
            }
        }
    }

    // This method asks for department and checks if it's one of the predefined ones
    private String getStudentDepartment(){
        while (true){
            Set<String> validDept = new HashSet<>(Arrays.asList("CSE","ECE","EEE","CIVIL","MECH","IT","AIML","AIDS"));
            dept = sc.nextLine().toUpperCase();
            if (dept.isEmpty()){
                System.out.println("Department name cannot be empty. Please enter a valid department: ");
            }
            else if (!validDept.contains(dept)) {
                System.out.println("Invalid department! Please enter one of the listed options: ");
            }
            else {
                return dept;
            }
        }
    }

    // This method collects the student's grade and validates it against allowed grades
    private String getStudentGrade(){
        while (true){
            Set<String> validGrade = new HashSet<>(Arrays.asList("A+","A","B","C","D","F"));
            grade = sc.nextLine().toUpperCase();
            if (grade.isEmpty()){
                System.out.println("Grade cannot be empty. Please enter a valid grade: ");
            }
            else if (!validGrade.contains(grade)) {
                System.out.println("Invalid grade! Please enter one of the listed options: ");
            }
            else {
                return grade;
            }
        }
    }

    // Reads student records from the file and loads them into the list
    private void readStudentsFromFile() {
        File file = new File("C:/CODSOFT/CODSOFT/src/task5_studentManagementSystem/students.txt");

        // Check if the file exists; if not, notify and stop reading
        if (!file.exists()) {
            System.out.println("Student file not found. A new file will be created on first save.");
            return;
        }

        // Set up valid departments and grades to validate entries
        Set<String> validDepartments = Set.of("CSE", "IT", "ECE", "EEE", "MECH");
        Set<String> validGrades = Set.of("A+", "A", "B", "C", "D", "F");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true; // Skip the header row

            // Read file line-by-line
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the column headings
                }
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Split line into parts based on double or more spaces
                String[] parts = line.trim().split("\\s{2,}");

                // Ensure exactly 4 pieces of data (name, dept, roll, grade)
                if (parts.length != 4) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                String name = parts[0];
                String dept = parts[1];
                String rollStr = parts[2];
                String grade = parts[3];

                // Validate department name
                if (!validDepartments.contains(dept)) {
                    System.out.println("Skipping invalid department: " + dept);
                    continue;
                }
                int roll;
                try {
                    // Convert roll number to integer and check if positive
                    roll = Integer.parseInt(rollStr);
                    if (roll <= 0) {
                        System.out.println("Skipping invalid roll number: " + rollStr);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping non-numeric roll number: " + rollStr);
                    continue;
                }

                // Validate grade
                if (!validGrades.contains(grade)) {
                    System.out.println("Skipping invalid grade: " + grade);
                    continue;
                }

                // Add valid student entry to the list
                studentsList.add(new Student(name, dept, roll, grade));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Appends a new student's data to the text file
    private void writeStudentToFile(Student student) {
        File file = new File("C:/CODSOFT/CODSOFT/src/task5_studentManagementSystem/students.txt");
        boolean fileExists = file.exists(); // Check if file already exists
        boolean isEmpty = file.length() == 0; // Check if file is empty
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            // If file is new or empty, write the header line
            if (!fileExists || isEmpty) {
                writer.write(String.format("%-15s %-15s %-12s %-5s", "Name", "Department", "RollNumber", "Grade"));
                writer.newLine();
            }

            // Add the student details in formatted style
            writer.write(String.format("%-15s %-15s %-12d %-5s", student.getStudentName(), student.getDept(), student.getRollNo(), student.getGrade()));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing student: " + e.getMessage());
        }
    }

    // Rewrites the entire student list into the file (used after edit/delete)
    private void overwriteFileWithAllStudents() {
        File file = new File("C:/CODSOFT/CODSOFT/src/task5_studentManagementSystem/students.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write the header row
            writer.write(String.format("%-15s %-15s %-12s %-5s", "Name", "Department", "RollNumber", "Grade"));
            writer.newLine();

            // Loop through all students and write them one by one
            for (Student student : studentsList) {
                writer.write(String.format("%-15s %-15s %-12d %-5s", student.getStudentName(), student.getDept(), student.getRollNo(), student.getGrade()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error overwriting file: " + e.getMessage());
        }
    }

    // This method allows the user to add a new student to the list
    public void addStudent(){
        while (true){
            boolean exists = false;

            // Get student details one by one
            System.out.println("\nEnter the student's name: ");
            name = getStudentName();

            System.out.println("Enter the student's roll number: ");
            rollNo = getStudentRollNumber();

            System.out.println("Available departments: CSE, ECE, EEE, CIVIL, MECH, IT, AIML, AIDS.");
            System.out.println("Enter the student's department: ");
            dept = getStudentDepartment();

            System.out.println("Available grades: A+, A, B, C, D, F.");
            System.out.println("Enter the student's grade: ");
            grade = getStudentGrade();

            // Create a student object with the collected data
            Student student = new Student(name, dept, rollNo, grade);

            // Check if a student with the same roll number and department already exists
            for (Student checkStudent : studentsList){
                if (checkStudent.getRollNo() == rollNo && checkStudent.getDept().equals(dept)){
                    System.out.println("This student already exists!");
                    exists = true;
                    return;
                }
            }

            // If student is unique, add to list and save to file
            if (!exists) {
                studentsList.add(student);
                System.out.println("Student added successfully!");
                writeStudentToFile(student);
                break;
            }
        }
    }

    // This method allows the user to remove a student using roll number and department
    public void removeStudent(){
        System.out.println("\nTo remove a student, you need to enter their roll number and department.");
        Student toRemove = null;

        // Get roll number and department from user
        System.out.println("Enter the student's roll number: ");
        rollNo = getStudentRollNumber();

        System.out.println("Available departments: CSE, ECE, EEE, CIVIL, MECH, IT, AIML, AIDS.");
        System.out.println("Enter the student's department: ");
        dept = getStudentDepartment();

        // Search for student in the list
        for (Student checkStudent : studentsList){
            if (checkStudent.getRollNo() == rollNo && checkStudent.getDept().equals(dept)){
                toRemove = checkStudent;
                break;
            }
        }

        // If found, remove from list and update the file
        if (toRemove != null) {
            studentsList.remove(toRemove);
            System.out.println("Student removed successfully!");
            overwriteFileWithAllStudents();
        }
        else {
            System.out.println("Student not found!");
        }
        System.out.println("\nDo you want to remove another student? Type 'yes' or 'no':");
    }

    // This method allows the user to search for a student by roll number and department
    public void searchStudent(){
        System.out.println("\nTo search for a student, you need to enter their roll number and department.");
        Student toSearch = null;

        System.out.println("Enter the student's roll number: ");
        rollNo = getStudentRollNumber();

        System.out.println("Available departments: CSE, ECE, EEE, CIVIL, MECH, IT, AIML, AIDS.");
        System.out.println("Enter the student's department: ");
        dept = getStudentDepartment();

        // Search student in the list
        for (Student checkStudent : studentsList){
            if (checkStudent.getRollNo() == rollNo && checkStudent.getDept().equals(dept)){
                toSearch = checkStudent;
                break;
            }
        }

        // Display details if student exists
        if (toSearch != null) {
            System.out.println("Student found!");
            System.out.printf("Name: %s\nDepartment: %s\nRollno: %d\nGrade: %s\n",toSearch.getStudentName(),toSearch.getDept(),toSearch.getRollNo(),toSearch.getGrade());
        }
        else {
            System.out.println("Student not found!");
        }
        System.out.println("\nDo you want to search for another student? Type 'yes' or 'no':");
    }

    // This method helps update student details by selecting specific fields or all
    public void updateStudent(){
        boolean found = false;
        System.out.println("\nTo update a student's details, you need to enter their roll number and department.");
        System.out.println("Enter the student's roll number: ");
        rollNo = getStudentRollNumber();

        System.out.println("Available departments: CSE, ECE, EEE, CIVIL, MECH, IT, AIML, AIDS.");
        System.out.println("Enter the student's department: ");
        dept = getStudentDepartment();

        for (Student checkStudent : studentsList){
            if (checkStudent.getRollNo() == rollNo && checkStudent.getDept().equals(dept)){
                found = true;

                // Ask user what fields to update
                System.out.println("What do you want to update?");
                System.out.println("1. Name \n2. Department \n3. Grade \n4. Name & Department \n5. Name & Grade \n6. Department & Grade \n7. All");
                System.out.println("Enter the appropriate number to update: ");

                while (true){
                    if (sc.hasNextInt()){
                        int choice = sc.nextInt();
                        sc.nextLine();

                        // Perform action based on user's choice
                        switch (choice){
                            case 1:
                                System.out.println("Enter the new name: ");
                                String newName = getStudentName();
                                checkStudent.setStudentName(newName);
                                System.out.println("Student's name updated successfully!");
                                overwriteFileWithAllStudents();
                                break;
                            case 2:
                                System.out.println("Available departments: CSE, ECE, EEE, CIVIL, MECH, IT, AIML, AIDS.");
                                System.out.println("Enter the new department: ");
                                String newDept = getStudentDepartment();
                                checkStudent.setDept(newDept);
                                System.out.println("Student's department updated successfully!");
                                overwriteFileWithAllStudents();
                                break;
                            case 3:
                                System.out.println("Available grades: A+, A, B, C, D, F.");
                                System.out.println("Enter the new grade: ");
                                String newGrade = getStudentGrade();
                                checkStudent.setGrade(newGrade);
                                System.out.println("Student's grade updated successfully!");
                                overwriteFileWithAllStudents();
                                break;
                            case 4:
                                System.out.println("Enter the new name: ");
                                String newName1 = getStudentName();
                                checkStudent.setStudentName(newName1);
                                System.out.println("Available departments: CSE, ECE, EEE, CIVIL, MECH, IT, AIML, AIDS.");
                                System.out.println("Enter the new department: ");
                                String newDept1 = getStudentDepartment();
                                checkStudent.setDept(newDept1);
                                System.out.println("Student's name and department have been updated successfully!");
                                overwriteFileWithAllStudents();
                                break;
                            case 5:
                                System.out.println("Enter the new name: ");
                                String newName2 = getStudentName();
                                checkStudent.setStudentName(newName2);
                                System.out.println("Available grades: A+, A, B, C, D, F.");
                                System.out.println("Enter the new grade: ");
                                String newGrade1 = getStudentGrade();
                                checkStudent.setGrade(newGrade1);
                                System.out.println("Student's name and grade have been updated successfully!");
                                overwriteFileWithAllStudents();
                                break;
                            case 6:
                                System.out.println("Available departments: CSE, ECE, EEE, CIVIL, MECH, IT, AIML, AIDS.");
                                System.out.println("Enter the new department: ");
                                String newDept2 = getStudentDepartment();
                                checkStudent.setDept(newDept2);
                                System.out.println("Available grades: A+, A, B, C, D, F.");
                                System.out.println("Enter the new grade: ");
                                String newGrade2 = getStudentGrade();
                                checkStudent.setGrade(newGrade2);
                                System.out.println("Student's department and grade have been updated successfully!");
                                overwriteFileWithAllStudents();
                                break;
                            case 7:
                                System.out.println("Enter the new name: ");
                                String newName3 = getStudentName();
                                checkStudent.setStudentName(newName3);
                                System.out.println("Available departments: CSE, ECE, EEE, CIVIL, MECH, IT, AIML, AIDS.");
                                System.out.println("Enter the new department: ");
                                String newDept3 = getStudentDepartment();
                                checkStudent.setDept(newDept3);
                                System.out.println("Available grades: A+, A, B, C, D, F.");
                                System.out.println("Enter the new grade: ");
                                String newGrade3 = getStudentGrade();
                                checkStudent.setGrade(newGrade3);
                                System.out.println("Student's name, department, and grade have been updated successfully!");
                                overwriteFileWithAllStudents();
                                break;
                            default:
                                System.out.println("Invalid choice! Please enter a valid option: ");
                                continue;
                        }
                        break;
                    }
                    else {
                        System.out.println("Invalid input! Please enter a valid integer choice: ");
                        sc.next();
                    }
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found!");
        }
        System.out.println("\nDo you want to update another student? Type 'yes' or 'no':");
    }

    // This method displays all student records stored in the list
    public void displayStudent(){
        if (!studentsList.isEmpty()){
            // Print header row
            System.out.printf("%-15s %-15s %-12s %-5s%n", "Name", "Department", "RollNumber", "Grade");

            // Display each student's info
            for (Student checkStudent : studentsList){
                System.out.printf("%-15s %-15s %-12d %-5s%n", checkStudent.getStudentName(), checkStudent.getDept(), checkStudent.getRollNo(), checkStudent.getGrade());
            }
        }
        else {
            System.out.println("No student records available.");
        }
    }

    // This method prints the list of available options for the user to choose from
    public void userChoice(){
        System.out.println("\nPlease choose an option:\n"+
                "1. Add Student\n"+
                "2. Remove Student\n"+
                "3. Search Student\n"+
                "4. Update Student\n"+
                "5. Display All Students\n"+
                "6. Exit\n"+
                "Enter your choice: ");
    }

    public static void main(String[] args){
        String repeat = null;

        // Creating an object to access all student-related methods
        StudentManagementSystem students = new StudentManagementSystem();

        // Reading existing student records from the text file (if available)
        students.readStudentsFromFile();

        // Displaying records already present in the file
        System.out.println("Existing Student Records:");
        students.displayStudent();

        // Short intro to let the user know what the system can do
        System.out.println("\nHey user! This is your Student Management System. Here, you can add and remove students.\nAnd also, you can search, update and display student details.");

        // This loop keeps the program running until the user decides to exit
        while (true){
            students.userChoice(); // show menu options to the user

            // Check if user entered a number
            if (sc.hasNextInt()){
                int choice = sc.nextInt(); // fetch user's choice
                sc.nextLine(); // Consume newline

                // Perform action based on the user's input
                switch (choice){
                    case 1:
                        students.addStudent(); // adding a new student

                        // Ask the user if they want to add more students
                        System.out.println("\nDo you want to add another student? Type 'yes' or 'no':");
                        while (true){
                            repeat = sc.next();
                            sc.nextLine();
                            if (repeat.equalsIgnoreCase("yes")){
                                students.addStudent();
                                System.out.println("\nDo you want to add another student? Type 'yes' or 'no':");
                            }
                            else if (repeat.equalsIgnoreCase("no")) {
                                break;
                            }
                            else {
                                System.out.println("Invalid input! Please enter 'yes' or 'no':");
                            }
                        }
                        break;
                    case 2:
                        students.removeStudent(); // deleting a student

                        // Ask if user wants to delete more
                        while (true){
                            repeat = sc.next();
                            sc.nextLine();
                            if (repeat.equalsIgnoreCase("yes")){
                                students.removeStudent();
                            }
                            else if (repeat.equalsIgnoreCase("no")) {
                                break;
                            }
                            else {
                                System.out.println("Invalid input! Please enter 'yes' or 'no':");
                            }
                        }
                        break;
                    case 3:
                        students.searchStudent(); // search by name or roll no.

                        // Ask if user wants to search again
                        while (true){
                            repeat = sc.next();
                            sc.nextLine();
                            if (repeat.equalsIgnoreCase("yes")){
                                students.searchStudent();
                            }
                            else if (repeat.equalsIgnoreCase("no")) {
                                break;
                            }
                            else {
                                System.out.println("Invalid input! Please enter 'yes' or 'no':");
                            }
                        }
                        break;
                    case 4:
                        students.updateStudent(); // update student details

                        // Ask if another update is needed
                        while (true) {
                            repeat = sc.next();
                            sc.nextLine();
                            if (repeat.equalsIgnoreCase("yes")){
                                students.updateStudent();
                            }
                            else if (repeat.equalsIgnoreCase("no")) {
                                break;
                            }
                            else {
                                System.out.println("Invalid input! Please enter 'yes' or 'no':");
                            }
                        }
                        break;
                    case 5:
                        students.displayStudent(); // show all student data
                        break;
                    case 6:
                        // User chose to exit
                        System.out.println("Thank you for using the Student Management System. Goodbye!");
                        return;
                    default:
                        // If choice is out of range
                        System.out.println("Invalid choice! Please enter a valid option: ");
                }
            }
            else {
                // When user enters a non-integer input
                System.out.println("Invalid input! Please enter a valid integer choice: ");
                sc.next(); // Clear wrong input
            }
        }
    }
}

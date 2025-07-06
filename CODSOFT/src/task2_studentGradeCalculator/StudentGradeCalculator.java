package task2_studentGradeCalculator;

import java.util.Scanner;

public class StudentGradeCalculator {
    static Scanner sc = new Scanner(System.in);
    public static void studentGradeCalculator(){
        int subjects = 0;
        double marks = 0, average = 0;
        System.out.println("\nPlease enter how many subjects you have: ");
        while (true){
            if (sc.hasNextInt()){
                subjects = sc.nextInt();
                if (subjects >0) {
                    break;
                }
                else {
                    System.out.println("Number of subjects must be greater than 0! Please enter the total number of subjects.");
                }
            }
            else {
                System.out.println("Invalid input! Please enter a valid integer for the total number of subjects.");
                sc.next();
            }
        }
        System.out.println();
        for (int i = 1; i <= subjects; i++){
            System.out.printf("Enter your Subject %d mark: ",i);
            if (sc.hasNextDouble()) {
                double mark = sc.nextDouble();
                if (mark < 0 || mark > 100) {
                    System.out.println("Marks must be between 0 and 100! Please enter a valid mark.");
                    i--;
                    continue;
                }
                marks += mark;
            }
            else {
                System.out.println("nvalid input! Please enter a valid mark.");
                i--;
                sc.next();
            }
        }
        average = marks / subjects;
        System.out.println();
        gradeSystem();
        System.out.println("\nYour Total Marks: "+marks);
        System.out.println("Your Average Percentage: "+average);
        if (average >= 90){
            System.out.println("Your Grade: A+");
            System.out.println("Excellent Grade! Keep it up for all upcoming exams.");
        }
        else if (average >= 80 && average <= 89) {
            System.out.println("Your Grade: A");
            System.out.println("Great job! You're doing very well, keep pushing for the top!");
        }
        else if (average >= 70 && average <= 79) {
            System.out.println("Your Grade: B");
            System.out.println("Good work! A little more effort can take you to the top grades.");
        }
        else if (average >= 60 && average <= 69) {
            System.out.println("Your Grade: C");
            System.out.println("Fair performance. Focus more and you can definitely improve!");
        }
        else if (average >= 50 && average <= 59) {
            System.out.println("Your Grade: D");
            System.out.println("Needs improvement. Don't give up — work harder and you'll do better next time.");
        }
        else {
            System.out.println("Your Grade: F");
            System.out.println("You did not pass this time. Stay motivated and study consistently to improve!");
        }
        System.out.println("\nDo you want to calculate for another student? Type 'yes' or 'no':");
    }
    public static void gradeSystem(){
        System.out.println("------------- Grade System -------------");
        System.out.println("Percentage range         Grade            \n" +
                                      "≥ 90%                     A+             \n" +
                                      "80 - 89%                  A              \n" +
                                      "70 - 79%                  B              \n" +
                                      "60 - 69%                  C              \n" +
                                      "50 - 59%                  D              \n" +
                                      "< 50%                     F");
    }
    public static void main(String[] args){
        System.out.println("Hey Student! This is the Student Grade Calculator.\nYou can calculate your grade by providing all your subject marks.");
        studentGradeCalculator();
        while (true) {
            String repeat = sc.next();
            if (repeat.equalsIgnoreCase("yes")) {
                studentGradeCalculator();
            } else if (repeat.equalsIgnoreCase("no")) {
                System.out.println("\nThanks for using our service!");
                break;
            }
            else {
                System.out.println("Invalid input! Please type 'yes' or 'no':");
            }
        }
    }
}

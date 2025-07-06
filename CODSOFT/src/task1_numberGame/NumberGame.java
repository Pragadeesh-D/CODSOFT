package task1_numberGame;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    static Scanner sc = new Scanner(System.in);
    public static void playGame(){
        Random rand = new Random();
        int numberToGuess = rand.nextInt(100) + 1;

        System.out.println("\nHey User! You have 5 chances to guess the correct number, which is between 1 and 100.");
        System.out.println("If you guess the correct number on your first try, you'll get 100 points. For each additional attempt, 20 points will be deducted.");
        int userNumber = 0;
        int attemptNumber = 5;
        while (attemptNumber > 0){
            System.out.println("Enter Your Guessing Number: ");
            if (sc.hasNextInt()){
                userNumber = sc.nextInt();
                if (userNumber < 1 || userNumber > 100){
                    System.out.println("Please enter a number between 1 and 100!\n");
                    continue;
                }
                if (userNumber == numberToGuess){
                    System.out.println("Congrats! Your guessing is correct");
                    switch (attemptNumber){
                        case 1:
                            System.out.println("You have found it in last choice!");
                            System.out.println("Your score is 20.\n");
                            break;
                        case 2:
                            System.out.println("You have found it in 4th choice!");
                            System.out.println("Your score is 40.\n");
                            break;
                        case 3:
                            System.out.println("You have found it in 3rd choice!");
                            System.out.println("Your score is 60.\n");
                            break;
                        case 4:
                            System.out.println("You have found it in 2nd choice!");
                            System.out.println("Your score is 80.\n");
                            break;
                        case 5:
                            System.out.println("You have found it in 1st choice!");
                            System.out.println("Your score is 100.\n");
                            break;
                    }
                    break;
                }
                else if (userNumber > numberToGuess) {
                    System.out.println("Your guess is high!\n");
                }
                else {
                    System.out.println("Your guess is low!\n");
                }
            }
            else {
                System.out.println("Invalid input! Please enter a valid integer.\n");
                sc.next();
                continue;
            }
            attemptNumber --;
            if (attemptNumber > 0){
                System.out.printf("You have %d chance left.\n\n",(attemptNumber));
            }
            else {
                System.out.println("Sorry! You have no chances left.");
                System.out.println("Your score is 0.\n");
            }
        }
        System.out.println("Thanks for playing!\nDo you want to play again? Type 'yes' or 'no'");

    }
    public static void main(String[] args){
        System.out.println("----- Number Guessing Game -----");
        playGame();
        while (true) {
            String replay = sc.next();
            if (replay.equalsIgnoreCase("yes")) {
                playGame();
            } else if (replay.equalsIgnoreCase("no")) {
                System.out.println("\nGoodbye!");
                break;
            }
            else {
                System.out.println("Invalid input! Please type 'yes' or 'no':");
            }

        }
    }
}

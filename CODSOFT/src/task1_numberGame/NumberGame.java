package task1_numberGame;

// Import required classes
import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    // Scanner object to read user input from the console
    static Scanner sc = new Scanner(System.in);

    // Main logic for the number guessing game
    public static void playGame(){
        Random rand = new Random();
        // Generates a random number between 1 and 100
        int numberToGuess = rand.nextInt(100) + 1;

        // Introduction to the game rules
        System.out.println("\nHey User! You have 5 chances to guess the correct number, which is between 1 and 100.");
        System.out.println("If you guess the correct number on your first try, you'll get 100 points. For each additional attempt, 20 points will be deducted.");

        int userNumber = 0; // User's guess
        int attemptNumber = 5; // Number of attempts remaining

        // Loop runs until all attempts are used
        while (attemptNumber > 0){
            System.out.println("Enter Your Guessing Number: ");

            // Check if the input is an integer
            if (sc.hasNextInt()){
                userNumber = sc.nextInt();

                // Make sure the input number is in the allowed range
                if (userNumber < 1 || userNumber > 100){
                    System.out.println("Please enter a number between 1 and 100!\n");
                    continue; // Skip to next iteration
                }

                // Check if the guess is correct
                if (userNumber == numberToGuess){
                    System.out.println("Congrats! Your guessing is correct");

                    // Display score based on which attempt was correct
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
                    break; // Exit the loop when guessed correctly
                }
                // If guessed number is higher than the target
                else if (userNumber > numberToGuess) {
                    System.out.println("Your guess is high!\n");
                }
                // If guessed number is lower than the target
                else {
                    System.out.println("Your guess is low!\n");
                }
            }
            else {
                // If input is not an integer
                System.out.println("Invalid input! Please enter a valid integer.\n");
                sc.next(); // Clear invalid input
                continue; // Skip to next attempt
            }

            // Decrease the number of attempts
            attemptNumber --;

            // Show remaining attempts or declare game over
            if (attemptNumber > 0){
                System.out.printf("You have %d chance left.\n\n",(attemptNumber));
            }
            else {
                // If user failed to guess correctly
                System.out.println("Sorry! You have no chances left.");
                System.out.println("The correct number was: " + numberToGuess);
                System.out.println("Your score is 0.\n");
            }
        }

        // Ask if the user wants to play another round
        System.out.println("Thanks for playing!\nDo you want to play again? Type 'yes' or 'no'");

    }

    // Main method that starts the game
    public static void main(String[] args){
        System.out.println("----- Number Guessing Game -----");
        playGame(); // Start the first round

        // Loop for repeated play
        while (true) {
            String replay = sc.next(); // Read user response
            if (replay.equalsIgnoreCase("yes")) {
                playGame(); // Replay the game
            } else if (replay.equalsIgnoreCase("no")) {
                System.out.println("\nGoodbye!"); // Exit the loop
                break;
            }
            else {
                // Handle unexpected input
                System.out.println("Invalid input! Please enter 'yes' or 'no':");
            }

        }
    }
}

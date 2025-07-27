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

        int userNumber; // User's guess
        int attemptNumber = 5; // Number of attempts remaining

        // Loop runs until all attempts are used
        while (attemptNumber > 0){

            // Show a different prompt for first and subsequent guesses
            if (attemptNumber == 5) {
                System.out.println("\nEnter your guessing number: ");
            } else {
                System.out.println("\nEnter your next guess: ");
            }

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

                    // Display score based on which attempt was correct
                    switch (attemptNumber) {
                        case 1 -> {
                            System.out.println("Well done! You guessed it on your final attempt.");
                            System.out.println("Your score is 20.\n");
                        }
                        case 2 -> {
                            System.out.println("Good effort! You found the number on your fourth attempt.");
                            System.out.println("Your score is 40.\n");
                        }
                        case 3 -> {
                            System.out.println("Nice work! You guessed the number on your third attempt.");
                            System.out.println("Your score is 60.\n");
                        }
                        case 4 -> {
                            System.out.println("Great job! You found the correct number on your second attempt.");
                            System.out.println("Your score is 80.\n");
                        }
                        case 5 -> {
                            System.out.println("Amazing! You guessed it on your very first try!");
                            System.out.println("Your score is 100.\n");
                        }
                    }
                    break; // Exit the loop when guessed correctly
                }
                // If guessed number is higher than the target
                else if (userNumber > numberToGuess) {
                    System.out.println("Your guess is high!");
                }
                // If guessed number is lower than the target
                else {
                    System.out.println("Your guess is low!");
                }
            }
            else {
                // If input is not an integer
                System.out.println("Invalid input! That doesn't count as an attempt. Please enter a valid number (1-100).");
                sc.next(); // Clear invalid input
                continue; // Skip to next attempt
            }

            // Decrease the number of attempts
            attemptNumber --;

            // Show remaining attempts or declare game over
            if (attemptNumber > 0){
                System.out.printf("You have %d chance left.\n",(attemptNumber));
            }
            else {
                // If user failed to guess correctly
                System.out.println("\nSorry! You have no chances left.");
                System.out.println("The correct number was: " + numberToGuess);
                System.out.println("\nYour score is 0.\n");
            }
        }

        // Ask if the user wants to play another round
        System.out.println("Do you want to play again? Type 'yes' or 'no'");

    }

    // Main method that starts the game
    public static void main(String[] args){
        System.out.println("----- Number Guessing Game -----");

        // Introduction to the game rules
        System.out.println("\nHey User! You have 5 chances to guess the correct number, which is between 1 and 100.");
        System.out.println("If you guess the correct number on your first try, you'll get 100 points. For each additional attempt, 20 points will be deducted.");

        playGame(); // Start the first round

        // Loop for repeated play
        while (true) {
            String replay = sc.next(); // Read user response
            if (replay.equalsIgnoreCase("yes")) {
                System.out.println("----- New Game Started -----");
                playGame(); // Replay the game
            } else if (replay.equalsIgnoreCase("no")) {
                System.out.println("\nGoodbye! Thanks for playing."); // Exit the loop
                break;
            }
            else {
                // Handle unexpected input
                System.out.println("Invalid input! Please enter 'yes' or 'no':");
            }

        }
    }
}

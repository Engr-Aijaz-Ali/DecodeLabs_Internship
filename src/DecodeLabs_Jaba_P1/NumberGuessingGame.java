package DecodeLabs_Jaba_P1;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class NumberGuessingGame{

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int secretNumber = random.nextInt(100) + 1; // Number between 1 and 100
        int maxAttempts = 5;
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("                    ===================================");
        System.out.println("                     NUMBER GUESSING GAME by Aijaz Ali");
        System.out.println("                    ===================================");
        System.out.println("     Guess a number between 1 and 100");
        System.out.println("     You have only 5 attempts.\n");

        while (attempts < maxAttempts && !guessedCorrectly) {

            try {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");

                int guess = scanner.nextInt();
                attempts++;

                if (guess == secretNumber) {
                    guessedCorrectly = true;
                    System.out.println("\nCongratulations!");
                    System.out.println("You guessed the correct number: " + secretNumber);
                    System.out.println("Attempts Used: " + attempts);
                    System.out.println("Score: " + (maxAttempts - attempts + 1) + "/5");
                }
                else if (guess < secretNumber) {
                    System.out.println("Too Low! Try Again.\n");
                }
                else {
                    System.out.println("Too High! Try Again.\n");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input! Please enter an integer only.\n");
                scanner.next(); // Clear invalid input
            }
        }

        if (!guessedCorrectly) {
            System.out.println("\nGame Over!");
            System.out.println("You have used all 5 attempts.");
            System.out.println("The correct number was: " + secretNumber);
            System.out.println("Score: 0/5");
        }

        scanner.close();
    }
}
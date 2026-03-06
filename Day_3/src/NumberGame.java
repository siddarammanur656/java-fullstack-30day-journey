//import java.util.Scanner;
//
//public class NumberGame {
//
////    public static void main(String[] args) {
////
////        Scanner sc = new Scanner(System.in);
////
////        int target = (int)(Math.random() * 100) + 1; // random number 1-100
////        int guess;
////        int attempts = 0;
////        int maxAttempts = 7;
////
////        System.out.println("===== Number Guessing Game =====");
////        System.out.println("Guess a number between 1 and 100");
////        System.out.println("You have 7 attempts");
////
////        while (attempts < maxAttempts) {
////
////            System.out.print("Enter your guess: ");
////            guess = sc.nextInt();
////
////            attempts++;
////
////            if (guess == target) {
////                System.out.println("🎉 Correct! You guessed the number.");
////                System.out.println("Attempts used: " + attempts);
////                break;
////            }
////            else if (guess < target) {
////                System.out.println("Too low! Try a higher number.");
////            }
////            else {
////                System.out.println("Too high! Try a lower number.");
////            }
////        }
////
////        if (attempts == maxAttempts) {
////            System.out.println(" You used all attempts.");
////            System.out.println("The correct number was: " + target);
////        }
////
////        sc.close();
////    }
//
//}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberGame {

    static int attempts;
    static int totalGames   = 0;
    static int totalWins    = 0;
    static List<Integer> attemptHistory = new ArrayList<>();

    // Recursive hint — gives narrowing hints based on attempt count
    static String getHint(int guess, int target, int attempt) {
        int diff = Math.abs(guess - target);
        if (attempt == 1) {
            return guess < target ? "Way too low!" : "Way too high!";
        }
        if (diff <= 5)  return guess < target ? "Very close! Just a bit higher"
                : "Very close! Just a bit lower";
        if (diff <= 15) return guess < target ? "Getting warmer! Go higher ⬆"
                : "Getting warmer! Go lower ⬇";
        return guess < target ? "Too low!" : "Too high!";
    }

    static void playRound(Scanner sc, int maxNumber, int maxAttempts) {
        int target = (int)(Math.random() * maxNumber) + 1;
        attempts = 0;
        boolean won = false;

        System.out.println("╔══════════════════════════════════╗");
        System.out.printf( "║  Guess a number between 1 and %3d║%n", maxNumber);
        System.out.printf( "║  You have %2d attempts           ║%n", maxAttempts);
        System.out.println("╚══════════════════════════════════╝");

        while (attempts < maxAttempts) {
            int remaining = maxAttempts - attempts;
            System.out.printf("%nAttempt %d/%d — Guesses left: %d%n",
                    attempts + 1, maxAttempts, remaining);
            System.out.print("Your guess: ");

            while (!sc.hasNextInt()) {
                System.out.print("Numbers only! Try again: ");
                sc.next();
            }
            int guess = sc.nextInt();
            attempts++;

            if (guess < 1 || guess > maxNumber) {
                System.out.printf("Please guess between 1 and %d!%n", maxNumber);
                attempts--; // don't count invalid guess
                continue;
            }

            if (guess == target) {
                won = true;
                break;
            }

            System.out.println(getHint(guess, target, attempts));

            // Binary search tip after halfway
            if (attempts == maxAttempts / 2) {
                System.out.println("Tip: Use binary search — always guess the middle!");
            }
        }

        totalGames++;
        if (won) {
            totalWins++;
            attemptHistory.add(attempts);
            String rating = attempts == 1   ? "PERFECT! First try!"
                    : attempts <= 3   ? "Excellent!"
                    : attempts <= 6   ? "Good job!"
                    : " You got it!";
            System.out.printf("%n Correct! The number was %d!%n", target);
            System.out.printf("You solved it in %d attempt%s. %s%n",
                    attempts, attempts == 1 ? "" : "s", rating);

            // Show math stats
            System.out.printf("Fun fact: %d! = %d%n", attempts, MathUtils.factorial(attempts));
        } else {
            System.out.printf("%n Out of attempts! The number was %d.%n", target);
        }
    }

    static void showStats() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║           GAME STATS             ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.printf("  Games Played: %d%n", totalGames);
        System.out.printf("  Games Won:    %d%n", totalWins);
        System.out.printf("  Win Rate:     %.1f%%%n",
                totalGames > 0 ? (double) totalWins / totalGames * 100 : 0);

        if (!attemptHistory.isEmpty()) {
            int[] hist = attemptHistory.stream().mapToInt(i -> i).toArray();
            System.out.printf("  Best Game:    %d attempts%n",    MathUtils.min(hist));
            System.out.printf("  Average:      %.1f attempts%n",  MathUtils.average(hist));
            System.out.printf("  Std Dev:      %.2f%n",           MathUtils.standardDeviation(hist));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║    NUMBER GUESSING GAME v2.0     ║");
        System.out.println("║    Powered by MathUtils          ║");
        System.out.println("╚══════════════════════════════════╝");

        boolean playing = true;
        while (playing) {
            System.out.println("\nSelect difficulty:");
            System.out.println("  1. Easy    (1–50,  10 attempts)");
            System.out.println("  2. Medium  (1–100, 7 attempts)");
            System.out.println("  3. Hard    (1–200, 6 attempts)");
            System.out.println("  4. Stats");
            System.out.println("  5. Quit");
            System.out.print("Choice: ");

            while (!sc.hasNextInt()) { sc.next(); }
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> playRound(sc, 50,  10);
                case 2 -> playRound(sc, 100, 7);
                case 3 -> playRound(sc, 200, 6);
                case 4 -> showStats();
                case 5 -> { playing = false; System.out.println("Thanks for playing! "); }
                default -> System.out.println("Please choose 1–5.");
            }
        }

        showStats();
        sc.close();
    }
}

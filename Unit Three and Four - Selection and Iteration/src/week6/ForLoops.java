package week6;

import java.util.Scanner;

public class ForLoops {
   public static void main(String[] args) {
      getNumber();
   }

   private static void exampleOne() {
      for (int i = 0, j = 0; i <= 100; i += 2, j -= 2) {
         System.out.println(i + " " + j);
      }
   }

   private static void test() {
      Scanner in = new Scanner(System.in);
      boolean playAgain = true;

      while (playAgain) {
         System.out.println("Game is playing...");
         System.out.println("Game is over");

         System.out.print("Do you want to play again: ");
         playAgain = in.nextLine().equals("Yes");
      }
      System.out.println("Bye");

   }

   private static int getNumber() {
      boolean validInput = false;
      Scanner in = new Scanner(System.in);
      System.out.println("Please enter a number [1,10]: ");
      while (!validInput) {
         String input = in.nextLine();

         try {
            int result = Integer.parseInt(input);
            if (result >= 1 && result <= 10)
               return result;
            else {
               System.out.print("Only integers between 1 and 10 please: ");
            }
         } catch (Exception ex) {
            System.out.print("Only integers between 1 and 10 please: ");
         }
      }

      return 0;

   }
}

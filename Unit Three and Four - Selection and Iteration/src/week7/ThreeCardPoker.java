package week7;

import java.util.Scanner;

public class ThreeCardPoker {
   public static void main(String[] args) {
      int wallet = 500;

      Scanner in = new Scanner(System.in);
      // int bet = getWager(in, 50, 100, wallet);

   }

   private static int getWager(Scanner in, int minBet, int maxBet, int wallet) {
      boolean validInput = false;
      int bet = 0;

      while (!validInput) {
         System.out.print("Please enter a bet [$" + minBet + " - $" + maxBet + "]: ");
         try {
            bet = Integer.parseInt(in.nextLine());

            if (bet > wallet)
               System.out.println("You only have $" + wallet);
            else if (bet > maxBet || bet < minBet)
               System.out.println("Your bet must be between [$" + minBet + " - $" + maxBet + "]");
            else
               validInput = true;
         } catch (Exception ex) {
            System.out.println("Invalid Input!");
         }
      }

      return bet;
   }
}

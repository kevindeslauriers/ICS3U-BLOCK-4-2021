package week7;

import java.util.Scanner;

public class CrazyEights {
   /**
    * Arya Twohey Crazy-Eights Assignment ICS3UA-2021-22
    */

   /* Constants used throughout the program */

   private static final int NUM_SUITS = 4;
   private static final int CARDS_PER_SUIT = 13;
   private static final String ACE = "A";
   private static final String JACK = "J";
   private static final String QUEEN = "Q";
   private static final String KING = "K";
   private static final String TEN = "10";
   private static final String HEARTS = "H";
   private static final String DIAMONDS = "D";
   private static final String CLUBS = "C";
   private static final String SPADES = "S";
   private static final int NUM_CARDS_TO_START = 5;

   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);

      // commented out the following to make testing easier.
      // System.out.println();
      // System.out.println("Press ENTER on your keyboard");

      // System.out.println();

      // startScreen(in);
      // System.out.println();

      gameObjective();
      System.out.println();

      System.out.println();
      System.out.println();
      System.out.println("Dealer: Thank you for playing everyone, good luck!");
      System.out.println();

      int playerPoints = 0, computer1Points = 0, computer2Points = 0;
      while (!gameOver(playerPoints, computer1Points, computer2Points)) {
         String result = playCrazyEights();
         int firstDash = result.indexOf("-");
         int secondDash = result.indexOf("-", firstDash + 1);

         playerPoints += Integer.parseInt(result.substring(0, firstDash));
         computer1Points += Integer.parseInt(result.substring(firstDash + 1, secondDash));
         computer2Points += Integer.parseInt(result.substring(secondDash + 1));

         System.out
               .println("The Current Score is: " + playerPoints + "-" + computer1Points + "-" + computer2Points + "-");
      }
   }

   private static boolean gameOver(int playerPoints, int computer1Points, int computer2Points) {
      return playerPoints >= 100 || computer1Points >= 100 || computer2Points >= 100;
   }

   private static boolean startScreen(Scanner in) {
      boolean validInput = false;
      while (!validInput) {
         String answer = in.nextLine().toUpperCase();

         System.out.println("Welcome to Crazy Eights JAVA Edition!");
         System.out.println();

         if (answer.equals("START") || answer.equals("S")) {
            validInput = true;

         } else {
            System.out.println();
            System.out.print("Please type [S]TART or [N]O on your keyboard");
         }
      }
      return validInput;
   }

   private static boolean gameObjective() {
      boolean readyPlayerOne = true;

      System.out.println("____________     ___________________________________________");
      System.out.println("|8   ♠️ ♠️   |    |GOAL:Try to get rid of all your cards      |");
      System.out.println("|     ♠️    |    |     by placing them in the stockpile.     |");
      System.out.println("|   ♠️   ♠️  |    |                                           |");
      System.out.println("|     ♠️    |    |     Points will be tallied based on how   |");
      System.out.println("|    ♠️ ♠️   |    |     many cards are left in a players deck.|");
      System.out.println("|_________8|    |___________________________________________|");

      return readyPlayerOne;
   }

   public static String playCrazyEights() {

      Scanner in = new Scanner(System.in);

      String computer2Cards = "", computer1Cards = "", playerCards = "";
      for (int i = 0; i < NUM_CARDS_TO_START; i++) {
         playerCards += getCard() + " ";
         computer1Cards += getCard() + " ";
         computer2Cards += getCard() + " ";
      }

      System.out.println("Your cards:     " + playerCards);
      System.out.println("Computer1Cards: XX XX XX XX XX"); // Doesnt show the computerPlayer1 cards
      System.out.println("Computer2Cards: XX XX XX XX XX"); // Doesnt show the computerPlayer2 card
      System.out.println();

      String stockPileCards = getCard();
      while (stockPileCards.indexOf("8") >= 0) {
         stockPileCards = getCard();
      }

      // System.out.println("Dealer: The stock pile card is " + stockPileCards);
      while (playerCards.length() >= 0 || computer1Cards.length() >= 0 || computer2Cards.length() >= 0) {

         String temp = processPlayer(playerCards, stockPileCards, in);
         playerCards = temp.substring(0, temp.indexOf("-"));
         stockPileCards = temp.substring(temp.indexOf("-") + 1);

         /*
          * This is commented out for now - you can bring it back afterwards - only want
          * to work on the player. temp = processComputer(computer1Cards,
          * stockPileCards); computer1Cards = temp.substring(0, temp.indexOf("-"));
          * stockPileCards = temp.substring(temp.indexOf("-") + 1);
          * 
          * temp = processComputer(computer2Cards, stockPileCards); computer2Cards =
          * temp.substring(0, temp.indexOf("-")); stockPileCards =
          * temp.substring(temp.indexOf("-") + 1);
          */

      }
      return playerTallie(playerCards) + "-" + playerTallie(computer1Cards) + "-" + playerTallie(computer2Cards);

   }

   private static String processComputer(String computer1Cards, String stockPileCards) {
      boolean validInput = false;
      String replaceCard = new String("");
      for (int i = 0; i < stockPileCards.length(); i++) {
         String subCard = computer1Cards.substring(i, i + 1);
         if (subCard.equals("8")) {
         }
      }
      return computer1Cards;
   }

   private static String processPlayer(String playerCards, String stockPileCards, Scanner in) { // all the functionality
                                                                                                // for p1 is inside of
                                                                                                // process player
      boolean validInput = false;

      while (!validInput) {
         // this checks if you can play. if you can play then we will ask the user what
         // they want to do. (added the check for an 8)
         if (checkSuit(playerCards, stockPileCards) || checkFace(playerCards, stockPileCards)
               || playerCards.indexOf("8") >= 0) {

            System.out.println("Dealer: The stock pile card is " + stockPileCards);
            System.out.println();
            System.out.println("Dealer: What card would you like to play:");
            String userAnswer = in.nextLine().toUpperCase();

            // Not sure why you care if the player has the stock Pile card in their hand. I
            // changed the line below it used to be if (playerCards.indexOf(stockPileCards)
            // >=
            // 0) {
            if ((playerCards.indexOf(userAnswer) >= 0)
                  && (checkFace(userAnswer, stockPileCards) || checkSuit(userAnswer, stockPileCards))) {
               validInput = true;
               stockPileCards = userAnswer;
               playerCards = playerCards.replace(userAnswer, "");
               System.out.println("Dealer: Top card " + stockPileCards);
               System.out.println();
               System.out.println("Your Hand: " + playerCards);
            }

            // looks like you are checking if they chose to play an eight.
            if (userAnswer.equals("8C") || userAnswer.equals("8S") || userAnswer.equals("8D")
                  || userAnswer.equals("8H")) {

               System.out.println("Dealer: Pick a suit: HEARTS, CLUBS, DIAMONDS, SPADES ");

               String newSuit = in.nextLine().toUpperCase();

               if (newSuit.equals("DIAMONDS")) {
                  System.out
                        .println("The new top card is " + stockPileCards.replace(stockPileCards.substring(0, 2), "8D"));
                  stockPileCards = "8D";
               } else if (newSuit.equals("SPADES")) {
                  System.out
                        .println("The new top card is " + stockPileCards.replace(stockPileCards.substring(0, 2), "8S"));
                  stockPileCards = "8S";
               } else if (newSuit.equals("CLUBS")) {
                  System.out
                        .println("The new top card is " + stockPileCards.replace(stockPileCards.substring(0, 2), "8C"));
                  stockPileCards = "8C";
               } else {
                  if (newSuit.equals("HEARTS")) {
                     System.out.println(
                           "The new top card is " + stockPileCards.replace(stockPileCards.substring(0, 2), "8H "));
                     stockPileCards = "8H";
                  }
               }
               playerCards = playerCards.replace(userAnswer, "");
               validInput = true;
               System.out.println("Your hand: " + playerCards);

            }
         }
      }
      return playerCards + "-" + stockPileCards;

   }

   private static boolean checkFace(String playerCards, String stockPileCards) {
      // what if this is a 10???? then go to 2 not 1. Check the length of the
      // stockPile. a length of 3 is a 10
      return playerCards.contains(stockPileCards.substring(0, 1));
   }

   private static boolean checkSuit(String playerCards, String stockPileCards) {
      // what if this is a 10???? then start at 2 not 1. Check the length of the
      // stockPile. a length of 3 is a 10
      return playerCards.contains(stockPileCards.substring(1, stockPileCards.length() - 1));
   }

   private static int playerTallie(String playerCards) {
      int p1Points = 0;

      /**
       * Cards 2 through 9 excluding 8 are worth their face value Ace are worth 1
       * eights are worth 50 10 to kind worth 10
       */

      for (int i = 0; i < playerCards.length(); i++) {

         if (playerCards.indexOf(ACE) >= 0) {
            p1Points += 1;
         } else if (playerCards.indexOf(KING) >= 0) {
            p1Points += 10;
         } else if (playerCards.indexOf(QUEEN) >= 0) {
            p1Points += 10;
         } else if (playerCards.indexOf(JACK) >= 0) {
            p1Points += 10;
         } else if (playerCards.indexOf(TEN) >= 0) {
            p1Points += 10;
         } else if (playerCards.indexOf("2") >= 0) {
            p1Points += 2;
         } else if (playerCards.indexOf("3") >= 0) {
            p1Points += 3;
         } else if (playerCards.indexOf("4") >= 0) {
            p1Points += 4;
         } else if (playerCards.indexOf("5") >= 0) {
            p1Points += 5;
         } else if (playerCards.indexOf("6") >= 0) {
            p1Points += 6;
         } else if (playerCards.indexOf("7") >= 0) {
            p1Points += 7;
         } else {
            p1Points += 9;
         }
      }
      return p1Points;
   }

   public static String getCard() {
      String card = getFace() + getSuit();
      return card;
   }

   private static String getSuit() {
      int suit = (int) (Math.random() * NUM_SUITS);
      if (suit == 0)
         return HEARTS;
      else if (suit == 1)
         return DIAMONDS;
      else if (suit == 2)
         return CLUBS;
      else
         return SPADES;
   }

   public static String getFace() {
      int suit = (int) (Math.random() * CARDS_PER_SUIT);
      if (suit >= 2 && suit <= 10)
         return suit + "";
      else if (suit == 1)
         return ACE;
      else if (suit == 11)
         return JACK;
      else if (suit == 12)
         return QUEEN;
      else
         return KING;
   }
}
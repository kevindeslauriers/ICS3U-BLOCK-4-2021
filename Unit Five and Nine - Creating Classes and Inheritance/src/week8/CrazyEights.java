package week8;

import java.util.Scanner;

public class CrazyEights {
   /**
    * Arya Twohey Crazy-Eights Assignment ICS3UA-2021-22
    */

   // Colours used throughout the program
   public static final String g = "\u001B[32m";
   public static final String p = "\u001B[35m";
   public static final String b = "\u001B[36m";
   public static final String r = "\u001B[31m";
   public static final String y = "\u001B[33m";
   public static final String reset = "\u001B[37m";

   // Constants used throughout the program

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
   final static String VALID_CARDS = "AS2S3S4S5S6S7S8S9S10SJSQSKSAC2C3C4C5C6C7C8C9C10CJCQCKCAD2D3D4D5D6D7D8D9D10DJDQDKDAH2H3H4H5H6H7H8H9H10HJHQHKH";

   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      // Print statements here are strictly for formatting
      System.out.println();
      System.out.println(b + "Press ENTER on your keyboard" + reset); // Prompts the user to press ENTER on their
                                                                      // keyboard, to verify that they are ready to play
      System.out.println();
      startScreen(in);
      System.out.println();

      gameObjective(); // Displays the basic rules of the game, and a cool graphic
      System.out.println();

      System.out.println();
      System.out.println();
      System.out.println(g + "Dealer: Thank you for playing everyone, good luck!" + reset);
      System.out.println();

      int playerPoints = 0, computer1Points = 0, computer2Points = 0;
      while (!gameOver(playerPoints, computer1Points, computer2Points)) { // Calls on gameOver and passes through
                                                                          // playerOne cards, computerOne cards, and
                                                                          // computerTwo cards
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

   private static boolean gameOver(int playerPoints, int computer1Points, int computer2Points) { // gameOver, which
                                                                                                 // states that the game
                                                                                                 // is only over, once
                                                                                                 // player one reaches
                                                                                                 // 100 points,
      return playerPoints >= 100 || computer1Points >= 100 || computer2Points >= 100;
   }

   private static boolean startScreen(Scanner in) { // The startscreen that prompts the user to press [S]TART to play or
                                                    // [N]O to stay on the startscreen
      boolean validInput = false;
      while (!validInput) {
         String answer = in.nextLine().toUpperCase();

         System.out.println(b + "Welcome to Crazy Eights JAVA Edition!");
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

      System.out.println("Your cards:     " + y + playerCards + reset);
      System.out.println("Computer1Cards: " + y + computer1Cards + reset); // Doesnt show the computerPlayer1 cards
      System.out.println("Computer2Cards: " + y + computer2Cards + reset); // Doesnt show the computerPlayer2 card
      System.out.println();

      String stockPileCards = getCard();
      while (stockPileCards.indexOf("8") >= 0) {
         stockPileCards = getCard();
      }

      while (playerCards.length() >= 0 || computer1Cards.length() >= 0 || computer2Cards.length() >= 0) {

         String temp = processPlayer(playerCards, stockPileCards, in); // Calling the processPlayer method
         playerCards = temp.substring(0, temp.indexOf("-"));
         stockPileCards = temp.substring(temp.indexOf("-") + 1);

         temp = processComputer(computer1Cards, stockPileCards); // Calling the processComputer1 method
         computer1Cards = temp.substring(0, temp.indexOf("-"));
         stockPileCards = temp.substring(temp.indexOf("-") + 1);

         temp = processComputer(computer2Cards, stockPileCards); // Calling the processComputer2 method
         computer2Cards = temp.substring(0, temp.indexOf("-"));
         stockPileCards = temp.substring(temp.indexOf("-") + 1);

      }
      return playerTallie(playerCards) + "-" + playerTallie(computer1Cards) + "-" + playerTallie(computer2Cards);

   }

   private static String processPlayer(String playerCards, String stockPileCards, Scanner in) {
      boolean validInput = false;
      String stockPileSuit = stockPileCards.substring(stockPileCards.length() - 1); // A variable that conducts the
                                                                                    // checking of a suit to see if the
                                                                                    // card is valid
      String stockPileFace = stockPileCards.substring(0, stockPileCards.length() - 1); // A variable that conducts the
                                                                                       // checking of a suit to see if
                                                                                       // the card is valiid

      int playerDraws = 0; // Player draws, which will help with determing how many times a player has
                           // drawn
      while ((playerCards.indexOf(stockPileSuit) < 0 && playerCards.indexOf(stockPileFace) < 0
            && playerCards.indexOf("8") < 0 && playerDraws < 5)) { // If the suit and face of the stockpile is not
                                                                   // matched in your deck
         if (playerDraws >= 5) { // is not matched in your deck, && you havent gone over the 5 card draw limit
            System.out.println(r + "Dealer: Player one has drawn too many card" + reset); // If you draw too many cards,
                                                                                          // you will be told by the
                                                                                          // deal // and you don't have
                                                                                          // an 8
         }
         playerCards += getCard() + " "; // If your playerDraws is less than 5, you will get a new card until you can
                                         // play
         playerDraws++; // playerDraws goes up by one, every time you draw more cards

      }

      if (checkSuit(playerCards, stockPileCards) == true || checkFace(playerCards, stockPileCards) == true) { // Calls
                                                                                                              // on
                                                                                                              // checkFace
                                                                                                              // &&
                                                                                                              // checkSuit
                                                                                                              // to see
                                                                                                              // if they
                                                                                                              // are
                                                                                                              // true

         System.out.println(g + "Dealer: The stock pile card is " + reset + y + stockPileCards + reset); // Displays the
                                                                                                         // stock pile
                                                                                                         // card
         System.out.println();
         System.out.println(g + "Dealer: What card would you like to play: (" + playerCards + ")" + reset); // Asks the
                                                                                                            // user, to
                                                                                                            // see what
                                                                                                            // card they
                                                                                                            // want to
                                                                                                            // play

         while (!validInput) { // A while loop that plays as long as validInput is true
            String userAnswer = in.nextLine().toUpperCase(); // Initializies a userAnswer variable (there are multiple
                                                             // userAnswers used throughout the program)

            if (VALID_CARDS.indexOf(userAnswer) < 0) { // If the card that is played does not exist in VALID_CARDS (All
                                                       // possible cards you can play), the dealer will say so
               System.out.println(r + "Dealer: Not a valid card " + reset + y + userAnswer + reset);
            } else if (playerCards.indexOf(userAnswer) < 0) { // If the card the user has played is not in their deck,
                                                              // the dealer will say so
               System.out.println(r + "Dealer: You do not have that card " + reset + y + userAnswer + reset);
            } else if (checkSuit(userAnswer, stockPileCards) || checkFace(userAnswer, stockPileCards)) { // If the suit
                                                                                                         // or face
                                                                                                         // mathces the
                                                                                                         // stock pile
                                                                                                         // card, the
                                                                                                         // card is
                                                                                                         // valid and
                                                                                                         // validInput
                                                                                                         // is set to
                                                                                                         // true
               validInput = true;

               /* These are the "Rules of Eight" */

               if (userAnswer.equals("8C") || userAnswer.equals("8S") || userAnswer.equals("8D")
                     || userAnswer.equals("8H")) { // If the user inputs that they want to play an 8S, 8D, 8C, or 8H, we
                                                   // prompt them to pick a suit
                  System.out.println(g + "Dealer: Pick a suit: HEARTS, CLUBS, DIAMONDS, SPADES " + reset);

                  String newSuit = in.nextLine().toUpperCase(); // Initialization of a newSuit variable, to update the
                                                                // stock pile card, once the suit of 8 is chosen

                  if (newSuit.equals("DIAMONDS")) { // Conditions for newSuit variable if the user choses DIAMONDS
                     playerCards = playerCards.replace(userAnswer, "").trim().replace("  ", " "); // replacing
                                                                                                  // userAnswer inside
                                                                                                  // playerCards to an
                                                                                                  // empty string and
                                                                                                  // replacing all
                                                                                                  // double spaces, with
                                                                                                  // single spaces for
                                                                                                  // formatting purposes
                     stockPileCards = "8D"; // The top card is now 8D
                     System.out.println(y + stockPileCards.replace(stockPileCards.substring(0, 2), "8D" + reset));

                  } else if (newSuit.equals("SPADES")) { // Conditions for newSuit variable if the user choses SPADES
                     playerCards = playerCards.replace(userAnswer, "").trim().replace("  ", " "); // replacing
                                                                                                  // userAnswer inside
                                                                                                  // playerCards to an
                                                                                                  // empty string and
                                                                                                  // replacing all
                                                                                                  // double spaces, with
                                                                                                  // single spaces for
                                                                                                  // formatting purposes
                     stockPileCards = "8S"; // The top card is now 8S
                     System.out.println(y + stockPileCards.replace(stockPileCards.substring(0, 2), "8S" + reset));

                  } else if (newSuit.equals("CLUBS")) { // Conditions for newSuit variable if the user choses CLUBS
                     playerCards = playerCards.replace(userAnswer, "").trim().replace("  ", " "); // replacing
                                                                                                  // userAnswer inside
                                                                                                  // playerCards to an
                                                                                                  // empty string and
                                                                                                  // replacing all
                                                                                                  // double spaces, with
                                                                                                  // single spaces for
                                                                                                  // formatting purposes
                     stockPileCards = "8C"; // The top card is now 8C
                     System.out.println(y + stockPileCards.replace(stockPileCards.substring(0, 2), "8C" + reset));

                  } else {
                     if (newSuit.equals("HEARTS")) { // Conditions for newSuit variable if the user choses HEARTS
                        playerCards = playerCards.replace(userAnswer, "").trim().replace("  ", " "); // replacing
                                                                                                     // userAnswer
                                                                                                     // inside
                                                                                                     // playerCards to
                                                                                                     // an empty string
                                                                                                     // and replacing
                                                                                                     // all double
                                                                                                     // spaces, with
                                                                                                     // single spaces
                                                                                                     // for formatting
                                                                                                     // purposes
                        stockPileCards = "8H"; // The top card is now 8H
                        System.out.println(y + stockPileCards.replace(stockPileCards.substring(0, 2), "8H" + reset));
                     }
                  }
               } else {
                  // not an eight
                  playerCards = playerCards.replace(userAnswer, "").trim().replace("  ", " "); // If the user input is
                                                                                               // not at eight, replace
                                                                                               // userAnswer with "" to
                                                                                               // discard userAnswer
                                                                                               // from the deck

                  stockPileCards = userAnswer; // The top card is now userAnswer
               }
            }
         }
      }
      return playerCards + "-" + stockPileCards;
   }

   private static String processComputer1(String computer1Cards, String stockPileCards) {
      /**
       * Current processComputer bugs Not changing the stockpile card when computer1
       * plays a card
       */
      /* Reusing stockPileSuit & stockPileFace from processPlayer */
      String stockPileSuit = stockPileCards.substring(stockPileCards.length() - 1);
      String stockPileFace = stockPileCards.substring(0, stockPileCards.length() - 1);
      int computerDraws = 0;

      while (computer1Cards.indexOf(stockPileSuit) < 0 && computer1Cards.indexOf(stockPileFace) < 0
            && computerDraws < 5) { // A while loop that determines whether or not to draw a card to computer1's
                                    // deck
         System.out.println(b + "Dealer: The computer1 has drawn a card... " + reset); // Displays that computer1 is
                                                                                       // drawing a card
         System.out.println();
         computer1Cards += getCard() + " ";
         System.out.println(b + "Dealer: Computer1 Cards: " + computer1Cards); // Once computer1 has drawn a card, the
                                                                               // dealer will display the new deck
         System.out.println();
         computerDraws += 1;

         if (computerDraws >= 5) {
            System.out.println("Dealer: Computer1 has drawn too many cards"); // If computer1 draws 5 or more cards,the
                                                                              // dealer will display "Computer1 has
                                                                              // drawn too many cards"

            if (checkSuit(computer1Cards, stockPileCards) == true
                  || checkFace(computer1Cards, stockPileCards) == true) {
               for (int i = 0; i < computer1Cards.length() - 1; i++) { // A for loop that goes through the
                                                                       // computer1Cards

                  String comCard = computer1Cards.substring(i, i + computer1Cards.indexOf(" "));

                  if (comCard.indexOf(stockPileFace) >= 0 && !checkSuit(computer1Cards, stockPileCards)) {
                     getHighSuit(computer1Cards, VALID_CARDS); // If there is more than one of a face in a deck, the
                                                               // computer1 will get the highest suit

                     stockPileCards = stockPileCards.replace(stockPileCards, comCard); // Once the highestSuit is
                                                                                       // picked, the computer replaces
                                                                                       // the top card they have played,
                                                                                       // with comCard
                     computer1Cards.replace(comCard, " "); // essientially discarding the card from the deck
                     System.out.println(g + "Dealer: Computer1 has played the :" + reset + y + stockPileCards + reset); // Displays
                                                                                                                        // the
                                                                                                                        // card
                                                                                                                        // that
                                                                                                                        // computer1
                                                                                                                        // has
                                                                                                                        // played
                     System.out.println();
                     System.out.println(
                           g + "Dealer: Computer1 Hand: ( " + reset + y + computer1Cards + reset + g + ")" + reset); // Displays
                                                                                                                     // the
                                                                                                                     // new
                                                                                                                     // computer1Hand

                  } else if (comCard.indexOf("8") >= 0 && !checkFace(computer1Cards, stockPileCards)
                        && !checkSuit(computer1Cards, stockPileCards)) { // If rule 1 and 2 cannot be met, result to an
                                                                         // 8
                     comCard = "8" + comCard.substring(comCard.length() - 1);
                     stockPileCards = stockPileCards.replace(stockPileCards, comCard);
                     computer1Cards.replace(comCard, " ");

                     System.out.println(g + "Dealer: Computer1 has played the : " + reset + y + stockPileCards + reset); // displays
                                                                                                                         // the
                                                                                                                         // type
                                                                                                                         // of
                                                                                                                         // 8
                                                                                                                         // that
                                                                                                                         // computer1
                                                                                                                         // has
                                                                                                                         // played
                     System.out.println();
                     System.out.println(
                           g + "Dealer: Computer1 Hand: (" + reset + y + computer1Cards + reset + g + ")" + reset); // shows
                                                                                                                    // the
                                                                                                                    // computer1
                                                                                                                    // deck
                                                                                                                    // without
                                                                                                                    // the
                                                                                                                    // 8
                  }
               }
            }
         }

      }
      return computer1Cards + "-" + stockPileCards;

   }

   private static String processComputer(String computerCards, String stockPileCards) {
      /**
       * Current processComputer2 bugs Not changing the stockpile card when computer
       * plays a card
       */

      // Re-initializing stockPileSuit & stockPileFace to be used in the
      // processComputer2 method
      String stockPileSuit = stockPileCards.substring(stockPileCards.length() - 1);
      String stockPileFace = stockPileCards.substring(0, stockPileCards.length() - 1);
      int computerDraws = 0;

      while (computerCards.indexOf(stockPileSuit) < 0 && computerCards.indexOf(stockPileFace) < 0
            && computerDraws < 5) { // A while loop that contiusosly checks whether the computer doesnt have the
                                    // same face same suit and makes sure they have not drawn 5 cards yet
         System.out.println(b + "Dealer: The computer2 has drawn a card... " + reset);
         System.out.println();
         computerCards += getCard() + " "; // If computerDraws is less than 5 and the computer2 hand does not posses
                                           // the same suit or face, draw another card
         System.out.println(b + "Computer2 Cards: " + computerCards); // Displays the new computer2 hand
         System.out.println();
         computerDraws += 1; // Add one to computerDraws after computerDraws goes up by one

         if (computerDraws >= 5) { // If computerDraws goes over the maximum5 limit, the dealer will say do
            System.out.println(r + "Dealer: Computer2 has drawn too many cards" + reset);

            if (checkSuit(computerCards, stockPileCards) == true || checkFace(computerCards, stockPileCards) == true) {
               for (int i = 0; i < computerCards.length() - 1; i++) {

                  String comCard = computerCards.substring(i, i + computerCards.indexOf(" "));

                  if (comCard.indexOf(stockPileFace) >= 0 && !checkSuit(computerCards, stockPileCards)) {
                     getHighSuit(computerCards, VALID_CARDS);

                     stockPileCards = stockPileCards.replace(stockPileCards, comCard);
                     computerCards.replace(comCard, " "); // essientially discarding the card from the deck
                     System.out.println(g + "Dealer: Computer2 has played the :" + reset + y + stockPileCards + reset);
                     System.out.println();
                     System.out.println(
                           g + "Dealer: Computer2 Hand: ( " + reset + y + computerCards + reset + g + ")" + reset);

                  } else if (comCard.indexOf("8") >= 0 && !checkFace(computerCards, stockPileCards)
                        && !checkSuit(computerCards, stockPileCards)) { // If rule 1 and 2 cannot be met, result to an
                                                                        // 8
                     comCard = "8" + comCard.substring(comCard.length() - 1); // comCard will now equal "8" + the suit
                                                                              // of the comCard
                     stockPileCards = stockPileCards.replace(stockPileCards, comCard); // replace the top card with
                                                                                       // comCard
                     computerCards = computerCards.replace(comCard, " "); // Remove the 8 from the deck

                     System.out.println(g + "Dealer: Computer2 has played the : " + reset + y + stockPileCards + reset); // Display
                                                                                                                         // what
                                                                                                                         // card
                                                                                                                         // computer2
                                                                                                                         // has
                                                                                                                         // played
                     System.out.println();
                     System.out.println(
                           g + "Dealer: Computer2 Hand: (" + reset + y + computerCards + reset + g + ")" + reset); // Display
                                                                                                                   // computer2's
                                                                                                                   // new
                                                                                                                   // deck
                  }
               }
            }
         }
      }
      return computerCards + "-" + stockPileCards;
   }

   private static boolean checkFace(String userAnswer, String stockPileCards) {

      return userAnswer.contains(stockPileCards.substring(0, 1));
   }

   private static boolean checkSuit(String userAnswer, String stockPileCards) {

      return userAnswer.contains(stockPileCards.substring(1, stockPileCards.length() - 1));
   }

   private static int playerTallie(String playerCards) {
      int p1Points = 0;

      /**
       * Cards 2 through 9 excluding 8, are worth their face value Aces are worth 1
       * point 10, JACK, QUEEN & KING are worth 10 points Eghts are worth 50 points
       */

      for (int i = 0; i < playerCards.length(); i++) {
         String pointCheck = playerCards.substring(i, i + 1);
         /*
          * Iterating through playerCards, computer1Cards & computer2Card to tallie up
          * the points
          */
         if (pointCheck.equals(TEN) || pointCheck.equals("J") || pointCheck.equals("Q") || pointCheck.equals("K")) {
            p1Points += 10;
         }
         if (pointCheck.equals("2")) {
            p1Points += 2;
         }
         if (pointCheck.equals("3")) {
            p1Points += 3;
         }
         if (pointCheck.equals("4")) {
            p1Points += 4;
         }
         if (pointCheck.equals("5")) {
            p1Points += 5;
         }
         if (pointCheck.equals("6")) {
            p1Points += 6;
         }
         if (pointCheck.equals("7"))
            p1Points += 7;
         if (pointCheck.equals("8")) {
            p1Points += 50;
         }
         if (pointCheck.equals("9")) {
            p1Points += 9;
         }
         if (pointCheck.equals("A")) {
            p1Points += 1;
         }
      }
      return p1Points;
   }/*
     * A method that determines the highest number of suits that computer1 or
     * computer2 has, in order to determine the card that is played
     */

   public static String getHighSuit(String computer1Cards, String VALID_CARDS) {
      int D = 0;
      int S = 0;
      int C = 0;
      int H = 0;

      int maxNumber = 0;

      for (int i = 0; i < computer1Cards.length(); i++) {
         String cardSuit = computer1Cards.substring(i, i + 1);

         if (cardSuit.equals("D") && !cardSuit.equals("8") && !("D").equals(VALID_CARDS)) {
            D++;
         } else if (cardSuit.equals("H") && !cardSuit.equals("8") && !("H").equals(VALID_CARDS)) {
            H++;
         } else if (cardSuit.equals("C") && !cardSuit.equals("8") && !("C").equals(VALID_CARDS)) {
            C++;
         } else if (cardSuit.equals("S") && !cardSuit.equals("8") && !("S").equals(VALID_CARDS)) {
            S++;
         }
      }

      maxNumber = Math.max(Math.max(H, D), Math.max(S, C));

      if (D == maxNumber) {
         return "D";

      } else if (H == maxNumber) {
         return "H";

      } else if (C == maxNumber) {
         return "C";

      } else {
         return "S";
      }

   }

   public static String getCard() { // Get card, which is meant to deal cards to player, computer1 & computer2

      String card = getFace() + getSuit();
      return card;
   }

   private static String getSuit() { // Gets a random out of the 4 possible suits for card, for each of the 5 cards
                                     // in a players deck

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

   public static String getFace() { // Gets a random out of the possible face's for each of the 5 cards in a players
                                    // deck

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

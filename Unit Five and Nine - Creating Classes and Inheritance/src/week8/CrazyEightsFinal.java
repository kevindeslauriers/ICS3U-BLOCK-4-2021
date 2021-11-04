package week8;

import java.util.Scanner;

public class CrazyEightsFinal {
   private static final double NUM_SUITS = 4;
   private static final double CARDS_PER_SUIT = 13;
   private static final String HEARTS = "H";
   private static final String CLUBS = "C";
   private static final String SPADES = "S";
   private static final String DIAMONDS = "D";
   private static final String ACE = "A";
   private static final String JACK = "J";
   private static final String QUEEN = "Q";
   private static final String KING = "K";
   private static final int NUM_CARDS_TO_START = 5;
   private static final String VALID_CARDS = "AS2S3S4S5S6S7S8S9S10SJSQSKSAC2C3C4C5C6C7C8C9C10CJCQCKCAD2D3D4D5D6D7D8D9D10DJDQDKDAH2H3H4H5H6H7H8H9H10HJHQHKH";

   public static void main(String[] args) {
      int pointsPlayer = 0, pointsThisC = 0, pointsC2 = 0;

      while (!gameOver(pointsPlayer, pointsThisC, pointsC2)) {
         String result = playRound();
         int firstDash = result.indexOf("-");
         int secondDash = result.lastIndexOf("-");
         pointsPlayer += Integer.parseInt(result.substring(0, firstDash));
         pointsThisC += Integer.parseInt(result.substring(firstDash + 1, secondDash));
         pointsC2 += Integer.parseInt(result.substring(secondDash + 1));

         System.out.println(pointsPlayer + " " + pointsThisC + " " + pointsC2);
      }

      if (Math.min(Math.min(pointsPlayer, pointsThisC), pointsC2) == pointsPlayer) {
         System.out.println("You are the winner");
      } else if (Math.min(Math.min(pointsPlayer, pointsThisC), pointsC2) == pointsThisC) {
         System.out.println("Computer 1 won.");
      } else if (Math.min(Math.min(pointsPlayer, pointsThisC), pointsC2) == pointsC2) {
         System.out.println("Computer 2 won.");
      }
   }

   private static String playRound() {
      Scanner in = new Scanner(System.in);
      String playerHand = "";
      String thisComputerHand = "";
      String computer2Hand = "";
      String temp = "";

      String initialDeckCard = "";

      for (int i = 0; i < NUM_CARDS_TO_START; i++) {

         playerHand += getCard() + " ";
         thisComputerHand += getCard() + " ";
         computer2Hand += getCard() + " ";
      }
      System.out.println("Hand:" + " " + playerHand);

      initialDeckCard = getCard();
      if (initialDeckCard.indexOf("8") >= 0) {
         initialDeckCard = getCard();
         System.out.println("Deck: " + initialDeckCard);
      } else {
         System.out.println("Deck: " + initialDeckCard);
      }

      while (playerHand.length() > 0 && computer2Hand.length() > 0 && thisComputerHand.length() > 0) {
         temp = processPlayer(in, playerHand, initialDeckCard);
         playerHand = temp.substring(0, temp.indexOf("-"));
         initialDeckCard = temp.substring(temp.indexOf("-") + 1);

         temp = processComputer(thisComputerHand, initialDeckCard, playerHand, computer2Hand);
         thisComputerHand = temp.substring(0, temp.indexOf("-"));
         initialDeckCard = temp.substring(temp.indexOf("-") + 1);

         temp = processComputer(computer2Hand, initialDeckCard, playerHand, thisComputerHand);
         computer2Hand = temp.substring(0, temp.indexOf("-"));
         initialDeckCard = temp.substring(temp.indexOf("-") + 1);

      }

      String points = playerTotal(playerHand) + "-" + playerTotal(thisComputerHand) + "-" + playerTotal(computer2Hand);
      return points;

   }

   private static int playerTotal(String playerHand) {
      int points = 0;
      for (int i = 0; i < playerHand.length(); i++) {
         String search = playerHand.substring(i, i + 1);

         if (search.equals(ACE)) {
            points += 1;
         } else {
            points += 0;
         }
         if (search.equals("2")) {
            points += 2;
         } else {
            points += 0;
         }
         if (search.equals("3")) {
            points += 3;
         } else {
            points += 0;
         }
         if (search.equals("4")) {
            points += 4;
         } else {
            points += 0;
         }
         if (search.equals("5")) {
            points += 5;
         } else {
            points += 0;
         }
         if (search.equals("6")) {
            points += 6;
         } else {
            points += 0;
         }
         if (search.equals("7")) {
            points += 7;
         } else {
            points += 0;
         }
         if (search.equals("8")) {
            points += 50;
         } else {
            points += 0;
         }
         if (search.equals("9")) {
            points += 9;
         } else {
            points += 0;
         }
         if (search.equals("10")) {
            points += 10;
         } else {
            points += 0;
         }
         if (search.equals(JACK)) {
            points += 10;
         } else {
            points += 0;
         }
         if (search.equals(QUEEN)) {
            points += 10;
         } else {
            points += 0;
         }
         if (search.equals(KING)) {
            points += 10;
         } else {
            points += 0;
         }

      }
      return points;
   }

   private static String processComputer(String thisComputerHand, String initialDeckCard, String playerHand,
         String computer2Hand) {
      boolean validInput = false;
      String deckCardSuit = initialDeckCard.substring(initialDeckCard.length() - 1);
      String deckCardRank = initialDeckCard.substring(0, initialDeckCard.length() - 1);

      while (!validInput) {

         if (playerHand.indexOf(" ") < 0 || computer2Hand.indexOf(" ") < 0) {
            if (thisComputerHand.indexOf(deckCardRank) >= 0
                  && !(thisComputerHand.substring(thisComputerHand.indexOf(deckCardRank)).equals("8"))) {
               if (deckCardRank.equals("10")) {
                  initialDeckCard = thisComputerHand.substring(thisComputerHand.indexOf(deckCardRank),
                        thisComputerHand.indexOf(deckCardRank) + 3);
                  thisComputerHand = thisComputerHand.substring(0, thisComputerHand.indexOf(deckCardRank))
                        + thisComputerHand.substring(thisComputerHand.indexOf(deckCardRank) + 3);
                  System.out.println("The opponents/computers played.");
                  System.out.println("New Deck: " + initialDeckCard);
                  // System.out.println("Computer Hand: " + thisComputerHand);
               } else {
                  initialDeckCard = thisComputerHand.substring(thisComputerHand.indexOf(deckCardRank),
                        thisComputerHand.indexOf(deckCardRank) + 2);
                  thisComputerHand = thisComputerHand.substring(0, thisComputerHand.indexOf(deckCardRank))
                        + thisComputerHand.substring(thisComputerHand.indexOf(deckCardRank) + 2);
                  System.out.println("The opponent/computer played.");
                  System.out.println("New Deck: " + initialDeckCard);
                  // System.out.println("Computer Hand: " + thisComputerHand);
               }
               validInput = true;

            } else if (thisComputerHand.indexOf("8") >= 0) {
               String suit = getHighSuit(thisComputerHand, "X");
               initialDeckCard = "8" + suit;
               thisComputerHand = thisComputerHand.substring(0, thisComputerHand.indexOf("8"))
                     + thisComputerHand.substring(thisComputerHand.indexOf("8") + 2);
               System.out.println("The opponents/computers played.");
               System.out.println("New Deck: " + initialDeckCard);
               // System.out.println("Computer Hand: " + thisComputerHand);
               validInput = true;

            }
         }

         if (thisComputerHand.indexOf(deckCardSuit) >= 0
               && !(thisComputerHand.substring((thisComputerHand.indexOf(deckCardSuit) - 1),
                     (thisComputerHand.indexOf(deckCardSuit)))).equals("8")) {
            if (thisComputerHand
                  .substring((thisComputerHand.indexOf(deckCardSuit) - 1), (thisComputerHand.indexOf(deckCardSuit)))
                  .equals("10")) {
               initialDeckCard = "10" + deckCardSuit;
               thisComputerHand = thisComputerHand.substring(0, thisComputerHand.indexOf(initialDeckCard))
                     + thisComputerHand.substring(thisComputerHand.indexOf(deckCardSuit) + 2);
               System.out.println("The opponent/computer played.");
               System.out.println("New Deck: " + initialDeckCard);
               // System.out.println("Computer Hand: " + thisComputerHand);
            } else {
               initialDeckCard = thisComputerHand.substring(thisComputerHand.indexOf(deckCardSuit) - 1,
                     thisComputerHand.indexOf(deckCardSuit) + 1);
               thisComputerHand = thisComputerHand.substring(0, thisComputerHand.indexOf(initialDeckCard))
                     + (thisComputerHand.substring(thisComputerHand.indexOf(deckCardSuit) + 2));
               System.out.println("The opponent/computer played.");
               System.out.println("New Deck: " + initialDeckCard);
               // System.out.println("Computer Hand: " + thisComputerHand);
            }

            validInput = true;

         } else if (thisComputerHand.indexOf(deckCardRank) >= 0
               && !(thisComputerHand.substring(thisComputerHand.indexOf(deckCardRank)).equals("8"))) {
            if (deckCardRank.equals("10")) {
               initialDeckCard = thisComputerHand.substring(thisComputerHand.indexOf(deckCardRank),
                     thisComputerHand.indexOf(deckCardRank) + 3);
               thisComputerHand = thisComputerHand.substring(0, thisComputerHand.indexOf(deckCardRank))
                     + thisComputerHand.substring(thisComputerHand.indexOf(deckCardRank) + 2);
               System.out.println("The opponent/computer played.");
               System.out.println("New Deck: " + initialDeckCard);
               // System.out.println("Computer Hand: " + thisComputerHand);
            } else {
               initialDeckCard = thisComputerHand.substring(thisComputerHand.indexOf(deckCardRank),
                     thisComputerHand.indexOf(deckCardRank) + 2);
               thisComputerHand = thisComputerHand.substring(0, thisComputerHand.indexOf(deckCardRank))
                     + thisComputerHand.substring(thisComputerHand.indexOf(deckCardRank) + 2);
               System.out.println("The opponent/computer played.");
               System.out.println("New Deck: " + initialDeckCard);
               // System.out.println("Computer Hand: " + thisComputerHand);
            }
            validInput = true;

         } else if (thisComputerHand.indexOf("8") >= 0) {
            String suit = getHighSuit(thisComputerHand, "X");
            initialDeckCard = "8" + suit;
            thisComputerHand = thisComputerHand.substring(0, thisComputerHand.indexOf("8"))
                  + thisComputerHand.substring(thisComputerHand.indexOf("8") + 3);
            System.out.println("The opponents/computers played.");
            System.out.println("New Deck: " + initialDeckCard);
            // System.out.println("Computer Hand: " + thisComputerHand);
            validInput = true;

         } else {
            if (thisComputerHand.indexOf(deckCardSuit) < 0 && thisComputerHand.indexOf(deckCardRank) < 0
                  && playerHand.indexOf("8") < 0) {
               System.out.println("The computer skipped it's turn");
               validInput = true;
            }
         }
      }
      return thisComputerHand + "-" + initialDeckCard;

   }

   private static String getHighSuit(String hand, String bannedSuit) {

      int numH = 0;
      int numD = 0;
      int numC = 0;
      int numS = 0;

      int maxSuit = 0;

      for (int i = 0; i < hand.length(); i++) {
         if (hand.substring(i, i + 1).equals("H") && !hand.substring(i - 1, i).equals("8")
               && !("H".equals(bannedSuit))) {
            numH++;
         } else if (hand.substring(i, i + 1).equals("D") && !hand.substring(i - 1, i).equals("8")
               && !("D".equals(bannedSuit))) {
            numD++;
         } else if (hand.substring(i, i + 1).equals("S") && !hand.substring(i - 1, i).equals("8")
               && !("S".equals(bannedSuit))) {
            numS++;
         } else if (hand.substring(i, i + 1).equals("C") && !hand.substring(i - 1, i).equals("8")
               && !("C".equals(bannedSuit))) {
            numC++;
         }
      }

      maxSuit = Math.max(Math.max(numH, numC), Math.max(numD, numS));
      if (numH == maxSuit) {
         return "H";
      } else if (numD == maxSuit) {
         return "D";
      } else if (numC == maxSuit) {
         return "C";
      } else if (numS == maxSuit) {
         return "S";
      }

      return null;
   }

   private static String processPlayer(Scanner in, String playerHand, String initialDeckCard) {
      boolean validInput = false;
      while (!validInput) {
         String deckCardSuit = initialDeckCard.substring(initialDeckCard.length() - 1);
         String deckCardRank = initialDeckCard.substring(0, initialDeckCard.length() - 1);
         if (playerHand.indexOf(deckCardSuit) >= 0 || playerHand.indexOf(deckCardRank) >= 0
               || playerHand.indexOf("8") >= 0) {
            System.out.print("Please enter the card to play: (ex. 7H KD): ");
            String cards = in.nextLine().toUpperCase();
            String playedCardSuit = cards.substring(cards.length() - 1);
            String playedCardRank = cards.substring(0, cards.length() - 1);
            if (VALID_CARDS.indexOf(cards) < 0) {
               System.out.println("Not a valid card: " + cards);
            } else if (playerHand.indexOf(playedCardSuit) < 0 || playerHand.indexOf(playedCardRank) < 0) {
               System.out.println("You don't have a " + cards);
            } else if (initialDeckCard.indexOf(playedCardSuit) < 0 && initialDeckCard.indexOf(playedCardRank) < 0
                  && playedCardRank != "8") {
               System.out.println("You cannot play that card.");
            } else if (cards.indexOf("8") >= 0) {
               ifEight(in, initialDeckCard, playerHand, cards);
               validInput = true;
            } else {
               playerHand = playerHand.replaceFirst(cards + " ", "");
               initialDeckCard = cards;
               validInput = true;
               System.out.println("Hand: " + playerHand);
               System.out.println("Deck " + initialDeckCard);
            }

         } else {
            int addedCards = 0;
            while (playerHand.indexOf(deckCardRank) == -1 && playerHand.indexOf(deckCardSuit) == -1
                  && playerHand.indexOf("8") == -1) {
               System.out.println("You cannot play. Press enter to draw a card.");
               in.nextLine().toUpperCase();
               playerHand += getCard() + " ";
               System.out.println("Hand: " + playerHand);
               System.out.println("Deck: " + initialDeckCard);
               addedCards++;
               validInput = false;
               if (addedCards == 5) {
                  System.out.println("Turn Skipped. You can draw a max of 5 cards.");
                  validInput = true;
                  return playerHand + "-" + initialDeckCard;
               }
            }

         }
      }

      return playerHand + "-" + initialDeckCard;

   }

   private static boolean gameOver(int playerPoints, int c1Points, int c2Points) {
      return playerPoints >= 100 || c1Points >= 100 || c2Points >= 100;

   }

   private static String ifEight(Scanner in, String initialDeckCard, String playerHand, String cards) {
      System.out.println("What suit is the 8?");
      String suit = in.nextLine().toUpperCase();
      if (suit.equals("DIAMONDS") || suit.equals("D")) {
         playerHand = playerHand.replaceFirst(cards + " ", "");
         initialDeckCard = initialDeckCard.replaceFirst(initialDeckCard.substring(0, initialDeckCard.length()), "8D");
         System.out.println("Deck: " + initialDeckCard);
      } else if (suit.equals("SPADES") || suit.equals("S")) {
         playerHand = playerHand.replaceFirst(cards + " ", "");
         initialDeckCard = initialDeckCard.replaceFirst(initialDeckCard.substring(0, initialDeckCard.length()), "8S");
         System.out.println("Deck: " + initialDeckCard);

      } else if (suit.equals("CLUBS") || suit.equals("C")) {
         playerHand = playerHand.replaceFirst(cards + " ", "");
         initialDeckCard = initialDeckCard.replaceFirst(initialDeckCard.substring(0, initialDeckCard.length()), "8C");
         System.out.println("Deck: " + initialDeckCard);

      } else if (suit.equals("HEARTS") || suit.equals("H")) {
         playerHand = playerHand.replaceFirst(cards + " ", "");
         initialDeckCard = initialDeckCard.replaceFirst(initialDeckCard.substring(0, initialDeckCard.length()), "8H");
         System.out.println("Deck: " + initialDeckCard);

      } else {
         System.out.println("Invalid input!");
      }
      System.out.println("Hand: " + playerHand);
      return playerHand;

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

   private static String getFace() {

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

   private static String getCard() {
      String card = getFace() + getSuit();
      return card;
   }

}
package week7;

import java.util.Scanner;

public class CrazyEightHelp {

   private static final int NUM_SUITS = 4;
   private static final String HEARTS = "H";
   private static final String DIAMONDS = "D";
   private static final String CLUBS = "C";
   private static final String SPADES = "S";
   private static final int CARDS_PER_SUIT = 13;
   private static final String ACE = "A";
   private static final String JACK = "J";
   private static final String QUEEN = "Q";
   private static final String KING = "K";
   private static final int CARDS_PER_HAND = 5;

   public static void main(String[] args) {
      greeting("Welcome to Crazy Eights!!!\n");
      int playerPoints = 0, c1Points = 0, c2Points = 0;
      Scanner in = new Scanner(System.in);

      while (!gameOver(playerPoints, c1Points, c2Points)) {
         String result = playRound(in);
         int firstDash = result.indexOf("-");
         int secondDash = result.lastIndexOf("-");

         playerPoints += Integer.parseInt(result.substring(0, firstDash));
         c1Points += Integer.parseInt(result.substring(firstDash + 1, secondDash));
         c2Points += Integer.parseInt(result.substring(secondDash + 1));

         System.out.printf("\n\n%20s%20s%20s\n", "Player 1", "Computer 1", "Computer 2");
         System.out.printf("%20s%20s%20s\n", "--------", "----------", "----------");
         System.out.printf("%20d%20d%20d\n\n", playerPoints, c1Points, c2Points);
      }

      if (playerPoints < c1Points && playerPoints < c2Points)
         System.out.println("Player 1 WINS!");
      else if (c1Points < playerPoints && c1Points < c2Points)
         System.out.println("Computer 1 WINS!");
      else if (c1Points < playerPoints && c1Points < c2Points)
         System.out.println("Computer 2 WINS!");
      else if (c1Points == playerPoints && c1Points < c2Points)
         System.out.println("Computer 1 and the Player WIN!");
      else if (c1Points < playerPoints && c1Points == c2Points)
         System.out.println("Computer 1 and Computer 2 WIN!");
      else if (c2Points < c1Points && playerPoints == c2Points)
         System.out.println("Player and Computer 2 WIN!");
      else
         System.out.println("Everyone gets a Ribbon!!!!");

   }

   private static void greeting(String msg) {
      for (int i = 0; i < msg.length(); i++) {
         try {
            Thread.sleep(200);
            System.out.print(msg.substring(i, i + 1));
         } catch (InterruptedException e) {

         }
      }
   }

   private static String playRound(Scanner in) {
      greeting("-----LET'S PLAY-----\n");
      String playerHand = "";
      String c1Hand = "";
      String c2Hand = "";

      for (int i = 0; i < CARDS_PER_HAND; i++) {
         playerHand += getCard() + " ";
         c1Hand += getCard() + " ";
         c2Hand += getCard() + " ";
      }

      playerHand = playerHand.trim();
      c1Hand = c1Hand.trim();
      c2Hand = c2Hand.trim();

      String topCard = getCard();

      while (topCard.indexOf("8") == 0)
         topCard = getCard();

      displayCards(playerHand, c1Hand, c2Hand, topCard);
      /*
       * hand-topCard
       */
      while (playerHand.length() > 0 && c1Hand.length() > 0 && c2Hand.length() > 0) {

         String temp = processPlayer(in, playerHand, topCard);
         playerHand = temp.substring(0, temp.indexOf("-"));
         topCard = temp.substring(temp.indexOf("-") + 1);

         displayCards(playerHand, c1Hand, c2Hand, topCard);
         if (playerHand.length() > 0) {
            temp = processComputer(c1Hand, topCard, playerHand, c2Hand);
            c1Hand = temp.substring(0, temp.indexOf("-"));
            topCard = temp.substring(temp.indexOf("-") + 1);

            displayCards(playerHand, c1Hand, c2Hand, topCard);
            if (c1Hand.length() > 0) {
               temp = processComputer(c2Hand, topCard, playerHand, c1Hand);
               c2Hand = temp.substring(0, temp.indexOf("-"));
               topCard = temp.substring(temp.indexOf("-") + 1);

               displayCards(playerHand, c1Hand, c2Hand, topCard);
            }
         }
      }

      return getPoints(playerHand) + "-" + getPoints(c1Hand) + "-" + getPoints(c2Hand);

   }

   private static void displayCards(String playerHand, String c1Hand, String c2Hand, String topCard) {
      System.out.println("Player: " + playerHand);
      System.out.println("Computer 1: " + c1Hand);
      System.out.println("Computer 2: " + c2Hand);

      System.out.println("Top Card: " + topCard + "\n--------------------------------\n");
   }

   private static int getPoints(String hand) {
      int points = 0;

      String card = "";

      if (hand.length() == 2 || hand.length() == 3)
         points += getCardValue(hand);
      else {
         for (int i = 0; i < hand.length(); i++) {
            String s = hand.substring(i, i + 1);
            if (!s.equals(" "))
               card += s;
            else {
               points += getCardValue(card);
               card = "";
            }
         }
      }

      return points;
   }

   private static int getCardValue(String card) {
      if (card.length() == 3)
         return 10;
      else {
         String rank = card.substring(0, 1);
         if (rank.equals("A"))
            return 1;
         else if (rank.equals("J") || rank.equals("Q") || rank.equals("K"))
            return 10;
         else
            return Integer.parseInt(rank);
      }
   }

   private static String getCard() {
      return getFace() + getSuit();

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

   private static String processComputer(String hand, String topCard, String oppHand1, String oppHand2) {

      int cardsDrawn = 0;
      while (!canPlay(hand, getSuit(topCard), getRank(topCard)) && cardsDrawn < 5) {
         String card = getCard();
         hand += ' ' + card;
         cardsDrawn++;
         System.out.println("Computer drew a " + card);
      }

      if (canPlay(hand, getSuit(topCard), getRank(topCard))) {
         return playComputerCard(hand, topCard, oppHand1, oppHand2);
      } else {
         return hand + "-" + topCard;
      }

   }

   private static String playComputerCard(String hand, String topCard, String oppHand1, String oppHand2) {
      // check if opp has 1 card and we can change the suit
      String card = "";
      if (hasSuit(hand, getSuit(topCard), true)) {
         card = getCardToPlay(hand, getSuit(topCard), true, false);
      } else if (hasRank(hand, getRank(topCard))) {
         card = getCardToPlay(hand, getRank(topCard), false, false);
      } else {
         card = getCardToPlay(hand, getSuit(topCard), false, true);
      }

      return removeCard(hand, card).trim() + '-' + card;
   }

   private static String getCardToPlay(String hand, String criteria, boolean isSuit, boolean allowEight) {

      int spaceIndex = hand.indexOf(" ", 0);
      int oldSpaceIndex = 0;
      String card;
      while (spaceIndex != -1) {
         card = hand.substring(oldSpaceIndex, spaceIndex).trim();
         if (isSuit && !allowEight && card.indexOf(criteria) >= 0) {
            return card;
         } else if (!isSuit && !allowEight && card.indexOf(criteria) >= 0) {
            return card;
         } else if (allowEight && card.indexOf("8") >= 0 && card.indexOf(criteria) < 0) {
            return card;
         }
         oldSpaceIndex = spaceIndex;
         spaceIndex = hand.indexOf(" ", spaceIndex + 1);
      }

      card = hand.substring(oldSpaceIndex).trim();
      if (isSuit && !allowEight && card.indexOf(criteria) >= 0) {
         return card;
      } else if (!isSuit && !allowEight && card.indexOf(criteria) >= 0) {
         return card;
      } else if (allowEight && card.indexOf("8") >= 0 && card.indexOf(criteria) < 0) {
         return card;
      }

      // find the eight and play it
      return hand.substring(hand.indexOf("8"), hand.indexOf("8") + 2);

   }

   private static boolean hasRank(String hand, String rank) {
      return hand.indexOf(rank) >= 0;
   }

   private static boolean hasSuit(String hand, String suit, boolean excludeEights) {
      for (int i = 1; i < hand.length(); i++) {
         if (hand.substring(i, i + 1).equals(suit) && excludeEights && !hand.substring(i - 1, i).equals("8"))
            return true;
         else if (hand.substring(i, i + 1).equals(suit) && !excludeEights)
            return true;

      }
      return false;
   }

   private static String removeCard(String hand, String cardToPlay) {
      String temp = hand.replaceFirst(cardToPlay, "");
      return temp.replace("  ", " ");
   }

   private static String getRank(String card) {
      if (card.length() == 2)
         return card.substring(0, 1);
      else
         return card.substring(0, 2);
   }

   private static String getSuit(String card) {
      return card.substring(card.length() - 1);
   }

   private static boolean canPlay(String hand, String suit, String rank) {
      return hand.indexOf("8") >= 0 || hand.indexOf(suit) >= 0 || hand.indexOf(rank) >= 0;
   }

   private static String playCard(Scanner in, String playerHand, String topCard) {

      String cardToPlay = getCardToPlay(in, playerHand, topCard);

      if (cardToPlay.indexOf('8') >= 0) {
         String suit = promptSuit(in);
         topCard = "8" + suit;
      }

      String temp = removeCard(playerHand, cardToPlay).trim() + "-";
      if (cardToPlay.indexOf('8') >= 0) {
         return temp + topCard;
      } else {
         return temp + cardToPlay;
      }

   }

   private static String promptSuit(Scanner in) {
      boolean validInput = false;
      String suit = "";
      while (!validInput) {
         System.out.print("Suit ([H]earts, [S]pades, [C]lubs, [D]iamonds): ");
         suit = in.nextLine();
         if (!("H".equals(suit) || "Hearts".equals(suit) || "S".equals(suit) || "Spades".equals(suit)
               || "D".equals(suit) || "Diamonds".equals(suit) || "C".equals(suit) || "Clubs".equals(suit)))
            System.out.println("Invalid Input.");
         else
            validInput = true;

      }
      return suit.substring(0, 1);
   }

   private static String getCardToPlay(Scanner in, String playerHand, String topCard) {
      final String VALID_CARDS = "AS2S3S4S5S6S7S8S9S10SJSQSKSAC2C3C4C5C6C7C8C9C10CJCQCKCAD2D3D4D5D6D7D8D9D10DJDQDKDAH2H3H4H5H6H7H8H9H10HJHQHKH";
      boolean validInput = false;
      String card = "";
      while (!validInput) {
         System.out.print("Please enter the card to play (" + playerHand + "): ");
         card = in.nextLine().toUpperCase();
         if (card.indexOf(" ") >= 0)
            System.out.println("You must choose 1 card to play.");
         else if (VALID_CARDS.indexOf(card) < 0)
            System.out.println("Not a valid card: " + card);
         else if (playerHand.indexOf(card) < 0)
            System.out.println("You don't have a " + card);
         else if (!canPlay(card, getSuit(topCard), getRank(topCard)))
            System.out.println("You cannot play the " + card);
         else
            validInput = true;
      }

      return card;
   }

   private static String processPlayer(Scanner in, String hand, String topCard) {
      int cardsDrawn = 0;
      while (!canPlay(hand, getSuit(topCard), getRank(topCard)) && cardsDrawn < 5) {
         String card = getCard();
         hand += ' ' + card;
         cardsDrawn++;
         System.out.println("You drew a " + card);
      }

      if (canPlay(hand, getSuit(topCard), getRank(topCard))) {
         return playCard(in, hand, topCard);
      } else {
         return hand + "-" + topCard;
      }
   }

   private static boolean gameOver(int playerPoints, int c1Points, int c2Points) {
      return playerPoints >= 100 || c1Points >= 100 || c2Points >= 100;
   }
}

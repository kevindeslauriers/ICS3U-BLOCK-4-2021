package week8;

public class DieGame {
   public static void main(String[] args) {
      Die die1 = new Die(); // use the keyword new with constructors (create an instance)
      Die die2 = new Die(6);
      int countPairs = 0;

      for (int i = 0; i < 100; i++) {
         die1.roll();
         die2.roll();

         System.out.println(die1 + " " + die2);

         if (die1.equals(die2))
            countPairs++;
      }

      System.out.println("Number of Pairs: " + countPairs);

   }
}

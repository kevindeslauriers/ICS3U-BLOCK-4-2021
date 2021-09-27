package week3;

public class ExtraPractice {
   public static void main(String[] args) {
      exampleOne();
      String str = questionSix("This is a sentence.", "ent"); // "This is a sence"
      System.out.println(str);

   }

   private static String questionSix(String str, String substr) {
      int index = str.indexOf(substr);
      String first = str.substring(0, index);
      String last = str.substring(index + substr.length());

      return first + last;
   }

   /*
    * Obtains and displays an integer between 1 and 100
    */
   public static void exampleOne() {
      int num = (int) (Math.random() * 100) + 1;
      // (int)(Math.random() * numPossibilities) + start
      System.out.println(num);
   }
}

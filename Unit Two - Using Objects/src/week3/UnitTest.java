package week3;

public class UnitTest {
   public static void main(String[] args) {
      System.out.println(threeCopies("Apple", 3));
      System.out.println(threeCopies("Computer", 2));
      System.out.println(removeChars("Computer Science", 3, 4));
      System.out.println(removeChars("Tired", 2, 3));
      System.out.println(sqrtSum(9999));
   }

   /**
    * Write a function that accepts a String and an integer. Return 3 copies of a
    * substring of length 2 at the specified index.
    */
   // threeCopies("Happy", 2) => "pppppp"
   // threeCopies("Apple", 3) => "lelele"
   public static String threeCopies(String str, int index) {
      String s = str.substring(index, index + 2);
      return s + s + s;
   }

   /*
    * removeChars("Happy", 1, 2) → "Hpy" removeChars ("Computer Science", 0,3) →
    * "puter Science" removeChars ("Tired", 2, 3) → "Ti" removeChars
    * ("Computer Science", 3,4) → "Comr Science"
    * 
    */
   public static String removeChars(String str, int index, int n) {
      String first = str.substring(0, index);
      String last = str.substring(index + n);
      return first + last;
   }

   // 7463
   public static double sqrtSum(int number) {
      int n1 = number / 1000; // 7463 / 1000 = 7
      int n2 = number / 100 % 10; // 7463 / 100 = 74 % 10 = 4
      int n3 = number / 10 % 10; // 7463 / 10 = 746 % 10 = 6
      int n4 = number % 10; // 7463 % 10 = 3

      int sum = n1 + n2 + n3 + n4;
      return Math.sqrt(sum);
   }
}

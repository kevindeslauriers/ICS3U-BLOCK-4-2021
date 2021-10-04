package week4;

public class IfStatementExamples {
   public static void main(String[] args) {
      exampleOne();
      String letterGrade = getLetterGrade(78);
      exampleThree();
   }

   private static void exampleThree() {
      /**
       * > < == >= <= != (not equal)
       * 
       * || OR (x>7) || (y>7)       either are TRUE to be TRUE
       * && AND (x>7) || (y>7)      both are TRUE to be TRUE
       * ! NOT  !(x>7)   x<=7
       * 
       */
   }

   private static String getLetterGrade(int mark) {
      String letter;

      if (mark >= 90) {
         letter = "A+";
      }
      if (mark >= 80) {
         letter = "A";
      }
      if (mark >= 70) {
         letter = "B";
      }
      if (mark >= 60) {
         letter = "C";
      }
      if (mark >= 50) {
         letter = "D";
      } else {
         letter = "F";
      }

      return letter;
   }

   private static void exampleOne() {
      int x = 7;

      if (x % 2 == 0) {
         System.out.println(x + " is even.");
      } else {
         System.out.println(x + " is odd.");
      }
      /**
       * if (x % 2 == 1) { System.out.println(x + " is odd."); }
       */
   }
}

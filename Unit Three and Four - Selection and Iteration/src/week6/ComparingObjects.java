package week6;

public class ComparingObjects {
   public static void main(String[] args) {
      // when we compare Strings we NEVER EVER EVER use == in Java

      // why? Coming Soon!
      // if we don''t instantiate an object then the variable stores NULL
      // otherwise the variable stores the address of the actual object

      // Why?
      // when you use == you are comparing what is stored in the variable
      // which is the address (location in memory where teh object is stored).

      // equals method compares the sequence of the characters in the two objects.

      String s1 = new String("hello");
      String s2 = new String("hello");

      System.out.println(s1.equals(s2));

      String str1 = "ABCD";
      String str2 = "BCD";

      // System.out.println(str1.compareTo(str2));
      System.out.println("A".compareTo("a"));
   }
}

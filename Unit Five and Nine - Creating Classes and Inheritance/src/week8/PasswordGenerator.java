package week8;

public class PasswordGenerator {
   private int numGenerated;
   private String prefix;
   private int length;

   public PasswordGenerator(int length) {
      this.length = length;
      this.prefix = "A";
      numGenerated = 0;
   }

   public PasswordGenerator(int length, String prefix) {
      this.length = length;
      this.prefix = prefix;
      numGenerated = 0;
   }

   public int pwCount() {
      return numGenerated;
   }

   public String pwGen() {
      String temp = prefix + ".";

      for (int i = 0; i < length; i++) {
         temp += (int) (Math.random() * 10);
      }
      return temp;
   }
}

package week9;

public class Driver {
   public static void main(String[] args) {
      SmartPhone myPhone = new SmartPhone(2.55, 4.53);

      System.out.println("Device has memory: " + myPhone.getMemory() +

            ", screen area: " + myPhone.getScreenWidth() * myPhone.getScreenHeight() + " square inches.");
   }
}



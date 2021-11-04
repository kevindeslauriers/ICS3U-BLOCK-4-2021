package week9;

public class SmartPhone extends Computer

{

   private double screenWidth, screenHeight;

   public SmartPhone(double w, double h)

   {

      super("flash");

      screenWidth = w;

      screenHeight = h;

   }

   public double getScreenWidth()

   {

      return screenWidth;

   }

   public double getScreenHeight()

   {

      return screenHeight;

   }

}

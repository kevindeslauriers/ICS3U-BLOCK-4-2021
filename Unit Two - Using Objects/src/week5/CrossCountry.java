package week5;

import java.util.Scanner;

public class CrossCountry {
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      processRunner(in);
      processRunner(in);
      processRunner(in);
      in.close();

   }

   private static void processRunner(Scanner in) {
      String firstName, lastName;
      String mileOne, mileTwo, finish;
      String splitTwo, splitThree;

      /**
       * get the first and last name of the runner get the mileOne, mileTwo and finish
       * times for the runner
       */

      splitTwo = subtractTime(mileTwo, mileOne);
      splitThree = subtractTime(finish, mileTwo);

      /**
       * Display a summary for the runner
       */

   }

   /**
    * 
    * @param endTime
    * @param startTime
    * @return
    */
   private static String subtractTime(String endTime, String startTime) {
      double endTimeInSeconds = convertToSeconds(endTime);
      double startTimeInSeconds = convertToSeconds(startTime);

      double diffInSeconds = endTimeInSeconds - startTimeInSeconds;

      return convertToTime(diffInSeconds);

   }

   private static String convertToTime(double timeInSeconds) {
      int minutes = 5;
      double seconds = 6.9;

      return String.format("%d:%06.3f", minutes, seconds);
   }

   private static double convertToSeconds(String time) {
      return 0;
   }

}

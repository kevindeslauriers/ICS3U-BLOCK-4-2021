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

      firstName = promptUser("Please enter your first name: ", in);
      lastName = promptUser("Please enter your last name: ", in);
      mileOne = promptUser("Please enter mile one time (mm:ss.sss): ", in);
      mileTwo = promptUser("Please enter mile two time (mm:ss.sss): ", in);
      finish = promptUser("Please enter 5 km time (mm:ss.sss): ", in);

      splitTwo = subtractTime(mileTwo, mileOne);
      splitThree = subtractTime(finish, mileTwo);

      System.out.println("Name: " + firstName + " " + lastName);
      System.out.println("Mile One: " + mileOne);
      System.out.println("Split Two: " + splitTwo);
      System.out.println("Split Three: " + splitThree);
      System.out.println("Finish Time: " + finish);

   }

   private static String promptUser(String prompt, Scanner in) {
      System.out.print(prompt);
      return in.nextLine();
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
      int minutes = getMinutes(timeInSeconds);
      double seconds = getSeconds(timeInSeconds);

      return String.format("%d:%06.3f", minutes, seconds);
   }

   private static double getSeconds(double timeInSeconds) {
      return timeInSeconds % 60;
   }

   private static int getMinutes(double timeInSeconds) {
      return (int) (timeInSeconds / 60);
   }

   private static double convertToSeconds(String time) {
      int colon = time.indexOf(":");
      int minutes = Integer.parseInt(time.substring(0, colon));
      double seconds = Double.parseDouble(time.substring(colon + 1));

      return minutes * 60 + seconds;

   }

}

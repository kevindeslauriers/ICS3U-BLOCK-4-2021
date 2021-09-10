package week1;

/**
 * Integer vs Double math
 * 
 */
public class ExampleFive {
  public static void main(String[] args) {
    int markOne = 75, markTwo = 80, markThree = 87;
    double average = (markOne + markTwo + markThree) / 3; // 242 / 3 = 80 (Integer math / integer division)

    average = (markOne + markTwo + markThree) / 3.0; // 242 / 3.0 = 80.666667
    System.out.println("The average is: " + average);

    // Integer vs Double Division
    System.out.print(4 / 5); // 0
    System.out.print(4.0 / 5); // 0.8
    System.out.print(5 / 4); // 1
    System.out.print(5 / 4.0); // 1.25

    // Modulus Operator % - Remainder

    System.out.println(13 / 3); // 13 divided by 3 = 4
    System.out.println(13 % 3); // 13 mod 3 = 1 because 1 is left over (remainder)

  }
}

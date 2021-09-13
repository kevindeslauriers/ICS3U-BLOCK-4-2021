package week2;

/**
 * final modifier - constant
 */
public class ExampleSeven {
  public static void main(String[] args) {
    final int x = 7;
    // x = 6; ///cannot reassign a value to a final variable

    final int y;
    y = 8;
    // y++; // cannot modify the variable once it is set if it is constant

    final int NUM_STUDENTS = 18; // naming convention for constants (final variable) is UPPER_CASE with _ for
                                 // spaces

  }
}

package week1;

/**
 * Escape Sequences
 */
public class ExampleThree {
  public static void main(String[] args) {
    // System.out.println("This is "very" important!"); // double quotes encapsulate
    // string literals - putting a quote in a Strigng literal gets Java UPSET

    System.out.println("This is \"very\" important!"); // \ backslash escapes a character - creates an escape sequence

    // System.out.println("This is \very important!"); // \v is not a valid escape
    // sequence

    System.out.println("This is very \\important!");
    System.out.println("This\nis\nvery\nimportant!"); // \n is newline

    /**
     * AP Exam only tests you on \" \n \\
     */

  }
}

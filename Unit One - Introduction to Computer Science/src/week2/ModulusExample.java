package week2;

public class ModulusExample {
  public static void main(String[] args) {
    int num = 1001;
    int num1 = num / 1000; // 5
    int num4 = num % 10; // 2
    int num2 = num % 1000 / 100; // 5672 % 1000 => 672 / 100 => 6
    num2 = num / 100 % 10; // 5672 / 100 => 56 % 10 => 6

    int num3 = num % 100 / 10; // 7
    num3 = num / 10 % 10;

    System.out.println(num1 + num2 + num3 + num4);

  }
}

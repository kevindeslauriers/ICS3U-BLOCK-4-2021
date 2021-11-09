package week10;

public class ArrayExamples {
   public static void main(String[] args) {
      exampleOne();
      exampleTwo();

      int[] arr = { 5, 7, 3, 2, 5, 2 };
      mystery(arr);

      int x = 7;
      mystery(x);

      int numOdd = numOdd(arr);

      String[] names = { "Kayhan", "Rayhan", "Brad", "Steve", "Wallee" };
      String[] names2 = { "Bradley", "Henry", "Laura", "Muriel", "Ave", "Kayhan", "Rayhan" };
      String[] matchingNames = mystery(names, names2);

   }

   private static String[] mystery(String[] names, String[] names2) {
      String[] greatNames = new String[5];
      int numGreatNames = 0;

      for (int i = 0; i < names.length; i++) {
         String name = names[i];
         for (int j = 0; j < names2.length; j++) {
            if (name.equals(names2[j])) {
               greatNames[numGreatNames] = name;
               numGreatNames++;
            }
         }
      }

      return greatNames;
   }

   private static int numOdd(int[] arr) {
      int numOdd = 0;

      for (int i = 0; i < arr.length; i++) {
         if (arr[i] % 2 == 1)
            numOdd++;
      }

      return numOdd;
   }

   private static void mystery(int x) {
      x = -x;
   }

   private static void mystery(int[] arr) {
      int index = (int) (Math.random() * arr.length);
      arr[index] = -arr[index];
   }

   private static void exampleTwo() {
      Animal[] animals = new Animal[3];
      animals[1] = new Animal();
      animals[1].eat();

   }

   private static void exampleOne() {
      int[] arr = new int[5];

      arr[4] = 2;
      arr[3] = 7;

      System.out.println(arr.length);

   }
}

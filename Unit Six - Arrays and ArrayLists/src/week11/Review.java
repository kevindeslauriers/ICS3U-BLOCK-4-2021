package week11;

public class Review {
   public static void main(String[] args) {
      int[] nums = { 1, 3, 4, 6, 3, 2, 4, 65, 3, 5 };

      for (int i = 0; i < nums.length; i++) {
         System.out.println(nums[i]);
      }

      int numEven = 0;
      for (int i = 0; i < nums.length; i++) {
         if (nums[i] % 2 == 0)
            numEven++;
      }

      System.out.println(numEven);
      String[] words = { "Hello", "jack", "Alphabet", "School" };

      int numLetters = 0;
      for (int i = 0; i < words.length; i++) {
         numLetters += words[i].length();
      }

      System.out.println(numLetters);

      String vowels = "aeiouAEIOU";

      int numVowels = 0;
      for (int i = 0; i < words.length; i++) { // iterate through the words
         for (int j = 0; j < words[i].length(); j++) { // iterate through letters in the word
            String letter = words[i].substring(j, j + 1); // get a letter in the word at index j
            if (vowels.indexOf(letter) >= 0) {
               numVowels++;
            }
         }
      }

      System.out.println(numVowels);

   }

}

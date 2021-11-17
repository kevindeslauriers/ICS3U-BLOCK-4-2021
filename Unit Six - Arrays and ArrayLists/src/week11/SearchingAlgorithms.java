package week11;

import java.util.Arrays;

public class SearchingAlgorithms {
   public static void main(String[] args) {
      int[] arr = { 80, 95, 10, 60, 35, 10, 25 };

      int index = linearSearch(arr, 35);
      index = linearSearch(arr, 36);

      Arrays.sort(arr);
      index = binarySearch(arr, 35);

      int[] nums = new int[1000000];
      for (int i = 0; i < nums.length; i++) {
         nums[i] = (int) (Math.random() * 1000);
      }

      Arrays.sort(nums);
      binarySearch(nums, -1);

   }

   private static int binarySearch(int[] arr, int findMe) {
      int lower = 0;
      int upper = arr.length - 1;

      int numComparisons = 0;
      while (upper >= lower) {
         numComparisons++;
         int mid = (upper + lower) / 2;

         if (arr[mid] == findMe)
            return mid;
         else if (arr[mid] > findMe)
            upper = mid - 1;
         else
            lower = mid + 1;
      }

      System.out.println(numComparisons);

      return -1;

   }

   private static int linearSearch(int[] arr, int findMe) {
      for (int i = 0; i < arr.length; i++) {
         if (arr[i] == findMe)
            return i;
      }

      return -1;
   }
}

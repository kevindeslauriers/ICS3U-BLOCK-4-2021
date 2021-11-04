package week9;

public class SampleClass {
   private int test;

   public SampleClass(int test) {
      /*
       * The code compiles but really does not do anything. The parameter test that is
       * in the round brackets is declared locally for this method as a result it is
       * the test in the line below so it is not using the instance variable. to fix
       * it you could use this.test = test or change the local variable to something
       * else then there would not be ambiguity.
       */
      test = test;
   }
}

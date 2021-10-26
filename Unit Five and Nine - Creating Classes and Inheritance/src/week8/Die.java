package week8;

public class Die {
   // attributes define the state of an instance of the class at any particular
   // time
   // private means that they are only accessible (visible inside the class)
   private int numSides;
   private int topSide;

   public static final int DEFAULT_SIDES = 6;

   /*
    * constructor Has the same names as the class Initialises the state of the
    * class (attributes) returns the Object (although we do not say return)
    */

   /**
    * If we do not EXPLICITLY create our own constructor JAVA will supply a NO
    * ARGUMENT constructor that DOES NOT initialise the state of the class
    * (attributes)
    */
   public Die() {
      numSides = DEFAULT_SIDES;
      roll();
   }

   public Die(int numSides) {
      this.numSides = numSides;
      roll();
   }

   /**
    * accessor methods
    * 
    * @return
    */
   public int getTopSide() {
      return topSide;
   }

   /**
    * mutator methods
    */
   public void roll() {
      topSide = (int) (Math.random() * numSides) + 1;
   }

   public boolean equals(Object obj) {
      if (obj == this)
         return true;

      if (obj instanceof Die) {
         return this.topSide == ((Die) obj).topSide;
      } else {
         return false;
      }
   }

   public String toString() {
      return "" + topSide;
   }

}

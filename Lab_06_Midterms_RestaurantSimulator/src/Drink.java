public class Drink extends Food {
   public static int totalDrinks = 0; 

   public Drink(String type, String desc, int price) {
       super(type, desc, price);
       this.id = menuCount;
       totalDrinks++;
       menuCount++;
   }
}

public class Burger extends Food {
   public static int totalBurgers = 0; 

   public Burger(String type, String desc, int price) {
       super(type, desc, price);
       this.id = menuCount;
       totalBurgers++;
       menuCount++;
   }
}

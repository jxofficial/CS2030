public class Burger extends Food {
   public static int totalBurgers = 0;
   public int burgerId;

   public Burger(String type, String desc, int price) {
       super(type, desc, price);
       this.id = menuCount;
       this.burgerId = totalBurgers;
       totalBurgers++;
       menuCount++;
   }
}

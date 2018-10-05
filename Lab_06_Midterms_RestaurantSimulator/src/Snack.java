public class Snack extends Food {
   public static int totalSnacks = 0; 
   public int snackId;

   public Snack(String type, String desc, int price) {
       super(type, desc, price);
       this.id = menuCount;
       this.snackId = totalSnacks;
       totalSnacks++;
       menuCount++;
   }
}

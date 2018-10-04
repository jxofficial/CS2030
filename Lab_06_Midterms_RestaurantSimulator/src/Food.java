public class Food {
    public String type;
    public String desc; 
    int price; 
    public static int menuCount = 0;
    public int id; 
    
    public Food(String type, String desc, int price) {
        this.type = type;
        this.desc = desc;
        this.price = price;
    }

    @Override
    public String toString() {
        return "#" + id + " " +
            type + ": " + desc +
            " (" + price + ")";
    }
}

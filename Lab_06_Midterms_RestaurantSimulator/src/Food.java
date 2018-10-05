public abstract class Food extends MenuItem {
    public String desc;
    
    public Food(String type, String desc, int price) {
        super(type);
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

public class Combo extends MenuItem {
    Burger item1;
    Snack item2;
    Drink item3;
    int totalCombos = 0;

    public Combo(String type, Burger item1, Snack item2, Drink item3, int price) {
        super(type);
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.price = price;
        this.id = menuCount;
        totalCombos++;
        menuCount++;
    }


    @Override
    public String toString() {
        return "#" + id + " " + type + " " + "(" + price + ")"
                + "\n" + "   " + item1
                + "\n" + "   " + item2
                + "\n" + "   " + item3;
    }
}

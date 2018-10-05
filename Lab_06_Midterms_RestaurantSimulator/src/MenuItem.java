public abstract class MenuItem {
    String type;
    public static int menuCount = 0;
    public int id;
    public int price;

    public MenuItem(String type) {
        this.type = type;
    }
}

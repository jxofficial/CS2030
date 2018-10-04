import java.util.ArrayList;

public class OrderHandler {
    ArrayList<Food> orderedMenu;
    private int price = 0;
    ArrayList<Food> orders = new ArrayList<Food>();


    public OrderHandler(ArrayList<Food> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    public void addOrderItem(int menuItemNum) {
        for (int i = 0; i < orderedMenu.size(); i++) {
            if (orderedMenu.get(i).id == menuItemNum) {
                orders.add(orderedMenu.get(i));
                return;
            }
        }

    }

    public void printOrders() {
        System.out.println("--- Order ---");

        for (Food order : orders) {
            System.out.println(order);
            price += order.price;
        }
        System.out.println("Total: " + price);
    }



}

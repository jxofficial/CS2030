import java.util.ArrayList;

public class OrderHandler {
    ArrayList<MenuItem> orderedMenu;
    private int price = 0;
    ArrayList<MenuItem> orders = new ArrayList<MenuItem>();


    public OrderHandler(ArrayList<MenuItem> orderedMenu) {
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

        for (MenuItem order : orders) {
            System.out.println(order);
            this.price += order.price;
        }

        System.out.println("Total: " + this.price);
    }


}

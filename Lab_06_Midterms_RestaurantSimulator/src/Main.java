import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuHandler mh = new MenuHandler();

        while(sc.next().equals("add")) {
            String type = sc.next();
            String desc = sc.next();
            int price = sc.nextInt();
            mh.sortItems(type, desc, price);
        }

        mh.printOrderedMenu();


        OrderHandler oh = new OrderHandler(mh.orderedMenu);

        // read order
        while (sc.hasNext()) {
            int orderMenuNum = sc.nextInt();
            oh.addOrderItem(orderMenuNum);
        }

        oh.printOrders();
    }
}

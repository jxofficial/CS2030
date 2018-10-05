import java.util.Scanner;


public class InputHandler {
    Scanner sc = new Scanner(System.in);
    MenuHandler mh = new MenuHandler();

    String type;
    String desc;
    int price;
    int id1, id2, id3;



    public MenuHandler scanInputs() {
        while(sc.next().equals("add")) {
            this.type = sc.next();

            if (type.equals("Combo")) {
                this.id1 = sc.nextInt();
                this.id2 = sc.nextInt();
                this.id3 = sc.nextInt();
                mh.sortItems(type, id1, id2, id3);
            } else {
                this.desc = sc.next();
                this.price = sc.nextInt();
                mh.sortItems(type, desc, price);
            }
        }
        return mh;
    }

    public OrderHandler scanOrders(OrderHandler oh) {
        while (sc.hasNext()) {
            int orderMenuNum = sc.nextInt();
            oh.addOrderItem(orderMenuNum);
        }
        return oh;
    }


}

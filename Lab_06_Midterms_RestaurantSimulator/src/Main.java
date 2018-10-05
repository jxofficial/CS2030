
public class Main {

    public static void main(String[] args) {

        InputHandler ih = new InputHandler();
        MenuHandler mh;


        mh = ih.scanInputs();
        mh.printOrderedMenu();


        OrderHandler oh = new OrderHandler(mh.orderedMenu);

        ih.scanOrders(oh);
        oh.printOrders();
    }
}

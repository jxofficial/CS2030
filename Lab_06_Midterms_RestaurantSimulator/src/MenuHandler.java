import java.util.ArrayList;

public class MenuHandler {
    ArrayList<MenuItem> orderedMenu = new ArrayList<MenuItem>();
    ArrayList<Burger> burgerMenu = new ArrayList<Burger>();
    ArrayList<Snack> snackMenu = new ArrayList<Snack>();
    ArrayList<Drink> drinkMenu = new ArrayList<Drink>();
    ArrayList<Combo> comboMenu = new ArrayList<Combo>();

    

    public void sortItems(String type, String desc, int price) {
        if ( type.equals("Burger") ) {
            Burger b = new Burger(type, desc, price);
            burgerMenu.add(b);
        } else if ( type.equals("Snack") ) {
            Snack s = new Snack(type, desc, price);
            snackMenu.add(s); 
        } else {
            Drink d = new Drink(type, desc, price);
            drinkMenu.add(d);
        }

    }

    public void sortItems(String type, int id1, int id2, int id3) {
        ComboHandler ch = new ComboHandler(type, id1, id2, id3, burgerMenu, snackMenu, drinkMenu);
        Combo c = ch.createCombo();
        if (! (c == null) ) {
            comboMenu.add(c);
        }
    }
    

    private void collateMenu(ArrayList<? extends MenuItem> inputMenu) {
        orderedMenu.addAll(inputMenu);
    }

    public void printOrderedMenu() {
        collateMenu(burgerMenu);
        collateMenu(snackMenu);
        collateMenu(drinkMenu);
        collateMenu(comboMenu);
        for (MenuItem menuItem: orderedMenu) {
            System.out.println(menuItem);
        }
    }

}
    



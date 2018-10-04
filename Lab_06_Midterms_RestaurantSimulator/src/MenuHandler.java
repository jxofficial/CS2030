import java.util.ArrayList;

public class MenuHandler {
    ArrayList<Food> orderedMenu = new ArrayList<Food>();
    ArrayList<Burger> burgerMenu = new ArrayList<Burger>();
    ArrayList<Snack> snackMenu = new ArrayList<Snack>();
    ArrayList<Drink> drinkMenu = new ArrayList<Drink>(); 
    

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
    

    private void collateMenu(ArrayList<? extends Food> inputMenu) {
        orderedMenu.addAll(inputMenu);
    }

    public void printOrderedMenu() {
        collateMenu(burgerMenu);
        collateMenu(snackMenu);
        collateMenu(drinkMenu);
        for (Food menuItem: orderedMenu) {
            System.out.println(menuItem);
        }
    }

}
    



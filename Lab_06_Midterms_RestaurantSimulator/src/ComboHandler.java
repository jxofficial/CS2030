import java.util.ArrayList;

public class ComboHandler {
    int id1, id2, id3;
    String type;
    ArrayList<Burger> burgerMenu = new ArrayList<Burger>();
    ArrayList<Snack> snackMenu = new ArrayList<Snack>();
    ArrayList<Drink> drinkMenu = new ArrayList<Drink>();
    Burger b;
    Drink d;
    Snack s;
    int price;

    public ComboHandler(String type, int id1, int id2, int id3,
                        ArrayList<Burger> burgerMenu, ArrayList<Snack> snackMenu, ArrayList<Drink> drinkMenu) {
        this.type = type;
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
        this.burgerMenu = burgerMenu;
        this.snackMenu = snackMenu;
        this.drinkMenu = drinkMenu;
    }

    private <T extends Food> int search(int searchId, ArrayList<T> foodList) {
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).id == searchId) {
                return i;
            }
        }

        return -1;
    }



    private boolean comboChecker() {
        if ( search(id1, burgerMenu) < 0 || search(id2, snackMenu) < 0 || search(id3, drinkMenu) < 0 ){
            return false;
        } else {
            b = burgerMenu.get(search(id1, burgerMenu));
            s = snackMenu.get(search(id2, snackMenu));
            d = drinkMenu.get(search(id3, drinkMenu));
            return true;
        }
    }

    public Combo createCombo() {
        if ( comboChecker() ) {
            this.price = b.price + s.price + d.price - 50;
            return new Combo(type, b, s, d, price);
        } else {
            System.out.println("Error: Invalid combo input " + id1 + " "
                    + id2 + " " + id3);
            return null;
        }
    }
}

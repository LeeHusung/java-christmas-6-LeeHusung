package christmas.model.menu;

public enum Drink {

    ZERO_COKE(3000),
    RED_WINE(60000),
    CHAMPAGNE(25000);

    private final int price;

    Drink(int price) {
        this.price = price;
    }
}

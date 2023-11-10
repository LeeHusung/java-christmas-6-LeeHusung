package christmas.model.menu;

public enum Desert {

    CHOCO_CAKE(15000),
    ICED_CREAM(5000);

    private final int price;

    Desert(int price) {
        this.price = price;
    }
}

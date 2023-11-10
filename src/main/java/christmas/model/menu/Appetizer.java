package christmas.model.menu;

public enum Appetizer {
    YANG_SONG_I(6000),
    TAPAS(5500),
    CAESAR(8000);

    private final int price;

    Appetizer(int price) {
        this.price = price;
    }
}

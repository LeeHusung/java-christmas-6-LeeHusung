package christmas.model.menu;

public enum Main {

    T_BON(55000),
    BARBEQUERIP(54000),
    SEA_PASTA(35000),
    CHRISTMAS_PASTA(25000);

    private final int price;

    Main(int price) {
        this.price = price;
    }
}

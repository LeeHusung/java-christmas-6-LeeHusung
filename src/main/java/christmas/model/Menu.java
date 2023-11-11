package christmas.model;

public enum Menu {
    양송이수프("APPETIZER", 6000),
    타파스("APPETIZER", 5500),
    시저샐러드("APPETIZER", 8000),
    티본스테이크("MAIN", 55000),
    바비큐립("MAIN", 54000),
    해산물파스타("MAIN", 35000),
    크리스마스파스타("MAIN", 25000),
    초코케이크("DESSERT", 15000),
    아이스크림("DESSERT", 5000),
    제로콜라("DRINK", 3000),
    레드와인("DRINK", 60000),
    샴페인("DRINK", 25000);

    private final String category;
    private final int price;

    Menu(String category, int price) {
        this.category = category;
        this.price = price;
    }

    public boolean isDrink() {
        return this.category.startsWith("DRINK");
    }

    public boolean isMain() {
        return this.category.startsWith("MAIN");
    }

    public boolean isDessert() {
        return this.category.startsWith("DESSERT");
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}


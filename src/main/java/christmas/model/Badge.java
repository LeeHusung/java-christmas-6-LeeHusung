package christmas.model;

public enum Badge {

    별("STAR"),
    트리("TREE"),
    산타("SANTA");

    private String badgeName;

    Badge(String badgeName) {
        this.badgeName = badgeName;
    }
}

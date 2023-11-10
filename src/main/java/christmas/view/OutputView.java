package christmas.view;

import christmas.model.Menu;

import java.util.Map;

public class OutputView {
    public void printMenu(Map<Menu, Integer> orderMap) {
        System.out.println("<주문 메뉴>");
        for (Menu menu : orderMap.keySet()) {
            System.out.print(menu.name() + " " + orderMap.get(menu) + "개");
            System.out.println();
        }
    }
    // ...
}
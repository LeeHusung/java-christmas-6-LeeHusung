package christmas.view;

import java.util.Map;

public class OutputView {
    public void printMenu(Map<String, Integer> orderMap) {
        System.out.println("<주문 메뉴>");
        for (String key : orderMap.keySet()) {
            System.out.print(key + " " + orderMap.get(key) + "개");
            System.out.println();
        }
    }
    // ...
}
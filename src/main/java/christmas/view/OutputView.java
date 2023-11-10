package christmas.view;

import christmas.model.Menu;
import christmas.model.OrderConst;

import java.util.Map;

import static christmas.model.OrderConst.*;

public class OutputView {
    public void printMenu(Map<Menu, Integer> orderMap) {
        System.out.println("<주문 메뉴>");
        int count = 0;
        for (Menu menu : orderMap.keySet()) {
            count += orderMap.get(menu);
            if (count > ORDER_MAX_COUNT) throw new IllegalArgumentException("[ERROR] 메뉴는 총 20개 까지만 주문 가능합니다.");
            System.out.print(menu.name() + " " + orderMap.get(menu) + "개");
            System.out.println();
        }
    }
    // ...
}
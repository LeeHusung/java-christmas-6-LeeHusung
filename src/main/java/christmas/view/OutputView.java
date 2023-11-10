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

    public int printTotalPriceBeforeDiscount(Map<Menu, Integer> orderMap) {
        System.out.println("<할인 전 총주문 금액>");
        int totalPriceBeforeDiscount = 0;
        for (Menu menu : orderMap.keySet()) {
            int count = orderMap.get(menu);
            totalPriceBeforeDiscount += menu.getPrice() * count;
        }
        System.out.println(totalPriceBeforeDiscount + "원");

        int giftCount = 0;
        while (totalPriceBeforeDiscount >= GIVE_CHAMPAGNE_PRICE) {
            giftCount++;
            totalPriceBeforeDiscount -= GIVE_CHAMPAGNE_PRICE;
        }
        printGift(giftCount);
        while (giftCount > 0) {
            Menu 샴페인 = Menu.valueOf("샴페인");
            orderMap.put(샴페인, orderMap.getOrDefault(샴페인, 0) + 1);
            giftCount--;
        }

        return totalPriceBeforeDiscount;
    }

    public void printGift(int giftCount) {
        System.out.println("<증정 메뉴>");
        if (giftCount == 0) {
            System.out.println("없음");
            return;
        }
        System.out.println("샴페인 " + giftCount + "개");

    }


}
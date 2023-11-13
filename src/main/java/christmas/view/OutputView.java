package christmas.view;

import christmas.common.consts.TitleConst;
import christmas.model.Menu;

import java.util.Map;

import static christmas.common.consts.EventConst.*;
import static christmas.common.consts.OrderConst.*;
import static christmas.common.consts.TitleConst.*;
import static christmas.model.Menu.*;

public class OutputView {

    public void printMenu(Map<Menu, Integer> orderMap, int expectedVisitDate) {
        System.out.println("12월 " + expectedVisitDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
        System.out.println(ORDER_MENU);
        int count = 0;
        for (Menu menu : orderMap.keySet()) {
            count += orderMap.get(menu);
            if (count > ORDER_MAX_COUNT) throw new IllegalArgumentException("[ERROR] 메뉴는 총 20개 까지만 주문 가능합니다.");
            System.out.print(menu.name() + " " + orderMap.get(menu) + "개");
            System.out.println();
        }
        System.out.println();
    }

    public int printTotalPriceBeforeDiscount(Map<Menu, Integer> orderMap) {
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT);
        int totalPriceBeforeDiscount = 0;
        for (Menu menu : orderMap.keySet()) {
            int count = orderMap.get(menu);
            totalPriceBeforeDiscount += menu.getPrice() * count;
        }
        System.out.printf(PRINT_MONEY, totalPriceBeforeDiscount);
        System.out.println();
        System.out.println();

        return totalPriceBeforeDiscount;
    }

    public void printGift(int giftCount) {
        System.out.println(GIFT_MENU);
        if (giftCount == 0) {
            System.out.println(NONE);
            System.out.println();
            return;
        }
        System.out.println("샴페인 " + giftCount + "개");
        System.out.println();
    }

    public int printGiftEvent(int giftCount) {
        int giftChampagneTotalMoney = 샴페인.getPrice() * giftCount;
        if (giftCount > 0) {
            System.out.printf("증정 이벤트: " + PRINT_MONEY, -giftChampagneTotalMoney);
            System.out.println();
        }
        System.out.println();
        return giftChampagneTotalMoney;
    }

    public void printEventBadge(int totalDisCountPrice) {
        System.out.println(EVENT_BADGE_12MON);
        if (totalDisCountPrice >= STAR_LEAST_PRICE && totalDisCountPrice < STAR_HIGHEST_PRICE) System.out.println(STAR);
        if (totalDisCountPrice >= TREE_LEAST_PRICE && totalDisCountPrice < TREE_HIGHEST_PRICE) System.out.println(TREE);
        if (totalDisCountPrice >= SANTA_LEAST_PRICE) System.out.println(SANTA);
        if (totalDisCountPrice < NOTHING) System.out.println(NONE);
    }

    public void printTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        System.out.println(TOTAL_PRICE_AFTER_DISCOUNT);
        System.out.printf(PRINT_MONEY, totalPriceAfterDiscount);
        System.out.println();
        System.out.println();
    }

    public void printTotalBenefitsPrice(int totalBenefits) {
        System.out.println(TOTAL_BENEFITS_MONEY);
        System.out.printf(PRINT_MONEY, -totalBenefits);
        System.out.println();
        System.out.println();
    }

}
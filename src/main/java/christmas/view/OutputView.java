package christmas.view;

import christmas.common.consts.EventConst;
import christmas.discount.DiscountPolicy;
import christmas.model.Menu;

import java.util.Map;

import static christmas.common.consts.EventConst.*;
import static christmas.common.consts.OrderConst.*;
import static christmas.model.Menu.*;

public class OutputView {

    private final DiscountPolicy discountPolicy;

    public OutputView(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

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
        System.out.printf("%,d원", totalPriceBeforeDiscount);
        System.out.println();

        if (totalPriceBeforeDiscount < LEAST_ORDER_PRICE) throw new IllegalArgumentException("최소 주문 금액은 10000원 입니다");

        int giftCount = getGiftCount(totalPriceBeforeDiscount);

        printGift(giftCount);

        while (giftCount > 0) {
            Menu 샴페인 = valueOf("샴페인");
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

    public int getGiftCount(int totalPriceBeforeDiscount) {
        int giftCount = 0;
        while (totalPriceBeforeDiscount >= GIVE_CHAMPAGNE_PRICE) {
            giftCount++;
            totalPriceBeforeDiscount -= GIVE_CHAMPAGNE_PRICE;
        }
        return giftCount;
    }

    public void printBenefits(int expectedVisitDate, Map<Menu, Integer> menuAndCount, int totalPriceBeforeDiscount, int giftChampagneCount) {
        System.out.println("<혜택 내역>");

        int totalDisCountPrice = discountPolicy.discount(expectedVisitDate, menuAndCount);

        if (totalDisCountPrice == 0) {
            System.out.println("없음");
        }

        int giftChampagneTotalMoney = printGiftEvent(giftChampagneCount);

        int totalBenefitsPrice = giftChampagneTotalMoney + totalDisCountPrice;

        printTotalBenefitsPrice(totalBenefitsPrice);

        int totalPriceAfterDiscount = totalPriceBeforeDiscount - totalDisCountPrice;

        printTotalPriceAfterDiscount(totalPriceAfterDiscount);

        printEventBadge(totalDisCountPrice);

    }

    private static int printGiftEvent(int giftCount) {
        int giftChampagneTotalMoney = 샴페인.getPrice() * giftCount;
        if (giftCount > 0) {
            System.out.println("증정 이벤트: " + -giftChampagneTotalMoney);
        }
        return giftChampagneTotalMoney;
    }

    public void printEventBadge(int totalDisCountPrice) {
        System.out.println("<12월 이벤트 배지>");
        if (totalDisCountPrice >= STAR_LEAST_PRICE && totalDisCountPrice < STAR_HIGHEST_PRICE) System.out.println("별");
        if (totalDisCountPrice >= TREE_LEAST_PRICE && totalDisCountPrice < TREE_HIGHEST_PRICE) System.out.println("트리");
        if (totalDisCountPrice >= SANTA_LEAST_PRICE) System.out.println("산타");
        if (totalDisCountPrice < NOTHING) System.out.println("없음");
    }

    public void printTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원", totalPriceAfterDiscount);
        System.out.println();
    }

    public void printTotalBenefitsPrice(int totalBenefits) {
        System.out.println("<총혜택 금액>");
        System.out.printf("%,d원", -totalBenefits);
        System.out.println();
    }
}
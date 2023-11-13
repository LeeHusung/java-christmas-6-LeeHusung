package christmas.service;

import christmas.discount.EventPolicy;
import christmas.model.Menu;

import java.util.Map;

import static christmas.common.consts.OrderConst.GIVE_CHAMPAGNE_PRICE;
import static christmas.model.Menu.valueOf;

public class ChristmasService {

    private final EventPolicy discountPolicy;

    public int discount(int expectedVisitDate, Map<Menu, Integer> orderMap) {
        return discountPolicy.discount(expectedVisitDate, orderMap);
    }

    public ChristmasService(EventPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public int getGiftCount(int totalPriceBeforeDiscount) {
        int giftCount = 0;
        while (totalPriceBeforeDiscount >= GIVE_CHAMPAGNE_PRICE) {
            giftCount++;
            totalPriceBeforeDiscount -= GIVE_CHAMPAGNE_PRICE;
        }
        return giftCount;
    }

    public void plusChampagneCountByGift(int giftCount, Map<Menu, Integer> orderMap) {
        while (giftCount > 0) {
            Menu 샴페인 = valueOf("샴페인");
            orderMap.put(샴페인, orderMap.getOrDefault(샴페인, 0) + 1);
            giftCount--;
        }
    }
}

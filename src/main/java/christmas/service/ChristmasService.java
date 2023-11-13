package christmas.service;

import christmas.discount.EventPolicy;
import christmas.model.Menu;

import java.util.Map;

import static christmas.common.consts.OrderConst.GIVE_CHAMPAGNE_PRICE;
import static christmas.model.Menu.valueOf;
import static christmas.model.Menu.샴페인;

public class ChristmasService {

    private final EventPolicy discountPolicy;

    public int discount(int expectedVisitDate, Map<Menu, Integer> orderMap) {
        return discountPolicy.discount(expectedVisitDate, orderMap);
    }

    public ChristmasService(EventPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public int getGiftCount(int totalPriceBeforeDiscount) {
        return totalPriceBeforeDiscount / GIVE_CHAMPAGNE_PRICE;
    }

    public void plusChampagneCountByGift(int giftCount, Map<Menu, Integer> orderMap) {
        Menu champagne = valueOf("샴페인");
        orderMap.put(champagne, orderMap.getOrDefault(champagne, 0) + giftCount);
    }

    public int getTotalPriceBeforeDiscount(Map<Menu, Integer> orderMap) {
        int totalPriceBeforeDiscount = 0;
        for (Menu menu : orderMap.keySet()) {
            int count = orderMap.get(menu);
            totalPriceBeforeDiscount += menu.getPrice() * count;
        }
        return totalPriceBeforeDiscount;
    }

    public int getGiftEventTotalMoney(int giftCount) {
        return 샴페인.getPrice() * giftCount;
    }
}

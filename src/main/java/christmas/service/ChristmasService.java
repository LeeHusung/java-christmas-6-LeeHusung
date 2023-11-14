package christmas.service;

import christmas.discount.EventPolicy;
import christmas.model.Menu;

import java.util.Map;

import static christmas.common.consts.EventConst.APPLY_EVENT_LEAST_PRICE;
import static christmas.common.consts.OrderConst.GIVE_CHAMPAGNE_PRICE;
import static christmas.common.consts.TitleConst.BENEFITS_DETAIL;
import static christmas.model.Menu.valueOf;
import static christmas.model.Menu.샴페인;

public class ChristmasService {

    private final EventPolicy discountPolicy;

    public ChristmasService(EventPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public int discount(int expectedVisitDate, Map<Menu, Integer> orderMap) {
        System.out.println(BENEFITS_DETAIL);
        int totalPriceBeforeDiscount = getTotalPriceBeforeDiscount(orderMap);
        if (totalPriceBeforeDiscount >= APPLY_EVENT_LEAST_PRICE) {
            return discountPolicy.discount(expectedVisitDate, orderMap, totalPriceBeforeDiscount);
        }
        return 0;
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

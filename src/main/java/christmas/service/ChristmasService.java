package christmas.service;

import christmas.discount.EventPolicy;
import christmas.model.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.common.consts.EventConst.APPLY_EVENT_LEAST_PRICE;
import static christmas.common.consts.OrderConst.GIVE_CHAMPAGNE_PRICE;
import static christmas.common.consts.TitleConst.BENEFITS_DETAIL;
import static christmas.common.exception.InputValidation.*;
import static christmas.model.Menu.valueOf;
import static christmas.model.Menu.샴페인;

public class ChristmasService {

    private final EventPolicy discountPolicy;

    public ChristmasService(EventPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public int discount(int expectedVisitDate, Map<Menu, Integer> orderMap) {
        return discountPolicy.discount(expectedVisitDate, orderMap);
    }

    public Map<Menu, Integer> readMenu(String input) {
        String[] menuSplit = input.split(",");
        List<String> menus = new ArrayList<>();
        Map<Menu, Integer> orderMap = new HashMap<>();
        int orderTotalCount = 0;
        for (String menu : menuSplit) {
            String[] menuAndCount = validateAndSplitMenuAndCount(menu);
            addMenuToOrder(orderMap, menu);
            menus.add(menuAndCount[0]);
            orderTotalCount += Integer.parseInt(menuAndCount[1]);
        }
        validateMenu(menus);
        validateInputOrderCountSumOver20(orderTotalCount);
        return orderMap;
    }

    private static void addMenuToOrder(Map<Menu, Integer> orderMap, String menu) {
        String[] menuAndCount = menu.split("-");
        Menu orderMenu = Menu.valueOf(menuAndCount[0]);
        int menuCount = Integer.parseInt(menuAndCount[1]);
        orderMap.put(orderMenu, menuCount);
    }

    public int calculateDiscount(int expectedVisitDate, Map<Menu, Integer> orderMap) {
        int totalPriceBeforeDiscount = getTotalPriceBeforeDiscount(orderMap);
        System.out.println(BENEFITS_DETAIL);
        if (totalPriceBeforeDiscount >= APPLY_EVENT_LEAST_PRICE) {
            return discount(expectedVisitDate, orderMap);
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

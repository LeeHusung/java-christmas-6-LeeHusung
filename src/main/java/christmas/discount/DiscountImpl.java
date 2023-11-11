package christmas.discount;

import christmas.model.Menu;

import java.util.Map;

import static christmas.common.consts.DiscountConst.*;
import static christmas.common.consts.DateConst.*;

public class DiscountImpl implements DiscountPolicy {

    @Override
    public int discount(int expectedVisitDate, Map<Menu, Integer> menuAndCount) {

        for (int i = 1; i <= 31; i++) {
            if (i % 7 == 1 || i % 7 == 2) weekends.add(i);
            else weekdays.add(i);
            if (i % 7 == 3) specialDays.add(i);
        }
        specialDays.add(CHRISTMAS_DAY);
        //특별할인
        int sum = 0;

        if (expectedVisitDate <= CHRISTMAS_DAY) {
            int benefit = (expectedVisitDate - 1) * 100 + 1000;
            sum += benefit;
            System.out.printf("크리스마스 디데이 할인: %,d원", -benefit);
            System.out.println();
        }

        if (specialDays.contains(expectedVisitDate)) {
            System.out.printf("특별 할인: %,d원", -SPECIAL_DISCOUNT);
            System.out.println();
            sum += SPECIAL_DISCOUNT;
        }

        int discountWeekday = 0;
        int discountWeekend = 0;
        for (Menu menu : menuAndCount.keySet()) {
            if (weekends.contains(expectedVisitDate) && menu.isMain()) {
                discountWeekend += menuAndCount.get(menu) * WEEKEND_MAIN_DISCOUNT;
                sum += menuAndCount.get(menu) * WEEKEND_MAIN_DISCOUNT;
            }

            if (weekdays.contains(expectedVisitDate) && menu.isDessert()) {
                discountWeekday += menuAndCount.get(menu) * WEEKDAY_DESSERT_DISCOUNT;
                sum += menuAndCount.get(menu) * WEEKDAY_DESSERT_DISCOUNT;
            }
        }

        if (discountWeekend > 0) {
            System.out.printf("주말 할인: %,d원", -discountWeekend);
            System.out.println();
        }
        if (discountWeekday > 0) {
            System.out.printf("평일 할인: %,d원", -discountWeekday);
            System.out.println();
        }
        return sum;
    }
}

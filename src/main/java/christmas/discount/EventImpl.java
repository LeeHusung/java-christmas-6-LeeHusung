package christmas.discount;

import christmas.model.Menu;

import java.util.Map;

import static christmas.common.consts.DiscountConst.*;
import static christmas.common.consts.DateConst.*;

public class EventImpl implements EventPolicy {

    @Override
    public int discount(int expectedVisitDate, Map<Menu, Integer> menuAndCount, int totalPriceBeforeDiscount) {
        setWeekendsAndWeekdays();

        int sum = 0;
        sum += discountChristmasDDay(expectedVisitDate);
        sum += discountSpecialDay(expectedVisitDate);
        sum += discountWeekdays(expectedVisitDate, menuAndCount);
        sum += discountWeekends(expectedVisitDate, menuAndCount);

        return sum;
    }

    public int discountWeekdays(int expectedVisitDate, Map<Menu, Integer> orderMap) {
        int discountWeekdays = 0;
        for (Menu menu : orderMap.keySet()) {
            if (weekdays.contains(expectedVisitDate) && menu.isDessert()) {
                discountWeekdays += orderMap.get(menu) * WEEKDAY_DESSERT_DISCOUNT;
            }
        }
        printDiscount("평일 할인", discountWeekdays);
        return discountWeekdays;
    }
    public int discountWeekends(int expectedVisitDate, Map<Menu, Integer> orderMap) {
        int discountWeekends = 0;
        for (Menu menu : orderMap.keySet()) {
            if (weekends.contains(expectedVisitDate) && menu.isMain()) {
                discountWeekends += orderMap.get(menu) * WEEKEND_MAIN_DISCOUNT;
            }
        }
        printDiscount("주말 할인", discountWeekends);
        return discountWeekends;
    }

    public int discountSpecialDay(int expectedVisitDate) {
        int specialDiscount = 0;
        if (specialDays.contains(expectedVisitDate)) {
            System.out.printf("특별 할인: %,d원", -SPECIAL_DISCOUNT);
            System.out.println();
            specialDiscount += SPECIAL_DISCOUNT;
        }
        return specialDiscount;
    }

    public int discountChristmasDDay(int expectedVisitDate) {
        int benefit = 0;
        if (expectedVisitDate <= CHRISTMAS_DAY) {
            benefit = (expectedVisitDate - 1) * 100 + 1000;
            System.out.printf("크리스마스 디데이 할인: %,d원", -benefit);
            System.out.println();
        }
        return benefit;
    }

    public void printDiscount(String discountType, int discountDay) {
        if (discountDay > 0) {
            System.out.printf(discountType + ": %,d원", -discountDay);
            System.out.println();
        }
    }

    public static void setWeekendsAndWeekdays() {
        for (int i = 1; i <= 31; i++) {
            if (i % 7 == 1 || i % 7 == 2) {
                weekends.add(i);
                continue;
            }
            if (i % 7 == 3 || i % 7 == 4 || i % 7 == 5 || i % 7 == 6) {
                weekdays.add(i);
                continue;
            }
            if (i % 7 == 3) {
                specialDays.add(i);
            }
        }
        specialDays.add(CHRISTMAS_DAY);
    }
}

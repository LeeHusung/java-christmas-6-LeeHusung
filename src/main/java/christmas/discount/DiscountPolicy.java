package christmas.discount;

import christmas.model.Menu;

import java.util.Map;

public interface DiscountPolicy {

    int discount(int expectedVisitDate, Map<Menu, Integer> menuAndCount);

    //할인
//    public void discount100PlusEverydayUntil25();
//    public void discountWeekdays();
//    public void discountWeekend();
//    public void discountHoliday();
}

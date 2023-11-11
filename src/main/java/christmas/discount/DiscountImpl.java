package christmas.discount;

import christmas.model.Menu;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DiscountImpl implements DiscountPolicy {

    static Set<Integer> weekdays = new HashSet<>();
    static Set<Integer> weekends = new HashSet<>();
    static Set<Integer> specialDays = new HashSet<>();

    @Override
    public int discount(int expectedVisitDate, Map<Menu, Integer> menuAndCount) {

        for (int i = 1; i <= 31; i++) {
            if (i % 7 == 1 || i % 7 == 2) weekends.add(i);
            else weekdays.add(i);
            if (i % 7 == 3) specialDays.add(i);
        }
        specialDays.add(25);
        //특별할인
        int sum = 0;

        if (expectedVisitDate <= 25) {
            int benefit = (expectedVisitDate - 1) * 100 + 1000;
            sum += benefit;
            System.out.printf("크리스마스 디데이 할인: %,d원", -benefit);
            System.out.println();
        }

        if (specialDays.contains(expectedVisitDate)) {
            System.out.printf("특별 할인: %,d원", -1000);
            System.out.println();
            sum += 1000;
        }


        if (weekends.contains(expectedVisitDate)) {
            for (Menu menu : menuAndCount.keySet()) {
                if (menu.isMain()) {
                    sum += menuAndCount.get(menu) * 2023;
                    System.out.printf("주말 할인: %,d원", -(menuAndCount.get(menu) * 2023));
                    System.out.println();
                }
            }
        }

        if (weekdays.contains(expectedVisitDate)) {
            for (Menu menu : menuAndCount.keySet()) {
                if (menu.isDessert()) {
                    sum += menuAndCount.get(menu) * 2023;
                    System.out.printf("평일 할인: %,d원", -(menuAndCount.get(menu) * 2023));
                    System.out.println();
                }
            }
        }
        return sum;
    }
}

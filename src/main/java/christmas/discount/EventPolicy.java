package christmas.discount;

import christmas.model.Menu;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface EventPolicy {

    Set<Integer> weekdays = new HashSet<>();
    Set<Integer> weekends = new HashSet<>();
    Set<Integer> specialDays = new HashSet<>();

    int discount(int expectedVisitDate, Map<Menu, Integer> menuAndCount);
}

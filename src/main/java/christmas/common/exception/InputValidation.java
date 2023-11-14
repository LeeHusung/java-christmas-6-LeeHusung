package christmas.common.exception;

import christmas.model.Menu;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static christmas.common.consts.ErrorMessageConst.*;
import static christmas.common.consts.OrderConst.*;

public class InputValidation {

    public static boolean validateDate(int expectedVisitDate) {
        return !(expectedVisitDate < 1 || expectedVisitDate > 31);
    }

    public static void validateMenu(List<String> menuList) {
        validateDuplicateMenu(menuList);
        validateDrinkOnly(menuList);
    }

    public static String[] validateAndSplitMenuAndCount(String menu) {
        validateSplitMenuAndCount(menu);
        String[] menuAndCount = menu.split("-");
        validateOrder(menuAndCount);
        return menuAndCount;
    }
    public static void validateOrder(String[] menuAndCount) {
        validateExistMenu(menuAndCount[0]);
        validateOrderCountNumberFormat(menuAndCount[1]);
        validateInputOrderCountUnder1(Integer.parseInt(menuAndCount[1]));
    }
    public static void validateDuplicateMenu(List<String> menuList) {
        Set<String> menuSet = new HashSet<>(menuList);
        if (menuSet.size() != menuList.size()) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    public static void validateDrinkOnly(List<String> menuList) {
        boolean isDrinkOnly = true;
        for (String menu : menuList) {
            if (!Menu.isDrink(menu)) {
                isDrinkOnly = false;
                break;
            }
        }
        if (isDrinkOnly) {
            throw new IllegalArgumentException(NOT_ORDER_ONLY_DRINK);
        }
    }

    public static void validateOrderCountNumberFormat(String orderCountInput) {
        try {
            Integer.parseInt(orderCountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    public static void validateInputOrderCountSumOver20(int orderCount) {
        if (orderCount > ORDER_MAX_COUNT) throw new IllegalArgumentException(NEED_ORDER_SUM_UNDER_20);
    }

    public static void validateInputOrderCountUnder1(int orderCount) {
        if (orderCount < 1) throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
    }

    public static void validateExistMenu(String inputMenu) {
        if (!Menu.contain(inputMenu)) throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
    }

    public static void validateSplitMenuAndCount(String s) {
        String[] real = s.split("-");
        if (real.length != 2) throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
    }
}

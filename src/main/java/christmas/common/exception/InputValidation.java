package christmas.common.exception;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static christmas.common.consts.ErrorMessageConst.INVALID_ORDER_MESSAGE;
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
        validateInputOrderCountSumOver20(Integer.parseInt(menuAndCount[1]));
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
            throw new IllegalArgumentException("[ERROR] 음료수만으로는 주문이 불가능합니다");
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
        int sum = 0;
        sum += orderCount;
        if (sum > ORDER_MAX_COUNT) throw new IllegalArgumentException("[ERROR] 메뉴는 총 20개 까지만 주문 가능합니다.");
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

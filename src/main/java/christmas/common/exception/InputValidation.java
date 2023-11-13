package christmas.common.exception;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;

import java.util.Map;

import static christmas.common.consts.ErrorMessageConst.*;
import static christmas.common.consts.OrderConst.*;

public class InputValidation {

    public static int validateNumber() {
        String input;
        try {
            input = Console.readLine();
            if (!validateDate(Integer.parseInt(input))) throw new IllegalArgumentException(INPUT_DATE_ERROR);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_DATE_ERROR);
        }
    }

    private static boolean validateDate(int expectedVisitDate) {
        return !(expectedVisitDate < 1 || expectedVisitDate > 31);
    }


    public static void validateDuplicateMenu(Map<Menu, Integer> orderMap, String inputMenu) {
        Menu menu = Menu.valueOf(inputMenu);
        if (orderMap.containsKey(menu)) throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public static void validateOrderCountNumberFormat(String orderCountInput) {
        try {
            Integer.parseInt(orderCountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateInputOrderCountSumOver20(int orderCount) {
        int sum = 0;
        sum += orderCount;
        if (sum > ORDER_MAX_COUNT) throw new IllegalArgumentException("[ERROR] 메뉴는 총 20개 까지만 주문 가능합니다.");
    }

    public static void validateInputOrderCountUnder1(int orderCount) {
        if (orderCount < 1) throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public static void validateExistMenu(String inputMenu) {
        if (!Menu.contain(inputMenu)) throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public static void validateSplitMenuAndCount(String s) {
        String[] real = s.split("-");
        if (real.length != 2) throw new IllegalArgumentException("[ERROR] 올바른 형식으로 입력해주세요");
    }

    public static boolean validateDrinkOnly(Map<Menu, Integer> orderMap) {
        boolean isDrinkOnly = true;
        for (Menu menu : orderMap.keySet()) {
            if (!menu.isDrink()) {
                isDrinkOnly = false;
                break;
            }
        }
        if (isDrinkOnly) {
            System.out.println("[ERROR] 음료수만으로는 주문이 불가능합니다");
            return false;
        }
        return true;
    }
}

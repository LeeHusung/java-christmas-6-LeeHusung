package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;

import java.util.HashMap;
import java.util.Map;

import static christmas.common.exception.InputValidation.*;

public class InputView {

    public int readDate() {
        int input = 0;
        try {
            input = validateNumber();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readDate();
        }
        return input;
    }

    public Map<Menu, Integer> readMenuAndCount() {
        try {
            System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            String input = Console.readLine();
            String[] menus = splitMenu(input);
            Map<Menu, Integer> orderMap = inputOrder(menus);
            if (!validateDrinkOnly(orderMap)) {
                return readMenuAndCount();
            }

            return orderMap;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readMenuAndCount();
        }
    }

    private String[] splitMenu(String input) {
        return input.split(",");
    }

    private Map<Menu, Integer> inputOrder(String[] menus) {
        Map<Menu, Integer> orderMap = new HashMap<>();
        for (String menu : menus) {
            validateSplitMenuAndCount(menu);
            String[] menuAndCount = menu.split("-");

            validateExistMenu(menuAndCount[0]);
            validateDuplicateMenu(orderMap, menuAndCount[0]);
            Menu orderMenu = Menu.valueOf(menuAndCount[0]);

            validateOrderCountNumberFormat(menuAndCount[1]);
            validateInputOrderCountUnder1(Integer.parseInt(menuAndCount[1]));
            validateInputOrderCountSumOver20(Integer.parseInt(menuAndCount[1]));


            orderMap.put(orderMenu, Integer.parseInt(menuAndCount[1]));
        }
        return orderMap;
    }
}
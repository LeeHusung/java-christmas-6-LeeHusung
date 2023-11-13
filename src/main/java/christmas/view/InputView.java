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
            Map<Menu, Integer> orderMap = parseMenuAndCount(input);

            if (!validateDrinkOnly(orderMap)) {
                return readMenuAndCount();
            }

            return orderMap;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readMenuAndCount();
        }
    }

    public static Map<Menu, Integer> parseMenuAndCount(String input) {
        Map<Menu, Integer> orderMap = new HashMap<>();;
        String[] split = input.split(",");
        for (String s : split) {

            validateSplitMenuAndCount(s);
            String[] real = s.split("-");

            validateExistMenu(real[0]);
            validateDuplicateMenu(orderMap, real[0]);
            Menu orderMenu = Menu.valueOf(real[0]);


            validateOrderCountNumberFormat(real[1]);
            int orderCount = Integer.parseInt(real[1]);
            validateInputOrderCountUnder1(orderCount);
            validateInputOrderCountSumOver20(orderCount);


            orderMap.put(orderMenu, orderCount);
        }
        return orderMap;
    }

}
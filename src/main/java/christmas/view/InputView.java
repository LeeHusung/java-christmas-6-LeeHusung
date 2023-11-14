package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.common.consts.ErrorMessageConst.INPUT_DATE_ERROR;
import static christmas.common.exception.InputValidation.*;

public class InputView {

    public int readDate() {
        String input = Console.readLine();
        int parseInput = 0;
        try {
            parseInput = parseInputDate(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readDate();
        }
        return parseInput;
    }

    public static int parseInputDate(String input) {
        try {
            int parseInput = Integer.parseInt(input);
            if (!validateDate(parseInput)) throw new IllegalArgumentException(INPUT_DATE_ERROR);
            return parseInput;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_DATE_ERROR);
        }
    }

    public String inputMenu() {
        try {
            System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            String input = Console.readLine();
            String[] menus = splitMenu(input);
            List<String> menuList = new ArrayList<>();
            for (String menu : menus) {
                String[] menuAndCount = validateAndSplitMenuAndCount(menu);
                menuList.add(menuAndCount[0]);
            }
            validateMenu(menuList);
            return input;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMenu();
        }
    }

    public Map<Menu, Integer> readMenu(String input) {
        String[] menus = splitMenu(input);

        Map<Menu, Integer> orderMap = new HashMap<>();
        for (String menu : menus) {
            addMenuToOrder(orderMap, menu);
        }
        return orderMap;
    }

    private static void addMenuToOrder(Map<Menu, Integer> orderMap, String menu) {
        String[] menuAndCount = menu.split("-");
        Menu orderMenu = Menu.valueOf(menuAndCount[0]);
        int menuCount = Integer.parseInt(menuAndCount[1]);
        orderMap.put(orderMenu, menuCount);
    }

    private String[] splitMenu(String input) {
        return input.split(",");
    }
}
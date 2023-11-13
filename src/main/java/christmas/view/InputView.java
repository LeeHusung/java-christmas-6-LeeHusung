package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;

import java.util.HashMap;
import java.util.Map;

public class InputView {

    public int readDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    public Map<Menu, Integer> readMenuAndCount() {

        boolean isDrinkOnly = true;
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        String[] split = input.split(",");
        Map<Menu, Integer> orderMap = new HashMap<>();
        for (String s : split) {
            String[] real = s.split("-");
            Menu menu = Menu.valueOf(real[0]);
            int orderCount = Integer.parseInt(real[1]);
            if (!menu.isDrink()) {
                isDrinkOnly = false;
            }
            orderMap.put(menu, orderCount);
        }

        if (isDrinkOnly) throw new IllegalArgumentException("[ERROR] 음료수만으로는 주문이 불가능합니다");

        return orderMap;
    }

}
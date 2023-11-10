package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;

import java.util.HashMap;
import java.util.Map;

public class InputView {

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    public void readMenuAndCount() {
        boolean isDrinkOnly = true;
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        String[] split = input.split(",");
        Map<Menu, Integer> orderMap = new HashMap<>();
        for (String s : split) {
            String[] real = s.split("-");
            Menu menu = Menu.valueOf(real[0]);
            if (!menu.isDrink()) {
                isDrinkOnly = false;
            }
            orderMap.put(menu, Integer.parseInt(real[1]));
        }

        if (isDrinkOnly) throw new IllegalArgumentException("[ERROR] 음료수만으로는 주문이 안됩니다");

        OutputView outputView = new OutputView();
        outputView.printMenu(orderMap);

    }

}
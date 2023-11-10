package christmas.controller;

import christmas.view.InputView;

public class Controller {

    public void takeOrder() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        InputView inputView = new InputView();
        int expectedVisitDate = inputView.readDate();

        System.out.println("12월 " + expectedVisitDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        inputView.readMenuAndCount();
    }
}

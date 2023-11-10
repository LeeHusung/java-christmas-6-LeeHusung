package christmas.controller;

import christmas.view.InputView;

public class Controller {

    public void takeOrder() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        InputView inputView = new InputView();
        int visitDate = inputView.readDate();
    }
}

package christmas.controller;

import christmas.discount.DiscountImpl;
import christmas.model.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class Controller {

    public void takeOrder() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        InputView inputView = new InputView();
        int expectedVisitDate = inputView.readDate();

        System.out.println("12월 " + expectedVisitDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        Map<Menu, Integer> readMenuAndCount = inputView.readMenuAndCount();
        OutputView outputView = new OutputView(new DiscountImpl());
        outputView.printMenu(readMenuAndCount);
        int totalPriceBeforeDiscount = outputView.printTotalPriceBeforeDiscount(readMenuAndCount);
        int giftCount = outputView.getGiftCount(totalPriceBeforeDiscount);
        outputView.printBenefits(expectedVisitDate, readMenuAndCount, totalPriceBeforeDiscount, giftCount);
    }
}

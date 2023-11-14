package christmas.controller;

import christmas.model.Menu;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.common.consts.EventConst.*;
import static christmas.common.consts.TitleConst.*;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasService christmasService;

    public ChristmasController(InputView inputView, OutputView outputView, ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasService = christmasService;
    }

    public void takeOrder() {
        greetCustomers();
        int expectedVisitDate = inputView.readDate();

        String input = inputView.inputMenu();
        Map<Menu, Integer> orderMap = inputView.readMenu(input);

        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);

        outputView.printOrder(orderMap, expectedVisitDate, giftCount);
        christmasService.plusChampagneCountByGift(giftCount, orderMap);

        int totalDiscount = calculateDiscount(expectedVisitDate, orderMap);

        int giftChampagneTotalMoney = christmasService.getGiftEventTotalMoney(giftCount);

        outputView.printResultAfterDiscount(totalDiscount, totalPriceBeforeDiscount, giftChampagneTotalMoney);
    }


    private int calculateDiscount(int expectedVisitDate, Map<Menu, Integer> orderMap) {
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        System.out.println(BENEFITS_DETAIL);
        if (totalPriceBeforeDiscount >= APPLY_EVENT_LEAST_PRICE) {
            return christmasService.discount(expectedVisitDate, orderMap);
        }
        return 0;
    }


    private static void greetCustomers() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }
}

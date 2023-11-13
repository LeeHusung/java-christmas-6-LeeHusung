package christmas.controller;

import christmas.common.exception.InputValidation;
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
    private final InputValidation validate;

    public ChristmasController(InputView inputView, OutputView outputView, ChristmasService christmasService, InputValidation validate) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasService = christmasService;
        this.validate = validate;
    }

    public void takeOrder() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int expectedVisitDate = inputView.readDate();

        Map<Menu, Integer> orderMap = inputView.readMenuAndCount();

        outputView.printMenu(orderMap, expectedVisitDate);

        int totalPriceBeforeDiscount = outputView.printTotalPriceBeforeDiscount(orderMap);

        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);
        outputView.printGift(giftCount);
        christmasService.plusChampagneCountByGift(giftCount, orderMap);

        System.out.println(BENEFITS_DETAIL);
        int totalDiscount = 0;
        if (totalPriceBeforeDiscount >= APPLY_EVENT_LEAST_PRICE) {
            totalDiscount = christmasService.discount(expectedVisitDate, orderMap);
        }
        if (totalDiscount == 0) System.out.println("없음");

        int giftChampagneTotalMoney = outputView.printGiftEvent(giftCount);
        outputView.printTotalBenefitsPrice(totalDiscount + giftChampagneTotalMoney);
        outputView.printTotalPriceAfterDiscount(totalPriceBeforeDiscount - totalDiscount);
        outputView.printEventBadge(totalDiscount + giftChampagneTotalMoney);
    }
}

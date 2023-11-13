package christmas.controller;

import christmas.common.consts.EventConst;
import christmas.common.consts.TitleConst;
import christmas.discount.DiscountImpl;
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

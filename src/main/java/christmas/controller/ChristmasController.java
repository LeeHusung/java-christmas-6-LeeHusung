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
        outputView.greetCustomers();

        int expectedVisitDate = inputView.inputDate();

        Map<Menu, Integer> orderMap = inputView.inputMenu();

        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);

        outputView.printOrder(orderMap, expectedVisitDate, giftCount);
        christmasService.plusChampagneCountByGift(giftCount, orderMap);

        int totalDiscount = christmasService.discount(expectedVisitDate, orderMap);

        int giftChampagneTotalMoney = christmasService.getGiftEventTotalMoney(giftCount);

        outputView.printResultAfterDiscount(totalDiscount, totalPriceBeforeDiscount, giftChampagneTotalMoney);
    }
}

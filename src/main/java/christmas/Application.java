package christmas;

import christmas.controller.ChristmasController;
import christmas.discount.EventImpl;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasController controller = new ChristmasController(new InputView(), new OutputView(), new ChristmasService(new EventImpl()));
        controller.takeOrder();
    }
}

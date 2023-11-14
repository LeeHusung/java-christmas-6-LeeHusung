package christmas.service;

import christmas.discount.EventImpl;
import christmas.model.Menu;
import christmas.view.InputView;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChristmasServiceTest {

    InputView inputView = new InputView();
    ChristmasService christmasService = new ChristmasService(new EventImpl());

    @Test
    void 할인전총가격계산() {
        //given
        String input = "티본스테이크-1,아이스크림-1";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        //then
        assertThat(totalPriceBeforeDiscount).isEqualTo(60000);
    }

    @Test
    void 할인전총가격계산2() {
        //given
        String input = "티본스테이크-10,아이스크림-10";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        //then
        assertThat(totalPriceBeforeDiscount).isEqualTo(600000);
    }

    @Test
    void 할인전총가격계산3() {
        //given
        String input = "타파스-10,레드와인-1,샴페인-2";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        //then
        assertThat(totalPriceBeforeDiscount).isEqualTo(165000);
    }

    @Test
    void 할인전총가격에의해받는샴페인개수() {
        //given
        String input = "타파스-10,레드와인-1,샴페인-2";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);
        //then
        assertThat(giftCount).isEqualTo(1);
    }

    @Test
    void 할인전총가격에의해받는샴페인개수2() {
        //given
        String input = "티본스테이크-1";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);
        //then
        assertThat(giftCount).isEqualTo(0);
    }

    @Test
    void 할인전총가격에의해받는샴페인개수3() {
        //given
        String input = "티본스테이크-20";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);
        //then
        assertThat(giftCount).isEqualTo(9);
    }

    @Test
    void 증정이벤트할인가격() {
        //given
        String input = "티본스테이크-20";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);
        int giftEventTotalMoney = christmasService.getGiftEventTotalMoney(giftCount);
        //then
        assertThat(giftEventTotalMoney).isEqualTo(25000 * 9);
    }

    @Test
    void 증정이벤트할인가격2() {
        //given
        String input = "해산물파스타-1";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);
        int giftEventTotalMoney = christmasService.getGiftEventTotalMoney(giftCount);
        //then
        assertThat(giftEventTotalMoney).isEqualTo(0);
    }

    @Test
    void 증정이벤트할인가격3() {
        //given
        String input = "해산물파스타-1,아이스크림-19";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);
        int giftEventTotalMoney = christmasService.getGiftEventTotalMoney(giftCount);
        //then
        assertThat(giftEventTotalMoney).isEqualTo(25000);
    }

    @Test
    void 증정샴페인도주문에포함() {
        //given
        String input = "해산물파스타-1,아이스크림-19";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);
        christmasService.plusChampagneCountByGift(giftCount, orderMap);
        //then
        assertThat(orderMap.get(Menu.샴페인)).isEqualTo(1);
    }

    @Test
    void 증정샴페인도주문에포함2() {
        //given
        String input = "티본스테이크-2,샴페인-1";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);
        christmasService.plusChampagneCountByGift(giftCount, orderMap);
        //then
        assertThat(orderMap.get(Menu.샴페인)).isEqualTo(2);
    }

    @Test
    void 증정샴페인도주문에포함3() {
        //given
        String input = "티본스테이크-2,샴페인-10";
        //when
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        int totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMap);
        int giftCount = christmasService.getGiftCount(totalPriceBeforeDiscount);
        christmasService.plusChampagneCountByGift(giftCount, orderMap);
        //then
        assertThat(orderMap.get(Menu.샴페인)).isEqualTo(13);
    }
}
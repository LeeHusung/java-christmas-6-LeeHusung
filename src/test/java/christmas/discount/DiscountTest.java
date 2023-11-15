package christmas.discount;

import christmas.model.Menu;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.discount.EventImpl.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DiscountTest {

    InputView inputView = new InputView();
    EventImpl event = new EventImpl();
    ChristmasService christmasService = new ChristmasService(event);

    @Test
    void 주문금액이만원이상이면할인() {
        //given
        int expectedVisitDate = 3;
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discountPrice = christmasService.discount(expectedVisitDate, orderMap);
        //then
        assertThat(discountPrice).isEqualTo(6246);
    }

    @Test
    void 주문금액이만원이하면할인X() {
        //given
        int expectedVisitDate = 3;
        String input = "아이스크림-1";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discountPrice = christmasService.discount(expectedVisitDate, orderMap);
        //then
        assertThat(discountPrice).isEqualTo(0);
    }

    @Test
    void 크리스마스디데이() {
        //given
        int expectedVisitDate = 24;
        //when
        int discountChristmasDDayPrice = event.discountChristmasDDay(expectedVisitDate);
        //then
        assertThat(discountChristmasDDayPrice).isEqualTo((expectedVisitDate - 1) * 100 + 1000);
    }

    @Test
    void 크리스마스디데이할인적용X() {
        //given
        int expectedVisitDate = 27;
        //when
        int discountChristmasDDayPrice = event.discountChristmasDDay(expectedVisitDate);
        //then
        assertThat(discountChristmasDDayPrice).isEqualTo(0);
    }

    @Test
    void 평일할인() {
        //given
        int expectedVisitDate = 24;
        String input = "아이스크림-20";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discountWeekdayPrice = event.discountWeekdays(expectedVisitDate, orderMap);
        //then
        assertThat(discountWeekdayPrice).isEqualTo(2023 * 20);
    }

    @Test
    void 평일할인2() {
        //given
        int expectedVisitDate = 6;
        String input = "아이스크림-2";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discountWeekdayPrice = event.discountWeekdays(expectedVisitDate, orderMap);
        //then
        assertThat(discountWeekdayPrice).isEqualTo(2023 * 2);
    }

    @Test
    void 평일할인3() {
        //given
        int expectedVisitDate = 6;
        String input = "아이스크림-1";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discount = christmasService.discount(expectedVisitDate, orderMap);
        int discountWeekdayPrice = event.discountWeekdays(expectedVisitDate, orderMap);
        //then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    void 주말할인() {
        //given
        int expectedVisitDate = 16;
        String input = "티본스테이크-1";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discountWeekendPrice = event.discountWeekends(expectedVisitDate, orderMap);
        //then
        assertThat(discountWeekendPrice).isEqualTo(2023);
    }

    @Test
    void 주말할인2() {
        //given
        int expectedVisitDate = 16;
        String input = "티본스테이크-20";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discountWeekendPrice = event.discountWeekends(expectedVisitDate, orderMap);
        //then
        assertThat(discountWeekendPrice).isEqualTo(2023 * 20);
    }

    @Test
    void 특별할인() {
        //given
        int expectedVisitDate = 25;
        //when
        int discountSpecialDayPrice = event.discountSpecialDay(expectedVisitDate);
        //then
        assertThat(discountSpecialDayPrice).isEqualTo(1000);
    }

    @Test
    void 특별할인2() {
        setWeekendsAndWeekdays();
        //given
        int expectedVisitDate = 31;
        //when
        int discountSpecialDayPrice = event.discountSpecialDay(expectedVisitDate);
        //then
        assertThat(discountSpecialDayPrice).isEqualTo(1000);
    }

    @Test
    void 크리스마스디데이할인_특별할인() {
        //given
        int expectedVisitDate = 25;
        String input = "티본스테이크-1";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discount = christmasService.discount(expectedVisitDate, orderMap);
        //then
        assertThat(discount).isEqualTo(4400);
    }

    @Test
    void 크리스마스디데이할인_평일할인() {
        //given
        int expectedVisitDate = 5;
        String input = "아이스크림-2";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discount = christmasService.discount(expectedVisitDate, orderMap);
        //then
        assertThat(discount).isEqualTo(5446);
    }

    @Test
    void 크리스마스디데이할인_평일할인_주문금액10000이하() {
        //given
        int expectedVisitDate = 5;
        String input = "아이스크림-1";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discount = christmasService.discount(expectedVisitDate, orderMap);
        //then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    void 크리스마스디데이할인_주말할인() {
        //given
        int expectedVisitDate = 23;
        String input = "티본스테이크-1";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discount = christmasService.discount(expectedVisitDate, orderMap);
        //then
        assertThat(discount).isEqualTo(5223);
    }

    @Test
    void 크리스마스디데이할인_주말할인_주문금액10000이하() {
        //given
        int expectedVisitDate = 23;
        String input = "아이스크림-1";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discount = christmasService.discount(expectedVisitDate, orderMap);
        //then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    void 크리스마스디데이할인_평일할인_특별할인() {
        //given
        int expectedVisitDate = 17;
        String input = "초코케이크-10";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discount = christmasService.discount(expectedVisitDate, orderMap);
        //then
        assertThat(discount).isEqualTo(23830);
    }

    @Test
    void 평일할인_특별할인() {
        //given
        int expectedVisitDate = 31;
        String input = "초코케이크-10";
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //when
        int discount = christmasService.discount(expectedVisitDate, orderMap);
        //then
        assertThat(discount).isEqualTo(21230);
    }
}

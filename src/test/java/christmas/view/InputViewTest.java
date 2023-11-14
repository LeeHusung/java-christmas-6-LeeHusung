package christmas.view;

import christmas.discount.EventImpl;
import christmas.model.Menu;
import christmas.service.ChristmasService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.view.InputView.*;
import static org.assertj.core.api.Assertions.*;

class InputViewTest {

    ChristmasService christmasService = new ChristmasService(new EventImpl());

    @Test
    void 날짜입력정상() {
        //given
        String input = "2";
        //when
        int parseInput = parseInputDate(input);
        //then
        assertThat(parseInput).isEqualTo(2);
    }

    @Test
    void 날짜입력비정상() {
        //given
        String input = "aa";
        //when
        //then
        assertThatThrownBy(() -> parseInputDate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비정상날짜입력1미만() {
        //given
        String input = "-2";
        //when
        //then
        assertThatThrownBy(() -> parseInputDate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비정상날짜입력31초과() {
        //given
        String input = "43";
        //when
        //then
        assertThatThrownBy(() -> parseInputDate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력정상테스트() {
        //given
        String input = "티본스테이크-20";
        //when
        Map<Menu, Integer> orderMap = christmasService.readMenu(input);

        //then
        assertThat(orderMap.size()).isEqualTo(1);
        assertThat(orderMap.containsKey(Menu.티본스테이크)).isTrue();
        assertThat(orderMap.get(Menu.티본스테이크)).isEqualTo(20);
    }
    @Test
    void 메뉴입력정상테스트두개주문() {
        //given
        String input = "티본스테이크-10,아이스크림-5";
        //when
        Map<Menu, Integer> orderMap = christmasService.readMenu(input);
        //then
        assertThat(orderMap.size()).isEqualTo(2);
        assertThat(orderMap.containsKey(Menu.티본스테이크)).isTrue();
        assertThat(orderMap.containsKey(Menu.아이스크림)).isTrue();
        assertThat(orderMap.get(Menu.티본스테이크)).isEqualTo(10);
        assertThat(orderMap.get(Menu.아이스크림)).isEqualTo(5);
    }

    @Test
    void 메뉴입력정상테스트여러개주문() {
        //given
        String input = "티본스테이크-10,아이스크림-5,레드와인-1,타파스-2";
        //when
        Map<Menu, Integer> orderMap = christmasService.readMenu(input);

        //then
        assertThat(orderMap.size()).isEqualTo(4);
        assertThat(orderMap.containsKey(Menu.티본스테이크)).isTrue();
        assertThat(orderMap.containsKey(Menu.아이스크림)).isTrue();
        assertThat(orderMap.containsKey(Menu.타파스)).isTrue();
        assertThat(orderMap.containsKey(Menu.레드와인)).isTrue();
        assertThat(orderMap.get(Menu.티본스테이크)).isEqualTo(10);
        assertThat(orderMap.get(Menu.아이스크림)).isEqualTo(5);
        assertThat(orderMap.get(Menu.타파스)).isEqualTo(2);
        assertThat(orderMap.get(Menu.레드와인)).isEqualTo(1);
    }

    @Test
    void 메뉴입력비정상테스트_형식잘못입력() {
        //given
        String input = "티본스테이크20";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력비정상테스트_형식잘못입력2() {
        //given
        String input = "티본스테이크 - 20";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력비정상테스트_형식잘못입력3() {
        //given
        String input = "티본스테이크-10, 아이스크림-3";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력비정상테스트_형식잘못입력4() {
        //given
        String input = "아이스크림-두개";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력비정상테스트_형식잘못입력5() {
        //given
        String input = "";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void 메뉴입력비정상테스트_개수1개미만() {
        //given
        String input = "티본스테이크-0";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력비정상테스트_음료수만주문() {
        //given
        String input = "레드와인-5";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력비정상테스트_없는메뉴주문() {
        //given
        String input = "김치피자탕수육-5";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void 메뉴입력비정상테스트_중복메뉴주문() {
        //given
        String input = "티본스테이크-10,티본스테이크-10";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력비정상테스트_중복메뉴주문2() {
        //given
        String input = "아이스크림-1,티본스테이크-1,티본스테이크-5";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력비정상테스트_총주문20개초과() {
        //given
        String input = "티본스테이크-30";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력비정상테스트_총주문20개초과2() {
        //given
        String input = "티본스테이크-10,아이스크림-10,타파스-10";
        //when
        //then
        assertThatThrownBy(() -> christmasService.readMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

}
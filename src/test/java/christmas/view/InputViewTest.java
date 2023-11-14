package christmas.view;

import christmas.model.Menu;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class InputViewTest {

    @Test
    void 날짜입력정상() {
        //given
        String input = "2";
        //when
        InputView inputView = new InputView();
        int parseInput = inputView.parseInputDate(input);
        //then
        assertThat(parseInput).isEqualTo(2);
    }

    @Test
    void 날짜입력비정상() {
        //given
        String input = "aa";
        //when
        InputView inputView = new InputView();
        //then
        assertThatThrownBy(() -> inputView.parseInputDate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비정상날짜입력1미만() {
        //given
        String input = "-2";
        //when
        InputView inputView = new InputView();
        //then
        assertThatThrownBy(() -> inputView.parseInputDate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비정상날짜입력31초과() {
        //given
        String input = "43";
        //when
        InputView inputView = new InputView();
        //then
        assertThatThrownBy(() -> inputView.parseInputDate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴입력정상테스트() {
        //given
        String input = "티본스테이크-20";
        //when
        InputView inputView = new InputView();
        Map<Menu, Integer> orderMap = inputView.readMenu(input);
        //then
        assertThat(orderMap.size()).isEqualTo(1);
        assertThat(orderMap.containsKey(Menu.티본스테이크)).isTrue();
        assertThat(orderMap.get(Menu.티본스테이크)).isEqualTo(20);
    }

}
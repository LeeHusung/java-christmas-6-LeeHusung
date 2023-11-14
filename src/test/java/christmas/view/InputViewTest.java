package christmas.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InputViewTest {

    @Test
    void 정상날짜입력() {
        //given
        String input = "2";
        //when
        InputView inputView = new InputView();
        int parseInput = inputView.parseInputDate(input);
        //then
        assertThat(parseInput).isEqualTo(2);
    }

    @Test
    void 비정상날짜입력() {
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

}
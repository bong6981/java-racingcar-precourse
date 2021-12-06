package racingcar.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.utils.StringConstants.*;

import org.junit.jupiter.api.Test;

import racingcar.car.Car;

class CarTest {

    @Test
    void 주행시_인자로_4이상의_수가_전달되면_position_1_증가() {
        Car car = Car.create("car");
        int initPosition = 0;
        int biggerThanFour = 5;

        assertThat(car.run(biggerThanFour)).isEqualTo(initPosition + NUMBER_TO_ADD_WHEN_CAR_GO);
    }

    @Test
    void 주행시_인자로_4미만의_수가_전달되면_position_변경_없음() {
        Car car = Car.create("car");
        int initPosition = 0;
        int smallerNumber= 1;

        assertThat(smallerNumber).isLessThan(MIN_NUMBER_TO_CAR_TO_GO);
        assertThat(car.run(smallerNumber)).isEqualTo(initPosition);
    }

    @Test
    void 자동차_이름이_공백이_들어왔을_경우_예외_발생() {
        String space = " ";

        assertThatThrownBy(() -> Car.create(space)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 자동차_이름이_정해진_글자수_아닐시_예외_발생() {
        String longName = "longerThanMaxLength";

        assertThat(longName.length()).isGreaterThan(MAX_LENGTH_OF_CAR_NAME);
        assertThatThrownBy(() -> Car.create(longName)).isInstanceOf(IllegalArgumentException.class);
    }
}
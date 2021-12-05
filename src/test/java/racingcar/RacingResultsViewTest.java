package racingcar;

import static org.junit.jupiter.api.Assertions.*;
import static racingcar.StringConstants.*;
import static racingcar.StringConstants.MARK_FOR_DISTANCE_RECORD;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RacingResultsViewTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final String car1Name = "car1";
    private final String car2Name = "car2";
    private final List<Car> cars = new ArrayList<>();

    private RacingResultsView racingResultsView;

    RacingResultsViewTest () {
        createCars();
    }

    void createCars() {
        cars.add(Car.create(car1Name));
        cars.add(Car.create(car2Name));
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        racingResultsView = new RacingResultsView(cars);
    }

    @Test
    void 전달된_차_리스트와_같은_순서로_board_생성() {
        racingResultsView.print();
        Assertions.assertThat(outputStreamCaptor.toString().trim()).isEqualTo(createExceptedInitialResult().toString());
    }

    private StringBuilder createExceptedInitialResult() {
        String ELIMINATED_TRAILING_SPACES_MARK_FOR_DISTANCE_RECORD = " :";
        StringBuilder expectedResult = new StringBuilder();
        expectedResult.append(car1Name);
        expectedResult.append(DELIMITER_BETWEEN_NAME_AND_DISTANCE_RECORD);
        expectedResult.append(NEW_LINE);
        expectedResult.append(car2Name);
        expectedResult.append(ELIMINATED_TRAILING_SPACES_MARK_FOR_DISTANCE_RECORD);
        return expectedResult;
    }

    @Test
    void 주행한_만큼_대시_더해서_출력() {
        List<Car> carsAfterRacing = new ArrayList<>();
        for (Car car : cars) {
            car.run(MIN_NUMBER_TO_CAR_TO_GO);
            carsAfterRacing.add(car);
        }
        racingResultsView.print();
        Assertions.assertThat(outputStreamCaptor.toString().trim()).isEqualTo(createExceptedAfterDrivingResult().toString());
    }

    private StringBuilder createExceptedAfterDrivingResult() {
        StringBuilder expectedResult = new StringBuilder();
        expectedResult.append(car1Name);
        expectedResult.append(DELIMITER_BETWEEN_NAME_AND_DISTANCE_RECORD);
        expectedResult.append(MARK_FOR_DISTANCE_RECORD);
        expectedResult.append(NEW_LINE);
        expectedResult.append(car2Name);
        expectedResult.append(DELIMITER_BETWEEN_NAME_AND_DISTANCE_RECORD);
        expectedResult.append(MARK_FOR_DISTANCE_RECORD);
        return expectedResult;
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }
}
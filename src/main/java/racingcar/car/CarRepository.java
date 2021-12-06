package racingcar.car;

import static racingcar.utils.StringConstants.INITIAL_POSITION;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private final List<Car> cars = new ArrayList<>();

    public List<Car> saveInOrder(List<Car> cars) {
        this.cars.addAll(cars);
        return cars;
    }

    public List<Car> findAllInOrder() {
        return new ArrayList<>(cars);
    }

    public List<Car> findTopByOrderByPosition() {
        int maxPosition = findMaxPosition();
        return findByPosition(maxPosition);
    }

    private int findMaxPosition() {
        int maxPosition = INITIAL_POSITION;
        for (Car car : cars) {
            int position = car.getPosition();
            if(car.getPosition() > maxPosition) {
                maxPosition = position;
                continue;
            }
        }
        return maxPosition;
    }
    
    private List<Car> findByPosition(int distance) {
        List<Car> carsWithCertainDistance = new ArrayList<>();
        for (Car car : cars) {
            if(car.getPosition() == distance) {
                carsWithCertainDistance.add(car);
            }
        }
        return carsWithCertainDistance;
    }
}

package racingcar.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.Car;

public class CarRepository {

	private static final String EMPTY_CAR_ERROR_MESSAGE = "등록된 자동차가 없습니다.";

	private List<Car> cars = new ArrayList<>();

	public void add(List<Car> cars) {
		this.cars.addAll(cars);
	}

	public List<Car> findCars() {
		return cars;
	}

	public void updateCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<Car> findWinnerCars() {
		Car maxPositionCar = cars.stream()
			.max(Car::compareTo)
			.orElseThrow(() -> new IllegalStateException(EMPTY_CAR_ERROR_MESSAGE));

		return cars.stream()
			.filter(car -> car.isSamePosition(maxPositionCar))
			.collect(Collectors.toList());
	}
}

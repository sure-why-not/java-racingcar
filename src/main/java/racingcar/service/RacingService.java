package racingcar.service;

import java.util.List;

import racingcar.domain.Car;
import racingcar.repository.CarRepository;
import racingcar.util.RandomUtil;

public class RacingService {
	private static final CarRepository carRepository = new CarRepository();

	public void registerCars(List<Car> carList) {
		carList.forEach(carRepository::addCar);
	}

	public void race(int attemptNumber, RandomUtil randomUtil) {
		List<Car> cars = carRepository.findCars();

		for (int i = 0; i < attemptNumber; i++) {
			cars.forEach(car -> car.move(randomUtil.generate(10)));
		}
	}
}
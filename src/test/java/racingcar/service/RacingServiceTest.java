package racingcar.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.domain.CarDto;

public class RacingServiceTest {

	private static final String POBI = "pobi";
	private static final String JOON = "joon";
	private static final String LALA = "lala";

	private final RacingService racingService = new RacingService();

	@BeforeEach
	public void beforeEach() {
		racingService.registerCars(List.of(CarDto.of(POBI, 0), CarDto.of(JOON, 0)));
	}

	@Test
	@DisplayName("우승자 한명 계산")
	public void findOneWinnerTest() {
		racingService.registerCars(List.of(CarDto.of(LALA, 1000)));
		List<CarDto> winnerCars = racingService.findWinnerCars();
		assertThat(winnerCars.size()).isEqualTo(1);

		List<String> winnerNames = winnerCars.stream()
			.map(CarDto::getName)
			.collect(Collectors.toList());

		assertThat(winnerNames).containsSequence(LALA);
	}

	@Test
	@DisplayName("우승자 여러명 계산")
	public void findSomeWinnerTest() {
		List<CarDto> winnerCars = racingService.findWinnerCars();
		assertThat(winnerCars.size()).isEqualTo(2);

		List<String> winnerNames = winnerCars.stream()
			.map(CarDto::getName)
			.collect(Collectors.toList());

		assertThat(winnerNames).containsSequence(POBI);
		assertThat(winnerNames).containsSequence(JOON);
	}
}
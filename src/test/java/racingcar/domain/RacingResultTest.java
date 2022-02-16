package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RacingResultTest {

	@Test
	@DisplayName("게임 결과 조회")
	public void findResultTest() {
		RacingResult racingResult = new RacingResult();
		racingResult.add(List.of(Car.from("라라"), Car.of("포비", 1), Car.from("더즈")));
		racingResult.add(List.of(Car.of("라라", 1), Car.of("포비", 2), Car.of("더즈", 1)));

		List<CarDto> resultCars = racingResult.findResult(Round.of(2)).stream()
			.map(Car::toDto)
			.collect(Collectors.toList());

		Assertions.assertThat(resultCars.get(1).getName()).isEqualTo("포비");
		Assertions.assertThat(resultCars.get(1).getPosition()).isEqualTo(2);
	}
}

package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.AttemptNumber;
import racingcar.domain.CarDto;
import racingcar.domain.Round;
import racingcar.service.RacingService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

	private final RacingService racingService = new RacingService();

	public void start() {
		racingService.registerCars(InputView.getCars());

		AttemptNumber attemptNumber = inputAttemptNumber();
		OutputView.printResultMessage();
		play(attemptNumber);

		printRacingRoundResult(attemptNumber);
		printRacingWinnerResult();
	}

	private AttemptNumber inputAttemptNumber() {
		String attemptNumberInput = InputView.getAttemptNumber();
		return AttemptNumber.fromStringValue(attemptNumberInput);
	}

	private void play(AttemptNumber attemptNumber) {
		racingService.race(attemptNumber);
	}

	private void printRacingRoundResult(AttemptNumber attemptNumber) {
		for (int round = 1; round <= attemptNumber.value(); round++) {
			List<CarDto> racingResult = racingService.findRacingResult(Round.of(round));
			OutputView.printRacingInfo(racingResult);
		}
	}

	private void printRacingWinnerResult() {
		List<String> winnerNames = racingService.findWinnerCars().stream()
			.map(CarDto::getName)
			.collect(Collectors.toList());

		OutputView.printWinnersMessage(winnerNames);
	}
}

package bridge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.util.Lists.newArrayList;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.MoveResult;
import bridge.domain.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("BridgeGameService 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BridgeGameServiceTest {

    private final BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
    private final BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
    private BridgeGameService bridgeGameService;

    @BeforeEach
    void setup() {
        bridgeGameService = new BridgeGameService(bridgeMaker);
    }

    @Test
    void initializeBridgeGame_메서드는_다리_길이를_입력받아_BridgeGame을_초기화시킨다() {
        int givenSize = 3;

        bridgeGameService.initializeBridgeGame(givenSize);

        assertThat(bridgeGameService.isPlayable()).isTrue();
    }

    @Test
    void play_메서드는_사용자와_방향을_입력받아_다리를_건너고_결과를_반환한다() {
        BridgeMaker bridgeMaker = new BridgeMaker(new TestBridgeNumberGenerator(newArrayList(0, 0, 1)));
        BridgeGameService bridgeGameService = new BridgeGameService(bridgeMaker);
        bridgeGameService.initializeBridgeGame(3);

        List<List<MoveResult>> result = bridgeGameService.play(new Player(), "U");

        assertThat(result.get(0)).isEqualTo(List.of(MoveResult.FAIL));
    }

    @Test
    void play_메서드는_다리가_준비되지_않은경우_IllegalStateException을_던진다() {
        assertThatThrownBy(() -> bridgeGameService.play(new Player(), "U"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 진행할 수 없습니다.");
    }

    @Test
    void isPlayable_메서드는_다리가_초기화되지_않은_경우_false를_반환한다() {
        boolean result = bridgeGameService.isPlayable();

        assertThat(result).isFalse();
    }

    static class TestBridgeNumberGenerator implements BridgeNumberGenerator {

        private final List<Integer> numbers;

        TestBridgeNumberGenerator(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public int generate() {
            return numbers.remove(0);
        }
    }
}

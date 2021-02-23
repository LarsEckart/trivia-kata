import com.adaptionsoft.games.uglytrivia.Console;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRollTest {

    @Test
    void name() {
        Game game = new GameWithOnePlayerAndSilentPrinter();

        game.roll(1);

        assertThat(game.places[0]).isEqualTo(1);
    }

    class GameWithOnePlayerAndSilentPrinter extends Game {
        public GameWithOnePlayerAndSilentPrinter() {
            super(new SilentConsole());
            add("Silver");
            places[0] = 0;
        }
    }

    class SilentConsole extends Console {
        @Override
        protected void print(String x) {

        }
    }

}

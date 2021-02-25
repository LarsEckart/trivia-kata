package com.adaptionsoft.games.trivia.runner;

import static org.assertj.core.api.Assertions.assertThat;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

public class RollDiceTest {

  @Test
  void first_player_of_new_game() {
    class OnePlayerGame extends Game{

      public OnePlayerGame() {
        super();
        add("irrelevant player name");
      }
    }
    Game game = new OnePlayerGame();
    // ASSUME the first player starts at 0

    game.roll(1);

    int indexOfFirstPlayer = 0;
    assertThat(game.places[indexOfFirstPlayer]).isEqualTo(1);
  }
}

package com.adaptionsoft.games.trivia.runner;

import static org.assertj.core.api.Assertions.assertThat;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

public class RollDiceTest {

  @Test
  void first_player_of_new_game() {
    int indexOfFirstPlayer = 0;
    class OnePlayerGameWithSilentGameReporter extends Game{

      public OnePlayerGameWithSilentGameReporter() {
        super();
        add("irrelevant player name");
        // Suppose the current player is at place 0
        super.places[0] = 0;
        // SUPPOSE the next player to move is the first player
        super.currentPlayer = indexOfFirstPlayer;
      }

      @Override
      protected void reportMessage(String message) {
        // intentionally shut up
      }
    }
    Game game = new OnePlayerGameWithSilentGameReporter();

    game.roll(1);

    assertThat(game.places[indexOfFirstPlayer]).isEqualTo(1);
  }
}

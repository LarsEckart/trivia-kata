package com.adaptionsoft.games.trivia.runner;

import static org.assertj.core.api.Assertions.assertThat;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

public class RollDiceTest {

  @Test
  void happy_path() {
    Game game = new Game();
    game.add("irrelevant player name");

    game.roll(1);

    assertThat(game.places[0]).isEqualTo(1);
  }
}

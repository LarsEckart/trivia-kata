package com.adaptionsoft.games.trivia.runner;

import static org.assertj.core.api.Assertions.assertThat;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

public class RollDiceForASingleTurnTest {

  @Test
  void first_player_moves_from_starting_place() {
    int indexOfFirstPlayer = 0;

    int indexOfStartingPlace = 0;
    class GameReadyToTakeASingleTurnWithSilentGameReporter extends Game {

      public GameReadyToTakeASingleTurnWithSilentGameReporter() {
        super();
        add("irrelevant player name");
        // Suppose the current player is at place 0
        super.places[0] = indexOfStartingPlace;
        // SUPPOSE the next player to move is the first player
        super.currentPlayer = indexOfFirstPlayer;
      }

      @Override
      protected void reportMessage(String message) {
        // intentionally shut up
      }
    }
    Game game = new GameReadyToTakeASingleTurnWithSilentGameReporter();

    game.roll(1);

    assertThat(game.places[indexOfFirstPlayer]).isEqualTo(1);
  }

  @Test
  void first_player_once_again_moves_from_starting_place() {
    int indexOfFirstPlayer = 0;

    int indexOfStartingPlace = 0;
    class GameReadyToTakeASingleTurnWithSilentGameReporter extends Game {

      public GameReadyToTakeASingleTurnWithSilentGameReporter() {
        super();
        add("irrelevant player name");
        // Suppose the current player is at place 0
        super.places[0] = indexOfStartingPlace;
        // SUPPOSE the next player to move is the first player
        super.currentPlayer = indexOfFirstPlayer;
      }

      @Override
      protected void reportMessage(String message) {
        // intentionally shut up
      }
    }
    Game game = new GameReadyToTakeASingleTurnWithSilentGameReporter();

    game.roll(8);

    assertThat(game.places[indexOfFirstPlayer]).isEqualTo(8);
  }

  @Test
  void first_player_moves_from_some_place_other_than_starting_place() {
    int indexOfFirstPlayer = 0;

    int indexOfStartingPlace = 4;
    class GameReadyToTakeASingleTurnWithSilentGameReporter extends Game {

      public GameReadyToTakeASingleTurnWithSilentGameReporter() {
        super();
        add("irrelevant player name");
        // Suppose the current player is at place 0
        super.places[0] = indexOfStartingPlace;
        // SUPPOSE the next player to move is the first player
        super.currentPlayer = indexOfFirstPlayer;
      }

      @Override
      protected void reportMessage(String message) {
        // intentionally shut up
      }
    }
    Game game = new GameReadyToTakeASingleTurnWithSilentGameReporter();

    game.roll(3);

    assertThat(game.places[indexOfFirstPlayer]).isEqualTo(7);
  }

  @Test
  void first_player_moves_around_the_end_of_the_board() {
    int indexOfFirstPlayer = 0;

    int indexOfStartingPlace = 11;
    class GameReadyToTakeASingleTurnWithSilentGameReporter extends Game {

      public GameReadyToTakeASingleTurnWithSilentGameReporter() {
        super();
        add("irrelevant player name");
        // Suppose the current player is at place 0
        super.places[0] = indexOfStartingPlace;
        // SUPPOSE the next player to move is the first player
        super.currentPlayer = indexOfFirstPlayer;
      }

      @Override
      protected void reportMessage(String message) {
        // intentionally shut up
      }
    }
    Game game = new GameReadyToTakeASingleTurnWithSilentGameReporter();

    game.roll(3);

    assertThat(game.places[indexOfFirstPlayer]).isEqualTo(2);
  }
}
package com.adaptionsoft.games.trivia.runner;

import static org.assertj.core.api.Assertions.assertThat;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.GameReporter;
import com.adaptionsoft.games.uglytrivia.Questions;
import org.junit.jupiter.api.Test;

public class AskQuestionTests {

  private String lastAskedQuestion;

  @Test
  void popQuestion() {
    Game game = new Game();

    Questions popQuestions = new Questions();
    popQuestions.add("::the next question in the Pop category::");
    game.askQuestion("Pop", new GameReporter() {
      @Override
      public void reportMessage(String message) {
        AskQuestionTests.this.lastAskedQuestion = message;
      }
    }, popQuestions, null, null, null);

    assertThat(lastAskedQuestion).isEqualTo("::the next question in the Pop category::");
  }

  @Test
  void scienceQuestion() {
    Game game = new Game();

    Questions scienceQuestions = new Questions();
    scienceQuestions.add("::the next question in the Science category::");
    game.askQuestion("Science", new GameReporter() {
      @Override
      public void reportMessage(String message) {
        AskQuestionTests.this.lastAskedQuestion = message;
      }
    }, null, scienceQuestions, null, null);

    assertThat(lastAskedQuestion).isEqualTo("::the next question in the Science category::");
  }

  @Test
  void sportsQuestion() {
    Game game = new Game();

    Questions sportsQuestions = new Questions();
    sportsQuestions.add("::the next question in the Sports category::");
    game.askQuestion("Sports", new GameReporter() {
      @Override
      public void reportMessage(String message) {
        AskQuestionTests.this.lastAskedQuestion = message;
      }
    }, null, null, sportsQuestions, null);

    assertThat(lastAskedQuestion).isEqualTo("::the next question in the Sports category::");
  }

  @Test
  void rockQuestion() {
    Game game = new Game();

    Questions rockQuestions = new Questions();
    rockQuestions.add("::the next question in the Rock category::");
    game.askQuestion("Rock", new GameReporter() {
      @Override
      public void reportMessage(String message) {
        AskQuestionTests.this.lastAskedQuestion = message;
      }
    }, null, null, null, rockQuestions);

    assertThat(lastAskedQuestion).isEqualTo("::the next question in the Rock category::");
  }
}

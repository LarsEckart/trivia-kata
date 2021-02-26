package com.adaptionsoft.games.trivia.runner;

import static org.assertj.core.api.Assertions.assertThat;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.GameReporter;
import com.adaptionsoft.games.uglytrivia.Questions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AskQuestionTests {

  private String lastAskedQuestion;
  private GameReporter spyGameReporter;

  @BeforeEach
  void setUp() {
    spyGameReporter = message -> AskQuestionTests.this.lastAskedQuestion = message;
  }

  @Test
  void popQuestion() {
    Questions popQuestions = new Questions();
    popQuestions.add("::the next question in the Pop category::");
    Game.askQuestion("Pop", spyGameReporter, popQuestions, null, null, null);

    assertThat(lastAskedQuestion).isEqualTo("::the next question in the Pop category::");
  }

  @Test
  void scienceQuestion() {
    Questions scienceQuestions = new Questions();
    scienceQuestions.add("::the next question in the Science category::");
    Game.askQuestion("Science", spyGameReporter, null, scienceQuestions, null, null);

    assertThat(lastAskedQuestion).isEqualTo("::the next question in the Science category::");
  }

  @Test
  void sportsQuestion() {
    Questions sportsQuestions = new Questions();
    sportsQuestions.add("::the next question in the Sports category::");
    Game.askQuestion("Sports", spyGameReporter, null, null, sportsQuestions, null);

    assertThat(lastAskedQuestion).isEqualTo("::the next question in the Sports category::");
  }

  @Test
  void rockQuestion() {
    Questions rockQuestions = new Questions();
    rockQuestions.add("::the next question in the Rock category::");
    Game.askQuestion("Rock", spyGameReporter, null, null, null, rockQuestions);

    assertThat(lastAskedQuestion).isEqualTo("::the next question in the Rock category::");
  }
}

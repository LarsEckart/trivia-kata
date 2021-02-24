package com.adaptionsoft.games.trivia.runner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.legacycode.Range;
import org.junit.jupiter.api.Test;

class GameRunnerTest {

  @Test
  void golden_master() {
    CombinationApprovals.verifyAllCombinations(this::playSeeded, Range.get(1, 100));
  }

  private String playSeeded(int seed) {
    final PrintStream systemOut = System.out;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      System.setOut(new PrintStream(baos, true, StandardCharsets.UTF_8));

      GameRunner.playGame(new Random(seed));

      return baos.toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      System.setOut(systemOut);
    }
  }
}

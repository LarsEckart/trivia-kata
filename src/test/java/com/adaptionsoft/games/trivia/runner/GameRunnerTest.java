package com.adaptionsoft.games.trivia.runner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.legacycode.Range;
import org.approvaltests.reporters.ClipboardReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

@UseReporter(ClipboardReporter.class)
class GameRunnerTest {

  @Test
  void golden_master() {
    CombinationApprovals.verifyAllCombinations(this::playGame, Range.get(1, 1000));
  }

  private String playGame(int seed) {
    String response;
    final PrintStream systemOut = System.out;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      System.setOut(new PrintStream(baos, true, StandardCharsets.UTF_8));

      GameRunner.predictable(new Random(seed));

      response = baos.toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      System.setOut(systemOut);
    }
    return response;
  }
}

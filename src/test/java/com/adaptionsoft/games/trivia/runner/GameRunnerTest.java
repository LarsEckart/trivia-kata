package com.adaptionsoft.games.trivia.runner;

import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.legacycode.Range;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

class GameRunnerTest {

    @Test
    void golden_master() {
        CombinationApprovals.verifyAllCombinations(this::playSeeded, Range.get(1, 100));
    }

    private String playSeeded(int seed) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(baos, true, StandardCharsets.UTF_8));

            GameRunner.playGame(new Random(seed));

            return baos.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

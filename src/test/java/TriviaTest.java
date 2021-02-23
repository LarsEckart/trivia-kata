import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.legacycode.Range;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TriviaTest {

    @Test
    void name() {
        CombinationApprovals.verifyAllCombinations(this::extracted, Range.get(1, 500));
    }

    private String extracted(int seed) {
        final PrintStream systemOut = System.out;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(baos, true, StandardCharsets.UTF_8));

            GameRunner.play(new Random(seed));

            return baos.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(systemOut);
        }
    }
}

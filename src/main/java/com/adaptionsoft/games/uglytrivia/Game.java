package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private final List<String> players = new ArrayList<>();
    public final int[] places = new int[6];
    private final int[] purses = new int[6];
    private final boolean[] inPenaltyBox = new boolean[6];

    private final Questions popQuestions = new Questions(new LinkedList<>());
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();
    private final Console console;

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game(Console console) {
        initializeQuestions();
        this.console = console;
    }

    private void initializeQuestions() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addQuestion(i);
            scienceQuestions.addLast(createQuestion(i, "Science Question"));
            sportsQuestions.addLast(createQuestion(i, "Sports Question"));
            rockQuestions.addLast(createQuestion(i, "Rock Question"));
        }
    }

    public String createQuestion(int index, String question) {
        return "%s %d".formatted(question, index);
    }

    public void add(String playerName) {
        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        console.print(playerName + " was added");
        console.print("They are player number " + players.size());
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        console.print(players.get(currentPlayer) + " is the current player");
        console.print("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            rollWhenInPenaltyBox(roll);
        } else {
            rollWhenOutsidePenaltyBox(roll);
        }
    }

    private void rollWhenInPenaltyBox(int roll) {
        if (isOddRoll(roll)) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
            rollWhenOutsidePenaltyBox(roll);
        } else {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
        }
    }

    private boolean isOddRoll(int roll) {
        return roll % 2 != 0;
    }

    private void rollWhenOutsidePenaltyBox(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) {
            places[currentPlayer] = places[currentPlayer] - 12;
        }

        console.print(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
        console.print("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        switch (currentCategory()) {
            case "Pop" -> System.out.println(popQuestions.getNextQuestion());
            case "Science" -> console.print(scienceQuestions.removeFirst());
            case "Sports" -> System.out.println(sportsQuestions.removeFirst());
            case "Rock" -> System.out.println(rockQuestions.removeFirst());
        }
    }

    private String currentCategory() {
        return switch (places[currentPlayer]) {
            case 0, 4, 8 -> "Pop";
            case 1, 5, 9 -> "Science";
            case 2, 6, 10 -> "Sports";
            default -> "Rock";
        };
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            return correctlyAnsweredWhenInPenaltyBox();
        } else {
            System.out.println("Answer was correct!!!!");
            return updatePlayerCoinsAndDecideIfGameWon();
        }
    }

    private boolean correctlyAnsweredWhenInPenaltyBox() {
        if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            return updatePlayerCoinsAndDecideIfGameWon();
        } else {
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;
            return true;
        }
    }

    private boolean updatePlayerCoinsAndDecideIfGameWon() {
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;

        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}

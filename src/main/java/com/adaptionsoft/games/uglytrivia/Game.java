package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {

    private final GameReporter gameReporter;
    ArrayList<String> players = new ArrayList<>();
    public int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    Questions popQuestions = new Questions(new LinkedList<>());
    Questions scienceQuestions = new Questions(new LinkedList<>());
    Questions sportsQuestions = new Questions(new LinkedList<>());
    Questions rockQuestions = new Questions(new LinkedList<>());

    protected int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        this(new PrintStreamGameReporter());
    }

    public Game(GameReporter gameReporter) {
        setupQuestions();
        this.gameReporter = gameReporter;
    }

    private void setupQuestions() {
        for (int i = 0; i < 50; i++) {
            popQuestions.add(createPopQuestion(i));
            scienceQuestions.add(createScienceQuestion(i));
            sportsQuestions.add(createSportsQuestion(i));
            rockQuestions.add(createRockQuestion(i));
        }
    }

    private String createPopQuestion(int index) {
        return createQuestion(index, "Pop");
    }

    private String createScienceQuestion(int index) {
        return createQuestion(index, "Science");
    }

    private String createSportsQuestion(int index) {
        return createQuestion(index, "Sports");
    }

    public String createRockQuestion(int index) {
        return createQuestion(index, "Rock");
    }

    private String createQuestion(int i, String category) {
        return category + " Question " + i;
    }

    public void add(String playerName) {
        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        gameReporter.reportMessage(playerName + " was added");
        gameReporter.reportMessage("They are player number " + players.size());
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        gameReporter.reportMessage(players.get(currentPlayer) + " is the current player");
        gameReporter.reportMessage("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");

                moveForward(roll);
                announceNewLocation();
                announceCategory();
                askQuestion();
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            moveForward(roll);
            announceNewLocation();
            announceCategory();
            askQuestion();
        }
    }

    private void announceCategory() {
        gameReporter.reportMessage("The category is " + currentCategory());
    }

    private void announceNewLocation() {
        gameReporter.reportMessage(players.get(currentPlayer)
                                   + "'s new location is "
                                   + places[currentPlayer]);
    }

    private void moveForward(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) {
            places[currentPlayer] = places[currentPlayer] - 12;
        }
    }

    private void askQuestion() {
        switch (currentCategory()) {
            case "Pop" -> gameReporter.reportMessage(popQuestions.nextQuestion());
            case "Science" -> gameReporter.reportMessage(scienceQuestions.nextQuestion());
            case "Sports" -> gameReporter.reportMessage(sportsQuestions.nextQuestion());
            case "Rock" -> gameReporter.reportMessage(rockQuestions.nextQuestion());
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
            if (isGettingOutOfPenaltyBox) {
                return applesauce();
            } else {
                updateCurrentPlayer();
                return true;
            }
        } else {
            return applesauce();
        }
    }

    private boolean applesauce() {
        announceCorrectAnswer();
        updatePlayerCoins();
        announcePlayerCoins();

        boolean winner = didPlayerWin();
        updateCurrentPlayer();

        return winner;
    }

    private void updateCurrentPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

    private void announcePlayerCoins() {
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");
    }

    private void updatePlayerCoins() {
        purses[currentPlayer]++;
    }

    private void announceCorrectAnswer() {
        System.out.println("Answer was correct!!!!");
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        updateCurrentPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}

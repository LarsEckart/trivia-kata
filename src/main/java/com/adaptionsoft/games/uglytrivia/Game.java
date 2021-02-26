package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Game {

  private final GameReporter gameReporter;
  ArrayList<String> players = new ArrayList<>();
  public int[] places = new int[6];
  int[] purses = new int[6];
  boolean[] inPenaltyBox = new boolean[6];

  Questions popQuestions = new Questions();
  Questions scienceQuestions = new Questions();
  Questions sportsQuestions = new Questions();
  Questions rockQuestions = new Questions();

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
    reportWhoIsCurrentPlayer();
    reportRollOfCurrentPlayer(roll);

    if (isCurrentPlayerInPenaltyBox()) {
      if (isOdd(roll)) {
        isGettingOutOfPenaltyBox = true;
        System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");

        updateLocationOfCurrentPlayer(roll);
        announceLocationOfCurrentPlayer();
        announceCategory();
        askQuestion(currentCategory(), gameReporter, popQuestions, scienceQuestions,
            sportsQuestions, rockQuestions);
      } else {
        System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }
    } else {
      updateLocationOfCurrentPlayer(roll);
      announceLocationOfCurrentPlayer();
      announceCategory();
      askQuestion(currentCategory(), gameReporter, popQuestions, scienceQuestions, sportsQuestions,
          rockQuestions);
    }
  }

  private boolean isOdd(int roll) {
    return roll % 2 != 0;
  }

  private boolean isCurrentPlayerInPenaltyBox() {
    return inPenaltyBox[currentPlayer];
  }

  private void reportRollOfCurrentPlayer(int roll) {
    gameReporter.reportMessage("They have rolled a " + roll);
  }

  private void reportWhoIsCurrentPlayer() {
    gameReporter.reportMessage(players.get(currentPlayer) + " is the current player");
  }

  private void announceCategory() {
    gameReporter.reportMessage("The category is " + currentCategory());
  }

  private void announceLocationOfCurrentPlayer() {
    gameReporter.reportMessage(players.get(currentPlayer)
                               + "'s new location is "
                               + places[currentPlayer]);
  }

  private void updateLocationOfCurrentPlayer(int roll) {
    places[currentPlayer] = places[currentPlayer] + roll;
    if (places[currentPlayer] > 11) {
      places[currentPlayer] = places[currentPlayer] - 12;
    }
  }

  public static void askQuestion(String category, GameReporter gameReporter, Questions popQuestions,
      Questions scienceQuestions, Questions sportsQuestions, Questions rockQuestions) {
    switch (category) {
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
    if (isCurrentPlayerInPenaltyBox()) {
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

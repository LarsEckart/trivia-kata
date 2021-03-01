package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {

  ArrayList players = new ArrayList();
  private Places places = new Places();
  private Purses purses = new Purses();
  private PenaltyBox penaltyBox = new PenaltyBox();

  LinkedList popQuestions = new LinkedList();
  LinkedList scienceQuestions = new LinkedList();
  LinkedList sportsQuestions = new LinkedList();
  LinkedList rockQuestions = new LinkedList();

  int currentPlayer = 0;
  boolean isGettingOutOfPenaltyBox;

  public Game() {
    for (int i = 0; i < 50; i++) {
      popQuestions.addLast("Pop Question " + i);
      scienceQuestions.addLast(("Science Question " + i));
      sportsQuestions.addLast(("Sports Question " + i));
      rockQuestions.addLast(createRockQuestion(i));
    }
  }

  public String createRockQuestion(int index) {
    return "Rock Question " + index;
  }

  public boolean isPlayable() {
    return (howManyPlayers() >= 2);
  }

  public boolean add(String playerName) {
    players.add(playerName);
    int playerNumber = howManyPlayers();
    int playerIndex = playerNumber - 1;
    places.movePlayerToStartingField(playerIndex);
    purses.setPlayerStartingCoins(playerIndex);
    penaltyBox.moveToPenaltyBox(playerIndex, false);

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.size());
    return true;
  }

  public int howManyPlayers() {
    return players.size();
  }

  public void roll(int roll) {
    System.out.println(players.get(currentPlayer) + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (penaltyBox.isInPenaltyBox(currentPlayer)) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
        places.moveForward(roll, currentPlayer);

        System.out.println(players.get(currentPlayer)
                           + "'s new location is "
                           + places.getPlace(currentPlayer));
        System.out.println("The category is " + currentCategory());
        askQuestion();
      } else {
        System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }

    } else {

      places.moveForward(roll, currentPlayer);

      System.out.println(players.get(currentPlayer)
                         + "'s new location is "
                         + places.getPlace(currentPlayer));
      System.out.println("The category is " + currentCategory());
      askQuestion();
    }

  }

  private void askQuestion() {
    if (currentCategory() == "Pop") {
      System.out.println(popQuestions.removeFirst());
    }
    if (currentCategory() == "Science") {
      System.out.println(scienceQuestions.removeFirst());
    }
    if (currentCategory() == "Sports") {
      System.out.println(sportsQuestions.removeFirst());
    }
    if (currentCategory() == "Rock") {
      System.out.println(rockQuestions.removeFirst());
    }
  }


  private String currentCategory() {
    if (places.getPlace(currentPlayer) == 0) {
      return "Pop";
    }
    if (places.getPlace(currentPlayer) == 4) {
      return "Pop";
    }
    if (places.getPlace(currentPlayer) == 8) {
      return "Pop";
    }
    if (places.getPlace(currentPlayer) == 1) {
      return "Science";
    }
    if (places.getPlace(currentPlayer) == 5) {
      return "Science";
    }
    if (places.getPlace(currentPlayer) == 9) {
      return "Science";
    }
    if (places.getPlace(currentPlayer) == 2) {
      return "Sports";
    }
    if (places.getPlace(currentPlayer) == 6) {
      return "Sports";
    }
    if (places.getPlace(currentPlayer) == 10) {
      return "Sports";
    }
    return "Rock";
  }

  public boolean wasCorrectlyAnswered() {
    if (penaltyBox.isInPenaltyBox(currentPlayer)) {
      if (isGettingOutOfPenaltyBox) {
        System.out.println("Answer was correct!!!!");
        purses.addCoin(currentPlayer);
        System.out.println(players.get(currentPlayer)
                           + " now has "
                           + purses.getPlayerCoinAmount(currentPlayer)
                           + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == players.size()) {
          currentPlayer = 0;
        }

        return winner;
      } else {
        currentPlayer++;
        if (currentPlayer == players.size()) {
          currentPlayer = 0;
        }
        return true;
      }


    } else {

      System.out.println("Answer was corrent!!!!");
      purses.addCoin(currentPlayer);
      System.out.println(players.get(currentPlayer)
                         + " now has "
                         + purses.getPlayerCoinAmount(currentPlayer)
                         + " Gold Coins.");

      boolean winner = didPlayerWin();
      currentPlayer++;
      if (currentPlayer == players.size()) {
        currentPlayer = 0;
      }

      return winner;
    }
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
    penaltyBox.moveToPenaltyBox(currentPlayer, true);

    currentPlayer++;
    if (currentPlayer == players.size()) {
      currentPlayer = 0;
    }
    return true;
  }


  private boolean didPlayerWin() {
    return !(purses.getPlayerCoinAmount(currentPlayer) == 6);
  }

}

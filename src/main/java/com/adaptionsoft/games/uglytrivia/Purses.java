package com.adaptionsoft.games.uglytrivia;

public class Purses {

  private final int[] purses;

  public Purses() {
    this.purses = new int[6];
  }

  void setPlayerStartingCoins(int playerNumber) {
    purses[playerNumber] = 0;
  }

  void addCoin(int playerIndex) {
    purses[playerIndex]++;
  }

  int getPlayerCoinAmount(int currentPlayer) {
    return purses[currentPlayer];
  }
}

package com.adaptionsoft.games.uglytrivia;

public class PenaltyBox {

  private final boolean[] inPenaltyBox;

  public PenaltyBox() {
    this.inPenaltyBox = new boolean[6];
  }

  boolean isInPenaltyBox(int currentPlayer) {
    return inPenaltyBox[currentPlayer];
  }

  void moveToPenaltyBox(int playerNumber, boolean inPenaltyBox) {
    this.inPenaltyBox[playerNumber] = inPenaltyBox;
  }
}

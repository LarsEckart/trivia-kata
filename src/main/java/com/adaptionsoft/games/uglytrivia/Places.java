package com.adaptionsoft.games.uglytrivia;

class Places {

  private final int[] places;

  public Places() {
    this.places = new int[6];
  }

  void movePlayerToStartingField(int i) {
    places[i] = 0;
  }

  void moveForward(int roll, int currentPlayer) {
    places[currentPlayer] = places[currentPlayer] + roll;
      if (places[currentPlayer] > 11)
          places[currentPlayer] = places[currentPlayer] - 12;
  }

  int getPlace(int player) {
    return places[player];
  }
}

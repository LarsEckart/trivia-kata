package com.adaptionsoft.games.uglytrivia;

public class PrintStreamGameReporter implements GameReporter {

  public PrintStreamGameReporter() {
  }

  @Override
  public void reportMessage(String message) {
    System.out.println(message);
  }
}

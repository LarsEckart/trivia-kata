package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {
    private final LinkedList<String> question;

    public Questions() {
        this.question = new LinkedList<>();
    }

    void add(String popQuestion) {
        question.addLast(popQuestion);
    }

    String nextQuestion() {
        return question.removeFirst();
    }

}

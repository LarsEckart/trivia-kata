package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {
    private final LinkedList<String> question;

    public Questions(LinkedList<String> question) {
        this.question = question;
    }

    void addQuestion(String popQuestion) {
        question.addLast(popQuestion);
    }

    String nextQuestion() {
        return question.removeFirst();
    }

}

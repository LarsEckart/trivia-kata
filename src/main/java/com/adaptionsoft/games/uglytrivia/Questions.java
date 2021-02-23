package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;
import java.util.List;

public class Questions {
    private final List<String> popQuestions;

    public Questions(List<String> popQuestions) {
        this.popQuestions = popQuestions;
    }

    String getNextQuestion() {
        String s = popQuestions.get(0);
        popQuestions.remove(s);
        return s;
    }

    void addQuestion(int i) {
        popQuestions.add("%s %d".formatted("Pop Question", i));
    }

}

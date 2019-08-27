package com.snn.bildim;

import java.io.Serializable;

class Card implements Serializable {

    private int value;
    private String title;
    private String answer;
    private String question;

    Card(String title, String answer, String question) {
        this.value = 0;
        this.title = title;
        this.answer = answer;
        this.question = question;
    }

    int getValue() {
        return value;
    }

    void setValue(int isKnown) {
        this.value = isKnown;
    }

    String getTitle() {
        return title;
    }

    String getQuestion() {
        return question;
    }

    String getAnswer() {
        return answer;
    }
}

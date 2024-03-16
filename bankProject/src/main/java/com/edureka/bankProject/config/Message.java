package com.edureka.bankProject.config;

public enum Message {
    APPROVED("Approve"),
    REJECTED("Reject"),
    NOTFOUND("Not Found"),

    INCORRECTCNO("Incorrect Card Number");

    private final String result;

    Message(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}

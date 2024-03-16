package com.edureka.bankProject.config;

public enum TransactionType {

    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("Withdraw"),
    CHEQUE("Cheque");
    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

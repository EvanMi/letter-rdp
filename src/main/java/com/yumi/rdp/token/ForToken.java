package com.yumi.rdp.token;

public class ForToken extends IterationToken {
    public static final ForToken INSTANCE = new ForToken("for");

    public ForToken(String value) {
        super(value);
    }
}

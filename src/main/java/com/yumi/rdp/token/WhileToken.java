package com.yumi.rdp.token;

public class WhileToken extends IterationToken {
    public static final WhileToken INSTANCE = new WhileToken("while");

    public WhileToken(String value) {
        super(value);
    }
}

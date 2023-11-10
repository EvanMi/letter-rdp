package com.yumi.rdp.token;

public class DoToken extends IterationToken {
    public static final DoToken INSTANCE = new DoToken("do");

    public DoToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

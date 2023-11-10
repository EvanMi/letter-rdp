package com.yumi.rdp.token;

public class ClosedBraceToken extends Token<String> {
    public static final ClosedBraceToken INSTANCE = new ClosedBraceToken("}");
    public ClosedBraceToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

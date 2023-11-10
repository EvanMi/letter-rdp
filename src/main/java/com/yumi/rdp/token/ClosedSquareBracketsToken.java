package com.yumi.rdp.token;

public class ClosedSquareBracketsToken extends Token<String> {
    public static final ClosedSquareBracketsToken INSTANCE = new ClosedSquareBracketsToken("]");
    public ClosedSquareBracketsToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

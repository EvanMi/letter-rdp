package com.yumi.rdp.token;

public class ClosedParenthesisToken extends Token<String> {
    public static final ClosedParenthesisToken INSTANCE = new ClosedParenthesisToken(")");
    public ClosedParenthesisToken(String value) {
        super(value);
    }
}

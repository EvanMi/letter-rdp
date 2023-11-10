package com.yumi.rdp.token;

public class OpenParenthesisToken extends Token<String> {
    public static final OpenParenthesisToken INSTANCE = new OpenParenthesisToken("(");
    public OpenParenthesisToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

package com.yumi.rdp.token;

public class ElseToken extends Token<String> {
    public static final ElseToken INSTANCE = new ElseToken("else");
    public ElseToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

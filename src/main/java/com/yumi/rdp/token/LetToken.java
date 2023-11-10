package com.yumi.rdp.token;

public class LetToken extends Token<String> {
    public static final LetToken INSTANCE = new LetToken("let");
    public LetToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

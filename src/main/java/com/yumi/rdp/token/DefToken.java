package com.yumi.rdp.token;

public class DefToken extends Token<String> {
    public static final DefToken INSTANCE = new DefToken("def");

    public DefToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

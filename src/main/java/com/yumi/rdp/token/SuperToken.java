    package com.yumi.rdp.token;

public class SuperToken extends Token<String> {
    public static final SuperToken INSTANCE = new SuperToken("super");

    public SuperToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

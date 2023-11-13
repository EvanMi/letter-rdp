package com.yumi.rdp.token;

public class ExtendsToken extends Token<String> {
    public static final ExtendsToken INSTANCE = new ExtendsToken("extends");

    public ExtendsToken(String value) {
        super(value);
    }
}

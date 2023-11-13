package com.yumi.rdp.token;

public class CommaToken extends Token<String> {
    public static final CommaToken INSTANCE = new CommaToken(",");
    public CommaToken(String value) {
        super(value);
    }
}

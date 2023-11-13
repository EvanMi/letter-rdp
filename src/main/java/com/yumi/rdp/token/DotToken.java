package com.yumi.rdp.token;

public class DotToken extends Token<String> {
    public static final DotToken INSTANCE = new DotToken(".");
    public DotToken(String value) {
        super(value);
    }
}

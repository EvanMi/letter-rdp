package com.yumi.rdp.token;

public class SemicolonToken extends Token<String> {
    public static final SemicolonToken INSTANCE = new SemicolonToken(";");

    public SemicolonToken(String value) {
        super(value);
    }
}

    package com.yumi.rdp.token;

public class NewToken extends Token<String> {
    public static final NewToken INSTANCE = new NewToken("new");

    public NewToken(String value) {
        super(value);
    }
}

package com.yumi.rdp.token;

public class ReturnToken extends Token<String> {
    public static final ReturnToken INSTANCE = new ReturnToken("return");

    public ReturnToken(String value) {
        super(value);
    }
}

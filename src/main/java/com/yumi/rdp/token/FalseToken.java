package com.yumi.rdp.token;

public class FalseToken extends BooleanLiteralToken {
    public static final FalseToken INSTANCE = new FalseToken(false);
    public FalseToken(Boolean value) {
        super(value);
    }
}

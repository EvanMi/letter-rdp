package com.yumi.rdp.token;

public class NullToken extends LiteralToken<String> {
    public static final NullToken INSTANCE = new NullToken("null");

    public NullToken(String value) {
        super(value);
    }
}

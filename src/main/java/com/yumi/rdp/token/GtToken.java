package com.yumi.rdp.token;

public class GtToken extends RelationalOperatorToken{
    public static final GtToken INSTANCE = new GtToken(">");
    public GtToken(String value) {
        super(value);
    }
}

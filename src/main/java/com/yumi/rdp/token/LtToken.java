package com.yumi.rdp.token;

public class LtToken extends RelationalOperatorToken{
    public static final LtToken INSTANCE = new LtToken("<");
    public LtToken(String value) {
        super(value);
    }
}

package com.yumi.rdp.token;

public class GeToken extends RelationalOperatorToken{
    public static final GeToken INSTANCE = new GeToken(">=");
    public GeToken(String value) {
        super(value);
    }
}

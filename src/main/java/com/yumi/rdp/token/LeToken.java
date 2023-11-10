package com.yumi.rdp.token;

public class LeToken extends RelationalOperatorToken{
    public static final LeToken INSTANCE = new LeToken("<=");
    public LeToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

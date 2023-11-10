package com.yumi.rdp.token;

public class EqualityToken extends EqualityOperatorToken{
    public static final EqualityToken INSTANCE = new EqualityToken("==");
    public EqualityToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

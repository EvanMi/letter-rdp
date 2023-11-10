package com.yumi.rdp.token;

public class NonEqualityToken extends EqualityOperatorToken{
    public static final NonEqualityToken INSTANCE = new NonEqualityToken("!=");
    public NonEqualityToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

package com.yumi.rdp.token;

public class MultiplicationToken extends AdditiveOperatorToken{

    public static final MultiplicationToken INSTANCE = new MultiplicationToken("*");
    public MultiplicationToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }

}

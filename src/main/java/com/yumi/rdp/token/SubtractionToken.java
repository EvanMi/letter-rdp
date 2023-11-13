package com.yumi.rdp.token;

public class SubtractionToken extends AdditiveOperatorToken{

    public static final SubtractionToken INSTANCE = new SubtractionToken("-");
    public SubtractionToken(String value) {
        super(value);
    }

}

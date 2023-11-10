package com.yumi.rdp.token;

public class DivisionToken extends AdditiveOperatorToken{

    public static final DivisionToken INSTANCE = new DivisionToken("/");
    public DivisionToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }

}

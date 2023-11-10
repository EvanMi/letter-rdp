package com.yumi.rdp.token;

public class AdditionToken extends AdditiveOperatorToken{

    public static final AdditionToken INSTANCE = new AdditionToken("+");
    public AdditionToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }

}

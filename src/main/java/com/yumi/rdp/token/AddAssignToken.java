package com.yumi.rdp.token;

public class AddAssignToken extends ComplexAssignToken {
    public static final AddAssignToken INSTANCE = new AddAssignToken("+=");

    public AddAssignToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

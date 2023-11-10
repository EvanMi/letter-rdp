package com.yumi.rdp.token;

public class DivAssignToken extends ComplexAssignToken {
    public static final DivAssignToken INSTANCE = new DivAssignToken("/=");

    public DivAssignToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

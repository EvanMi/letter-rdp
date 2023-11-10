package com.yumi.rdp.token;

public class MulAssignToken extends ComplexAssignToken {
    public static final MulAssignToken INSTANCE = new MulAssignToken("*=");

    public MulAssignToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

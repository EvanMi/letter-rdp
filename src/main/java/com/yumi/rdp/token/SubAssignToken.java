package com.yumi.rdp.token;

public class SubAssignToken extends ComplexAssignToken {
    public static final SubAssignToken INSTANCE = new SubAssignToken("-=");

    public SubAssignToken(String value) {
        super(value);
    }
}

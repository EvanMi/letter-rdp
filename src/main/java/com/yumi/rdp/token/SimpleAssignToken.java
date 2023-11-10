package com.yumi.rdp.token;

public class SimpleAssignToken extends AssignToken {
    public static final SimpleAssignToken INSTANCE = new SimpleAssignToken("=");

    public SimpleAssignToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

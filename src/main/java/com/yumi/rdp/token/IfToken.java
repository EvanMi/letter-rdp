package com.yumi.rdp.token;

public class IfToken extends Token<String> {
    public static final IfToken INSTANCE = new IfToken("if");
    public IfToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

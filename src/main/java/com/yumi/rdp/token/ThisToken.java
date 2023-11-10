    package com.yumi.rdp.token;

public class ThisToken extends Token<String> {
    public static final ThisToken INSTANCE = new ThisToken("this");

    public ThisToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

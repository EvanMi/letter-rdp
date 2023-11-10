package com.yumi.rdp.token;

public class OpenBraceToken extends Token<String> {
    public static final OpenBraceToken INSTANCE = new OpenBraceToken("{");
    public OpenBraceToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

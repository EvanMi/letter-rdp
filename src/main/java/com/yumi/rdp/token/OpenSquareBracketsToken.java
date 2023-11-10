package com.yumi.rdp.token;

public class OpenSquareBracketsToken extends Token<String> {
    public static final OpenSquareBracketsToken INSTANCE = new OpenSquareBracketsToken("[");
    public OpenSquareBracketsToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

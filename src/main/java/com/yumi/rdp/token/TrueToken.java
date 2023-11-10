package com.yumi.rdp.token;

public class TrueToken extends BooleanLiteralToken {
    public static final TrueToken INSTANCE = new TrueToken(true);
    public TrueToken(Boolean value) {
        super(value);
    }

    @Override
    public Boolean getValue() {
        return INSTANCE.getValue();
    }
}

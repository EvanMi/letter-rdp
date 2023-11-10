package com.yumi.rdp.token;

public class LogicalNotToken extends OperatorToken{
    public static final LogicalNotToken INSTANCE = new LogicalNotToken("!");
    public LogicalNotToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

package com.yumi.rdp.token;

public class LogicalAndToken extends OperatorToken{
    public static final LogicalAndToken INSTANCE = new LogicalAndToken("&&");
    public LogicalAndToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

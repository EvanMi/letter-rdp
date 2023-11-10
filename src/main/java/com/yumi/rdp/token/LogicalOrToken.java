package com.yumi.rdp.token;

public class LogicalOrToken extends OperatorToken{
    public static final LogicalOrToken INSTANCE = new LogicalOrToken("||");
    public LogicalOrToken(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return INSTANCE.getValue();
    }
}

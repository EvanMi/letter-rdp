package com.yumi.rdp.token;

public abstract class LiteralToken <T> extends Token<T>{
    public LiteralToken(T value) {
        super(value);
    }
}

package com.yumi.rdp.token;

public abstract class Token <T> {
    private T value;

    public Token(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

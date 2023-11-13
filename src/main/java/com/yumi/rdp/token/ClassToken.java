package com.yumi.rdp.token;

public class ClassToken extends Token<String> {
    public static final ClassToken INSTANCE = new ClassToken("class");

    public ClassToken(String value) {
        super(value);
    }
}

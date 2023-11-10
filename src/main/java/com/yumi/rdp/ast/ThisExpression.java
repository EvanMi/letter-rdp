package com.yumi.rdp.ast;

public class ThisExpression implements AstNode {

    public static final ThisExpression INSTANCE = new ThisExpression();

    private ThisExpression() {
    }
}
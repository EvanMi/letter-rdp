package com.yumi.rdp.ast;

public record MemberExpression(
        // 属性是否需要计算
        boolean computed,
        AstNode object,
        AstNode property
) implements AstNode {
}

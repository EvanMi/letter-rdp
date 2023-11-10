package com.yumi.rdp.ast;

import com.yumi.rdp.token.OperatorToken;

public record BinaryExpression(
        OperatorToken operator,
        AstNode left,
        AstNode right
) implements AstNode {
}

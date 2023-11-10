package com.yumi.rdp.ast;

import com.yumi.rdp.token.OperatorToken;

public record LogicalExpression(
        OperatorToken operator,
        AstNode left,
        AstNode right
) implements AstNode {
}

package com.yumi.rdp.ast;

import com.yumi.rdp.token.OperatorToken;

public record UnaryExpression(
        OperatorToken operator,
        AstNode argument
) implements AstNode {
}

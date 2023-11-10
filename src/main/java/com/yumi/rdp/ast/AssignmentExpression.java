package com.yumi.rdp.ast;

import com.yumi.rdp.token.AssignToken;

public record AssignmentExpression(
        AssignToken operator,
        AstNode left,
        AstNode right
) implements AstNode {
}

package com.yumi.rdp.ast;

import java.util.List;

public record NewExpression(
        AstNode callee,
        List<AstNode> arguments
) implements AstNode {
}

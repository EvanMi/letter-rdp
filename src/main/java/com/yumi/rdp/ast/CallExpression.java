package com.yumi.rdp.ast;

import java.util.List;

public record CallExpression(
        AstNode callee,
        List<AstNode> arguments
) implements AstNode {
}

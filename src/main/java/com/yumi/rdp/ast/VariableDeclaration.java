package com.yumi.rdp.ast;

import com.yumi.rdp.ast.literal.Identifier;

public record VariableDeclaration(
        Identifier id,
        AstNode init
) implements AstNode {
}

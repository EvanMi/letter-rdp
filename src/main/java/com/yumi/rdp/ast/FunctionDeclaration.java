package com.yumi.rdp.ast;

import com.yumi.rdp.ast.statement.BlockStatement;

import java.util.List;

public record FunctionDeclaration(
        AstNode name,
        List<AstNode> params,
        BlockStatement body
) implements Statement {
}

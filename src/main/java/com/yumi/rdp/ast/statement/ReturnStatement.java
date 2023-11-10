package com.yumi.rdp.ast.statement;

import com.yumi.rdp.ast.AstNode;
import com.yumi.rdp.ast.Statement;

public record ReturnStatement(
        AstNode argument
) implements Statement {
}

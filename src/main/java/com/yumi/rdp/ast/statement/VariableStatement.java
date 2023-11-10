package com.yumi.rdp.ast.statement;

import com.yumi.rdp.ast.AstNode;
import com.yumi.rdp.ast.Statement;

import java.util.List;

public record VariableStatement(
        List<AstNode> declarations
) implements Statement {
}

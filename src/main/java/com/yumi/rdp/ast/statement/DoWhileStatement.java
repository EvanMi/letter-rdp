package com.yumi.rdp.ast.statement;

import com.yumi.rdp.ast.AstNode;
import com.yumi.rdp.ast.Statement;

public record DoWhileStatement(AstNode test, Statement body) implements Statement {
}

package com.yumi.rdp.ast.statement;

import com.yumi.rdp.ast.AstNode;
import com.yumi.rdp.ast.Statement;

import java.util.List;

public record BlockStatement(List<AstNode> body) implements Statement {
}

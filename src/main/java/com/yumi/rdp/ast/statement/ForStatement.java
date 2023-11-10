package com.yumi.rdp.ast.statement;

import com.yumi.rdp.ast.AstNode;
import com.yumi.rdp.ast.Statement;

public record ForStatement(
        AstNode init,
        AstNode test,
        AstNode update,
        Statement body
) implements Statement {
}

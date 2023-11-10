package com.yumi.rdp.ast.statement;

import com.yumi.rdp.ast.Statement;
import com.yumi.rdp.ast.literal.Identifier;

public record ClassDeclaration(Identifier id, Identifier superClass, BlockStatement body) implements Statement {
}
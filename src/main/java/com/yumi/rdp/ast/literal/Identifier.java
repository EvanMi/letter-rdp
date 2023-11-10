package com.yumi.rdp.ast.literal;

import com.yumi.rdp.ast.AstNode;

public record Identifier(String name) implements AstNode {
}

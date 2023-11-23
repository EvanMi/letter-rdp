package com.yumi.rdp.ast;

import java.util.List;

public record Program(List<Statement> body) implements AstNode {
}

package com.yumi.rdp.ast.literal;

import com.yumi.rdp.ast.Literal;

public record StringLiteral(String value) implements Literal {
}

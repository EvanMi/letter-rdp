package com.yumi.rdp.ast.literal;

import com.yumi.rdp.ast.Literal;

public record BooleanLiteral(Boolean value) implements Literal {
}

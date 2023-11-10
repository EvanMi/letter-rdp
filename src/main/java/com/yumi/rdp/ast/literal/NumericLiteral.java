package com.yumi.rdp.ast.literal;

import com.yumi.rdp.ast.Literal;

public record NumericLiteral(Integer value) implements Literal {
}

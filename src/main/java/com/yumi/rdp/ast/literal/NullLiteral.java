package com.yumi.rdp.ast.literal;

import com.yumi.rdp.ast.Literal;

public record NullLiteral(Boolean value) implements Literal {
}

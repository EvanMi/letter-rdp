package com.yumi.rdp;

import com.alibaba.fastjson2.JSON;
import com.yumi.rdp.ast.AstNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ParserTestSupport {
    private final Parser parser = new Parser();

    protected void parseAndAssertEquals(String program, String ast) {
        AstNode parse = parser.parse(program);
        assertEquals(JSON.parse(ast).toString(), JSON.toJSONString(parse));
    }
}

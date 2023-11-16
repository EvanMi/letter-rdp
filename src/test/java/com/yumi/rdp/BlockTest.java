package com.yumi.rdp;

import org.junit.jupiter.api.Test;

public class BlockTest extends ParserTestSupport {
    @Test
    public void testNormalBlock() {
        String ast = """
                {
                    "body":[
                        {
                            "body":[
                                {
                                    "expression":{
                                        "value":42,
                                        "type":"NumericLiteral"
                                    },
                                    "type":"ExpressionStatement"
                                },
                                {
                                    "expression":{
                                        "name":"hello",
                                        "type":"Identifier"
                                    },
                                    "type":"ExpressionStatement"
                                }
                            ],
                            "type":"BlockStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                {
                42;
                
                hello;
                }
                """;
        parseAndAssertEquals(program, ast);
    }

    @Test
    public void testEmptyBlock() {
        String ast = """
                {
                    "body":[
                        {
                            "body":[
                                
                            ],
                            "type":"BlockStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                {}
                """;
        parseAndAssertEquals(program, ast);
    }

    @Test
    public void testNestedBlock() {
        String ast = """
                {
                    "body":[
                        {
                            "body":[
                                {
                                    "expression":{
                                        "value":42,
                                        "type":"NumericLiteral"
                                    },
                                    "type":"ExpressionStatement"
                                },
                                {
                                    "body":[
                                        {
                                            "expression":{
                                                "name":"hello",
                                                "type":"Identifier"
                                            },
                                            "type":"ExpressionStatement"
                                        }
                                    ],
                                    "type":"BlockStatement"
                                }
                            ],
                            "type":"BlockStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                {
                42;
                {
                hello;
                }
                }
                """;
        parseAndAssertEquals(program, ast);
    }
}

package com.yumi.rdp;

import com.alibaba.fastjson2.JSON;
import com.yumi.rdp.ast.AstNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private final Parser parser = new Parser();

    private void parseAndAssertEquals(String program, String ast) {
        AstNode parse = parser.parse(program);
        assertEquals(JSON.toJSONString(parse), JSON.parse(ast).toString());
    }

    @Test
    public void testExpression() {
        String ast = """
                {
                    "body":[
                        {
                            "expression":{
                                "value":"hello",
                                "type":"StringLiteral"
                            },
                            "type":"ExpressionStatement"
                        },
                        {
                            "expression":{
                                "value":42,
                                "type":"NumericLiteral"
                            },
                            "type":"ExpressionStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                /**
                 * Documentation comment;
                 */
                 "hello";
                 
                 //Number
                 42;
                """;

        parseAndAssertEquals(program, ast);
    }

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


    @Test
    public void testEmptyStatement() {
        String ast = """
                {
                    "body":[
                        {
                            "type":"EmptyStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                ;
                """;
        parseAndAssertEquals(program, ast);
    }

}

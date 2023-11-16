package com.yumi.rdp;

import org.junit.jupiter.api.Test;

public class AdditiveTest extends ParserTestSupport{
    @Test
    public void testAdditive() {
        String ast = """
                {
                    "body":[
                        {
                            "expression":{
                                "left":{
                                    "value":2,
                                    "type":"NumericLiteral"
                                },
                                "operator":{
                                    "value":"+"
                                },
                                "right":{
                                    "value":2,
                                    "type":"NumericLiteral"
                                },
                                "type":"BinaryExpression"
                            },
                            "type":"ExpressionStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                2 + 2;
                """;
        parseAndAssertEquals(program, ast);
    }

    @Test
    public void testNestAdditive() {
        String ast = """
                {
                    "body":[
                        {
                            "expression":{
                                "left":{
                                    "left":{
                                        "value":2,
                                        "type":"NumericLiteral"
                                    },
                                    "operator":{
                                        "value":"+"
                                    },
                                    "right":{
                                        "value":2,
                                        "type":"NumericLiteral"
                                    },
                                    "type":"BinaryExpression"
                                },
                                "operator":{
                                    "value":"+"
                                },
                                "right":{
                                    "value":2,
                                    "type":"NumericLiteral"
                                },
                                "type":"BinaryExpression"
                            },
                            "type":"ExpressionStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                2 + 2 + 2;
                """;
        parseAndAssertEquals(program, ast);
    }


    @Test
    public void testNestAdditiveWithParenthesis() {
        String ast = """
                {
                    "body":[
                        {
                            "expression":{
                                "left":{
                                    "value":2,
                                    "type":"NumericLiteral"
                                },
                                "operator":{
                                    "value":"+"
                                },
                                "right":{
                                    "left":{
                                        "value":2,
                                        "type":"NumericLiteral"
                                    },
                                    "operator":{
                                        "value":"+"
                                    },
                                    "right":{
                                        "value":2,
                                        "type":"NumericLiteral"
                                    },
                                    "type":"BinaryExpression"
                                },
                                "type":"BinaryExpression"
                            },
                            "type":"ExpressionStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                2 + (2 + 2);
                """;
        parseAndAssertEquals(program, ast);
    }
}

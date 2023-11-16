package com.yumi.rdp;

import org.junit.jupiter.api.Test;

public class VariableTest extends ParserTestSupport{

    @Test
    public void testVariable() {
        String ast = """
                {
                    "body":[
                        {
                            "declarations":[
                                {
                                    "id":{
                                        "name":"x",
                                        "type":"Identifier"
                                    },
                                    "init":{
                                        "value":3,
                                        "type":"NumericLiteral"
                                    },
                                    "type":"VariableDeclaration"
                                }
                            ],
                            "type":"VariableStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                let x = 3;
                """;
        parseAndAssertEquals(program, ast);
    }


    @Test
    public void testMultiVariable() {
        String ast = """
                {
                    "body":[
                        {
                            "declarations":[
                                {
                                    "id":{
                                        "name":"x",
                                        "type":"Identifier"
                                    },
                                    "init":{
                                        "value":3,
                                        "type":"NumericLiteral"
                                    },
                                    "type":"VariableDeclaration"
                                },
                                {
                                    "id":{
                                        "name":"y",
                                        "type":"Identifier"
                                    },
                                    "init":{
                                        "value":3,
                                        "type":"NumericLiteral"
                                    },
                                    "type":"VariableDeclaration"
                                }
                            ],
                            "type":"VariableStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                let x = 3, y = 3;
                """;
        parseAndAssertEquals(program, ast);
    }

    @Test
    public void testNestedVariable() {
        String ast = """
                {
                    "body":[
                        {
                            "declarations":[
                                {
                                    "id":{
                                        "name":"x",
                                        "type":"Identifier"
                                    },
                                    "init":{
                                        "left":{
                                            "name":"y",
                                            "type":"Identifier"
                                        },
                                        "operator":{
                                            "value":"="
                                        },
                                        "right":{
                                            "value":3,
                                            "type":"NumericLiteral"
                                        },
                                        "type":"AssignmentExpression"
                                    },
                                    "type":"VariableDeclaration"
                                }
                            ],
                            "type":"VariableStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                let x = y = 3;
                """;
        parseAndAssertEquals(program, ast);
    }

    @Test
    public void testMultiVariableWithLastValue() {
        String ast = """
                {
                    "body":[
                        {
                            "declarations":[
                                {
                                    "id":{
                                        "name":"x",
                                        "type":"Identifier"
                                    },
                                    "type":"VariableDeclaration"
                                },
                                {
                                    "id":{
                                        "name":"y",
                                        "type":"Identifier"
                                    },
                                    "init":{
                                        "value":3,
                                        "type":"NumericLiteral"
                                    },
                                    "type":"VariableDeclaration"
                                }
                            ],
                            "type":"VariableStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                let x , y = 3;
                """;
        parseAndAssertEquals(program, ast);
    }
}

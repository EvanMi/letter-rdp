package com.yumi.rdp;

import org.junit.jupiter.api.Test;

public class IfTest extends ParserTestSupport {

    @Test
    public void testIf() {
        String ast = """
                {
                    "body":[
                        {
                            "consequent":{
                                "body":[
                                
                                ],
                                "type":"BlockStatement"
                            },
                            "test":{
                                "name":"x",
                                "type":"Identifier"
                            },
                            "type":"IfStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                if (x) {}
                """;
        parseAndAssertEquals(program, ast);
    }


    @Test
    public void testIfElse() {
        String ast = """
                {
                    "body":[
                        {
                            "alternate":{
                                "body":[
                                
                                ],
                                "type":"BlockStatement"
                            },
                            "consequent":{
                                "body":[
                                
                                ],
                                "type":"BlockStatement"
                            },
                            "test":{
                                "name":"x",
                                "type":"Identifier"
                            },
                            "type":"IfStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                if (x) {} else {}
                """;
        parseAndAssertEquals(program, ast);
    }

    @Test
    public void testIfIfElse() {
        String ast = """
                {
                    "body":[
                        {
                            "consequent":{
                                "body":[
                                
                                ],
                                "type":"BlockStatement"
                            },
                            "test":{
                                "name":"x",
                                "type":"Identifier"
                            },
                            "type":"IfStatement"
                        },
                        {
                            "alternate":{
                                "body":[
                                
                                ],
                                "type":"BlockStatement"
                            },
                            "consequent":{
                                "body":[
                                
                                ],
                                "type":"BlockStatement"
                            },
                            "test":{
                                "name":"y",
                                "type":"Identifier"
                            },
                            "type":"IfStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                if (x) {} if (y) {} else {}
                """;
        parseAndAssertEquals(program, ast);
    }

    @Test
    public void testIfNestIfElse() {
        String ast = """
                {
                    "body":[
                        {
                            "consequent":{
                                "alternate":{
                                    "body":[
                                
                                    ],
                                    "type":"BlockStatement"
                                },
                                "consequent":{
                                    "body":[
                                
                                    ],
                                    "type":"BlockStatement"
                                },
                                "test":{
                                    "name":"y",
                                    "type":"Identifier"
                                },
                                "type":"IfStatement"
                            },
                            "test":{
                                "name":"x",
                                "type":"Identifier"
                            },
                            "type":"IfStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                if (x) if (y) {} else {}
                """;
        parseAndAssertEquals(program, ast);
    }

    @Test
    public void testIfElseNestIfElse() {
        String ast = """
                {
                    "body":[
                        {
                            "alternate":{
                                "body":[
                                
                                ],
                                "type":"BlockStatement"
                            },
                            "consequent":{
                                "alternate":{
                                    "body":[
                                
                                    ],
                                    "type":"BlockStatement"
                                },
                                "consequent":{
                                    "body":[
                                
                                    ],
                                    "type":"BlockStatement"
                                },
                                "test":{
                                    "name":"y",
                                    "type":"Identifier"
                                },
                                "type":"IfStatement"
                            },
                            "test":{
                                "name":"x",
                                "type":"Identifier"
                            },
                            "type":"IfStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                if (x) if (y) {} else {} else {}
                """;
        parseAndAssertEquals(program, ast);
    }

    @Test
    public void testIfElseNestIfNestIfElse() {
        String ast = """
                {
                    "body":[
                        {
                            "alternate":{
                                "consequent":{
                                    "expression":{
                                        "left":{
                                            "name":"z",
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
                                    "type":"ExpressionStatement"
                                },
                                "test":{
                                    "name":"x",
                                    "type":"Identifier"
                                },
                                "type":"IfStatement"
                            },
                            "consequent":{
                                "alternate":{
                                    "body":[
                                
                                    ],
                                    "type":"BlockStatement"
                                },
                                "consequent":{
                                    "body":[
                                
                                    ],
                                    "type":"BlockStatement"
                                },
                                "test":{
                                    "name":"y",
                                    "type":"Identifier"
                                },
                                "type":"IfStatement"
                            },
                            "test":{
                                "name":"x",
                                "type":"Identifier"
                            },
                            "type":"IfStatement"
                        }
                    ],
                    "type":"Program"
                }
                """;
        String program = """
                if (x) if (y) {} else {} else if (x) z = 3;
                """;
        parseAndAssertEquals(program, ast);
    }
}

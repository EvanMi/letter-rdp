package com.yumi.rdp;

import org.junit.jupiter.api.Test;

public class ExpressionTest extends ParserTestSupport {
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
}

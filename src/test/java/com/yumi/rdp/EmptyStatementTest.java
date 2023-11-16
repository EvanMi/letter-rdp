package com.yumi.rdp;

import org.junit.jupiter.api.Test;

public class EmptyStatementTest extends ParserTestSupport {

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

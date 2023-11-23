package com.yumi.rdp;

import com.yumi.rdp.ast.AstNode;
import org.junit.jupiter.api.Test;

public class InterpreterTest extends ParserTestSupport{

    @Test
    public void testNumberSelfEvaluate() {
        AstNode ast = parser.parse("2;");
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(ast);
    }

    @Test
    public void testStringSelfEvaluate() {
        AstNode ast = parser.parse("""
                "hello world";
                """);
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(ast);
    }
}

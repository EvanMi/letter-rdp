package com.yumi.rdp;

import com.yumi.rdp.ast.AstNode;
import com.yumi.rdp.ast.Program;
import com.yumi.rdp.ast.Statement;
import com.yumi.rdp.ast.literal.NumericLiteral;
import com.yumi.rdp.ast.literal.StringLiteral;
import com.yumi.rdp.ast.statement.ExpressionStatement;

import java.util.List;

public class Interpreter {

    public void interpret(AstNode astNode) {
        if (astNode instanceof Program program) {
            List<Statement> statements = program.body();
            for (Statement statement : statements) {
                interpretStatement(statement);
            }
        }
    }

    private void interpretStatement(Statement statement) {
        switch (statement) {
            case ExpressionStatement expStatement -> processExpressionStatement(expStatement);
            default -> throw new IllegalStateException("未知的statement类型");
        }
    }

    private void processExpressionStatement(ExpressionStatement expStatement) {
        AstNode expression = expStatement.expression();
        switch (expression) {
            case NumericLiteral numericLiteral -> processNumericLiteral(numericLiteral);
            case StringLiteral stringLiteral -> processStringLiteral(stringLiteral);
            default -> throw new IllegalStateException("未知的表达式类型");
        }
    }

    private void processStringLiteral(StringLiteral stringLiteral) {
        System.out.println(stringLiteral.value());
    }

    private void processNumericLiteral(NumericLiteral numericLiteral) {
        System.out.println(numericLiteral.value());
    }
}

package com.yumi.rdp;

import com.yumi.rdp.ast.AssignmentExpression;
import com.yumi.rdp.ast.AstNode;
import com.yumi.rdp.ast.BinaryExpression;
import com.yumi.rdp.ast.CallExpression;
import com.yumi.rdp.ast.FunctionDeclaration;
import com.yumi.rdp.ast.Literal;
import com.yumi.rdp.ast.LogicalExpression;
import com.yumi.rdp.ast.MemberExpression;
import com.yumi.rdp.ast.NewExpression;
import com.yumi.rdp.ast.Program;
import com.yumi.rdp.ast.Statement;
import com.yumi.rdp.ast.Super;
import com.yumi.rdp.ast.ThisExpression;
import com.yumi.rdp.ast.UnaryExpression;
import com.yumi.rdp.ast.VariableDeclaration;
import com.yumi.rdp.ast.literal.BooleanLiteral;
import com.yumi.rdp.ast.literal.Identifier;
import com.yumi.rdp.ast.literal.NullLiteral;
import com.yumi.rdp.ast.literal.NumericLiteral;
import com.yumi.rdp.ast.literal.StringLiteral;
import com.yumi.rdp.ast.statement.BlockStatement;
import com.yumi.rdp.ast.statement.ClassDeclaration;
import com.yumi.rdp.ast.statement.DoWhileStatement;
import com.yumi.rdp.ast.statement.EmptyStatement;
import com.yumi.rdp.ast.statement.ExpressionStatement;
import com.yumi.rdp.ast.statement.ForStatement;
import com.yumi.rdp.ast.statement.IfStatement;
import com.yumi.rdp.ast.statement.ReturnStatement;
import com.yumi.rdp.ast.statement.VariableStatement;
import com.yumi.rdp.ast.statement.WhileStatement;
import com.yumi.rdp.token.AdditiveOperatorToken;
import com.yumi.rdp.token.AssignToken;
import com.yumi.rdp.token.BooleanLiteralToken;
import com.yumi.rdp.token.ClassToken;
import com.yumi.rdp.token.ClosedBraceToken;
import com.yumi.rdp.token.ClosedParenthesisToken;
import com.yumi.rdp.token.ClosedSquareBracketsToken;
import com.yumi.rdp.token.CommaToken;
import com.yumi.rdp.token.DefToken;
import com.yumi.rdp.token.DoToken;
import com.yumi.rdp.token.DotToken;
import com.yumi.rdp.token.ElseToken;
import com.yumi.rdp.token.EqualityOperatorToken;
import com.yumi.rdp.token.ExtendsToken;
import com.yumi.rdp.token.ForToken;
import com.yumi.rdp.token.IdentifierToken;
import com.yumi.rdp.token.IfToken;
import com.yumi.rdp.token.IterationToken;
import com.yumi.rdp.token.LetToken;
import com.yumi.rdp.token.LiteralToken;
import com.yumi.rdp.token.LogicalAndToken;
import com.yumi.rdp.token.LogicalNotToken;
import com.yumi.rdp.token.LogicalOrToken;
import com.yumi.rdp.token.MultiplicativeOperatorToken;
import com.yumi.rdp.token.NewToken;
import com.yumi.rdp.token.NullToken;
import com.yumi.rdp.token.NumberToken;
import com.yumi.rdp.token.OpenBraceToken;
import com.yumi.rdp.token.OpenParenthesisToken;
import com.yumi.rdp.token.OpenSquareBracketsToken;
import com.yumi.rdp.token.OperatorToken;
import com.yumi.rdp.token.RelationalOperatorToken;
import com.yumi.rdp.token.ReturnToken;
import com.yumi.rdp.token.SemicolonToken;
import com.yumi.rdp.token.SimpleAssignToken;
import com.yumi.rdp.token.StringToken;
import com.yumi.rdp.token.SuperToken;
import com.yumi.rdp.token.ThisToken;
import com.yumi.rdp.token.Token;
import com.yumi.rdp.token.WhileToken;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class Parser {
    private final Tokenizer tokenizer;
    private Token<?> lookAhead;

    public Parser() {
        this.tokenizer = new Tokenizer("");
    }

    public AstNode parse(String str) {
        this.tokenizer.setStr(str);
        this.lookAhead = tokenizer.getNextToken();
        return this.program();
    }

    /*
     * program
     *  : StatementList
     *  ;
     */
    public AstNode program() {
       return new Program(this.statementList());
    }

    /*
     * StatementList
     *  : Statement
     *  | StatementList Statement -> Statement Statement Statement Statement
     *  ;
     */
    private List<AstNode> statementList() {
        return this.statementList(null);
    }

    private List<AstNode> statementList(Class<? extends Token<?>> stopLookahead) {
        final List<AstNode> statementList = new ArrayList<>();
        statementList.add(this.statement());

        while (this.lookAhead != null && !this.lookaheadEq(stopLookahead)) {
            statementList.add(this.statement());
        }

        return statementList;
    }

    /*
        Statement
            : ExpressionStatement
            | BlockStatement
            | EmptyStatement
            | VariableStatement
            | IfStatement
            | IterationStatement
            | FunctionDeclaration
            | ReturnStatement
            | ClassDeclaration
            ;
     */
    private Statement statement() {
        if (this.lookaheadEq(SemicolonToken.class)) {
            return this.emptyStatement();
        } else if (this.lookaheadEq(OpenBraceToken.class)) {
            return this.blockStatement();
        } else if (this.lookaheadEq(LetToken.class)) {
            return this.variableStatement();
        } else if (this.lookaheadEq(IfToken.class)) {
            return this.ifStatement();
        } else if (this.lookaheadEq(IterationToken.class)) {
            return this.iterationStatement();
        } else if (this.lookaheadEq(DefToken.class)) {
            return this.functionDeclaration();
        } else if (this.lookaheadEq(ReturnToken.class)) {
            return this.returnStatement();
        } else if (this.lookaheadEq(ClassToken.class)) {
            return this.classDeclaration();
        } else {
            return this.expressionStatement();
        }
    }

    /*
        ClassDeclaration
            : 'class' Identifier OptClassExtends BlockStatement
            ;
     */
    private Statement classDeclaration() {
        this.eat(ClassToken.class);
        Identifier id = this.identifier();

        Identifier superClass = this.lookaheadEq(ExtendsToken.class) ? this.clasExtends() : null;
        BlockStatement body = this.blockStatement();

        return new ClassDeclaration(
                id,
                superClass,
                body
        );
    }

    /*
        ClasExtends
            : 'extends' Identifier
            ;
     */
    private Identifier clasExtends() {
        this.eat(ExtendsToken.class);
        return this.identifier();
    }

    /*
        FunctionDeclaration
            : 'def' Identifier '(' OptFormalParameterList ')' BlockStatement
            ;

     */
    private Statement functionDeclaration() {
        this.eat(DefToken.class);
        Identifier name = this.identifier();

        this.eat(OpenParenthesisToken.class);

        // OptFormalParameterList
        List<AstNode> params = this.lookaheadEq(ClosedParenthesisToken.class)
                ? List.of()
                : this.formalParameterList();

        this.eat(ClosedParenthesisToken.class);

        BlockStatement body = this.blockStatement();
        return new FunctionDeclaration(
                name,
                params,
                body
        );
    }

    /*
       FormalParameterList
           : Identifier
           | FormalParameterList ',' Identifier
           ;
    */
    private List<AstNode> formalParameterList() {
        List<AstNode> params = new ArrayList<>();

        params.add(this.identifier());
        while (this.lookaheadEq(CommaToken.class)) {
            this.eat(CommaToken.class);
            params.add(this.identifier());
        }

        return params;
    }

    /*
        ReturnStatement
            : 'return' OptExpression ';'
            ;
     */
    private Statement returnStatement() {
        this.eat(ReturnToken.class);
        AstNode argument = this.lookaheadEq(SemicolonToken.class) ? null : this.expression();

        this.eat(SemicolonToken.class);
        return new ReturnStatement(argument);
    }

    /*
       IterationStatement
           : WhileStatement
           | DoWhileStatement
           | ForStatement
           ;
    */
    private Statement iterationStatement() {
        if (this.lookaheadEq(WhileToken.class)) {
            return this.whileStatement();
        } else if (this.lookaheadEq(DoToken.class)) {
            return this.doWhileStatement();
        } else {
            return this.forStatement();
        }
    }

    /*
        ForStatement
            : 'for' '(' OptForStatementInit ';' OptExpression ';' OptExpression ')' Statement
            ;
     */
    private Statement forStatement() {
        this.eat(ForToken.class);
        this.eat(OpenParenthesisToken.class);

        AstNode init = this.lookaheadEq(SemicolonToken.class) ? null : this.forStatementInit();
        this.eat(SemicolonToken.class);

        AstNode test = this.lookaheadEq(SemicolonToken.class) ? null : this.expression();
        this.eat(SemicolonToken.class);

        AstNode update = this.lookaheadEq(ClosedParenthesisToken.class) ? null : this.expression();
        this.eat(ClosedParenthesisToken.class);

        Statement body = this.statement();

        return new ForStatement(
                init,
                test,
                update,
                body
        );
    }

    /*
       ForStatementInit
           : VariableStatementInit
           | Expression
           ;
    */
    private AstNode forStatementInit() {
        if (this.lookaheadEq(LetToken.class)) {
            return this.variableStatementInit();
        }
        return this.expression();
    }

    /*
        DoWhileStatement
            : 'do' Statement 'while' '(' Expression ')' ';'
            ;
     */
    private Statement doWhileStatement() {
        this.eat(DoToken.class);

        Statement body = this.statement();

        this.eat(WhileToken.class);

        this.eat(OpenParenthesisToken.class);
        AstNode test = this.expression();
        this.eat(ClosedParenthesisToken.class);

        this.eat(SemicolonToken.class);

        return new DoWhileStatement(test, body);
    }

    /*
        WhileStatement
            : 'while' '(' Expression ')' Statement
            ;
     */
    private Statement whileStatement() {
        this.eat(WhileToken.class);

        this.eat(OpenParenthesisToken.class);
        AstNode test = this.expression();
        this.eat(ClosedParenthesisToken.class);

        Statement body = this.statement();
        return new WhileStatement(test, body);
    }

    /*
       IfStatement
           : 'if' '(' Expression ')' Statement
           | 'if' '(' Expression ')' Statement 'else' Statement
           ;
    */
    private Statement ifStatement() {
        this.eat(IfToken.class);

        this.eat(OpenParenthesisToken.class);
        AstNode test = this.expression();
        this.eat(ClosedParenthesisToken.class);

        Statement consequent = this.statement();

        Statement alternate = null;
        if (this.lookAhead != null && this.lookaheadEq(ElseToken.class)) {
            this.eat(ElseToken.class);
            alternate = this.statement();
        }

        return new IfStatement(
                test,
                consequent,
                alternate
        );
    }

    /*
        VariableStatementInit
            : 'let' VariableDeclarationList
            ;
     */
    private VariableStatement variableStatementInit() {
        this.eat(LetToken.class);
        List<AstNode> list = this.variableDeclarationList();
        return new VariableStatement(list);
    }

    /*
        VariableStatement
            : VariableStatementInit ';'
            ;
     */
    private VariableStatement variableStatement() {
        VariableStatement variableStatement = this.variableStatementInit();
        this.eat(SemicolonToken.class);
        return variableStatement;
    }

    /*
       VariableDeclarationList
           : VariableDeclaration
           | VariableDeclarationList ',' VariableDeclaration
           ;
    */
    private List<AstNode> variableDeclarationList() {
        List<AstNode> list = new ArrayList<>();
        list.add(this.variableDeclaration());

        while (this.lookaheadEq(CommaToken.class)) {
            this.eat(CommaToken.class);
            list.add(this.variableDeclaration());
        }
        return list;
    }

    /*
        VariableDeclaration
            : Identifier OptVariableInitializer
            ;
     */
    private AstNode variableDeclaration() {
        Identifier id = this.identifier();

        AstNode init = this.lookaheadEq(SemicolonToken.class) || this.lookaheadEq(CommaToken.class)
                ? null
                : this.variableInitializer();

        return new VariableDeclaration(id, init);
    }

    /*
        VariableInitializer
            : SIMPLE_ASSIGN assignmentExpression
            ;
     */
    private AstNode variableInitializer() {
        this.eat(SimpleAssignToken.class);
        return this.assignmentExpression();
    }

    /*
        EmptyStatement
            : ';'
            ;
     */
    private EmptyStatement emptyStatement() {
        this.eat(SemicolonToken.class);
        return EmptyStatement.INSTANCE;
    }

    /*
        BlockStatement
            : '{' OptStatementList '}'
            ;
     */
    private BlockStatement blockStatement() {
        this.eat(OpenBraceToken.class);

        final List<AstNode> body = this.lookaheadEq(ClosedBraceToken.class)
                ? List.of()
                : this.statementList(ClosedBraceToken.class);

        this.eat(ClosedBraceToken.class);

        return new BlockStatement(body);
    }

    /*
        ExpressionStatement
            : Expression ';'
            ;
     */
    private ExpressionStatement expressionStatement() {
        AstNode expression = this.expression();
        this.eat(SemicolonToken.class);

        return new ExpressionStatement(expression);
    }


    /*
        Expression
            : AssignmentExpression
            ;
     */
    private AstNode expression() {
        return this.assignmentExpression();
    }

    /*
        AssignmentExpression
            : LogicalOrExpression
            | LeftHandSideExpression ASSIGNMENT_OPERATOR AssignmentExpression
            ;
     */
    private AstNode assignmentExpression() {
        AstNode left = this.logicalOrExpression();

        // 如果下一个Token不是赋值运算符直接返回
        if (!(this.lookAhead instanceof AssignToken)) {
            return left;
        }

        return new AssignmentExpression(
                this.eat(AssignToken.class),
                this.checkValidAssignmentTarget(left),
                this.assignmentExpression()
        );
    }

    /*
       Identifier
           : IDENTIFIER
           ;
    */
    private Identifier identifier() {
        IdentifierToken token = this.eat(IdentifierToken.class);
        return new Identifier(token.getValue());
    }

    /*
        Logical OR expression.
        x || y

        LogicalOrExpression
            : LogicalAndExpression
            | LogicalOrExpression LOGICAL_OR LogicalAndExpression
            ;
     */
    private AstNode logicalOrExpression() {
        return this.logicalExpression(this::logicalAndExpression, LogicalOrToken.class);
    }

    /*
       Logical AND expression.
       x && y

       LogicalAndExpression
           : EqualityExpression
           | LogicalAndExpression LOGICAL_AND EqualityExpression
           ;
    */
    private AstNode logicalAndExpression() {
        return this.logicalExpression(this::equalityExpression, LogicalAndToken.class);
    }

    /*
        EQUALITY_OPERATOR: ==, !=
        x == y
        x != y

        EqualityExpression
            : RelationalExpression
            | EqualityExpression EQUALITY_OPERATOR RelationalExpression
            ;
     */
    private AstNode equalityExpression() {
        return this.binaryExpression(this::relationalExpression, EqualityOperatorToken.class);
    }

    /*
        RELATIONAL_OPERATOR: >, >=, <, <=
        x > y
        x >= y
        x < y
        x <= y

        RelationalExpression
            : AdditiveExpression
            | RelationalExpression RELATIONAL_OPERATOR AdditiveExpression
            ;
     */
    private AstNode relationalExpression() {
        return this.binaryExpression(this::additiveExpression, RelationalOperatorToken.class);
    }

    /*
        AdditiveExpression
            : MultiplicativeExpression
            | AdditiveExpression ADDITIVE_OPERATOR MultiplicativeExpression
            ;
     */
    private AstNode additiveExpression() {
        return this.binaryExpression(this::multiplicativeExpression, AdditiveOperatorToken.class);
    }

    /*
        MultiplicativeExpression
            : UnaryExpression
            | MultiplicativeExpression MULTIPLICATIVE_OPERATOR UnaryExpression
            ;
     */
    private AstNode multiplicativeExpression() {
        return this.binaryExpression(this::unaryExpression, MultiplicativeOperatorToken.class);
    }

    /**
     * 构建二进制运算表达式
     *
     * @param builderMethod     构建数据
     * @param operatorTokenType 运算符类型
     */
    private AstNode binaryExpression(Supplier<AstNode> builderMethod, Class<? extends OperatorToken> operatorTokenType) {
        AstNode left = builderMethod.get();

        while (this.lookaheadEq(operatorTokenType)) {
            OperatorToken operator = this.eat(operatorTokenType);
            AstNode right = builderMethod.get();

            left = new BinaryExpression(
                    operator,
                    left,
                    right
            );
        }

        return left;
    }

    /**
     * 构建逻辑运算表达式
     *
     * @param builderMethod     构建数据
     * @param operatorTokenType 运算符类型
     */
    private AstNode logicalExpression(Supplier<AstNode> builderMethod, Class<? extends OperatorToken> operatorTokenType) {
        AstNode left = builderMethod.get();

        while (this.lookaheadEq(operatorTokenType)) {
            OperatorToken operator = this.eat(operatorTokenType);
            AstNode right = builderMethod.get();

            left = new LogicalExpression(
                    operator,
                    left,
                    right
            );
        }

        return left;
    }

    /*
        UnaryExpression
            : LeftHandSideExpression
            | ADDITIVE_OPERATOR UnaryExpression
            | LOGICAL_NOT UnaryExpression
            ;
     */
    private AstNode unaryExpression() {
        OperatorToken operator = null;
        if (this.lookaheadEq(AdditiveOperatorToken.class)) {
            operator = this.eat(AdditiveOperatorToken.class);
        } else if (this.lookaheadEq(LogicalNotToken.class)) {
            operator = this.eat(LogicalNotToken.class);
        }

        if (operator != null) {
            return new UnaryExpression(
                    operator,
                    this.unaryExpression()
            );
        }

        return this.leftHandSideExpression();
    }

    /*
       LeftHandSideExpression
           : CallMemberExpression
           ;
    */
    private AstNode leftHandSideExpression() {
        return this.callMemberExpression();
    }

    /*
        CallMemberExpression
            : MemberExpression
            | CallExpression
            ;
     */
    private AstNode callMemberExpression() {
        if (this.lookaheadEq(SuperToken.class)) {
            return this.callExpression(this.superAst());
        }

        AstNode member = this.memberExpression();

        if (this.lookaheadEq(OpenParenthesisToken.class)) {
            return this.callExpression(member);
        }

        return member;
    }

    /*
        CallExpression
            : Callee Arguments
            ;

        Callee
            : MemberExpression
            | CallExpression
            ;
     */
    private AstNode callExpression(AstNode callee) {
        AstNode callExpression = new CallExpression(callee, this.arguments());

        if (this.lookaheadEq(OpenParenthesisToken.class)) {
            callExpression = this.callExpression(callExpression);
        }

        return callExpression;
    }
    /*
       Arguments
           : '(' OptArgumentList ')'
           ;
    */
    private List<AstNode> arguments() {
        this.eat(OpenParenthesisToken.class);

        List<AstNode> argumentList = this.lookaheadEq(ClosedParenthesisToken.class) ? List.of() : this.argumentList();

        this.eat(ClosedParenthesisToken.class);

        return argumentList;
    }
    /*
        ArgumentList
            : AssignmentExpression
            | ArgumentList ',' AssignmentExpression
            ;
     */
    private List<AstNode> argumentList() {
        var argumentList = new ArrayList<AstNode>();
        argumentList.add(this.assignmentExpression());

        while (this.lookaheadEq(CommaToken.class)) {
            this.eat(CommaToken.class);
            argumentList.add(this.assignmentExpression());
        }

        return argumentList;
    }
    /*
        MemberExpression
            : PrimaryExpression
            | MemberExpression '.' Identifier
            | MemberExpression '[' Expression ']'
            ;
     */
    private AstNode memberExpression() {
        var object = this.primaryExpression();

        while (this.lookaheadEq(DotToken.class) || this.lookaheadEq(OpenSquareBracketsToken.class)) {

            if (this.lookaheadEq(DotToken.class)) {
                this.eat(DotToken.class);
                Identifier property = this.identifier();

                object = new MemberExpression(
                        false,
                        object,
                        property
                );

            }

            if (this.lookaheadEq(OpenSquareBracketsToken.class)) {
                this.eat(OpenSquareBracketsToken.class);
                AstNode property = this.expression();
                this.eat(ClosedSquareBracketsToken.class);

                object = new MemberExpression(
                        true,
                        object,
                        property
                );
            }
        }

        return object;
    }

    /*
        PrimaryExpression
            : Literal
            | ParenthesizedExpression
            | Identifier
            | ThisExpression
            | NewExpression
            ;
     */
    private AstNode primaryExpression() {
        if (this.lookAhead instanceof LiteralToken<?>) {
            return this.literal();
        } else if (this.lookaheadEq(OpenParenthesisToken.class)) {
            return this.parenthesizedExpression();
        } else if (this.lookaheadEq(IdentifierToken.class)) {
            return this.identifier();
        } else if (this.lookaheadEq(ThisToken.class)) {
            return this.thisExpression();
        } else if (this.lookaheadEq(NewToken.class)) {
            return this.newExpression();
        }

        throw new IllegalStateException("Unexpected primary expression.");
    }

    /*
        NewExpression
            : 'new' MemberExpression Arguments
            ;
     */
    private AstNode newExpression() {
        this.eat(NewToken.class);

        return new NewExpression(
                this.memberExpression(),
                this.arguments()
        );
    }


    /*
       ThisExpression
           : 'this'
           ;
    */
    private AstNode thisExpression() {
        this.eat(ThisToken.class);
        return ThisExpression.INSTANCE;
    }

    /*
        Super
            : 'super'
            ;
     */
    private AstNode superAst() {
        this.eat(SuperToken.class);
        return Super.INSTANCE;
    }

    /*
        ParenthesizedExpression
            : '(' Expression ')'
            ;
     */
    private AstNode parenthesizedExpression() {
        this.eat(OpenParenthesisToken.class);
        AstNode expression = this.expression();
        this.eat(ClosedParenthesisToken.class);

        return expression;
    }


    /*
        Literal
            : NumericLiteral
            | StringLiteral
            | BooleanLiteral
            | NullLiteral
            ;
     */
    private Literal literal() {
        if (this.lookAhead instanceof BooleanLiteralToken) {
            return this.booleanLiteral(this.eat(BooleanLiteralToken.class).getValue());
        } else if (this.lookaheadEq(NumberToken.class)) {
            return this.numericLiteral();
        } else if (this.lookaheadEq(StringToken.class)) {
            return this.stringLiteral();
        } else if (this.lookaheadEq(NullToken.class)) {
            return this.nullLiteral();
        }

        throw new IllegalStateException("Literal: unexpected literal production");
    }

    /*
        BooleanLiteral
            : 'true'
            | 'false'
            ;
     */
    private Literal booleanLiteral(boolean b) {
        return new BooleanLiteral(b);
    }

    /*
        NullLiteral
            : 'null'
            ;
     */
    private Literal nullLiteral() {
        this.eat(NullToken.class);
        return new NullLiteral(null);
    }


    /*
           NumericLiteral
               : NUMBER
               ;
        */
    private NumericLiteral numericLiteral() {
        NumberToken token = this.eat(NumberToken.class);
        return new NumericLiteral(token.getValue());
    }

    /*
           StringLiteral
               : STRING
               ;
        */
    private StringLiteral stringLiteral() {
        StringToken token = this.eat(StringToken.class);
        return new StringLiteral(token.getValue().substring(1, token.getValue().length() - 1));

    }

    private <T extends Token<?>> boolean lookaheadEq(Class<T> tokenType) {
        return tokenType.isAssignableFrom(lookAhead.getClass());
    }
    private <T extends Token<?>> T eat(Class<T> typeClass) {
        final Token<?> token = this.lookAhead;
        if (null == token) {
            throw new IllegalStateException("Unexpected end of input, expected: " + typeClass.getSimpleName());
        }
        if (!typeClass.isInstance(token)) {
            throw new IllegalStateException("Unexpected token " + token.getValue()
                    + ", expected: "  + typeClass.getSimpleName());
        }
        this.lookAhead = this.tokenizer.getNextToken();
        return typeClass.cast(token);
    }

    /**
     * 检查赋值运算符左侧是不是一个标识符
     */
    private AstNode checkValidAssignmentTarget(AstNode tree) {
        if (tree instanceof Identifier || tree instanceof MemberExpression) {
            return tree;
        }

        throw new IllegalStateException("Invalid left-hand side in assignment expression");
    }
}

package com.yumi.rdp;

import com.yumi.rdp.token.AddAssignToken;
import com.yumi.rdp.token.AdditionToken;
import com.yumi.rdp.token.ClassToken;
import com.yumi.rdp.token.ClosedBraceToken;
import com.yumi.rdp.token.ClosedParenthesisToken;
import com.yumi.rdp.token.ClosedSquareBracketsToken;
import com.yumi.rdp.token.CommaToken;
import com.yumi.rdp.token.DefToken;
import com.yumi.rdp.token.DivAssignToken;
import com.yumi.rdp.token.DivisionToken;
import com.yumi.rdp.token.DoToken;
import com.yumi.rdp.token.DotToken;
import com.yumi.rdp.token.ElseToken;
import com.yumi.rdp.token.EqualityToken;
import com.yumi.rdp.token.ExtendsToken;
import com.yumi.rdp.token.FalseToken;
import com.yumi.rdp.token.ForToken;
import com.yumi.rdp.token.GeToken;
import com.yumi.rdp.token.GtToken;
import com.yumi.rdp.token.IdentifierToken;
import com.yumi.rdp.token.IfToken;
import com.yumi.rdp.token.LeToken;
import com.yumi.rdp.token.LetToken;
import com.yumi.rdp.token.LogicalAndToken;
import com.yumi.rdp.token.LogicalNotToken;
import com.yumi.rdp.token.LogicalOrToken;
import com.yumi.rdp.token.LtToken;
import com.yumi.rdp.token.MulAssignToken;
import com.yumi.rdp.token.MultiplicationToken;
import com.yumi.rdp.token.NewToken;
import com.yumi.rdp.token.NonEqualityToken;
import com.yumi.rdp.token.NullToken;
import com.yumi.rdp.token.NumberToken;
import com.yumi.rdp.token.OpenBraceToken;
import com.yumi.rdp.token.OpenParenthesisToken;
import com.yumi.rdp.token.OpenSquareBracketsToken;
import com.yumi.rdp.token.ReturnToken;
import com.yumi.rdp.token.SemicolonToken;
import com.yumi.rdp.token.SimpleAssignToken;
import com.yumi.rdp.token.StringToken;
import com.yumi.rdp.token.SubAssignToken;
import com.yumi.rdp.token.SubtractionToken;
import com.yumi.rdp.token.SuperToken;
import com.yumi.rdp.token.ThisToken;
import com.yumi.rdp.token.Token;
import com.yumi.rdp.token.TrueToken;
import com.yumi.rdp.token.WhileToken;

import java.util.regex.Pattern;

public class Tokenizer {
    //正则表达式
    private static final RegexpInfo[] TOKENIZER_SPEC_ARR = {
            //空白字符
            new RegexpInfo(Pattern.compile("^\\s+"), it -> null),
            //单行注释
            new RegexpInfo(Pattern.compile("^//.*"), it -> null),
            //多行注释
            new RegexpInfo(Pattern.compile("^/\\*[\\s\\S]*?\\*/"), it -> null),
            //符合，分隔符
            new RegexpInfo(Pattern.compile("^;"), it -> SemicolonToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\{"), it -> OpenBraceToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^}"), it -> ClosedBraceToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\("), it -> OpenParenthesisToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\)"), it -> ClosedParenthesisToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^,"), it -> CommaToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\."), it -> DotToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\["), it -> OpenSquareBracketsToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^]"), it -> ClosedSquareBracketsToken.INSTANCE),
            // 关键字
            new RegexpInfo(Pattern.compile("^\\blet\\b"), it -> LetToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bif\\b"), it -> IfToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\belse\\b"), it -> ElseToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\btrue\\b"), it -> TrueToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bfalse\\b"), it -> FalseToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bnull\\b"), it -> NullToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bwhile\\b"), it -> WhileToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bdo\\b"), it -> DoToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bfor\\b"), it -> ForToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bdef\\b"), it -> DefToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\breturn\\b"), it -> ReturnToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bclass\\b"), it -> ClassToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bextends\\b"), it -> ExtendsToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bsuper\\b"), it -> SuperToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bnew\\b"), it -> NewToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\bthis\\b"), it -> ThisToken.INSTANCE),
            // 数字:
            new RegexpInfo(Pattern.compile("^\\d+"), it -> new NumberToken(Integer.parseInt(it))),
            // 标识符
            new RegexpInfo(Pattern.compile("^\\w+"), IdentifierToken::new),
            // 等式运算符: ==. !=
            new RegexpInfo(Pattern.compile("^[=!]="), it -> {
                if (it.equals(EqualityToken.INSTANCE.getValue())) {
                    return EqualityToken.INSTANCE;
                } else {
                    return NonEqualityToken.INSTANCE;
                }
            }),
            // 简单赋值运算符: =
            new RegexpInfo(Pattern.compile("^="), it -> SimpleAssignToken.INSTANCE),
            // 复杂赋值运算符: *=, /=, +=, -=
            new RegexpInfo(Pattern.compile("^[*/+-]="), it -> {
                if (it.equals(MulAssignToken.INSTANCE.getValue())) {
                    return MulAssignToken.INSTANCE;
                } else if (it.equals(DivAssignToken.INSTANCE.getValue())) {
                    return DivAssignToken.INSTANCE;
                } else if (it.equals(AddAssignToken.INSTANCE.getValue())) {
                    return AddAssignToken.INSTANCE;
                } else {
                    return SubAssignToken.INSTANCE;
                }
            }),
            // 数学运算符: +, -, *, /
            new RegexpInfo(Pattern.compile("^[+-]"), it -> {
                if (it.equals(AdditionToken.INSTANCE.getValue())) {
                    return AdditionToken.INSTANCE;
                } else {
                    return SubtractionToken.INSTANCE;
                }
            }),
            new RegexpInfo(Pattern.compile("^[*/]"), it -> {
                if (it.equals(MultiplicationToken.INSTANCE.getValue())) {
                    return MultiplicationToken.INSTANCE;
                } else {
                    return DivisionToken.INSTANCE;
                }
            }),
            // 关系运算符 >, >=, <, <=
            new RegexpInfo(Pattern.compile("^[><]=?"), it -> {
                if (it.equals(LeToken.INSTANCE.getValue())) {
                    return LeToken.INSTANCE;
                } else if (it.equals(LtToken.INSTANCE.getValue())) {
                    return LtToken.INSTANCE;
                } else if (it.equals(GeToken.INSTANCE.getValue())) {
                    return GeToken.INSTANCE;
                } else {
                    return GtToken.INSTANCE;
                }
            }),
            // 逻辑运算符 &&, ||
            new RegexpInfo(Pattern.compile("^&&"), it -> LogicalAndToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^\\|\\| "), it -> LogicalOrToken.INSTANCE),
            new RegexpInfo(Pattern.compile("^!"), it -> LogicalNotToken.INSTANCE),
            // 字符串:
            new RegexpInfo(Pattern.compile("^\"[^\"]*\""), StringToken::new),
            new RegexpInfo(Pattern.compile("^'[^']*'"), StringToken::new),
    };
    private String str;
    private int cursor;

    public Tokenizer(String str) {
        this.str = str;
        this.cursor = 0;
    }

    public void setStr(String str) {
        this.str = str;
    }

    private boolean hasMoreTokens() {
        return this.cursor < this.str.length();
    }

    public Token<?> getNextToken() {
        if (!hasMoreTokens()) {
            return null;
        }
        final String s = this.str.substring(this.cursor);
        for (RegexpInfo regexpInfo : TOKENIZER_SPEC_ARR) {
            String tokenValue = regexpInfo.match(s);
            if (tokenValue == null) {
                continue;
            }
            this.cursor += tokenValue.length();
            Token<?> token = regexpInfo.makeToken(tokenValue);
            if (token == null) {
                return this.getNextToken();
            }
            return token;
        }
        throw new IllegalStateException("Unexpected token: [" + this.str.charAt(0) + "]");
    }
}

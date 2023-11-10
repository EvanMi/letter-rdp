package com.yumi.rdp.ast.statement;

import com.yumi.rdp.ast.Statement;

public class EmptyStatement implements Statement {
    public static final EmptyStatement INSTANCE = new EmptyStatement();
    private EmptyStatement() {

    }
}

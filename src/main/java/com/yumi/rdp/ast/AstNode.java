package com.yumi.rdp.ast;

import com.alibaba.fastjson2.annotation.JSONField;

public interface AstNode {
    @JSONField(ordinal = 1, name = "type")
    default String type() {
        return this.getClass().getSimpleName();
    }
}

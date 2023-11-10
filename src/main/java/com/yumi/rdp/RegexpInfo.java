package com.yumi.rdp;

import com.yumi.rdp.token.Token;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record RegexpInfo(Pattern regexp, Function<String, ? extends Token<?>> tokenFunc) {

    public String match(String str) {
        Matcher matcher = this.regexp.matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }

        return null;
    }

    public Token<?> makeToken(String tokenValue) {
        return this.tokenFunc.apply(tokenValue);
    }
}

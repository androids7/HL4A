/*
 * Copyright (c) 2013 Tah Wei Hoon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License Version 2.0,
 * with full text available at http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * This software is provided "as is". Use at your own risk.
 */

package com.myopicmobile.textwarrior.common;

import java.util.*;
import 间.安卓.工具.*;

public abstract class ColorScheme {
    public enum Colorable {
        FOREGROUND, BACKGROUND, SELECTION_FOREGROUND, SELECTION_BACKGROUND,
        CARET_FOREGROUND, CARET_BACKGROUND, CARET_DISABLED, LINE_HIGHLIGHT,
        NON_PRINTING_GLYPH, COMMENT, KEYWORD, NAME, LITERAL,STRING,
        SECONDARY
        }

    protected HashMap<Colorable, Integer> _colors = generateDefaultColors();

    public void setColor(Colorable colorable,int color) {
        _colors.put(colorable, color);
    }

    public int getColor(Colorable colorable) {
        Integer color = _colors.get(colorable);
        if (color == null) {
            TextWarriorException.fail("Color not specified for " + colorable);
            return 0;
        }
        return color.intValue();
    }

    // Currently, color scheme is tightly coupled with semantics of the token types
    public int getTokenColor(int tokenType) {
        Colorable element;
        switch (tokenType) {
            case Lexer.NORMAL:
                element = Colorable.FOREGROUND;
                break;
            case Lexer.KEYWORD:
                element = Colorable.KEYWORD;
                break;
            case Lexer.NAME:
                element = Colorable.NAME;
                break;
            case Lexer.DOUBLE_SYMBOL_LINE: //fall-through
            case Lexer.DOUBLE_SYMBOL_DELIMITED_MULTILINE:
                //case Lexer.SINGLE_SYMBOL_LINE_B:
                element = Colorable.COMMENT;
                break;
            case Lexer.SINGLE_SYMBOL_DELIMITED_A: //fall-through
            case Lexer.SINGLE_SYMBOL_DELIMITED_B:
                element = Colorable.STRING;
                break;
            case Lexer.LITERAL:
                element = Colorable.LITERAL;
                break;
            case Lexer.SINGLE_SYMBOL_LINE_A: //fall-through
            case Lexer.SINGLE_SYMBOL_WORD:
            case Lexer.OPERATOR:
                element = Colorable.SECONDARY;
                break;
            case Lexer.SINGLE_SYMBOL_LINE_B: //类型
                element = Colorable.NAME;
                break;
            default:
                TextWarriorException.fail("Invalid token type");
                element = Colorable.FOREGROUND;
                break;
        }
        return getColor(element);
    }

    /**
     * Whether this color scheme uses a dark background, like black or dark grey.
     */
    public abstract boolean isDark();

    private HashMap<Colorable, Integer> generateDefaultColors() {

        // High-contrast, black-on-white color scheme
        HashMap<Colorable, Integer> colors = new HashMap<Colorable, Integer>(Colorable.values().length);
        
        colors.put(Colorable.FOREGROUND, 白色);//前景色
        colors.put(Colorable.BACKGROUND, 白色);
        colors.put(Colorable.SELECTION_FOREGROUND, 白色);//选择文本的前景色
        colors.put(Colorable.SELECTION_BACKGROUND, 蓝色);//选择文本的背景色
        colors.put(Colorable.CARET_FOREGROUND, 蓝色);
        colors.put(Colorable.CARET_BACKGROUND, 蓝色);
        colors.put(Colorable.CARET_DISABLED, 蓝色);
        colors.put(Colorable.LINE_HIGHLIGHT, 0x20888888);

        colors.put(Colorable.NON_PRINTING_GLYPH, 蓝色);//行号
        colors.put(Colorable.COMMENT, 绿色); //注释
        colors.put(Colorable.KEYWORD, 蓝色); //关键字
        colors.put(Colorable.NAME, 绿色); // Eclipse default color
        colors.put(Colorable.LITERAL, 蓝色); // Eclipse default color
        colors.put(Colorable.STRING, 暗红); //字符串
        colors.put(Colorable.SECONDARY, 淡蓝);//宏
        return colors;
    }

    public static int 淡蓝 = 颜色.蓝色.取控件色();
    public static int 蓝色 = 颜色.靛蓝.取控件色();
    public static int 白色 = 颜色.转换(颜色.白色);
    private static final int 暗红 = 0xFFA31515;
    private static final int 绿色 = 0xFF3F7F5F;
    
}

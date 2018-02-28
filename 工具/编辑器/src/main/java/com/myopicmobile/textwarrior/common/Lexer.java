/*
 * Copyright (c) 2013 Tah Wei Hoon. * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License Version 2.0,
 * with full text available at http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * This software is provided "as is". Use at your own risk.
 o */
package com.myopicmobile.textwarrior.common;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import 间.安卓.脚本.JavaScript;
import 间.工具.反射;
import 间.收集.哈希表;
import 间.收集.集合;
import 间.安卓.工具.提示;

/**
 * Does lexical analysis of a text for C-like languages.
 * The programming language syntax used is set as a static class variable.
 */
public class Lexer {
    
    
    private static String[] 工具类前缀 = {
        "间.安卓.插件","间.安卓.组件",
        "间.安卓.工具","间.安卓.弹窗",
        "间.安卓.网络","间.安卓.组件",
        "间.安卓.绘画","间.安卓.视图",
        "间.安卓.视图.扩展","间.安卓.视图.适配器",
        "间.安卓.视图.实现",
        "间.工具","间.数据","间.收集","间.接口",
        "java.lang","java.io","java.util",
        "android.os","android.util","android.content",
        "android.view","android.widget"
    };

    public static 哈希表<String,Class<?>> 类缓存表 = new 哈希表<>();
    public static 哈希表<String,Boolean> 类检查表 = new 哈希表<>();

    public static boolean 是工具类(String $名称) {
        if (类检查表.检查键值($名称)) {
            return 类检查表.读取($名称) == true;
        }
        Class<?> $类;
        for (String $单个 : 工具类前缀) {
            if (($类 = 反射.取类($单个 + "." + $名称))!= null) {
                类检查表.设置($名称,true);
                类缓存表.设置($名称,$类);
                return true;
            }
        }
        类检查表.设置($名称,false);
        return false;
    }

    public static Class<?> 找工具类(String $名称) {
        if (类缓存表.检查键值($名称)) {
            return 类缓存表.读取($名称);
        }
        Class<?> $类;
        for (String $单个 : 工具类前缀) {
            if (($类 = 反射.取类($单个 + "." + $名称))!= null) {
                类缓存表.设置($名称,$类);
                return $类;
            }
        }
        return null;
    }
    
    private final static int MAX_KEYWORD_LENGTH = 127;

    public final static int UNKNOWN = -1;
    public final static int NORMAL = 0;
    public final static int KEYWORD = 1;
    public final static int OPERATOR = 2;
    public final static int NAME = 3;
    public final static int LITERAL = 4;
    /** A word that starts with a special symbol, inclusive.
     * Examples:
     * :ruby_symbol
     * */
    public final static int SINGLE_SYMBOL_WORD = 10;

    /** Tokens that extend from a single start symbol, inclusive, until the end of line.
     * Up to 2 types of symbols are supported per language, denoted by A and B
     * Examples:
     * #include "myCppFile"
     * #this is a comment in Python
     * %this is a comment in Prolog
     * */
    public final static int SINGLE_SYMBOL_LINE_A = 20;
    public final static int SINGLE_SYMBOL_LINE_B = 21;

    /** Tokens that extend from a two start symbols, inclusive, until the end of line.
     * Examples:
     * //this is a comment in C
     * */
    public final static int DOUBLE_SYMBOL_LINE = 30;

    /** Tokens that are enclosed between a start and end sequence, inclusive,
     * that can span multiple lines. The start and end sequences contain exactly
     * 2 symbols.
     * Examples:
     * {- this is a...
     *  ...multi-line comment in Haskell -}
     * */
    public final static int DOUBLE_SYMBOL_DELIMITED_MULTILINE = 40;

    /** Tokens that are enclosed by the same single symbol, inclusive, and
     * do not span over more than one line.
     * Examples: 'c', "hello world"
     * */
    public final static int SINGLE_SYMBOL_DELIMITED_A = 50;
    public final static int SINGLE_SYMBOL_DELIMITED_B = 51;

    private static Language _globalLanguage = LanguageNonProg.getInstance();
    synchronized public static void setLanguage(Language lang) {
        _globalLanguage = lang;
    }

    synchronized public static Language getLanguage() {
        return _globalLanguage;
    }


    private DocumentProvider _hDoc;
    private LexThread _workerThread = null;
    LexCallback _callback = null;

    public Lexer(LexCallback callback) {
        _callback = callback;
    }

    public void tokenize(DocumentProvider hDoc) {
        if (!Lexer.getLanguage().isProgLang()) {
            return;
        }

        //tokenize will modify the state of hDoc; make a copy
        setDocument(new DocumentProvider(hDoc));
        if (_workerThread == null) {
            _workerThread = new LexThread(this);
            _workerThread.start();
        } else {
            _workerThread.restart();
        }
    }

    void tokenizeDone(List<Pair> result) {
        if (_callback != null) {
            _callback.lexDone(result);
        }
        _workerThread = null;
    }

    public void cancelTokenize() {
        if (_workerThread != null) {
            _workerThread.abort();
            _workerThread = null;
        }
    }

    public synchronized void setDocument(DocumentProvider hDoc) {
        _hDoc = hDoc;
    }

    public synchronized DocumentProvider getDocument() {
        return _hDoc;
    }





    private class LexThread extends Thread {
        private boolean rescan = false;
        private final Lexer _lexManager;
        /** can be set by another thread to stop the scan immediately */
        private final Flag _abort;
        /** A collection of Pairs, where Pair.first is the start
         *  position of the token, and Pair.second is the type of the token.*/
        /**
         * pair的集合，first表示token的开始，second表示token的类型
         */
        private List<Pair> _tokens;

        public LexThread(Lexer p) {
            _lexManager = p;
            _abort = new Flag();
        }

        @Override
        public void run() {
            do{
                rescan = false;
                _abort.clear();
                tokenize();
            }
            while(rescan);

            if (!_abort.isSet()) {
                // lex complete
                _lexManager.tokenizeDone(_tokens);
            }
        }

        public void restart() {
            rescan = true;
            _abort.set();
        }

        public void abort() {
            _abort.set();
        }
        /**
         * Scans the document referenced by _lexManager for tokens.
         * The result is stored internally.
         *扫描结果存在list
         * *******************************
         * #include <stdio.h>
         * int main(void)
         * {
         *
         *     return 0;
         * }
         * *******************************
         * 以上的C代码将产生以下词法分析器
         * (0,20)---->#include <stdio.h>
         * (20,1)---->int
         * (24,0)空格
         * (29,1)----->void
         * (33,0)
         * (42,1)----->return
         * (49,0)
         * ------------------------------
         * 它的特点：first记录从哪开始有标记，second记录标记的类型
         */

        public final static String[] 蓝色关键字 = {
            "(", ")", "{", "}", ".", ",", ";",":", "[", "]","?",
        };

        public final static String[] 绿色关键字 = {
            "=", "+", "-","/", "*", "&", "!", "|", "<", ">","~", "%", "^",
        };

        public 集合 蓝色集合 = 集合.到集合(蓝色关键字);
        public 集合 绿色集合 = 集合.到集合(绿色关键字);

        
        
        
        public void tokenize() {
            DocumentProvider hDoc = getDocument();
            Language language = Lexer.getLanguage();
            //这里用ArrayList速度会发生质的飞跃
            List<Pair> tokens = new ArrayList<>();

            //language.isProgLang()返回真
            if (!language.isProgLang()) {
                tokens.add(new Pair(0, NORMAL));
                _tokens = tokens;
                return;
            }
            StringReader stringReader=new StringReader(hDoc.toString());
            JavaScriptLexer cLexer=new JavaScriptLexer(stringReader);

            try {
                JavaScriptType cType=null;
                int idx=0;
                while ((cType = cLexer.yylex()) != JavaScriptType.EOF) {
                    String name = cLexer.yytext();
                    switch (cType) {
                        case KEYWORD:
                            tokens.add(new Pair(idx, KEYWORD));
                            break;
                        case COMMENT:
                            tokens.add(new Pair(idx, DOUBLE_SYMBOL_DELIMITED_MULTILINE));
                            break;
                        case STRING:
                        case CHARACTER_LITERAL:
                            tokens.add(new Pair(idx, SINGLE_SYMBOL_DELIMITED_A));
                            break;                        
                        case INTEGER_LITERAL:
                        case FLOATING_POINT_LITERAL:
                            tokens.add(new Pair(idx, OPERATOR));
                            break;
                        default:

                            if (JavaScript.替换关键字表.检查键值(name)) {
                                tokens.add(new Pair(idx, KEYWORD));
                                break;
                            }  else if (是工具类(name)) {
                                tokens.add(new Pair(idx, OPERATOR));
                                break;
                            } else if (蓝色集合.检查(name)) {
                                tokens.add(new Pair(idx, OPERATOR));
                                break;

                            } else {
                                boolean 状态 = true;
                                for (int k = 0;k < name.length();k ++) {
                                    if (!绿色集合.检查(name.substring(k, k + 1))) {
                                        状态 = false;
                                    }
                                }
                                if (状态) {
                                    tokens.add(new Pair(idx, NAME));
                                    break;
                                }
                            }
                            tokens.add(new Pair(idx, NORMAL));
                    }
                    idx += name.length();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tokens.isEmpty()) {
                // return value cannot be empty
                tokens.add(new Pair(0, NORMAL));
            }
            //printList(tokens);
            _tokens = tokens;
        }


    }

    public interface LexCallback {
        public void lexDone(List<Pair> results);
    }
}

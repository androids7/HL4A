/*
 * Copyright (c) 2011 Tah Wei Hoon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License Version 2.0,
 * with full text available at http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * This software is provided "as is". Use at your own risk.
 */
package com.myopicmobile.textwarrior.common;
import 间.安卓.脚本.JavaScript;
import 间.收集.集合;

/**
 * Singleton class containing the symbols and operators of the Javascript language
 */
public class LanguageJavascript extends Language {
    private static Language _theOne = null;
    
    private static String[] keywords = {
            "abstract","arguments","boolean","break","byte",
            "case","catch","char","class","const",
            "continue","debugger","default","delete","do",
            "double","else","enum","eval","export",
            "extends","false","final","finally","float",
            "for","function","goto","if","implements",
            "import","in","instanceof","int","interface",
            "let","long","native","new","null",
            "package","private","protected","public","return",
            "short","static","super","switch","synchronized",
            "this","throw","throws","transient","true",
            "try","typeof","var","void","volatile",
            "while","with","yield"
    };
    
    static {
        集合<String> $返回 = new 集合<>(keywords);
        for (String[] $单个 : JavaScript.默认替换表) {
            $返回.添加($单个[0]);
        }
        keywords = $返回.到数组(String.class);
        
    }
    /*
    private final static char[] BASIC_OPERATORS = {
            '(', ')', '{', '}', '.', ',', ';', '=', '+', '-',
            '/', '*', '&', '!', '|', ':', '[', ']', '<', '>',
            '?', '~', '%', '^'
    };
    */
    public static Language getInstance(){
        if(_theOne == null){
            _theOne = new LanguageJavascript();
        }
        return _theOne;
    }
    
    private LanguageJavascript(){
        setKeywords(keywords);
        }
        
    public boolean isLineAStart(char c){
        return false;
    }
}

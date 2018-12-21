/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexanalyzer;

import static java.lang.Character.isDigit;
import java.util.ArrayList;

/**
 *
 * @author vampire
 */
public class Validation {

    boolean mcomment = false;
    ArrayList<token> Keyword = new ArrayList<token>();
    ArrayList<token> Operator = new ArrayList<token>();
    ArrayList<token> Punctuator = new ArrayList<token>();
    String ClassPart = "", ValuePart = "";

    public Validation() {
        Keyword.add(new token("Data Types", new String[]{"var", "array", "stack", "queue", "linklist"}));
        Keyword.add(new token("Access Modifiers", new String[]{"public", "private", "protected"}));
        Keyword.add(new token("Category", new String[]{"abstract", "final"}));
        Keyword.add(new token("", new String[]{"new", "try", "catch", "finally", "this", "for", "for of", "while", "continue", "break", "construct", "void", "static", "class", "extends", "implements", "return", "if", "else", "throws", "do", "switch", "case", "default","super"}));

        Operator.add(new token("Plus/Minus", new String[]{"+", "-"}));
        Operator.add(new token("Multi/div/Mod", new String[]{"*", "/", "%"}));
        Operator.add(new token("Relational Operators", new String[]{"<", ">", "<=", ">=", "!=", "=="}));
        Operator.add(new token("INC/DEC", new String[]{"++", "--"}));
        Operator.add(new token("Bitwise Operators", new String[]{"&", "|", "<<", ">>", "~", "^"}));
        Operator.add(new token("Assignment Operators", new String[]{"*=", "/=", "+=", "-=", "%="}));
        Operator.add(new token("Logical Operators", new String[]{"&&", "||"}));
        Operator.add(new token("", new String[]{"=", "!", "."}));

        Punctuator.add(new token("", new String[]{"{", "}", "(", ")", "[", "]", ";", ":", "?", ","}));
    }

    public boolean isIdentifier(String word) {
        int InitialState;
        int allowInIdentifier = 3;
        boolean iden;
        //Input for Identifier
        int[][] TransitionTableIden = {{2, 3, 1},
        {2, 2, 1},
        {2, 2, 2},
        {3, 3, 3}};
        int noOfStatesIden = 4;
        InitialState = 0;
        int[] FinalStatesIden = {2};
        String[] inputIden = new String[allowInIdentifier];
        inputIden[0] = "[A-Za-z]";
        inputIden[1] = "[0-9]";
        inputIden[2] = "[_]";

        dfa objIden = new dfa(noOfStatesIden, inputIden, TransitionTableIden, InitialState, FinalStatesIden);

        iden = objIden.validate(word);
        return iden;
    }

//    public boolean isVar(String word) {
//        int InitialState;
//        boolean Var;
//        //Input for Identifier
//        int[][] TransitionTableVar = {{1, 2, 3, 4, 5, 3, 3, 6, 3},
//        {3, 2, 3, 4, 3, 3, 3, 3, 3},
//        {3, 2, 3, 4, 3, 3, 3, 3, 3},
//        {3, 3, 3, 3, 3, 3, 3, 3, 3},
//        {3, 7, 3, 3, 3, 3, 3, 3, 3},
//        {8, 8, 8, 8, 3, 8, 8, 8, 9},
//        {10, 10, 10, 10, 10, 10, 10, 11, 12},
//        {3, 7, 13, 3, 3, 3, 3, 3, 3},
//        {3, 3, 3, 3, 14, 3, 3, 3, 3},
//        {3, 8, 3, 3, 8, 8, 3, 8, 8},
//        {10, 10, 10, 10, 10, 10, 10, 11, 12},
//        {3, 3, 3, 3, 3, 3, 3, 3, 3},
//        {3, 3, 3, 3, 10, 10, 3, 10, 10},
//        {15, 16, 3, 3, 3, 3, 3, 3, 3},
//        {3, 3, 3, 3, 3, 3, 3, 3, 3},
//        {3, 16, 3, 3, 3, 3, 3, 3, 3},
//        {3, 16, 3, 3, 3, 3, 3, 3, 3}};
//
//        int noOfStatesVar = 17;
//        InitialState = 0;
//        int[] FinalStatesVar = {2, 7, 11, 14, 16};
//        String[] inputVar = {"[+-]", "[0-9]", "[Ee]", "[.]", "[\']", "[rbtno]", "[^\\\\\'\"rbtno]", "[\"]", "[\\\\]"};
//
//        dfa objVar = new dfa(noOfStatesVar, inputVar, TransitionTableVar, InitialState, FinalStatesVar);
//
//        Var = objVar.validate(word);
//        return Var;
//    }
    public boolean isInt(String word) {
        int S = 4;                                                  //Int
        String[] VC = {"[0-9]", "[+]", "[-]"};
        int IS = 0;
        int FS[] = {3};
        int[][] TranTable = {{3, 1, 1}, {3, 2, 2}, {2, 2, 2}, {3, 2, 2}};

        dfa FA = new dfa(S, VC, TranTable, IS, FS);

        return FA.validate(word);
    }

    public boolean isFloat(String word) {
        int S = 9;                                                  //Float
        String[] VC = {"[0-9]", "[+-]", "[.]", "[Ee]"};
        int IS = 0;
        int FS[] = {5, 8};
        int[][] TranTable = {{1, 4, 2, 3}, {1, 3, 2, 3}, {5, 3, 3, 3}, {3, 3, 3, 3}, {1, 3, 2, 3}, {5, 3, 3, 6}, {8, 7, 3, 3}, {8, 3, 3, 3}, {8, 3, 3, 3}};

        dfa FA = new dfa(S, VC, TranTable, IS, FS);

        return FA.validate(word);
    }

    public boolean isChar(String word) {
        int S = 6;                                                  //Char
        String[] VC = {"[^\\\\\'\"rbtno]", "[rbtno]", "[\\\\]", "[\']"};
        int IS = 0;
        int FS[] = {4};
        int[][] TranTable = {{5, 5, 5, 1}, {3, 3, 2, 5}, {5, 3, 3, 3}, {5, 5, 5, 4}, {5, 5, 5, 5}, {5, 5, 5, 5}};

        dfa FA = new dfa(S, VC, TranTable, IS, FS);

        return FA.validate(word);
    }

    public boolean isString(String word) {
        int S = 6;                                                  //String
        String[] VC = {"[^\\\\\"rbtno]", "[rbtno]", "[\\\\]", "[\"]"};
        int IS = 0;
        int FS[] = {4};
        int[][] TranTable = {{5, 5, 5, 1}, {3, 3, 2, 4}, {5, 3, 3, 3}, {3, 3, 2, 4}, {5, 5, 5, 5}, {5, 5, 5, 5}};

        dfa FA = new dfa(S, VC, TranTable, IS, FS);

        return FA.validate(word);
    }

    public token isKeyword(String word) {
        boolean flag = false;

        for (int i = 0; i < this.Keyword.size(); i++) {
            for (int j = 0; j < Keyword.get(i).getVplist().length; j++) {
                if (word.equals(Keyword.get(i).getVplist()[j])) {
                    ClassPart = Keyword.get(i).getCp();
                    ValuePart = word;
                    flag = true;
                    break;
                }
            }
            if (flag == true) {
                break;
            }
        }
        if (flag == true) {
            return new token(ClassPart, ValuePart);
        } else {
            return new token("", "");
        }
    }

    public token isOperators(String word) {
        boolean flag = false;

        for (int i = 0; i < this.Operator.size(); i++) {
            for (int j = 0; j < Operator.get(i).getVplist().length; j++) {
                if (word.equals(Operator.get(i).getVplist()[j])) {
                    ClassPart = Operator.get(i).getCp();
                    ValuePart = word;
                    flag = true;
                    break;
                }
            }
            if (flag == true) {
                break;
            }
        }
        if (flag == true) {
            return new token(ClassPart, ValuePart);
        } else {
            return new token("", "");
        }
    }

    public boolean isOperatorscheck(String word) {
        for (int i = 0; i < this.Operator.size(); i++) {
            for (int j = 0; j < Operator.get(i).getVplist().length; j++) {
                if (word.equals(Operator.get(i).getVplist()[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPunctuatorcheck(String word) {
        for (int i = 0; i < this.Punctuator.size(); i++) {
            for (int j = 0; j < Punctuator.get(i).getVplist().length; j++) {
                if (word.equals(Punctuator.get(i).getVplist()[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public token isPunctuator(String word) {
        boolean flag = false;

        for (int i = 0; i < this.Punctuator.size(); i++) {
            for (int j = 0; j < Punctuator.get(i).getVplist().length; j++) {
                if (word.equals(Punctuator.get(i).getVplist()[j])) {
                    ClassPart = Punctuator.get(i).getCp();
                    ValuePart = word;
                    flag = true;
                    break;
                }
            }
            if (flag == true) {
                break;
            }
        }
        if (flag == true) {
            return new token(ClassPart, ValuePart);
        } else {
            return new token("", "");
        }
    }

//    public boolean j_1null(int j){
//        
//    } 
    public String[] wordBreaker(String word) {
        String floatcheck = "";
        boolean match = false;
        ArrayList<String> str = new ArrayList<String>();
        String st = "";
        for (int j = 0; j < word.length(); j++) {
            if (mcomment == true) {
                int b = j;
                for (; b < word.length(); b++) {
                    if (word.charAt(b) == '*' && b < word.length() - 1) {
                        if (word.charAt(b + 1) == '/') {
                            b += 2;
                            j = b;
                            mcomment = false;
                            break;

                        }
                    }
                }
                if (j == word.length()) {
                    break;
                }
                if (!(word.charAt(j) == '*') && mcomment == true) {
                    break;
                }
                if (word.charAt(j) == '*' && j < word.length() - 1 && mcomment == true) {
                    if (word.charAt(j + 1) == '/') {
                        mcomment = false;
                        j += 2;
                        if (j >= word.length() - 1) {
                            break;
                        }
                    }
                }
            }
            if (word.charAt(j) == '/' && j < word.length() - 1) {
                if (word.charAt(j + 1) == '/') {
                    break;
                }
            }
            if (word.charAt(j) == '/' && j < word.length() - 1) {
                if (word.charAt(j + 1) == '*') {
                    mcomment = true;
                    j += 2;
                    int b = j;
                    for (; b < word.length(); b++) {
                        if (word.charAt(b) == '*' && b < word.length() - 1) {
                            if (word.charAt(b + 1) == '/') {
                                b += 2;
                                j = b;
                                mcomment = false;
                                break;

                            }
                        }
                    }
                    if (mcomment == true) {
                        break;
                    }
                }
            }
            if (j == word.length()) {
                break;
            }
            if (word.charAt(j) == ' ' || isOperatorscheck(String.valueOf(word.charAt(j))) || isPunctuatorcheck(String.valueOf(word.charAt(j))) || word.charAt(j) == '\"' || word.charAt(j) == '\'' || word.charAt(j) == '.') {
                match = true;
                char ch;

                if (!st.equals("")) {
                    str.add(st);
                    floatcheck = st;
                    st = "";
                }

                if (isPunctuatorcheck(String.valueOf(word.charAt(j)))) {
                    str.add("" + word.charAt(j));
                }
                if (isOperatorscheck(String.valueOf(word.charAt(j)))) {
                    ch = 'o';
                } else {
                    ch = word.charAt(j);
                }
                switch (ch) {
                    case 'o':
                        while (j < word.length() - 1 && isOperatorscheck(String.valueOf(word.charAt(j + 1)))) {
                            String current;
                            String next;
                            current = String.valueOf(word.charAt(j));
                            st += current;
                            next = String.valueOf(word.charAt(j + 1));
                            st += next;
                            if (isOperatorscheck(st)) {
                                str.add(st);
                                j += 2;
                                st = "";
                            } else {
                                str.add(current);
                                j++;
                                st = "";
                            }
                            if (j == word.length()) {
                                break;
                            }
                        }
                        if (j < word.length()) {
                            str.add(String.valueOf(word.charAt(j)));
                        }
                        break;
                    case '\'':
                        if (j < word.length() - 1 && word.charAt(j + 1) == '\\') {
                            st += word.charAt(j);
                            st += word.charAt(j + 1);
                            j++;
                            if (j < word.length() - 1) {
                                st += word.charAt(j + 1);
                                j++;
                            }
                            if (j < word.length() - 1) {
                                st += word.charAt(j + 1);
                                j++;
                            }
                            str.add(st);
                            st = "";
                        } else {
                            st += word.charAt(j);
                            if (j < word.length() - 1) {
                                st += word.charAt(j + 1);
                                j++;
                            }
                            if (j < word.length() - 1) {
                                st += word.charAt(j + 1);
                                j++;
                            }
                            str.add(st);
                            st = "";
                        }
                        break;
                    case '"':
                        boolean string = false;
                        st += word.charAt(j);
                        if (j < word.length() - 1) {
                            j++;
                            while (word.charAt(j) != '"') {
                                if (word.charAt(j) == '\\') {
                                    st += word.charAt(j);
                                    if (j < word.length() - 1) {
                                        st += word.charAt(j + 1);
                                        j++;
                                    }
                                } else {
                                    st += word.charAt(j);
                                }
                                if (j + 1 == word.length()) {
                                    string = true;
                                    break;
                                } else {
                                    j++;
                                }
                            }
                            if (string == false) {
                                st += word.charAt(j);
                            }
                        }
                        str.add(st);
                        st = "";
                        break;
                    case '.':
                        boolean fl = false;
                        for (int i = 0; i < floatcheck.length(); i++) {
                            if (isDigit(floatcheck.charAt(i))) {
                                fl = true;
                            } else {
                                fl = false;
                                break;
                            }
                        }
                        if (fl == true) {
                            if (j < word.length() - 1) {
                                if (isDigit(word.charAt(j + 1))) {
                                    floatcheck += word.charAt(j);
                                    j++;
                                    floatcheck += word.charAt(j);
                                    j++;
                                    for (; j < word.length(); j++) {
                                        if (word.charAt(j) == ' ' || isOperatorscheck(String.valueOf(word.charAt(j))) || isPunctuatorcheck(String.valueOf(word.charAt(j)))) {
                                            if ((word.charAt(j) == '+' || word.charAt(j) == '-') && (word.charAt(j - 1) == 'e' || word.charAt(j - 1) == 'E')) {

                                            } else {
                                                j--;
                                                break;
                                            }
                                        }
                                        floatcheck += word.charAt(j);
                                    }
                                    str.set(str.size() - 1, floatcheck);
                                } else {
                                    str.add("" + word.charAt(j));
                                }
                            } else {
                                str.add("" + word.charAt(j));
                            }
                        } else {
                            if (j < word.length() - 1) {
                                if (isDigit(word.charAt(j + 1))) {
                                    floatcheck = "";
                                    floatcheck += word.charAt(j);
                                    j++;
                                    floatcheck += word.charAt(j);
                                    j++;
                                    for (; j < word.length(); j++) {
                                        if (word.charAt(j) == ' ' || isOperatorscheck(String.valueOf(word.charAt(j))) || isPunctuatorcheck(String.valueOf(word.charAt(j)))) {
                                            if ((word.charAt(j) == '+' || word.charAt(j) == '-') && (word.charAt(j - 1) == 'e' || word.charAt(j - 1) == 'E')) {

                                            } else {
                                                j--;
                                                break;
                                            }
                                        }
                                        floatcheck += word.charAt(j);
                                    }
                                    str.add(floatcheck);
                                } else {
                                    str.add("" + word.charAt(j));
                                }
                            } else {
                                str.add("" + word.charAt(j));
                            }
                        }
                        floatcheck = "";
                        break;
                }
                if (j == word.length()) {
                    break;
                }
                st = "";
            }
            if (match == false) {
                st += word.charAt(j);
            }
            match = false;
        }
        if (!st.equals("")) {
            str.add(st);
        }

        String[] str1 = new String[str.size()];
        for (int i = 0; i < str.size(); i++) {
            str1[i] = str.get(i);
        }
        return str1;
    }
}

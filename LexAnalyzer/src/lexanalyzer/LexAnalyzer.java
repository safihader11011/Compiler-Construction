/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexanalyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vampire
 */
public class LexAnalyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ArrayList<token> tokens = new ArrayList<token>();
        Validation validate = new Validation();
        token FToken = new token();
        String word = "count";

        FileWriter fw = new FileWriter("src\\lexanalyzer\\Output.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        FileReader fr = new FileReader(new File("src\\lexanalyzer\\Input2.txt"));
        BufferedReader br = new BufferedReader(fr);

        char c;
        String[] str;
        int line = 1;
        word = br.readLine();
        str = validate.wordBreaker(word);
        while (word != null) {
            for (int count = 0; count < str.length; count++) {
                word = str[count];
                if (isLetter(word.charAt(0))) {
                    c = 'a';
                } else if ((word.charAt(0) == '\'' && word.length() != 1) || (word.charAt(0) == '\"' && word.length() != 1) || isDigit(word.charAt(0)) || (word.charAt(0) == '.' && word.length() != 1)) {
                    c = 'v';
                } else {
                    c = word.charAt(0);
                }
                switch (c) {
                    case '_': {
                        if (validate.isIdentifier(word)) {
                            FToken.setCp("Identifier");
                            FToken.setVp(word);
                            FToken.setLine(line);
                        } else {
                            FToken.setCp("Invalid");
                            FToken.setVp(word);
                            FToken.setLine(line);
                        }
                        break;
                    }
                    case 'a': {
                        if (validate.isIdentifier(word)) {
                            token token2 = validate.isKeyword(word);
                            if (!token2.getVp().equals("")) {
                                if (token2.getCp().equals("")) {
                                    FToken.setCp(word);
                                    FToken.setVp(word);
                                    FToken.setLine(line);
                                } else {
                                    FToken.setCp(token2.getCp());
                                    FToken.setVp(word);
                                    FToken.setLine(line);
                                }
                            } else if (token2.getVp().equals("")) {
                                if (word.equals("true") || word.equals("false")) {
                                    FToken.setCp("Var_Bool");
                                    FToken.setVp(word);
                                    FToken.setLine(line);
                                } else {
                                    FToken.setCp("Identifier");
                                    FToken.setVp(word);
                                    FToken.setLine(line);
                                }
                            }
                        } else {
                            FToken.setCp("Invalid");
                            FToken.setVp(word);
                            FToken.setLine(line);
                        }
                        break;
                    }
                    case 'v': {
                        if (validate.isFloat(word)) {
                            FToken.setCp("Var_Float");
                            FToken.setVp(word);
                            FToken.setLine(line);
                        } else if (validate.isInt(word)) {
                            FToken.setCp("Var_Integer");
                            FToken.setVp(word);
                            FToken.setLine(line);
                        } else if (validate.isChar(word)) {
                            FToken.setCp("Var_Char");
                            FToken.setVp(word);
                            FToken.setLine(line);
                        } else if (validate.isString(word)) {
                            FToken.setCp("Var_String");
                            FToken.setVp(word);
                            FToken.setLine(line);
                        } else {
                            FToken.setCp("Invalid");
                            FToken.setVp(word);
                            FToken.setLine(line);
                        }
                        break;
                    }
                    default: {
                        boolean oppu = false;
                        token token2 = validate.isOperators(word);
                        if (!token2.getVp().equals("")) {
                            if (token2.getCp().equals("")) {
                                FToken.setCp(word);
                                FToken.setVp(word);
                                FToken.setLine(line);
                            } else {
                                FToken.setCp(token2.getCp());
                                FToken.setVp(word);
                                FToken.setLine(line);
                            }
                            oppu = true;
                        }
                        token2 = validate.isPunctuator(word);
                        if (!token2.getVp().equals("")) {
                            if (token2.getCp().equals("")) {
                                FToken.setCp(word);
                                FToken.setVp(word);
                                FToken.setLine(line);
                            } else {
                                FToken.setCp(token2.getCp());
                                FToken.setVp(word);
                                FToken.setLine(line);
                            }
                            oppu = true;
                        }
                        if (oppu == false) {
                            FToken.setCp("Invalid");
                            FToken.setVp(word);
                            FToken.setLine(line);

                        }
                        break;
                    }
                }
//                System.out.println("CP: " + FToken.getCp() + " VP : " + FToken.getVp() + " line : " + FToken.getLine());
//                System.out.println();
                tokens.add(new token(FToken.getCp(), FToken.getVp(), FToken.getLine()));

                bw.write("CP: " + FToken.getCp() + " VP : " + FToken.getVp() + " line : " + FToken.getLine());
                bw.newLine();
                if (count >= str.length) {
                    count = 0;
                    word = br.readLine();
                    if (word == null) {
                        break;
                    }
                    str = validate.wordBreaker(word);
                }
            }
            word = br.readLine();
            if (word == null) {
                break;
            }
            str = validate.wordBreaker(word);
            line++;
        }
        bw.close();
        tokens.add(new token("$","$",line));

        cfgs cfg = new cfgs(tokens);
        if (cfg.CLASS0()) 
        {
            System.out.println("Valid");
        }
        //cfg.display();
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexanalyzer;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author VAMPIRE
 */
public class dfa {
    private int noOfStates;
    private String[] inputStr;
    private int[][] TransitionTable;
    private int InitialState;
    private int[] FinalStates;
    private boolean flag;

    public dfa(int noOfStates, String[] iStr, int[][] TT, int IS, int[] FS) {
        this.noOfStates = noOfStates;
        this.inputStr = iStr;
        this.TransitionTable = new int[noOfStates][inputStr.length];
        this.TransitionTable = TT;
        this.InitialState = IS;
        this.FinalStates = FS;
        this.flag = false;
    }

    public boolean validate(String input) {
        int CurrentState = this.InitialState;
        for (int i = 0; i < input.length(); i++) {
            CurrentState = transition(CurrentState, input.charAt(i));
            if(CurrentState==0){
                break;
            }
        }
        for (int i = 0; i < this.FinalStates.length; i++) {
            if (this.FinalStates[i] == CurrentState) {
                flag = true;
            }
        }
        return flag;
    }

    public int transition(int st, char inpChar) {
        int ind = -1;
        for (int i = 0; i < this.inputStr.length; i++) {
            Pattern p = Pattern.compile(inputStr[i]);
            Matcher m = p.matcher(String.valueOf(inpChar));
            boolean b = m.matches();
            if (b == true) {
                ind = i;
                break;
            }
        }
        if (ind == -1) {
            //System.out.println("invalid input");
            return 0;
        }
        return this.TransitionTable[st][ind];
    }
}

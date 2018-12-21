/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexanalyzer;

/**
 *
 * @author vampire
 */
public class token {

    private String cp;
    private String vp;
    private int line;
    private String[] vplist;

    public token() {
        cp = "";
        vp = "";
    }

    public token(String CP, String VP,int line) {
        this.cp = CP;
        this.vp = VP;
        this.line=line;
    }
    
    public token(String CP, String VP) {
        this.cp = CP;
        this.vp = VP;
        this.line=line;
    }

    public token(String CP, String[] VPlist) {
        this.cp = CP;
        this.vplist = VPlist;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVp() {
        return vp;
    }

    public void setVp(String vp) {
        this.vp = vp;
    }

    public String[] getVplist() {
        return vplist;
    }

    public void setVplist(String[] vplist) {
        this.vplist = vplist;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void Display() {
        System.out.println("Cp : " + this.cp + " Vp : " + this.vp + " line : " + this.line);
    }

    public void DisplayList() {
        for (int i = 0; i < vplist.length; i++) {
            System.out.println("class part: " + this.cp + "\nvalue part: " + this.vplist[i]);
        }
    }
}

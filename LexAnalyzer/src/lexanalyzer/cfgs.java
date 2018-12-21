/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexanalyzer;

import java.util.ArrayList;

/**
 *
 * @author Hp
 */
public class cfgs {

    ArrayList<token> a = new ArrayList<token>();
    int i;

    cfgs(ArrayList<token> b) {
        for (int i = 0; i < b.size(); i++) {
            this.a.add(new token(b.get(i).getCp(), b.get(i).getVp(), b.get(i).getLine()));
        }
    }

    void display() {
        for (int i = 0; i < a.size(); i++) {
            System.out.println("CP: " + a.get(i).getCp() + " VP : " + a.get(i).getVp() + " line : " + a.get(i).getLine());
            System.out.println();
        }
    }

    void inc() {
        if (i < a.size() - 1) {
            i++;
        }
    }

    boolean CLASS0() {
        if (a.get(i).getCp().equals("Access Modifiers") || a.get(i).getCp().equals("class") || a.get(i).getCp().equals("Category")) {
            if (CLASS()) {
                if (CLASS0()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("$")) {
            return true;
        }
        System.out.println("Error at line: "+a.get(i).getLine());
        return false;
    }

    boolean CLASS() {
        if (a.get(i).getCp().equals("Access Modifiers") || a.get(i).getCp().equals("class") || a.get(i).getCp().equals("Category")) {
            if (AM()) {
                if (Cat()) {
                    if (a.get(i).getCp().equals("class")) {
                        inc();
                        if (a.get(i).getCp().equals("Identifier")) {
                            inc();
                            if (E_I()) {
                                if (a.get(i).getCp().equals("{")) {
                                    inc();
                                    if (Mst_C_Body()) {
                                        if (a.get(i).getCp().equals("}")) {
                                            inc();
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean Cat() {
        if (a.get(i).getCp().equals("Category")) {
            if (i < a.size() - 1) {
                i++;
            }
            return true;
        } else if (a.get(i).getCp().equals("class")) {
            return true;
        }
        return false;
    }

    private boolean AM() {
        if (a.get(i).getCp().equals("Access Modifiers")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("class") || a.get(i).getCp().equals("Category")) {
            return true;
        }
        return false;
    }

    private boolean E_I() {
        if (a.get(i).getCp().equals("extends")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                return true;
            }
        } else if (a.get(i).getCp().equals("implements")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                return true;
            }
        } else if (a.get(i).getCp().equals("{")) {
            return true;
        }
        return false;
    }

    //                                  EXPRESSION
    boolean Exp() {
        if (a.get(i).getCp().equals("(")) {
            inc();

            if (B()) {
                if (a.get(i).getCp().equals(")")) {
                    inc();

                    return true;
                }
            }
        }
        return false;
    }

    private boolean B() {
        if (a.get(i).getCp().equals("INC/DEC") || a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (E()) {
                if (BDash()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean BDash() {
        if (a.get(i).getCp().equals("Bitwise Operators")) {
            inc();

            if (E()) {
                if (BDash()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals(")")) {
            return true;
        }
        return false;
    }

    private boolean E() {
        if (a.get(i).getCp().equals("INC/DEC") || a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (T()) {
                if (EDash()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean EDash() {
        if (a.get(i).getCp().equals("Plus/Minus")) {
            inc();

            if (T()) {
                if (EDash()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("Bitwise Operators") || a.get(i).getCp().equals(")")) {
            return true;
        }
        return false;
    }

    private boolean T() {
        if (a.get(i).getCp().equals("INC/DEC") || a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (F()) {
                if (TDash()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean TDash() {
        if (a.get(i).getCp().equals("Multi/div/Mod")) {
            inc();

            if (F()) {
                if (TDash()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals(")") || a.get(i).getCp().equals("Plus/Minus") || a.get(i).getCp().equals("Bitwise Operators")) {
            return true;
        }
        return false;
    }

    private boolean F() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();

            if (F1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("INC/DEC")) {
            inc();

            if (a.get(i).getCp().equals("Identifier")) {
                inc();

                return true;
            }
        } else if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String")) {
            inc();

            return true;
        } else if (a.get(i).getCp().equals("(")) {
            inc();

            if (B()) {
                if (a.get(i).getCp().equals(")")) {
                    inc();

                    return true;
                }
            }
        }
        return false;
    }

    private boolean F1() {
        if (a.get(i).getCp().equals("INC/DEC")) {
            inc();

            return true;
        } else if (a.get(i).getCp().equals(".") || a.get(i).getCp().equals("(")) {
            if (fn_call1()) {
                if (fList1()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("Multi/div/Mod") || a.get(i).getCp().equals("Plus/Minus") || a.get(i).getCp().equals("Bitwise Operators") || a.get(i).getCp().equals(")")) {
            return true;
        }
        return false;
    }

    //                              FUNCTION CALLING
    private boolean fn_call() {
        if (a.get(i).getCp().equals(".") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")")) {
            if (fn_call1()) {
                if (fList1()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals(":") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals(";")) {
            if (fList1()) {
                return true;
            }
        }
        return false;
    }

    private boolean fn_call1() {
        if (a.get(i).getCp().equals(".")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                if (fn_call1()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("(") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")")) {
            return true;
        }
        return false;
    }

    private boolean fList1() {
        if (a.get(i).getCp().equals("(")) {
            inc();
            if (Arg()) {
                if (a.get(i).getCp().equals(")")) {
                    inc();
                    if (fList2()) {
                        return true;
                    }
                }
            }
        } else if (a.get(i).getCp().equals(":") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")")) {
            return true;
        }
        return false;
    }

    private boolean fList2() {
        if (a.get(i).getCp().equals(".")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                if (fn_call()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals(":") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals(",")) {
            return true;
        }
        return false;
    }

    //                                      OBJECT CREATION
    private boolean Obj1() {
        if (a.get(i).getCp().equals("=")) {
            inc();
            if (Obj2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(";")) {
            return true;
        }
        return false;
    }

    private boolean Obj2() {
        if (a.get(i).getCp().equals("new")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                if (a.get(i).getCp().equals("(")) {
                    inc();
                    if (Arg()) {
                        if (a.get(i).getCp().equals(")")) {
                            inc();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //                                  ARGUMENTS
    boolean Arg() {
        if (a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("new") || a.get(i).getCp().equals("Var_Bool")) {
            if (Arg1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(")")) {
            return true;
        }
        return false;
    }

    private boolean Arg1() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (choose3()) {
                if (Alist1()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (Const_Exp()) {
                if (choose4()) {
                    if (Alist1()) {
                        return true;
                    }
                }
            }
        } else if (a.get(i).getCp().equals("new")) {
            if (Obj2()) {
                if (Alist1()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("Var_Bool")) {
            inc();
            if (Cond1()) {
                if (Alist1()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean Alist1() {
        if (a.get(i).getCp().equals(",")) {
            inc();
            if (Arg1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(")")) {
            return true;
        }
        return false;
    }

    //                                 Constructor
    boolean constructor() {
        if (a.get(i).getCp().equals("construct")) {
            inc();
            if (a.get(i).getCp().equals("(")) {
                inc();
                if (para()) {
                    if (a.get(i).getCp().equals(")")) {
                        inc();
                        if (Constructbody()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean DT_ID() {
        if (a.get(i).getCp().equals("Data Types")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("Identifier")) {
            inc();
            return true;
        }
        return false;
    }

    private boolean para() {
        if (a.get(i).getCp().equals("Data Types") || a.get(i).getCp().equals("Identifier")) {
            if (para1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(")")) {
            return true;
        }
        return false;
    }

    private boolean para1() {
        if (a.get(i).getCp().equals("Data Types") || a.get(i).getCp().equals("Identifier")) {
            if (DT_ID()) {
                if (a.get(i).getCp().equals("Identifier")) {
                    inc();
                    if (A_D1()) {
                        if (para2()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean para2() {
        if (a.get(i).getCp().equals(",")) {
            inc();
            if (para1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(")")) {
            return true;
        }
        return false;
    }

    private boolean A_D11() {
        if (a.get(i).getCp().equals("[")) {
            inc();
            if (a.get(i).getCp().equals("]")) {
                inc();
                if (A_D2()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean A_D1() {
        if (a.get(i).getCp().equals("[")) {
            inc();
            if (a.get(i).getCp().equals("]")) {
                inc();
                if (A_D2()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals(",") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals("Identifier")) {
            return true;
        }
        return false;
    }

    private boolean A_D2() {
        if (a.get(i).getCp().equals("[")) {
            inc();
            if (a.get(i).getCp().equals("]")) {
                inc();
                return true;
            }
        } else if (a.get(i).getCp().equals(",") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals("Identifier")) {
            return true;
        }
        return false;
    }

    boolean body() {
        if (a.get(i).getCp().equals("return")) {
            if (Return()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("{")) {
            inc();
            if (MST()) {
                if (a.get(i).getCp().equals("}")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean MST() {
        if (a.get(i).getCp().equals("for") || a.get(i).getCp().equals("while") || a.get(i).getCp().equals("Data Types") || a.get(i).getCp().equals("if") || a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("INC/DEC") || a.get(i).getCp().equals("return") || a.get(i).getCp().equals("try") || a.get(i).getCp().equals("do") || a.get(i).getCp().equals("this") || a.get(i).getCp().equals("super") || a.get(i).getCp().equals("switch")) {
            if (SST()) {
                if (MST()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("}")) {
            return true;
        }
        return false;
    }

    private boolean SST() {
        if (a.get(i).getCp().equals("for")) {
            if (for_st()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("while")) {
            if (While()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Data Types")) {
            if (Dec_fn()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("if")) {
            if (IF_ELSE()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Identifier")) {
            if (SST1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("INC/DEC")) {
            if (Inc_Dec()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("return")) {
            if (Return()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("try")) {
            if (E_H()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("do")) {
            if (Do_While()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("this")) {
            if (THIS1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("super")) {
            if (SUPER1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("switch")) {
            if (SWITCH()) {
                return true;
            }
        }
        return false;
    }

    private boolean SST1() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (choose()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean choose() {
        if (a.get(i).getCp().equals(".") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")")) {
            if (fn_call()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(";") || a.get(i).getCp().equals("=") || a.get(i).getCp().equals("Assignment Operators")) {
            if (Ass_Init()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("INC/DEC")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (Obj1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("[")) {
            inc();
            if (Num()) {
                if (a.get(i).getCp().equals("]")) {
                    inc();
                    if (arr_init()) {
                        if (a.get(i).getCp().equals("=")) {
                            inc();
                            if (Init1()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean Ass_Init() {
        if (a.get(i).getCp().equals("=")) {
            inc();
            if (T_S()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Assignment Operators")) {
            inc();
            if (T_S1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(";")) {
            return true;
        }
        return false;
    }

    private boolean Ass_Init1() {
        if (a.get(i).getCp().equals("=")) {
            inc();
            if (T_S()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Assignment Operators")) {
            inc();
            if (T_S1()) {
                return true;
            }
        }
        return false;
    }

    private boolean T_S() {
        if (a.get(i).getCp().equals("this")) {
            if (THIS2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("super")) {
            if (SUPER2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("Var_Bool") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("new")) {
            if (choose1()) {
                return true;
            }
        }
        return false;
    }

    private boolean T_S1() {
        if (a.get(i).getCp().equals("this")) {
            if (THIS2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("super")) {
            if (SUPER2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("Var_Bool") || a.get(i).getCp().equals("(")) {
            if (Assign01()) {
                return true;
            }
        }
        return false;
    }

    private boolean choose1() {
        if (a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("Var_Bool") || a.get(i).getCp().equals("(")) {
            if (Assign01()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("new")) {
            if (Initialize01()) {
                return true;
            }
        }
        return false;
    }

    private boolean Assign01() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (List25()) {
                if (Ass_Init()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("Var_Bool") || a.get(i).getCp().equals("(")) {
            if (Const_Exp_Bool()) {
                return true;
            }
        }
        return false;
    }

    private boolean Initialize01() {
        if (a.get(i).getCp().equals("new")) {
            inc();
            if (Initialize02()) {
                return true;
            }
        }
        return false;
    }

    private boolean Initialize02() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (a.get(i).getCp().equals("(")) {
                inc();
                if (Arg()) {
                    if (a.get(i).getCp().equals(")")) {
                        inc();
                        return true;
                    }
                }
            }
        } else if (a.get(i).getCp().equals("Data Types")) {
            inc();
            if (a.get(i).getCp().equals("[")) {
                inc();
                if (Num()) {
                    if (a.get(i).getCp().equals("]")) {
                        inc();
                        if (Initialize03()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean Initialize03() {
        if (a.get(i).getCp().equals("[")) {
            inc();
            if (Num()) {
                if (a.get(i).getCp().equals("]")) {
                    inc();
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals(";")) {
            return true;
        }
        return false;
    }

    private boolean List25() {
        if (a.get(i).getCp().equals(".") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")")) {
            if (fn_call()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("=") || a.get(i).getCp().equals("Assignment Operators") || a.get(i).getCp().equals(";")) {
            return true;
        }
        return false;
    }

    //                              Constructor body
    boolean Constructbody() {
        if (a.get(i).getCp().equals("{")) {
            inc();
            if (ConstructMST()) {
                if (a.get(i).getCp().equals("}")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ConstructMST() {
        if (a.get(i).getCp().equals("for") || a.get(i).getCp().equals("while") || a.get(i).getCp().equals("Data Types") || a.get(i).getCp().equals("if") || a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("INC/DEC") || a.get(i).getCp().equals("try") || a.get(i).getCp().equals("do") || a.get(i).getCp().equals("this") || a.get(i).getCp().equals("super") || a.get(i).getCp().equals("switch")) {
            if (ConstructSST()) {
                if (ConstructMST()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("}")) {
            return true;
        }
        return false;
    }

    private boolean ConstructSST() {
        if (a.get(i).getCp().equals("for")) {
            if (for_st()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("while")) {
            if (While()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Data Types")) {
            if (Dec_fn()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("if")) {
            if (IF_ELSE()) {            
                return true;
            }
        } else if (a.get(i).getCp().equals("Identifier")) {
            if (SST1()) {
               
                return true;
            }
        } else if (a.get(i).getCp().equals("INC/DEC")) {
            if (Inc_Dec()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("try")) {
            if (E_H()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("do")) {
            if (Do_While()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("this")) {
            if (THIS()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("super")) {
            if (SUPER()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("switch")) {
            if (SWITCH()) {
                return true;
            }
        }
        return false;
    }

    //           INC/DEC
    private boolean Inc_Dec() {
        if (a.get(i).getCp().equals("INC/DEC")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    //                                              Decleration
    private boolean DEC1() {
        if (a.get(i).getCp().equals("=") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals(",")) {
            if (Init()) {
                if (List1()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("[")) {
            if (A_D()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean A_D() {
        if (a.get(i).getCp().equals("[")) {
            inc();
            if (a.get(i).getCp().equals("]")) {
                inc();
                if (A_D01()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals(";")) {
            return true;
        }
        return false;
    }

    private boolean A_D01() {
        if (a.get(i).getCp().equals("=")) {
            if (ArrayDec1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("[")) {
            inc();
            if (a.get(i).getCp().equals("]")) {
                inc();
                if (ArrayDec2()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ArrayDec1() {
        if (a.get(i).getCp().equals("=")) {
            inc();
            if (ArrayDec01()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(";")) {
            return true;
        }
        return false;
    }

    private boolean ArrayDec2() {
        if (a.get(i).getCp().equals("=")) {
            inc();
            if (ArrayDec02()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(";")) {
            return true;
        }
        return false;
    }

    private boolean ArrayDec01() {
        if (a.get(i).getCp().equals("new")) {
            inc();
            if (a.get(i).getCp().equals("Data Types")) {
                inc();
                if (a.get(i).getCp().equals("[")) {
                    inc();
                    if (Num()) {
                        if (a.get(i).getCp().equals("]")) {
                            inc();
                            return true;
                        }
                    }
                }
            }
        } else if (a.get(i).getCp().equals("{")) {
            inc();
            if (Num1()) {
                if (a.get(i).getCp().equals("}")) {
                    inc();
                }
                return true;
            }
        }
        return false;
    }

    private boolean ArrayDec02() {
        if (a.get(i).getCp().equals("new")) {
            inc();

            if (a.get(i).getCp().equals("Data Types")) {
                inc();

                if (a.get(i).getCp().equals("[")) {
                    inc();

                    if (Num()) {
                        if (a.get(i).getCp().equals("]")) {
                            inc();

                            if (a.get(i).getCp().equals("[")) {
                                inc();
                                if (Num()) {
                                    if (a.get(i).getCp().equals("]")) {
                                        inc();
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (a.get(i).getCp().equals("{")) {
            if (A_D_M()) {
                return true;
            }
        }
        return false;
    }

    private boolean A_D_M() {
        if (a.get(i).getCp().equals("{")) {
            inc();
            if (Num1()) {
                if (a.get(i).getCp().equals("}")) {
                    inc();
                    if (A_D_M2()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean A_D_M2() {
        if (a.get(i).getCp().equals(",")) {
            inc();
            if (A_D_M()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(";")) {
            return true;
        }
        return false;
    }

    private boolean List() {
        if (a.get(i).getCp().equals("Access Modifiers")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("Data Types") || a.get(i).getCp().equals("void") || a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Category") || a.get(i).getCp().equals("static")) {
            return true;
        }
        return false;
    }

    private boolean List01() {
        if (a.get(i).getCp().equals("Category")) {
            inc();
            if (List02()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("static")) {
            inc();
            if (List03()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Data Types") || a.get(i).getCp().equals("void") || a.get(i).getCp().equals("Identifier")) {
            return true;
        }
        return false;
    }

    private boolean List02() {
        if (a.get(i).getCp().equals("static")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("Data Types")) {
            return true;
        }
        return false;
    }

    private boolean List03() {
        if (a.get(i).getCp().equals("Category")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("Data Types")) {
            return true;
        }
        return false;
    }

    private boolean List1() {
        if (a.get(i).getCp().equals(";")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals(",")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                if (Init()) {
                    if (List1()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean Init() {
        if (a.get(i).getCp().equals("=")) {
            inc();
            if (Init1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(";") || a.get(i).getCp().equals(",")) {
            return true;
        }
        return false;
    }

    private boolean arrdecz() {
        if (a.get(i).getCp().equals("[")) {
            inc();
            if (Num()) {
                if (a.get(i).getCp().equals("]")) {
                    inc();
                    if (arr_init()) {
                        return true;
                    }
                }
            }
        } else if (a.get(i).getCp().equals(":") || a.get(i).getCp().equals(".") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals(",")) {
            return true;
        }
        return false;
    }

    private boolean arr_init() {
        if (a.get(i).getCp().equals("[")) {
            inc();
            if (Num()) {
                if (a.get(i).getCp().equals("]")) {
                    inc();
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("=") || a.get(i).getCp().equals(".") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals(",")) {
            return true;
        }
        return false;
    }

    private boolean Init1() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (arrdecz()) {
                if (choose3()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (Const_Exp()) {
                if (choose4()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("Var_Bool")) {
            inc();
            if (Cond1()) {
                return true;
            }
        }
        return false;
    }

    private boolean Num() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();

            return true;
        } else if (a.get(i).getCp().equals("Var_Integer")) {
            inc();

            return true;
        } else if (a.get(i).getCp().equals("(")) {
            if (Exp()) {
                return true;
            }
        }
        return false;
    }

    private boolean Num01() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (Const_Exp()) {
                if (choose4()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("Var_Bool")) {
            inc();
            if (Cond1()) {
                return true;
            }
        }
        return false;
    }

    private boolean Num1() {
        if (a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("Var_Bool")) {
            if (Num01()) {
                if (Num2()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean Num2() {
        if (a.get(i).getCp().equals(",")) {
            inc();
            if (Num1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("}")) {
            return true;
        }
        return false;
    }

    boolean Return() {
        if (a.get(i).getCp().equals("return")) {
            inc();
            if (choose2()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean choose2() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (choose3()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (Const_Exp()) {
                if (choose4()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("new")) {
            if (Obj2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Var_Bool")) {
            inc();
            if (Cond1()) {
                return true;
            }
        }
        return false;
    }

    private boolean choose3() {
        if (a.get(i).getCp().equals(".") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")")) {
            if (fn_call()) {
                if (RO()) {
                    if (Cond1()) {
                        return true;
                    }
                }
            }
        } else if (a.get(i).getCp().equals(":") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals(",")) {
            if (RO()) {
                if (Cond1()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean choose4() {
        if (a.get(i).getCp().equals("Relational Operators")) {
            inc();
            if (RO1()) {
                if (Cond1()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals(":") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals(",") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals("}")) {
            return true;
        }
        return false;
    }

    //   Function Decleration
    private boolean F_DEC() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (F_DEC1()) {
                return true;
            }
        }
        return false;
    }

    private boolean F_DEC1() {
        if (a.get(i).getCp().equals("(")) {
            inc();
            if (para()) {
                if (a.get(i).getCp().equals(")")) {
                    inc();
                    if (Throws()) {
                        if (body()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //                                       While
    boolean While() {
        if (a.get(i).getCp().equals("while")) {
            if (i < a.size() - 1) {
                i++;
            }
            if (a.get(i).getCp().equals("(")) {
                if (i < a.size() - 1) {
                    i++;
                }
                if (Cond()) {
                    if (a.get(i).getCp().equals(")")) {
                        if (i < a.size() - 1) {
                            i++;
                        }
                        if (loopbody()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //                              CONDITION
    private boolean Cond() {
        if (a.get(i).getCp().equals("!")) {
            inc();
            if (a.get(i).getCp().equals("(")) {
                inc();
                if (Cond()) {
                    if (a.get(i).getCp().equals(")")) {
                        inc();
                        if (Cond1()) {
                            return true;
                        }
                    }
                }
            }
        } else if (a.get(i).getCp().equals("Var_Bool") || a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (CList()) {
                if (Cond1()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean Cond1() {
        if (a.get(i).getCp().equals("Logical Operators")) {
            inc();
            if (Cond()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(":") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals(",")) {
            return true;
        }
        return false;
    }

    private boolean CList() {
        if (a.get(i).getCp().equals("Var_Bool")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (CList2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (CList1()) {
                return true;
            }
        }
        return false;
    }

    private boolean CList1() {
        if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (Const_Exp()) {
                if (a.get(i).getCp().equals("Relational Operators")) {
                    inc();
                    if (RO1()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean CList2() {
        if (a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(";")) {
            if (RO()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(":") || a.get(i).getCp().equals(".") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")")) {
            if (fn_call()) {
                if (RO()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean CList3() {
        if (a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals(";")) {
            if (RO()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals(";")) {
            return true;
        }
        return false;
    }

    private boolean RO() {
        if (a.get(i).getCp().equals("Relational Operators")) {
            inc();
            if (RO1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(":") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals(",")) {
            return true;
        }
        return false;
    }

    private boolean RO1() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (CList2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(")) {
            if (Const_Exp()) {
                if (RO()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean Const_Exp() {
        if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("(")) {
            if (Exp()) {
                return true;
            }
        }
        return false;
    }

    private boolean Const_Exp_Bool() {
        if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("Var_Bool")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("(")) {
            if (Exp()) {
                return true;
            }
        }
        return false;
    }

    //                               IF_ELSE
    boolean IF_ELSE() {
        if (a.get(i).getCp().equals("if")) {
            inc();
            if (a.get(i).getCp().equals("(")) {
                inc();
                if (Cond()) {
                    if (a.get(i).getCp().equals(")")) {
                        inc();
                        if (body()) {
                            if (ELSE()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean ELSE() {
        if (a.get(i).getCp().equals("else")) {
            inc();
            if (ELSE1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("}") || a.get(i).getCp().equals("for") || a.get(i).getCp().equals("while") || a.get(i).getCp().equals("Data Types") || a.get(i).getCp().equals("if") || a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("INC/DEC") || a.get(i).getCp().equals("return") || a.get(i).getCp().equals("try") || a.get(i).getCp().equals("this") || a.get(i).getCp().equals("super") || a.get(i).getCp().equals("do") || a.get(i).getCp().equals("break") || a.get(i).getCp().equals("continue")) {
            return true;
        }
        return false;
    }

    private boolean ELSE1() {
        if (a.get(i).getCp().equals("if")) {
            if (IF_ELSE()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("{")) {
            if (body()) {
                return true;
            }
        }
        return false;
    }

    //                                  ASSIGNMENT
    boolean Assign() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (Init01()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean Init01() {
        if (a.get(i).getCp().equals("Assignment Operators")) {
            inc();
            if (Init11()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("=")) {
            inc();
            if (Init11()) {
                return true;
            }
        }
        return false;
    }

    private boolean Init11() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (Init01()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String")) {
            inc();
            return true;
        }
        return false;
    }

    //                                          INITIALIZATION
    boolean Initialize() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (Initialize1()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    boolean Initialize1() {
        if (a.get(i).getCp().equals("=")) {
            inc();
            if (Initialize2()) {
                return true;
            }
        }
        return false;
    }

    boolean Initialize2() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (Initialize1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("(")) {
            if (Exp()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("new")) {
            if (Obj2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("new") || a.get(i).getCp().equals("{")) {
            if (ArrayDec02()) {
                return true;
            } else {
                if (a.get(i).getCp().equals("new") || a.get(i).getCp().equals("{")) {
                    if (ArrayDec01()) {
                        return true;
                    }
                }
            }
            if (ArrayDec01()) {
                return true;
            }
        }
        return false;
    }

    //                  Class Body
    boolean Mst_C_Body() {
        if (a.get(i).getCp().equals("construct") || a.get(i).getCp().equals("Access Modifiers") || a.get(i).getCp().equals("Category") || a.get(i).getCp().equals("static") || a.get(i).getCp().equals("Data Types") || a.get(i).getCp().equals("void") || a.get(i).getCp().equals("Identifier")) {
            if (Sst_C_Body()) {
                if (Mst_C_Body()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("}")) {
            return true;
        }
        return false;
    }

    private boolean Sst_C_Body() {
        if (a.get(i).getCp().equals("construct")) {
            if (constructor()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Access Modifiers") || a.get(i).getCp().equals("Category") || a.get(i).getCp().equals("static") || a.get(i).getCp().equals("Data Types") || a.get(i).getCp().equals("void") || a.get(i).getCp().equals("Identifier")) {
            if (List()) {
                if (List01()) {
                    if (C_body1()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean C_body1() {
        if (a.get(i).getCp().equals("Data Types")) {
            inc();
            if (C_body2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (C_body4()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("void")) {
            inc();
            if (F_DEC()) {
                return true;
            }
        }
        return false;
    }

    private boolean C_body2() {
        if (a.get(i).getCp().equals("[")) {
            if (A_D11()) {
                if (F_DEC()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (C_body3()) {
                return true;
            }
        }
        return false;
    }

    private boolean C_body3() {
        if (a.get(i).getCp().equals("=") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals(",") || a.get(i).getCp().equals("[")) {
            if (DEC1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("(")) {
            if (F_DEC1()) {
                return true;
            }
        }
        return false;
    }

    private boolean C_body4() {
        if (a.get(i).getCp().equals("[")) {
            if (A_D11()) {
                if (F_DEC()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (C_body5()) {
                return true;
            }
        }
        return false;
    }

    private boolean C_body5() {
        if (a.get(i).getCp().equals("=") || a.get(i).getCp().equals(";")) {
            if (Obj1()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("(")) {
            if (F_DEC1()) {
                return true;
            }
        }
        return false;
    }

    boolean RET() {
        if (a.get(i).getCp().equals("(")) {
            if (fn_call11()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("=") || a.get(i).getCp().equals(",") || a.get(i).getCp().equals(";") || a.get(i).getCp().equals("[")) {
            if (DEC1()) {
                return true;
            }
        }
        return false;
    }

    boolean fn_call11() {
        if (a.get(i).getCp().equals("(")) {
            inc();
            if (para()) {
                if (a.get(i).getCp().equals(")")) {
                    inc();
                    if (Throws()) {
                        if (body()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean Throws() {
        if (a.get(i).getCp().equals("throws")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                return true;
            }
        } else if (a.get(i).getCp().equals("{")) {
            return true;
        }
        return false;
    }

    private boolean E_H() {
        if (a.get(i).getCp().equals("try")) {
            inc();
            if (Constructbody()) {
                if (Catch()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean Catch() {
        if (a.get(i).getCp().equals("catch")) {
            inc();
            if (a.get(i).getCp().equals("(")) {
                inc();
                if (a.get(i).getCp().equals("Identifier")) {
                    inc();
                    if (a.get(i).getCp().equals("Identifier")) {
                        inc();
                        if (a.get(i).getCp().equals(")")) {
                            inc();
                            if (Constructbody()) {
                                if (Catch1()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean Catch1() {
        if (a.get(i).getCp().equals("finally")) {
            inc();
            if (Constructbody()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(";")) {
            return true;
        } else if (a.get(i).getCp().equals("Catch")) {
            if (Catch()) {
                return true;
            }

        }
        return false;
    }

    //                            FOR LOOP
    boolean for_st() {
        if (a.get(i).getCp().equals("for")) {
            inc();
            if (a.get(i).getCp().equals("(")) {
                inc();
                if (f1()) {
                    if (f2()) {
                        if (f3()) {
                            if (a.get(i).getCp().equals(")")) {
                                inc();
                                if (loopbody()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean f1() {
        if (a.get(i).getCp().equals("Data Types")) {
            if (Dec_fn()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Identifier")) {
            if (Assign()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(";")) {
            inc();
            return true;
        }
        return false;
    }

    private boolean f2() {
        if (a.get(i).getCp().equals("Var_Bool") || a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("Var_Integer") || a.get(i).getCp().equals("Var_Float") || a.get(i).getCp().equals("Var_Char") || a.get(i).getCp().equals("Var_String") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("!")) {
            if (Cond()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals(";")) {
            inc();
            return true;
        }
        return false;
    }

    private boolean f3() {
        if (a.get(i).getCp().equals("Identifier")) {
            inc();
            if (f03()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("INC/DEC")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                return true;
            }
        }
        return false;
    }

    private boolean f03() {
        if (a.get(i).getCp().equals("INC/DEC")) {
            inc();
            return true;
        } else if (a.get(i).getCp().equals("Assignment Operators") || a.get(i).getCp().equals("=")) {
            if (Init01()) {
                return true;
            }
        }
        return false;
    }

    //                       Decleration within Function
    boolean Dec_fn() {
        if (a.get(i).getCp().equals("Data Types")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                if (DEC1()) {
                    return true;
                }
            }
        }
        return false;
    }

//              Loop body
    boolean loopbody() {
        if (a.get(i).getCp().equals("return")) {
            if (Return()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("{")) {
            inc();
            if (loopMST()) {
                if (a.get(i).getCp().equals("}")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean loopMST() {
        if (a.get(i).getCp().equals("for") || a.get(i).getCp().equals("while") || a.get(i).getCp().equals("Data Types") || a.get(i).getCp().equals("if") || a.get(i).getCp().equals("Identifier") || a.get(i).getCp().equals("INC/DEC") || a.get(i).getCp().equals("return") || a.get(i).getCp().equals("try") || a.get(i).getCp().equals("continue") || a.get(i).getCp().equals("break") || a.get(i).getCp().equals("do") || a.get(i).getCp().equals("this") || a.get(i).getCp().equals("super") || a.get(i).getCp().equals("switch")) {
            if (loopSST()) {
                if (loopMST()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("}")) {
            return true;
        }
        return false;
    }

    private boolean loopSST() {
        if (a.get(i).getCp().equals("for")) {
            if (for_st()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("while")) {
            if (While()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Data Types")) {
            if (Dec_fn()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("if")) {
            if (IF_ELSE()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("Identifier")) {
            if (SST1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("INC/DEC")) {
            if (Inc_Dec()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("return")) {
            if (Return()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("try")) {
            if (E_H()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("continue") || a.get(i).getCp().equals("break")) {
            inc();
            if (a.get(i).getCp().equals(";")) {
                inc();
                return true;
            }
        } else if (a.get(i).getCp().equals("do")) {
            if (Do_While()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("this")) {
            if (THIS1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("super")) {
            if (SUPER1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("switch")) {
            if (SWITCH()) {
                return true;
            }
        }
        return false;
    }

//                               DO_WHILE
    boolean Do_While() {
        if (a.get(i).getCp().equals("do")) {
            inc();
            if (loopbody()) {
                if (a.get(i).getCp().equals("while")) {
                    inc();
                    if (a.get(i).getCp().equals("(")) {
                        inc();
                        if (Cond()) {
                            if (a.get(i).getCp().equals(")")) {
                                inc();
                                if (a.get(i).getCp().equals(";")) {
                                    inc();
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean THIS() {
        if (a.get(i).getCp().equals("this")) {
            inc();
            if (S()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean THIS1() {
        if (a.get(i).getCp().equals("this")) {
            inc();
            if (C()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean THIS2() {
        if (a.get(i).getCp().equals("this")) {
            inc();
            if (C2()) {
                return true;
            }
        }
        return false;
    }

    private boolean SUPER() {
        if (a.get(i).getCp().equals("super")) {
            inc();
            if (S()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean SUPER1() {
        if (a.get(i).getCp().equals("super")) {
            inc();
            if (C()) {
                if (a.get(i).getCp().equals(";")) {
                    inc();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean SUPER2() {
        if (a.get(i).getCp().equals("super")) {
            inc();
            if (C2()) {
                return true;
            }
        }
        return false;
    }

    private boolean S() {
        if (a.get(i).getCp().equals("(")) {
            inc();
            if (Arg()) {
                if (a.get(i).getCp().equals(")")) {
                    inc();
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals(".")) {
            if (C()) {
                return true;
            }
        }
        return false;
    }

    private boolean C() {
        if (a.get(i).getCp().equals(".")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                if (C1()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean C1() {
        if (a.get(i).getCp().equals(".")) {
            if (C()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("=") || a.get(i).getCp().equals("Assignment Operators")) {
            if (Ass_Init1()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean C2() {
        if (a.get(i).getCp().equals(".")) {
            inc();
            if (a.get(i).getCp().equals("Identifier")) {
                inc();
                if (C3()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean C3() {
        if (a.get(i).getCp().equals(".")) {
            if (C2()) {
                return true;
            }
        } else if (a.get(i).getCp().equals(";") || a.get(i).getCp().equals("Relational Operators") || a.get(i).getCp().equals("(") || a.get(i).getCp().equals("Logical Operators") || a.get(i).getCp().equals(")")) {
            if (fList1()) {
                return true;
            }
        } else if (a.get(i).getCp().equals("=") || a.get(i).getCp().equals("Assignment Operators") || a.get(i).getCp().equals(";")) {
            if (Ass_Init()) {
                return true;
            }
        }
        return false;
    }

    private boolean SWITCH() {
        if (a.get(i).getCp().equals("switch")) {
            inc();
            if (a.get(i).getCp().equals("(")) {
                inc();
                if (Init1()) {
                    if (a.get(i).getCp().equals(")")) {
                        inc();
                        if (a.get(i).getCp().equals("{")) {
                            inc();
                            if (SWITCH_BODY()) {
                                if (a.get(i).getCp().equals("}")) {
                                    inc();
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean SWITCH_BODY() {
        if (a.get(i).getCp().equals("case")) {
            inc();
            if (Init1()) {
                if (a.get(i).getCp().equals(":")) {
                    inc();
                    if (loopbody()) {
                        if (SWITCH_BODY()) {
                            return true;
                        }
                    }
                }
            }
        } else if (a.get(i).getCp().equals("default")) {
            inc();
            if (a.get(i).getCp().equals(":")) {
                inc();
                if (loopbody()) {
                    return true;
                }
            }
        } else if (a.get(i).getCp().equals("}")) {
            return true;
        }
        return false;
    }
}

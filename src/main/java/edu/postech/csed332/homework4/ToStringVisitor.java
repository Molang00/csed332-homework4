package edu.postech.csed332.homework4;
import edu.postech.csed332.homework4.expression.*;

/**
 * A visitor to compute the string expression of a given expression
 */
public class ToStringVisitor implements ExpVisitor<String> {

    // TODO write and implement the visitor methods for ToStringVisitor, satisfying
    //  the specification of Exp.toString. A double value is written using exponents,
    //  e.g., using Double.toString().
    
    @Override
    public String visitNumberExp(NumberExp cur){
        String rst = cur.getValue().toString();
        return rst;
    }

    @Override
    public String visitVariableExp(VariableExp cur){
        String rst = "x"+Integer.toString(cur.getName());
        return rst;
    }
    
    @Override
    public String visitBinaryExp(BinaryExp curExp, String operator){
        String rstLeft = curExp.getLeft().toString();
        String rstRight = curExp.getRight().toString();
        String rst = "("+rstLeft+" "+operator+" "+rstRight+")";
        return rst;
    }
}
